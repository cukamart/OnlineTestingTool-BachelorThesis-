package sk.uniza.fri.cuka.test.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import sk.uniza.fri.cuka.data.dao.QuestionCategoryDao;
import sk.uniza.fri.cuka.data.dao.QuestionDaoImpl;
import sk.uniza.fri.cuka.data.dao.SubjectDaoImpl;
import sk.uniza.fri.cuka.data.dao.SubjectTestDaoImpl;
import sk.uniza.fri.cuka.data.dao.TestDaoImpl;
import sk.uniza.fri.cuka.data.entity.Question;
import sk.uniza.fri.cuka.data.entity.QuestionCategory;
import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.SubjectTest;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestDaoTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private TestDaoImpl testDao;

	@Autowired
	private SubjectDaoImpl subjectDao;

	@Autowired
	private SubjectTestDaoImpl subjectTestDao;

	@Autowired
	private QuestionDaoImpl questionDao;

	@Autowired
	private QuestionCategoryDao questionCategoryDao;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		questionDao.deleteTable();
		questionCategoryDao.deleteTable();
		testDao.deleteTable();
		subjectTestDao.deleteTable();
		subjectDao.deleteTable();

		transaction.commit();
		session.close();
	}

	/**
	 * Vytvorim dva predmety (vlozim do databazy). Vytvorim dva predm_test (vlozim do databazy). Vytvorim test pre 1. predmet (5S001) a
	 * skontrolujem spravne vytvorenie. Vytvorim 2.test pre 1. predmet (5S001) a skontrolujem spravne vytvorenie Vytvorim 3.test pre 2.
	 * predmet (5S003) a skontrolujem ci su 1. predmetu stale priradene iba 2 testy. Vytvorim dve otazky, do 1. testu vlozim obe otazky, do
	 * 2. testu vlozim len prvu otazku a skontrolujem ci je to tam spravne vlozene (ManyToMany) potom vytiahnem znenie otazky z testu cislo
	 * 2 a zistim ci sa znenie otazky zhoduje s tym co predpokladam ze tam bude...
	 * 
	 */
	@Test
	public void testCreateandGetTests() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		Date now = new Date();

		Subject subject = new Subject("5S001", "Operačné systémy", "OS", "Operating Systems", "A");
		Subject subject2 = new Subject("5S003", "Softvérové inžinierstvo", "SI", "Software Engineering", "A");

		subjectDao.create(subject);
		subjectDao.create(subject2);

		SubjectTest subjectTest = new SubjectTest(2015, "5S001", "1. zapocet", 1, 123, true, 0.0, 1, 20);
		subjectTest.setSubject(subject);
		subject.getSubjectTests().add(subjectTest);
		subjectTestDao.create(subjectTest);

		SubjectTest subjectTest2 = new SubjectTest(2015, "5S003", "1. zapocet", 1, 123, true, 0.0, 1, 20);
		subjectTest2.setSubject(subject2);
		subject2.getSubjectTests().add(subjectTest2);
		subjectTestDao.create(subjectTest2);

		List<SubjectTest> subjectTests = subjectTestDao.findAll();

		long teType = subjectTests.get(0).getId();
		long teType2 = subjectTests.get(1).getId();

		sk.uniza.fri.cuka.data.entity.Test test = new sk.uniza.fri.cuka.data.entity.Test(now, now, now, now, "5S001",
				"Vlakna", "127.16.08.111", "127.16.08.131", 45, 20, 20, "N", 20, "Y", "N", "Y", 1, 2015, teType);

		sk.uniza.fri.cuka.data.entity.Test test2 = new sk.uniza.fri.cuka.data.entity.Test(now, now, now, now, "5S001",
				"Semafory", "127.16.08.111", "127.16.08.131", 45, 20, 20, "N", 20, "Y", "N", "Y", 1, 2015, teType);

		sk.uniza.fri.cuka.data.entity.Test test3 = new sk.uniza.fri.cuka.data.entity.Test(now, now, now, now, "5S003",
				"Extreme Programming", "127.16.08.111", "127.16.08.131", 45, 20, 20, "N", 20, "Y", "N", "Y", 1, 2015,
				teType2);
		
		System.out.println(test);

		test.setSubject(subject);
		test.setSubjectTest(subjectTest);
		subject.getTests().add(test);
		subjectTest.getTests().add(test);

		testDao.create(test);

		List<sk.uniza.fri.cuka.data.entity.Test> tests;
		tests = testDao.findAll();

		assertEquals("Number of tests should be 1", 1, tests.size());

		test2.setSubject(subject);
		test2.setSubjectTest(subjectTest);
		subject.getTests().add(test2);
		subjectTest.getTests().add(test2);

		testDao.create(test2);

		tests = testDao.findAll();

		assertEquals("Number of tests should be 2", 2, tests.size());

		test3.setSubject(subject2);
		test3.setSubjectTest(subjectTest2);
		subject2.getTests().add(test3);
		subjectTest2.getTests().add(test3);

		testDao.create(test3);

		List<Object[]> objects = testDao.getTestBySubjectId("5S001");

		List<sk.uniza.fri.cuka.data.entity.Test> testBySubject = new ArrayList<>();
		for (Object[] obj : objects) {
			testBySubject.add((sk.uniza.fri.cuka.data.entity.Test) obj[0]);
		}

		assertEquals("There should be 2 tests for Subject with ID 5S001", 2, testBySubject.size());

		QuestionCategory questionCategory = new QuestionCategory("Je monitor reentrantny?", "5S003", "monitor", "T",
				123, "Popis co vies o monitore", 13, "Y");

		questionCategoryDao.create(questionCategory);

		List<QuestionCategory> questionCategories = questionCategoryDao.findAll();

		Question question = new Question("Co viete o monitore ?", subject.getPr_id(), "T", 1, "text", 13,
				questionCategories.get(0).getSo_id(), "aaa", "aaa", 1L, "mojModel");

		Question question2 = new Question("Co viete o semafore ?", subject.getPr_id(), "T", 1, "text", 13,
				questionCategories.get(0).getSo_id(), "aaa", "aaa", 1L, "mojModel");

		test.getQuestions().add(question);
		test.getQuestions().add(question2);
		question.getTests().add(test);
		question2.getTests().add(test);

		test2.getQuestions().add(question);
		question.getTests().add(test2);

		testDao.create(test);
		testDao.create(test2);

		objects = questionDao.getQuestionsRelatedToTestId(tests.get(0).getTe_id());

		List<Question> questions = new ArrayList<>();

		for (Object[] obj : objects) {
			questions.add((Question) obj[0]);
		}

		assertEquals("Test 1 should contain 2 questions", 2, questions.size());

		questions.clear();
		objects = questionDao.getQuestionsRelatedToTestId(tests.get(1).getTe_id());

		for (Object[] obj : objects) {
			questions.add((Question) obj[0]);
		}

		assertEquals("Test 2 should contain 1 question", 1, questions.size());
		assertEquals("Test 2 should contain question which ot_znenie = Co viete o monitore ? ", "Co viete o monitore ?",
				questions.get(0).getOt_znenie());

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
		testDao.setSession(session);
		subjectTestDao.setSession(session);
		subjectDao.setSession(session);
	}

}
