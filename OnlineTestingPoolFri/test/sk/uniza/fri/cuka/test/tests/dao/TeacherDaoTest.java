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

import sk.uniza.fri.cuka.data.dao.TeacherDaoImpl;
import sk.uniza.fri.cuka.data.entity.Teacher;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TeacherDaoTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private TeacherDaoImpl teacherDao;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		teacherDao.deleteTable();

		transaction.commit();
		session.close();
	}

	/**
	 * Vyskusame CRUD operacie na entitou ucitel.
	 * Taktiez vyskusame funkcnost dotazu najst ucitela podla loginu.
	 */
	@Test
	public void testCreateTeacher() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		Teacher teacher = new Teacher("Vagner", "Dusan", "vagner2", "letmein", "druzby", "Zilina", "97404",
				"cukamartin@gmail.com", "", "0904112355", "Y", "Y", "5ZI03");

		Teacher teacher2 = new Teacher("Osko", "Peter", "osko1", "letmein", "druzby", "Zilina", "97404",
				"cukamartin@gmail.com", "", "0904112355", "Y", "Y", "5ZI03");

		List<Teacher> teachers;

		teacherDao.create(teacher);
		teachers = teacherDao.findAll();
		assertEquals("Number of teachers should be 1", 1, teachers.size());

		teacherDao.create(teacher2);
		teachers = teacherDao.findAll();
		assertEquals("Number of teachers should be 2", 2, teachers.size());

		assertEquals("Created teacher should be identical to retrieved", teacher, teachers.get(0));
		
		Teacher teacherByLogin = teacherDao.getTeacherByLogin("vagner2");
		
		assertEquals("Teacher retrieved by login should be \"Dusan Vagner\"", teacherByLogin, teacher);
		
		teacher2.setUc_meno("Martin");
		teacherDao.create(teacher2);
		
		assertEquals("Updated name of teacher should be Martin", "Martin", teacherDao.getTeacherByLogin("osko1").getUc_meno());

		transaction.commit();
		session.close();
	}

	@After
	public void cleanMess() {
		init();
	}

	private void shareSession(Session session) {
		teacherDao.setSession(session);
	}
}
