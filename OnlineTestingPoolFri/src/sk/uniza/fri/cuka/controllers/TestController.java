package sk.uniza.fri.cuka.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	// formular na vytvorenie testu
	@RequestMapping(value = "/createTest", method = RequestMethod.GET)
	public String showCreateTest(Model model) {
		teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		model.addAttribute("subjectTest", new SubjectTest());

		return "createTest";
	}

	// prida test do databazy a redirectne na prehlad testov s tym ze posle novo vytvoreny test cez RedirectAttributes
	@RequestMapping(value = "/viewTests", method = RequestMethod.POST)
	public String doCreateTest(Model model, @Valid SubjectTest subjectTest, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		if (result.hasErrors()) {
			return "createTest";
		}

		subjectTestService.createSubjectTest(subjectTest);

		addSubjectTestsBySubjectIdToModel(model);

		redirectAttributes.addFlashAttribute("subjectTest", subjectTest);

		return "redirect:viewTests";
	}

	// prehlad testov - ModelAttribute si vytiahne novo pridany test z POST verzie...
	@RequestMapping(value = "/viewTests", method = RequestMethod.GET)
	public String previewTests(Model model, @ModelAttribute("subjectTest") final SubjectTest subjectTest) {
		teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);
		addSubjectTestsBySubjectIdToModel(model);

		model.addAttribute("subjectTest", subjectTest);

		return "viewTests";
	}

	// vymaze Typ Testu aj s otazkami a pripadnymi testami studentov
	@RequestMapping(value = "/deletePredmTest", method = RequestMethod.POST)
	public String deleteQuestion(HttpServletRequest request) {
		subjectTestService.delete(Long.parseLong(request.getParameter("testId")));
		questionService.deleteByTestId(Long.parseLong(request.getParameter("testId")));

		return "redirect:viewTests";
	}

	// vytvorenie testu, ak neexistuje typ testu, redirectne na vytvorenie typu testu
	@RequestMapping(value = "/createSpecificTest", method = RequestMethod.GET)
	public String showSpecificTestCreate(Model model) {
		Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		List<SubjectTest> subjectTests = subjectService.getSubjectTestsForSubjectById(subject.getPr_id());

		if (subjectTests.size() == EMPTY) {
			return "redirect:createTest?noTest=true1";
		}

		model.addAttribute("subjectTests", subjectTests);
		model.addAttribute("test", new Test());

		return "createSpecificTest";
	}

	// vytvorenie konkretneho testu - prida do databazy
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

	// prehlad konkretnych testov
	@RequestMapping(value = "/viewSpecificTests", method = RequestMethod.GET)
	public String viewSpecificTests(Model model) {
		Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		List<Test> tests = testService.getAllTestsBySubjectId(subject.getPr_id());
		
		addSubjectTestsBySubjectIdToModel(model);

		model.addAttribute("tests", tests);

		return "viewSpecificTests";
	}

	private void addSubjectTestsBySubjectIdToModel(Model model) {
		List<SubjectTest> subjectTests = subjectService
				.getSubjectTestsForSubjectById(teacherService.getTeacherByLogin(ldapInfo.getLdapLogin()).getUc_pr_id());

		List<Boolean> usageList = new ArrayList<>();
		for (SubjectTest subjectTest : subjectTests) {
			if (subjectTestService.getSizeOfStudentTestsBySubjectTestId(subjectTest.getId()) == EMPTY) {
				usageList.add(false);
			} else {
				usageList.add(true);
			}
		}

		model.addAttribute("usageList", usageList);
		model.addAttribute("subjectTests", subjectTests);
	}

}
