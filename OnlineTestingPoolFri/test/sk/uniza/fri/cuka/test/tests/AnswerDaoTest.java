package sk.uniza.fri.cuka.test.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sk.uniza.fri.cuka.data.dao.AnswerCategoryDao;
import sk.uniza.fri.cuka.data.dao.AnswerDaoImpl;
import sk.uniza.fri.cuka.data.dao.QuestionCategoryDao;
import sk.uniza.fri.cuka.data.dao.QuestionDaoImpl;
import sk.uniza.fri.cuka.data.dao.SubjectDaoImpl;
import sk.uniza.fri.cuka.data.entity.Answer;
import sk.uniza.fri.cuka.data.entity.AnswerCategory;
import sk.uniza.fri.cuka.data.entity.Question;
import sk.uniza.fri.cuka.data.entity.QuestionCategory;
import sk.uniza.fri.cuka.data.entity.Subject;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AnswerDaoTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private AnswerCategoryDao answerCategoryDao;

	@Autowired
	private AnswerDaoImpl answerDao;

	@Autowired
	@Qualifier(value = "questionDao")
	private QuestionDaoImpl questionDao;

	@Autowired
	private QuestionCategoryDao questionCategoryDao;

	@Autowired
	private SubjectDaoImpl subjectDao;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		answerDao.deleteTable();
		questionDao.deleteTable();
		questionCategoryDao.deleteTable();
		subjectDao.deleteTable();
		answerCategoryDao.deleteTable();

		transaction.commit();
		session.close();
	}

	@Test
	public void testCreateAndGetAnswers() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		AnswerCategory answerCategory = new AnswerCategory("skupinova odpoved");
		answerCategoryDao.create(answerCategory);

		List<AnswerCategory> answerCategories = answerCategoryDao.findAll();

		Subject subject = new Subject("5S001", "Operačné systémy", "OS", "Operating Systems", "A");
		subjectDao.create(subject);

		List<Subject> subjects = subjectDao.findAll();

		QuestionCategory questionCategory = new QuestionCategory("Je monitor reentrantny?", subjects.get(0).getPr_id(),
				"monitor", "T", 123, "Popis co vies o monitore", 13, "Y");

		questionCategoryDao.create(questionCategory);

		List<QuestionCategory> questionCategories = questionCategoryDao.findAll();

		Question question = new Question("Co viete o monitore ?", subject.getPr_id(), "T", 1, "text", 13,
				questionCategories.get(0).getSo_id(), "aaa", "aaa", 1L, "mojModel");

		question.setQuestionCategory(questionCategory);
		questionCategory.getQuestions().add(question);

		questionDao.create(question);

		List<Question> questions = questionDao.findAll();

		Answer answer = new Answer("A", questions.get(0).getOt_id(), "T", 1, answerCategories.get(0).getSko_id(),
				"Monitor", ".jpg");

		answer.setAnswerCategory(answerCategory);
		answerCategory.getAnswers().add(answer);

		answer.setQuestion(question);
		question.getAnswers().add(answer);

		answerDao.create(answer);

		List<Answer> answers = answerDao.findAll();

		assertEquals("There should be 1 Answer", 1, answers.size());
		assertEquals("There should be 1 Question", 1, questions.size());

		transaction.commit();
		session.close();
	}

	@After
	public void cleanMess() {
		init();
	}

	private void shareSession(Session session) {
		answerDao.setSession(session);
		questionDao.setSession(session);
		questionCategoryDao.setSession(session);
		subjectDao.setSession(session);
		answerCategoryDao.setSession(session);
	}
}
