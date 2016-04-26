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

	private Integer questionId;

	// formular pre vytvorenie otazky
	@RequestMapping(value = "/createQuestion", method = RequestMethod.GET)
	public String showCreateQuestion(Model model) {
		Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		List<SubjectTest> subjectTests = subjectService.getSubjectTestsForSubjectById(subject.getPr_id());

		if (subjectTests.size() == EMPTY)
			return "redirect:createTest?noTest=true";

		model.addAttribute("subjectTests", subjectTests);
		model.addAttribute("question", new Question());

		return "createQuestion";
	}

	// ak sa nepodari vytvorit otazku tak napis preco a poziadaj o opravu inak vytvor otazku a presmeruj na pridanie odpovedi
	@RequestMapping(value = "/addAnswer", method = RequestMethod.POST)
	public String showAddAnswer(Model model, @Valid Question question, BindingResult result) {
		if (result.hasErrors()) {
			Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

			model.addAttribute("subjectTests", subjectService.getSubjectTestsForSubjectById(subject.getPr_id()));

			return "createQuestion";
		}

		questionId = questionService.createQuestion(question);

		return "redirect:addAnswer";
	}

	// pridaj odpoved k prave vytvorenej otazke (ak otazka bola deletnuta alebo user zada rovno URL bez zadania otazky vrati ho na formular
	// vytvorenia otazky
	@RequestMapping(value = "/addAnswer", method = RequestMethod.GET)
	public String showAddMoreAnswer(Model model) {
		if (questionId == null) {
			return "redirect:createQuestion";
		}

		teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		model.addAttribute("question", questionService.findById(questionId));

		addAnswersToModel(model);

		model.addAttribute("answer", new Answer());

		return "addAnswer";
	}

	// prida odpoved k otazke (ak sa nepodari pridat, vyzve ucitela aby vyplnil znenie odpovede spravne)
	@RequestMapping(value = "/addingAnswer", method = RequestMethod.POST)
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

	// prehlad otazok na zaklade implicitneho predmetu ucitela
	@RequestMapping(value = "/viewQuestions", method = RequestMethod.GET)
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

	// delete otazky
	@RequestMapping(value = "/deleteQuestion", method = RequestMethod.POST)
	public String deleteQuestion(HttpServletRequest request) {
		questionService.delete(Integer.parseInt(request.getParameter("questionId")));

		this.questionId = null;

		return "redirect:viewQuestions";
	}

	// formular pre export otazok
	@RequestMapping(value = "/questionChange", method = RequestMethod.GET)
	public String showQuestionChange(Model model) {
		Subject subject = teacherImplicitSubject.addTeacherAndImplicitSubjectToModel(model);

		List<SubjectTest> subjectTests = subjectTestService.findBySubjectId(subject.getPr_id());

		model.addAttribute("subjectTests", subjectTests);

		return "questionChange";
	}

	// vykonanie exportu otazok
	@RequestMapping(value = "/questionChange", method = RequestMethod.POST)
	public String changeQuestion(HttpServletRequest request) {

		List<Question> questions = questionService.findQuestionByTestType(Long.parseLong(request.getParameter("from")));

		for (Question q : questions) {
			questionService.changeQuestionTestType(q, Long.parseLong(request.getParameter("to")));
		}

		return "redirect:questionChange?question=changed";
	}

	private void addAnswersToModel(Model model) {
		List<Answer> answers = questionService.findById(questionId).getAnswers();

		model.addAttribute("answers", answers);
	}
}
