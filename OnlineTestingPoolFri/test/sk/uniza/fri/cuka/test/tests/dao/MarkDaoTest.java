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

import sk.uniza.fri.cuka.data.dao.MarkDao;
import sk.uniza.fri.cuka.data.entity.Mark;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MarkDaoTest {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private MarkDao markDao;
	
	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		markDao.deleteTable();

		transaction.commit();
		session.close();
	}
	
	/**
	 * Jednoducha entita znamka.
	 * Skontrolujeme konzistenciu dat nad CRUD operaciami nakolko tato entita zlozitejsie selecty zatial nevyuziva
	 */
	@Test
	public void markDaoTest() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		Mark mark = new Mark("A", "výborne");

		List<Mark> marks;

		markDao.create(mark);
		marks = markDao.findAll();
		assertEquals("Number of marks should be 1", 1, marks.size());
		assertEquals("Created subject should be identical to retrieved", mark, marks.get(0));
		
		mark = new Mark("B", "chválitebne");
		markDao.create(mark);
		marks = markDao.findAll();
		assertEquals("Number of marks should be 2", 2, marks.size());
		assertEquals("Created subject should be identical to retrieved", mark, marks.get(1));
		
		mark.setPopis("nový popis"); // update
		markDao.create(mark);
		marks = markDao.findAll();
		assertEquals("Number of marks should be 2", 2, marks.size());
		assertEquals("Created subject should be identical to retrieved", "výborne", marks.get(0).getPopis());
		assertEquals("Created subject should be identical to retrieved", "nový popis", marks.get(1).getPopis());
		

		transaction.commit();
		session.close();
	}
	
	@After
	public void cleanMess() {
		init();
	}
	
	private void shareSession(Session session) {
		markDao.setSession(session);
	}
}
