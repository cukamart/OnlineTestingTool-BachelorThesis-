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
import sk.uniza.fri.cuka.data.entity.Subject;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SubjectDaoTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private SubjectDaoImpl subjectDao;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		subjectDao.deleteTable();

		transaction.commit();
		session.close();
	}

	/**
	 * Entita predmet sa vytvara zriedka a aplikacia si vystaci so zakladnymi
	 * CRUD operaciami
	 */
	@Test
	public void testCreateandGetSubject() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		Subject subject = new Subject("5S003", "Softvérové inžinierstvo", "SI", "Software Engineering", "A");

		List<Subject> subjects;

		subjectDao.create(subject);

		subjects = subjectDao.findAll();
		assertEquals("Number of subjects should be 1", 1, subjects.size());

		// neporovnava ID a heslo (ID je automaticky generovane a heslo
		// zasifrovane...)
		assertEquals("Created subject should be identical to retrieved", subject, subjects.get(0));

		Subject mySubject = subjectDao.findById("5S003");

		assertEquals("Retrieved subject find by id should be indentical to created", subject, mySubject);
		
		subject.setPr_nazov("Zajisteni Kvality Software");
		subjectDao.create(subject);
		
		assertEquals("Updated subject should be Zajisteni Kvality Software", subject.getPr_nazov(), subjectDao.findAll().get(0).getPr_nazov());

		transaction.commit();
		session.close();
	}

	@After
	public void cleanMess() {
		init();
	}

	private void shareSession(Session session) {
		subjectDao.setSession(session);
	}
}