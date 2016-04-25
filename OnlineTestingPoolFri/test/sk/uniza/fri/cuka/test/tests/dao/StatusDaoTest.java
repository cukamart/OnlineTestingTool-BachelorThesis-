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

	@Test
	public void testCreateandGetStatus() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		Status status = new Status(1, "VIP");

		List<Status> statuses;

		statusDao.create(status);
		statuses = statusDao.findAll();
		assertEquals("Number of statuses should be 1", 1, statuses.size());

		// neporovnava ID a heslo (ID je automaticky generovane a heslo zasifrovane...)
		assertEquals("Created subject should be identical to retrieved", status, statuses.get(0));

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
