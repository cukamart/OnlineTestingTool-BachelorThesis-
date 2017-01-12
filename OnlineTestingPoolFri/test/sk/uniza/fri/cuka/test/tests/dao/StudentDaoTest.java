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

import sk.uniza.fri.cuka.data.dao.StudentDaoImpl;
import sk.uniza.fri.cuka.data.entity.Student;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentDaoTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StudentDaoImpl studentDao;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		studentDao.deleteTable();

		transaction.commit();
		session.close();
	}

	/**
	 * Zkontroluje konzistenciu dat nad entitou student.
	 * Aplikacia v sucasnom stave si vystaci len zo zakladnymi crud operaciami, ktore odtestujeme
	 */
	@Test
	public void testCreateandGetStudent() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		Student student = new Student("cuka", "martin", "cuka666", "letmein", 1, 1, "druzby", "Zilina", "97404",
				"cukamartin@gmail.com", "", "0904112355", "S", "5ZI031");

		Student student2 = new Student("cuka2", "martin2", "cuka6662", "letmein", 1, 1, "druzby", "Zilina", "97404",
				"cukamartin@gmail.com", "", "0904112354", "S", "5ZI031");

		List<Student> students;

		studentDao.create(student);
		students = studentDao.findAll();
		assertEquals("Number of students should be 1", 1, students.size());

		studentDao.create(student2);
		students = studentDao.findAll();
		assertEquals("Number of students should be 2", 2, students.size());

		assertEquals("Created student should be identical to retrieved", student, students.get(0));
		
		student.setSt_ulica("mladeznicka");
		studentDao.create(student);
		
		Student updatedStudent = studentDao.findById(student.getSt_id());
		assertEquals("Updated student should live on mladeznicka street", "mladeznicka", updatedStudent.getSt_ulica());

		transaction.commit();
		session.close();
	}

	@After
	public void cleanMess() {
		init();
	}

	private void shareSession(Session session) {
		studentDao.setSession(session);
	}
}
