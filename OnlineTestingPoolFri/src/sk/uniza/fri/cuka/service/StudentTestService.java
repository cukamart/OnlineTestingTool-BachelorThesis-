package sk.uniza.fri.cuka.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.uniza.fri.cuka.data.dao.StudentDaoImpl;
import sk.uniza.fri.cuka.data.dao.StudentTestDaoImpl;
import sk.uniza.fri.cuka.data.dao.SubjectTestDaoImpl;
import sk.uniza.fri.cuka.data.dao.TestDaoImpl;
import sk.uniza.fri.cuka.data.entity.Student;
import sk.uniza.fri.cuka.data.entity.StudentTest;
import sk.uniza.fri.cuka.data.entity.SubjectTest;
import sk.uniza.fri.cuka.data.entity.Test;
import sk.uniza.fri.cuka.ldap.LdapInfo;
import sk.uniza.fri.cuka.model.CurrentSchoolYear;

@Service
public class StudentTestService {
	
	@Autowired
	private StudentDaoImpl studentDaoImpl;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StudentTestDaoImpl studentTestDao;

	@Autowired
	private SubjectTestDaoImpl subjectTestDao;

	@Autowired
	private LdapInfo ldapInfo;

	@Autowired
	private StudentService studentService;

	@Autowired
	private CurrentSchoolYear currentSchoolYear;

	@Autowired
	private TestDaoImpl testDaoImpl;

	public StudentTest create(Test myTest) {
		Student myStudent = studentService.getStudentByLogin(ldapInfo.getLdapLogin());

		Session session = sessionFactory.openSession();
		studentTestDao.setSession(session);
		testDaoImpl.setSession(session);
		subjectTestDao.setSession(session);
		studentDaoImpl.setSession(session);
		Transaction transaction = session.beginTransaction();

		Student student = studentDaoImpl.findById(myStudent.getSt_id());
		Test test = testDaoImpl.findById(myTest.getTe_id());
		SubjectTest subjectTest = subjectTestDao.findById(test.getTe_typ());
		StudentTest studentTest = new StudentTest(student.getSt_id(), test.getTe_id(),
				currentSchoolYear.getCurrentSchoolYear(), new Date(), null, 0, subjectTest.getMax(), "N", "N", false,
				null, null);

		studentTest.setTest(test);
		studentTest.setStudent(student);
		test.getStudentTests().add(studentTest);
		student.getStudentTests().add(studentTest);

		studentTestDao.create(studentTest);

		transaction.commit();
		session.close();

		return studentTest;
	}

	public StudentTest finalize(Long studentTestId, int sum) {
		Session session = sessionFactory.openSession();
		studentTestDao.setSession(session);
		Transaction tx = session.beginTransaction();

		StudentTest studentTest = studentTestDao.findById(studentTestId);

		studentTest.setSte_koniec(new Date());
		studentTest.setSte_vysledok(sum);
		studentTest.setSte_vypracovany("A");

		StudentTest valuatedStudentTest = studentTestDao.create(studentTest);

		tx.commit();
		session.close();

		return valuatedStudentTest;
	}

	public List<StudentTest> getAllActiveStudentTestsByTestId(int testId) {
		Session session = sessionFactory.openSession();
		studentTestDao.setSession(session);

		List<StudentTest> studentTests = studentTestDao.getAllActiveStudentTestsByTestId(testId);

		session.close();

		return studentTests;
	}

	public StudentTest findById(long studentTestId) {
		Session session = sessionFactory.openSession();
		studentTestDao.setSession(session);

		StudentTest studentTest = studentTestDao.findById(studentTestId);

		session.close();

		return studentTest;
	}

	public StudentTest setResults(long studentTestId, int sum) {
		Session session = sessionFactory.openSession();
		studentTestDao.setSession(session);
		Transaction tx = session.beginTransaction();

		StudentTest studentTest = studentTestDao.findById(studentTestId);

		studentTest.setSte_vysledok(sum);

		StudentTest valuatedStudentTest = studentTestDao.create(studentTest);

		tx.commit();
		session.close();

		return valuatedStudentTest;
	}

	public void checked(long studTestId) {
		Session session = sessionFactory.openSession();
		studentTestDao.setSession(session);
		Transaction tx = session.beginTransaction();

		StudentTest studentTest = studentTestDao.findById(studTestId);

		studentTest.setSte_kontrola(true);

		studentTestDao.create(studentTest);

		tx.commit();
		session.close();
	}

	public List<StudentTest> getArchiveStudentTestsByTestId(int testId) {
		Session session = sessionFactory.openSession();
		studentTestDao.setSession(session);

		List<StudentTest> studentTests = studentTestDao.getArchiveStudentTestsByTestId(testId);

		session.close();

		return studentTests;
	}
}
