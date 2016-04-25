package sk.uniza.fri.cuka.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.SubjectTest;
import sk.uniza.fri.cuka.data.entity.Test;
import sk.uniza.fri.cuka.ldap.LdapInfo;
import sk.uniza.fri.cuka.model.TeacherImplicitSubject;
import sk.uniza.fri.cuka.service.QuestionService;
import sk.uniza.fri.cuka.service.SubjectService;
import sk.uniza.fri.cuka.service.SubjectTestService;
import sk.uniza.fri.cuka.service.TeacherService;
import sk.uniza.fri.cuka.service.TestService;

@Controller
public class TestController {

	private static final int EMPTY = 0;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SubjectTestService subjectTestService;

	@Autowired
	private TestService testService;

	@Autowired
	private LdapInfo ldapInfo;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private TeacherImplicitSubject teacherImplicitSubject;

	private boolean firstTime = false;

	@RequestMapping("/createTest")
	public String showCreateTest(Model model) {
		teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		// commandName pre form:form
		model.addAttribute("subjectTest", new SubjectTest());
		firstTime = true;

		return "createTest";
	}

	/**
	 * Spusti sa ked vytvaram test a submitnem formu
	 */
	@RequestMapping(value = "/viewTests", method = RequestMethod.POST)
	public String doCreateTest(Model model, @Valid SubjectTest subjectTest, BindingResult result) {
		teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		if (result.hasErrors()) {
			return "createTest";
		}

		if (firstTime) {
			subjectTestService.createSubjectTest(subjectTest);
			firstTime = false;
		} else {
			return "redirect:createTest";
		}

		addSubjectTestsBySubjectIdToModel(model);

		model.addAttribute("subjectTest", subjectTest);

		return "viewTests";
	}

	/**
	 * Spusti sa ked klikne hore na menu - testy - prehlad testov.
	 */
	@RequestMapping(value = "/viewTests", method = RequestMethod.GET)
	public String previewTests(Model model) {
		teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);
		addSubjectTestsBySubjectIdToModel(model);

		return "viewTests";
	}

	/**
	 * Vymaze aj otazky naviazane k testu...
	 */
	@RequestMapping("/deletePredmTest")
	public String deleteQuestion(HttpServletRequest request) {
		subjectTestService.delete(Long.parseLong(request.getParameter("testId")));
		questionService.deleteByTestId(Long.parseLong(request.getParameter("testId")));

		return "redirect:viewTests";
	}

	@RequestMapping("/createSpecificTest")
	public String showSpecificTestCreate(Model model) {
		Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		List<SubjectTest> subjectTests = subjectService.getSubjectTestsForSubjectById(subject.getPr_id());

		if (subjectTests.size() == EMPTY)
			return "redirect:createTest?noTest=true1";

		model.addAttribute("subjectTests", subjectTests);
		model.addAttribute("test", new Test());

		return "createSpecificTest";
	}

	@RequestMapping(value = "/viewSpecificTests", method = RequestMethod.POST)
	public String viewSpecificTests(Model model, @Valid Test test, BindingResult result) {
		Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		if (result.hasErrors()) {
			List<SubjectTest> subjectTests = subjectService.getSubjectTestsForSubjectById(subject.getPr_id());

			model.addAttribute("subjectTests", subjectTests);

			return "createSpecificTest";
		}

		if (testService.createTestAndGenerateQuestions(test)) {
			return "redirect:viewSpecificTests";
		}

		return "redirect:viewSpecificTests?error=true";
	}

	@RequestMapping(value = "/viewSpecificTests", method = RequestMethod.GET)
	public String viewSpecificTests(Model model) {
		Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		List<Test> tests = testService.getAllTestsBySubjectId(subject.getPr_id());

		model.addAttribute("tests", tests);

		return "viewSpecificTests";
	}

	private void addSubjectTestsBySubjectIdToModel(Model model) {
		List<SubjectTest> subjectTests = subjectService
				.getSubjectTestsForSubjectById(teacherService.getTeacherByLogin(ldapInfo.getLdapLogin()).getUc_pr_id());

		List<Boolean> usageList = new ArrayList<>();
		for (SubjectTest subjectTest : subjectTests) {
			if (subjectTestService.getSizeOfStudentTestsBySubjectTestId(subjectTest.getId()) == 0) {
				usageList.add(false);
			} else {
				usageList.add(true);
			}
		}

		model.addAttribute("usageList", usageList);
		model.addAttribute("subjectTests", subjectTests);
	}

}
