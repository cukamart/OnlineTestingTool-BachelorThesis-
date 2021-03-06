package sk.uniza.fri.cuka.test.tests.dao;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sk.uniza.fri.cuka.data.dao.SubjectDaoImpl;
import sk.uniza.fri.cuka.data.dao.SubjectTestDaoImpl;
import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.SubjectTest;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SubjectTestDaoTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private SubjectDaoImpl subjectDao;

	@Autowired
	private SubjectTestDaoImpl subjectTestDao;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		subjectTestDao.deleteTable();
		subjectDao.deleteTable();

		transaction.commit();
		session.close();
	}

	/**
	 * Vytvorime testy na predmety Operacne Systemy a Softverove inzinierstvo
	 * Priradime testy predmetom a zistime ci sa spravne dotazujeme ktory test
	 * je ktoremu testu priradeny
	 */
	@Test
	public void testCreateandGetSubjectTest() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		Subject subject = new Subject("5S003", "Softvérové inžinierstvo", "SI", "Software Engineering", "A");
		subjectDao.create(subject);

		Subject subject1 = new Subject("5S001", "Operacne Systemy", "OS", "Operating Systems", "A");
		subjectDao.create(subject1);

		SubjectTest subjectTest = new SubjectTest(2015, "5S003", "1. zapocet", 1, 13, true, 0.0, 1, 20);
		subjectTest.setSubject(subject);
		subject.getSubjectTests().add(subjectTest);
		subjectTestDao.create(subjectTest);

		SubjectTest subjectTest2 = new SubjectTest(2015, "5S003", "2. zapocet", 1, 13, true, 0.0, 1, 20);
		subjectTest2.setSubject(subject);
		subject.getSubjectTests().add(subjectTest2);
		subjectTestDao.create(subjectTest2);

		SubjectTest subjectTest3 = new SubjectTest(2015, "5S001", "1. zapocet", 1, 13, true, 0.0, 1, 20);
		subjectTest3.setSubject(subject1);
		subject1.getSubjectTests().add(subjectTest3);
		subjectTestDao.create(subjectTest3);

		List<SubjectTest> subjectTests = subjectDao.getSubjectTestsForSubjectById("5S003");

		assertEquals("Subject SI should have 2 subjectTests", 2, subjectTests.size());
		assertEquals("1. SubjectTest name should be \"1. zapocet\"", "1. zapocet", subjectTests.get(0).getNazov());
		assertEquals("2. SubjectTest name should be \"2. zapocet\"", "2. zapocet", subjectTests.get(1).getNazov());

		subjectTests = subjectDao.getSubjectTestsForSubjectById("5S001");
		assertEquals("Subject OS should have 1 subjectTest", 1, subjectTests.size());

		transaction.commit();
		session.close();
	}

	@After
	public void cleanMess() {
		init();
	}

	private void shareSession(Session session) {
		subjectTestDao.setSession(session);
		subjectDao.setSession(session);
	}
}
