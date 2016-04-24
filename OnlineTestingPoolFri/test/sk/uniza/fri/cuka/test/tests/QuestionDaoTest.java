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

import sk.uniza.fri.cuka.data.dao.QuestionCategoryDao;
import sk.uniza.fri.cuka.data.dao.QuestionDaoImpl;
import sk.uniza.fri.cuka.data.dao.SubjectDaoImpl;
import sk.uniza.fri.cuka.data.entity.Question;
import sk.uniza.fri.cuka.data.entity.QuestionCategory;
import sk.uniza.fri.cuka.data.entity.Subject;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionDaoTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private QuestionCategoryDao questionCategoryDao;

	@Autowired
	@Qualifier("questionDao")
	private QuestionDaoImpl questionDao;

	@Autowired
	private SubjectDaoImpl subjectDao;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		questionDao.deleteTable();
		questionCategoryDao.deleteTable();
		subjectDao.deleteTable();

		transaction.commit();
		session.close();
	}

	@Test
	public void testCreateandGetQuestions() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		QuestionCategory questionCategory = new QuestionCategory("Je monitor reentrantny?", "5S001", "monitor", "T",
				123, "Popis co vies o monitore", 13, "Y");

		Subject subject = new Subject("5S001", "Operačné systémy", "OS", "Operating Systems", "A");

		questionCategoryDao.create(questionCategory);
		subjectDao.create(subject);

		List<QuestionCategory> questionCategories = questionCategoryDao.findAll();

		Question question = new Question("Co viete o monitore ?", subject.getPr_id(), "T", 1, "text", 13,
				questionCategories.get(0).getSo_id(), "aaa", "aaa", 1L, "mojModel");

		Question question2 = new Question("Co viete o semafore ?", subject.getPr_id(), "T", 1, "text", 13,
				questionCategories.get(0).getSo_id(), "aaa", "aaa", 1L, "mojModel");

		question.setSubject(subject);
		question.setQuestionCategory(questionCategory);
		subject.getQuestions().add(question);
		questionCategory.getQuestions().add(question);

		questionDao.create(question);

		List<Question> questions;

		questions = questionDao.findAll();
		assertEquals("Number of Questions should be 1", 1, questions.size());

		questionDao.create(question2);

		questions = questionDao.findAll();
		assertEquals("Number of Questions should be 2", 2, questions.size());

		transaction.commit();
		session.close();
	}

	@After
	public void cleanMess() {
		init();
	}

	private void shareSession(Session session) {
		questionDao.setSession(session);
		questionCategoryDao.setSession(session);
		subjectDao.setSession(session);
	}
}
