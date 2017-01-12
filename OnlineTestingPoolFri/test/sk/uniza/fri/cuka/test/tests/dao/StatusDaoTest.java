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

import sk.uniza.fri.cuka.data.dao.StatusDao;
import sk.uniza.fri.cuka.data.entity.Status;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class StatusDaoTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StatusDao statusDao;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		statusDao.deleteTable();

		transaction.commit();
		session.close();
	}

	/**
	 * Status je jednoducha entita ktora cleni ucitelov na prednasajucich / cviciacich ...
	 * V sucasnosti si aplikacia vystaci iba z CRUD operaciami preto odtestujem konzistenciu dat zatial len 
	 * nad jednoduchymi crud operaciami.
	 */
	@Test
	public void testCreateandGetStatus() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		Status status = new Status(1, "prednášajúci");
		Status status2 = new Status(2, "cvičiaci");

		List<Status> statuses;

		statusDao.create(status);
		statusDao.create(status2);
		statuses = statusDao.findAll();
		assertEquals("Number of statuses should be 2", 2, statuses.size());

		assertEquals("Created status should be identical to retrieved", status, statuses.get(0));
		assertEquals("Created status should be identical to retrieved", status2, statuses.get(1));
		
		status.setUr_nazov("suplujúci");
		statusDao.create(status);
		assertEquals("Updated status should be suplujúci", "suplujúci", statuses.get(0).getUr_nazov());

		transaction.commit();
		session.close();
	}

	@After
	public void cleanMess() {
		init();
	}

	private void shareSession(Session session) {
		statusDao.setSession(session);
	}
}
