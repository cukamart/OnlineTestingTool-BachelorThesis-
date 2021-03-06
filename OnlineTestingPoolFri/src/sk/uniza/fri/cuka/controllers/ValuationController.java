package sk.uniza.fri.cuka.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sk.uniza.fri.cuka.data.entity.Question;
import sk.uniza.fri.cuka.data.entity.Student;
import sk.uniza.fri.cuka.data.entity.StudentQuestion;
import sk.uniza.fri.cuka.data.entity.StudentTest;
import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.Test;
import sk.uniza.fri.cuka.data.entity.ids.StudentQuestionId;
import sk.uniza.fri.cuka.model.TeacherImplicitSubject;
import sk.uniza.fri.cuka.model.enums.AnswerType;
import sk.uniza.fri.cuka.model.enums.QuestionType;
import sk.uniza.fri.cuka.service.AnswerService;
import sk.uniza.fri.cuka.service.QuestionService;
import sk.uniza.fri.cuka.service.StudentQuestionService;
import sk.uniza.fri.cuka.service.StudentService;
import sk.uniza.fri.cuka.service.StudentTestService;
import sk.uniza.fri.cuka.service.TestService;

@Controller
public class ValuationController {

	@Autowired
	private TeacherImplicitSubject teacherImplicitSubject;

	@Autowired
	private TestService testService;

	@Autowired
	private StudentTestService studentTestService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private StudentQuestionService studentQuestionService;

	@Autowired
	private AnswerService answerService;

	@RequestMapping("/listOfStudentTests")
	public String showListOfStudentTests(Model model) {
		Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		List<Test> tests = testService.getAllTestsBySubjectId(subject.getPr_id());
		List<StudentTest> studentTests = new ArrayList<>();

		for (Test t : tests) {
			studentTests.addAll(studentTestService.getAllActiveStudentTestsByTestId(t.getTe_id()));
		}

		Set<Student> students = new HashSet<>();
		for (StudentTest st : studentTests) {
			students.add(studentService.findById(st.getSte_st_id()));
		}

		model.addAttribute("students", students);
		model.addAttribute("tests", tests);
		model.addAttribute("studentTests", studentTests);

		return "listOfStudentTests";
	}

	@RequestMapping("/archiveTest")
	public String showArchiveOfTests(Model model) {
		Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		List<Test> tests = testService.getAllTestsBySubjectId(subject.getPr_id());
		List<StudentTest> studentTests = new ArrayList<>();

		for (Test t : tests) {
			studentTests.addAll(studentTestService.getArchiveStudentTestsByTestId(t.getTe_id()));
		}

		Set<Student> students = new HashSet<>();
		for (StudentTest st : studentTests) {
			students.add(studentService.findById(st.getSte_st_id()));
		}

		model.addAttribute("students", students);
		model.addAttribute("tests", tests);
		model.addAttribute("studentTests", studentTests);

		return "checkedTest";
	}

	@RequestMapping("/teacherValidation/{studentTestId}")
	public String showTestToValidate(@PathVariable long studentTestId, Model model) {
		teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);
		StudentTest studentTest = studentTestService.findById(studentTestId);

		List<StudentQuestion> studentQuestions = studentQuestionService
				.getAllStudentQuestionsByTestId(studentTest.getSte_id());

		int sum = 0;
		int max = studentTest.getSte_plnypocet();
		for (StudentQuestion sq : studentQuestions) {
			sum += sq.getSot_body();
			if (sq.getSot_body_new() != null) {
				sum += sq.getSot_body_new();
			}
		}

		studentTest = studentTestService.setResults(studentTestId, sum);

		List<Question> questions = new ArrayList<>();
		List<String> answers = new ArrayList<>();

		findOutStudentAnswersToQuestions(studentQuestions, questions, answers);

		Student student = studentService.findById(studentTest.getSte_st_id());

		model.addAttribute("studentQuestions", studentQuestions);
		model.addAttribute("questions", questions);
		model.addAttribute("answers", answers);
		model.addAttribute("student", student);
		model.addAttribute("studentTest", studentTest);
		model.addAttribute("sum", sum);
		model.addAttribute("max", max);

		return "teacherValidation";
	}

	@RequestMapping("/editStudentQuestion/{studentTestId}/{studentQuestionId}")
	public String showEditQuestion(@PathVariable long studentTestId, @PathVariable int studentQuestionId, Model model,
			@ModelAttribute("uc_text") final String ucText) {

		StudentQuestionId stQId = new StudentQuestionId(studentTestId, studentQuestionId);
		StudentQuestion studentQuestion = studentQuestionService.findById(stQId);
		model.addAttribute("studentQuestion", studentQuestion);
		model.addAttribute("ucText", ucText);
		model.addAttribute("studentTestId", studentTestId);
		model.addAttribute("studentQuestionId", studentQuestionId);
		model.addAttribute("question", questionService.findById(studentQuestionId));
		return "editStudentQuestion";
	}

	@RequestMapping("/editedStudentQuestion")
	public String editQuestionPoints(@RequestParam("studentTestId") String studentTestId,
			@RequestParam("studentQuestionId") String studentQuestionId, @RequestParam("points") String points,
			@RequestParam("confirm") String confirm, @RequestParam("uc_text") String ucText,
			final RedirectAttributes redirectAttributes) {

		Question question = questionService.findById(Integer.parseInt(studentQuestionId));

		StudentQuestionId studQuestionId = new StudentQuestionId(Long.parseLong(studentTestId),
				Integer.parseInt(studentQuestionId));

		if (question.getOt_body() < Integer.parseInt(points) && confirm.equals("false")) {
			redirectAttributes.addFlashAttribute("uc_text", ucText);

			return "redirect:editStudentQuestion/" + Long.parseLong(studentTestId) + "/"
					+ Integer.parseInt(studentQuestionId) + "?confirm=true&points=" + Integer.parseInt(points);
		}

		studentQuestionService.setUcText(studQuestionId, ucText);
		studentQuestionService.setPoints(studQuestionId, Integer.parseInt(points));

		return "redirect:teacherValidation/" + studentTestId;
	}

	@RequestMapping("/checkedTest")
	public String checkedTest(@RequestParam("studTestId") long studTestId) {
		studentTestService.checked(studTestId);

		return "redirect:listOfStudentTests";
	}

	private void findOutStudentAnswersToQuestions(List<StudentQuestion> studentQuestions, List<Question> questions,
			List<String> answers) {
		for (StudentQuestion sq : studentQuestions) {
			Question question = questionService.findById(sq.getSot_ot_id());
			if (question.getOt_typ().equals(QuestionType.ABCD.getValue())) {
				if (sq.getSot_zodpovedana() == AnswerType.NOTANSWERED.getValue()) {
					answers.add("");
				} else {
					answers.add(answerService.findById(Integer.parseInt(sq.getSot_textodpoved())).getOd_znenie());
				}
			} else {
				answers.add("textova");
			}
			questions.add(question);
		}
	}
}
