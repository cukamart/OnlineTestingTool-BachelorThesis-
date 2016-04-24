package sk.uniza.fri.cuka.test.tests;

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

import sk.uniza.fri.cuka.data.dao.ValuationDao;
import sk.uniza.fri.cuka.data.entity.Valuation;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ValuationDaoTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ValuationDao valuationDao;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		valuationDao.deleteTable();

		transaction.commit();
		session.close();
	}

	@Test
	public void testCreateandGetStatus() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		Valuation valuation = new Valuation("A", null, null, 91);
		valuationDao.create(valuation);

		List<Valuation> valuations;

		valuations = valuationDao.findAll();

		assertEquals("Number of valuations should be 1", 1, valuations.size());

		assertEquals("Created valuation should be identical to retrieved", valuation, valuations.get(0));

		transaction.commit();
		session.close();
	}

	@After
	public void cleanMess() {
		init();
	}

	private void shareSession(Session session) {
		valuationDao.setSession(session);
	}
}
