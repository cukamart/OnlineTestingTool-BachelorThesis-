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

import sk.uniza.fri.cuka.data.entity.Answer;
import sk.uniza.fri.cuka.data.entity.Question;
import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.SubjectTest;
import sk.uniza.fri.cuka.model.TeacherImplicitSubject;
import sk.uniza.fri.cuka.service.AnswerService;
import sk.uniza.fri.cuka.service.QuestionService;
import sk.uniza.fri.cuka.service.SubjectService;
import sk.uniza.fri.cuka.service.SubjectTestService;

@Controller
public class QuestionController {

	private static final int EMPTY = 0;

	@Autowired
	private TeacherImplicitSubject teacherImplicitSubject;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SubjectTestService subjectTestService;

	private boolean firstTime = false;

	private int questionId;

	/**
	 * Cez Menu - Otazky - Vytvorit, user vyplni formular ako ma vyzerat otazka
	 */
	@RequestMapping("/createQuestion")
	public String showCreateQuestion(Model model) {
		Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		List<SubjectTest> subjectTests = subjectService.getSubjectTestsForSubjectById(subject.getPr_id());

		if (subjectTests.size() == EMPTY)
			return "redirect:createTest?noTest=true";

		model.addAttribute("subjectTests", subjectTests);
		model.addAttribute("question", new Question());

		firstTime = true;

		return "createQuestion";
	}

	/**
	 * Ked submitnem formu v createQuestion presuniem sa sem. Ak som zle vyplnil formu vrati ma naspat na createQuestion a vypise chyby. Ak
	 * som vyplnil formu spravne insertne do databazy otazku a da mi druhy formular na pridanie odpovede. BUGFIX - ak stlaci user F5 ked je
	 * na pridavani odpovede presmeruje ho na createQuestion (nesmie pridat 2x tu istu otazku do databazy)
	 */
	@RequestMapping(value = "/addAnswer", method = RequestMethod.POST)
	public String showAddAnswer(Model model, @Valid Question question, BindingResult result) {
		Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		if (result.hasErrors()) {
			List<SubjectTest> subjectTests = subjectService.getSubjectTestsForSubjectById(subject.getPr_id());
			model.addAttribute("subjectTests", subjectTests);

			return "createQuestion";
		}

		// v pripade ze uzivatel refreshne stranku otazka sa znovu nevlozi do databazy
		if (firstTime) {
			firstTime = false;
		} else {
			return "redirect:addAnswer"; // presmeruje na GET...
		}

		questionId = questionService.createQuestion(question);

		addAnswersToModel(model);

		model.addAttribute("answer", new Answer());

		return "addAnswer";
	}

	/**
	 * Prida odpoved a presmeruje znovu na pridanie dalsej odpovede, tentokrat cez metodu.GET
	 */
	@RequestMapping("/addingAnswer")
	public String addingAnswer(Model model, @Valid Answer answer, BindingResult result) {
		Question question = questionService.findById(questionId);

		if (result.hasErrors()) {
			teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

			model.addAttribute("question", question);

			addAnswersToModel(model);

			return "addAnswer";
		}

		answerService.create(answer, question);

		return "redirect:addAnswer";
	}

	@RequestMapping(value = "/addAnswer", method = RequestMethod.GET)
	public String showAddMoreAnswer(Model model) {
		teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		Question question = questionService.findById(questionId);
		model.addAttribute("question", question);

		addAnswersToModel(model);

		model.addAttribute("answer", new Answer());

		return "addAnswer";
	}

	@RequestMapping("/viewQuestions")
	public String showAllQuestions(Model model) {
		Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		List<Question> questions = questionService.findBySubject(subject.getPr_id());
		List<SubjectTest> subjectTests = new ArrayList<>();

		for (Question q : questions) {
			subjectTests.add(subjectTestService.findById(q.getOt_pr_te_id()));
		}

		model.addAttribute("subjectTests", subjectTests);
		model.addAttribute("questions", questions);

		return "viewQuestions";
	}

	@RequestMapping("/deleteQuestion")
	public String deleteQuestion(HttpServletRequest request) {
		questionService.delete(Integer.parseInt(request.getParameter("questionId")));

		return "redirect:viewQuestions";
	}

	private void addAnswersToModel(Model model) {
		List<Answer> answers = questionService.findById(questionId).getAnswers();

		model.addAttribute("answers", answers);
	}

	@RequestMapping("/questionChange")
	public String showQuestionChange(Model model) {
		Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		List<SubjectTest> subjectTests = subjectTestService.findBySubjectId(subject.getPr_id());

		model.addAttribute("subjectTests", subjectTests);

		return "questionChange";
	}

	@RequestMapping(value = "/questionChanged", method = RequestMethod.POST)
	public String changeQuestion(HttpServletRequest request) {

		List<Question> questions = questionService.findQuestionByTestType(Long.parseLong(request.getParameter("from")));

		for (Question q : questions) {
			questionService.changeQuestionTestType(q, Long.parseLong(request.getParameter("to")));
		}

		return "redirect:questionChange?question=changed";
	}
}
