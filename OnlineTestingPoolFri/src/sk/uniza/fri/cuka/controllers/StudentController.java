package sk.uniza.fri.cuka.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sk.uniza.fri.cuka.data.entity.Answer;
import sk.uniza.fri.cuka.data.entity.Question;
import sk.uniza.fri.cuka.data.entity.RegisteredSubject;
import sk.uniza.fri.cuka.data.entity.Student;
import sk.uniza.fri.cuka.data.entity.StudentQuestion;
import sk.uniza.fri.cuka.data.entity.StudentTest;
import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.Test;
import sk.uniza.fri.cuka.data.entity.WrapperStudentQuestion;
import sk.uniza.fri.cuka.data.entity.ids.StudentQuestionId;
import sk.uniza.fri.cuka.ldap.LdapInfo;
import sk.uniza.fri.cuka.model.CurrentSchoolYear;
import sk.uniza.fri.cuka.model.enums.QuestionType;
import sk.uniza.fri.cuka.model.enums.Result;
import sk.uniza.fri.cuka.service.AnswerService;
import sk.uniza.fri.cuka.service.QuestionService;
import sk.uniza.fri.cuka.service.StudentQuestionService;
import sk.uniza.fri.cuka.service.StudentService;
import sk.uniza.fri.cuka.service.StudentTestService;
import sk.uniza.fri.cuka.service.SubjectService;
import sk.uniza.fri.cuka.service.TestService;

//TODO - logiku dat do MODELU !!!, celkovo refactor tejto triedy...
@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private TestService testService;

	@Autowired
	private CurrentSchoolYear currentSchoolYear;

	@Autowired
	private LdapInfo ldapInfo;

	@Autowired
	private StudentTestService studentTestService;

	@Autowired
	private StudentQuestionService studentQuestionService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	private boolean firstTime = false;

	@RequestMapping("/sindex")
	public String showsIndex(Model model) {
		Student student = studentService.getStudentByLogin(ldapInfo.getLdapLogin());
		
		// zapisane predmety studenta na aktualny skolsky rok
		List<RegisteredSubject> registeredSubjects = studentService
				.getStudentsRegisteredSubjectsCurrentSchoolYear(student.getSt_id());

		// nazov predmetu (zo zapisanych viem iba ID - citatelnejsie pre uzivatela)
		List<Subject> subjects = new ArrayList<>();
		for (RegisteredSubject rs : registeredSubjects) {
			subjects.add(subjectService.getSubjectById(rs.getZp_pr_id()));
		}

		List<StudentTest> studentTests = studentService.getAllStudentTestsByStudentId(student.getSt_id());
		List<Test> tests = new ArrayList<>();

		for (StudentTest st : studentTests) {
			tests.add(testService.findById(st.getSte_te_id()));
		}

		model.addAttribute("tests", tests);
		model.addAttribute("student", student);
		model.addAttribute("studentTests", studentTests);
		model.addAttribute("subjects", subjects);
		model.addAttribute("schoolYear", currentSchoolYear.getStringRepresentationOfSchoolYear());

		return "sindex";
	}

	@RequestMapping("/stest")
	public String showsTest(Model model) {
		Student student = studentService.getStudentByLogin(ldapInfo.getLdapLogin());

		// vrati zapisane predmety studenta na aktualny sk. rok
		List<RegisteredSubject> registeredSubjects = studentService
				.getStudentsRegisteredSubjectsCurrentSchoolYear(student.getSt_id());

		// nazov predmetu (zo zapisanych viem iba ID - citatelnejsie pre uzivatela)
		List<Subject> subjects = new ArrayList<>();
		for (RegisteredSubject rs : registeredSubjects) {
			subjects.add(subjectService.getSubjectById(rs.getZp_pr_id())); // nazov predmetu (nie len ID)
		}

		// vrati testy pre tento rok
		 List<Test> currentYearTests = testService.getAllCurrentYearTests(currentSchoolYear.getCurrentSchoolYear());

		// vrati testy ktore moze student este vypracovat (neriesi pocet Pokusov to sa riesi az na stranke)
		List<Test> relevantTests = new ArrayList<>();
		for (RegisteredSubject rs : registeredSubjects) {
			for (Test ct : currentYearTests) {
				if (rs.getZp_pr_id().equals(ct.getTe_pr_id())) { // testy na predmet ktore ma student zapisany
					if (isAvailable(ct)) { // je este test k danemu datumu otvoreny ?
						relevantTests.add(ct);
					}
				}
			}
		}

		// zisti kolko pokusov na dany test uz student mal...
		List<Integer> pokusov = new ArrayList<>();
		for (Test test : relevantTests) {
			pokusov.add(studentService.getStudentTestsByTestId(student.getSt_id(), test.getTe_id()).size());
		}

		model.addAttribute("pokusov", pokusov);
		model.addAttribute("schoolYear", currentSchoolYear.getStringRepresentationOfSchoolYear());
		model.addAttribute("subjects", subjects);
		model.addAttribute("relevantTests", relevantTests);
		model.addAttribute("student", student);

		firstTime = true;

		return "stest";
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String confirmPassword(Model model, HttpServletRequest request) {
		String password = request.getParameter("password");

		Test test = testService.findById(Integer.parseInt(request.getParameter("testId")));

		if (!test.getTe_heslo().equals(password)) {
			return "redirect:stest?error=true";
		}

		if (firstTime) {
			firstTime = false;
		} else {
			return "redirect:sindex";
		}

		StudentTest studentTest = studentTestService.create(test);

		List<Question> questions = testService.getQuestionsByTestId(Integer.parseInt(request.getParameter("testId")));

		WrapperStudentQuestion arrStudentQuestion = new WrapperStudentQuestion();

		// poprehadzuje odpovede
		for (Question question : questions) {
			Collections.shuffle(question.getAnswers());
		}

		for (int i = 0; i < questions.size(); i++) {
			int por = i + 1;
			arrStudentQuestion.getsQuestions().add(studentQuestionService.create(questions.get(i), studentTest, por));
		}

		model.addAttribute("arrStudentQuestion", arrStudentQuestion);
		model.addAttribute("test", test);
		model.addAttribute("questions", questions);
		model.addAttribute("studentTest", studentTest);

		return "test";
	}

	@RequestMapping(value = "/valuateTest", method = RequestMethod.POST)
	public String showValuationOfTest(Model model, WrapperStudentQuestion arrStudentQuestion,
			HttpServletRequest request) {
		Long studentTestId = Long.parseLong(request.getParameter("studentTest"));
		List<StudentQuestion> studentQuestion = arrStudentQuestion.getsQuestions();

		int sum = valuateTest(studentTestId, studentQuestion);

		StudentTest studentTest = studentTestService.finalize(studentTestId, sum);

		model.addAttribute("studentTest", studentTest);

		return "valuateTest";
	}

	private int valuateTest(Long studentTestId, List<StudentQuestion> studentQuestion) {
		int sum = 0;

		for (StudentQuestion sq : studentQuestion) {
			Question question = questionService.findById(sq.getSot_ot_id()); // vrati aktualnu otazku

			if (sq.getSot_textodpoved() == null || sq.getSot_textodpoved().equals("")) { // ak otazka nebola zodpovedana nic nerob
				continue;
			}

			if (question.getOt_typ().equals(QuestionType.TEXT.getValue())) {
				List<Answer> answers = question.getAnswers();
				for (Answer answer : answers) {
					if (answer.getOd_znenie().equals(sq.getSot_textodpoved())) {
						studentQuestionService.addPoints(new StudentQuestionId(studentTestId, question.getOt_id()),
								question.getOt_body(), sq.getSot_textodpoved());
						sum += question.getOt_body();
						continue;
					} else {
						studentQuestionService.addPoints(new StudentQuestionId(studentTestId, question.getOt_id()), 0,
								sq.getSot_textodpoved());
						continue;
					}
				}
			}

			if (question.getOt_typ().equals(QuestionType.ABCD.getValue())) {
				Answer answer = answerService.findById(Integer.parseInt(sq.getSot_textodpoved()));

				if (answer.getAnswerCategory().getSko_id() == Result.CORRECT.getValue()) {
					studentQuestionService.addPoints(new StudentQuestionId(studentTestId, question.getOt_id()),
							question.getOt_body(), sq.getSot_textodpoved());
					sum += question.getOt_body();
					continue;
				} else {
					studentQuestionService.addPoints(new StudentQuestionId(studentTestId, question.getOt_id()), 0,
							sq.getSot_textodpoved());
					continue;
				}
			}
		}
		return sum;
	}

	@SuppressWarnings("deprecation")
	private boolean isAvailable(Test ct) {
		Date now = new Date();

		int nowYear = now.getYear() + 1900;
		int nowMonth = now.getMonth() + 1;
		int nowDay = now.getDate();
		int nowHour = now.getHours();
		int nowMinute = now.getMinutes();

		// ak ucitel blizsie nezada test je otvoreny donekonecna...
		if (ct.getTe_datum_zac() == null || ct.getTe_datum_kon() == null || ct.getTe_cas_zac() == null
				|| ct.getTe_cas_kon() == null) {
			return true;
		}

		String zac = ct.getTe_datum_zac().toString();
		String kon = ct.getTe_datum_kon().toString();

		String zacCas = ct.getTe_cas_zac().toString();
		String konCas = ct.getTe_cas_kon().toString();

		String[] sMyDateZac = zac.split("-");
		String[] sMyDateKon = kon.split("-");

		String[] sCasZac = zacCas.split(":");
		String[] sCasKon = konCas.split(":");

		// rok mesiac den || hodiny minuty (24 hodinovy format)
		int[] myDateZac = { Integer.parseInt(sMyDateZac[0]), Integer.parseInt(sMyDateZac[1]),
				Integer.parseInt(sMyDateZac[2]) };
		int[] myDateKon = { Integer.parseInt(sMyDateKon[0]), Integer.parseInt(sMyDateKon[1]),
				Integer.parseInt(sMyDateKon[2]) };
		int[] myCasZac = { Integer.parseInt(sCasZac[0]), Integer.parseInt(sCasZac[1]) };
		int[] myCasKon = { Integer.parseInt(sCasKon[0]), Integer.parseInt(sCasKon[1]) };

		if (nowYear >= myDateZac[0] && nowYear <= myDateKon[0]) {
			if (nowMonth >= myDateZac[1] && nowMonth <= myDateKon[1]) {
				if (nowDay >= myDateZac[2] && nowDay <= myDateKon[2]) {
					if (nowMonth == myDateKon[1] && nowDay == myDateKon[2]) { // je to posledny den co je otvoreny test ?
						if (nowHour >= myCasZac[0] && nowHour <= myCasKon[0]) { // ak ano pozri este cas....
							if (nowMinute >= myCasZac[1] && nowMinute <= myCasKon[1]) { // pozri este cas....
								return true;
							}
						}
					} else { // ak to nieje posledny den, cas neries a vrat true...
						return true;
					}
				}
			}
		}

		return false;
	}
}
