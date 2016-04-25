package sk.uniza.fri.cuka.test.tests.dao;

import static org.junit.Assert.assertEquals;

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

import sk.uniza.fri.cuka.data.dao.QuestionDaoImpl;
import sk.uniza.fri.cuka.data.dao.StudentDaoImpl;
import sk.uniza.fri.cuka.data.dao.StudentQuestionDaoImpl;
import sk.uniza.fri.cuka.data.dao.StudentTestDaoImpl;
import sk.uniza.fri.cuka.data.dao.SubjectDaoImpl;
import sk.uniza.fri.cuka.data.dao.SubjectTestDaoImpl;
import sk.uniza.fri.cuka.data.dao.TestDaoImpl;
import sk.uniza.fri.cuka.data.entity.Question;
import sk.uniza.fri.cuka.data.entity.Student;
import sk.uniza.fri.cuka.data.entity.StudentQuestion;
import sk.uniza.fri.cuka.data.entity.StudentTest;
import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.SubjectTest;
import sk.uniza.fri.cuka.data.entity.ids.StudentQuestionId;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentQuestionDaoTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StudentTestDaoImpl studentTestDao;

	@Autowired
	private StudentDaoImpl studentDao;

	@Autowired
	private TestDaoImpl testDaoImpl;

	@Autowired
	private SubjectDaoImpl subjectDaoImpl;

	@Autowired
	private SubjectTestDaoImpl subjectTestDao;

	@Autowired
	private StudentQuestionDaoImpl studentQuestionDao;

	@Autowired
	private QuestionDaoImpl questionDaoImpl;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		studentQuestionDao.deleteTable();
		questionDaoImpl.deleteTable();
		studentTestDao.deleteTable();
		testDaoImpl.deleteTable();
		subjectTestDao.deleteTable();
		subjectDaoImpl.deleteTable();
		studentDao.deleteTable();

		transaction.commit();
		session.close();
	}

	@Test
	public void testCreateandGetStudentTest() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		// vytvorim predmet
		Subject subject = new Subject("5S003", "Softvérové inžinierstvo", "SI", "Software Engineering", "A");
		subjectDaoImpl.create(subject);

		// vytvorim predm_test (Typ Testu)
		SubjectTest subjectTest = new SubjectTest(2015, subject.getPr_id(), "1. zapocet", 1, 123, true, 0.0, 1, 20);
		subjectTest.setSubject(subject);
		subject.getSubjectTests().add(subjectTest);
		subjectTestDao.create(subjectTest);

		List<SubjectTest> subjectTests = subjectTestDao.findAll();
		long teType = subjectTests.get(0).getId();

		// vytvorim konkretny Test
		Date now = new Date();
		sk.uniza.fri.cuka.data.entity.Test test = new sk.uniza.fri.cuka.data.entity.Test(now, now, now, now,
				subject.getPr_id(), "Vlakna", "127.16.08.111", "127.16.08.131", 45, 20, 20, "N", 20, "Y", "N", "Y", 1,
				2015, teType);

		test.setSubject(subject);
		test.setSubjectTest(subjectTest);
		subject.getTests().add(test);
		subjectTest.getTests().add(test);

		testDaoImpl.create(test);

		// vytvorim studenta
		Student student = new Student("cuka", "martin", "cuka666", "letmein", 1, 1, "druzby", "Zilina", "97404",
				"cukamartin@gmail.com", "", "0904112355", "S", "5ZI031");

		studentDao.create(student);

		// vytvorim Student Test
		StudentTest studentTest = new StudentTest(student.getSt_id(), test.getTe_id(), 2015, now, now, 0,
				subjectTest.getMax(), "N", "N", false, now, now);

		studentTest.setTest(test);
		studentTest.setStudent(student);
		test.getStudentTests().add(studentTest);
		student.getStudentTests().add(studentTest);

		studentTestDao.create(studentTest);

		// vytvorim Otazku
		Question question = new Question("Co viete o monitore ?", subject.getPr_id(), "T", 1, "text", 13, null, "aaa",
				"aaa", 1L, "mojModel");

		question.setSubject(subject);
		subject.getQuestions().add(question);
		questionDaoImpl.create(question);

		// vytvorim Stud_otazka
		StudentQuestion studentQuestion = new StudentQuestion(studentTest.getSte_id(), question.getOt_id(), 0, 1, "ano",
				0, null, 0, 0);

		studentQuestion.setQuestion(question);
		studentQuestion.setStudentTest(studentTest);
		question.getStudentQuestions().add(studentQuestion);
		studentTest.getStudentQuestions().add(studentQuestion);

		studentQuestionDao.create(studentQuestion);

		// testujem funkcnost DAO
		List<StudentQuestion> studentQuestions;
		studentQuestions = studentQuestionDao.findAll();

		assertEquals("Number of StudentQuestions should be 1", 1, studentQuestions.size());

		StudentQuestionId sqId = new StudentQuestionId(studentTest.getSte_id(), question.getOt_id());
		StudentQuestion myStudentQuestion = studentQuestionDao.findById(sqId);

		assertEquals("Retrieved studentQuestion find by id should be indentical to created", studentQuestion,
				myStudentQuestion);

		transaction.commit();
		session.close();
	}

	@After
	public void cleanMess() {
		init();
	}

	private void shareSession(Session session) {
		subjectDaoImpl.setSession(session);
		subjectTestDao.setSession(session);
		testDaoImpl.setSession(session);
		studentDao.setSession(session);
		studentTestDao.setSession(session);
		questionDaoImpl.setSession(session);
		studentQuestionDao.setSession(session);
	}
}