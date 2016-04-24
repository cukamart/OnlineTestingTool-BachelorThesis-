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

import sk.uniza.fri.cuka.data.dao.AnswerCategoryDao;
import sk.uniza.fri.cuka.data.entity.AnswerCategory;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AnswerCategoryDaoTest {

	@Autowired
	private AnswerCategoryDao answerCategoryDao;

	@Autowired
	private SessionFactory sessionFactory;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		answerCategoryDao.setSession(session);
		Transaction transaction = session.beginTransaction();
		answerCategoryDao.deleteTable();
		transaction.commit();
		session.close();
	}

	@Test
	public void testCreateandGetAnswerCategory() {
		Session session = sessionFactory.openSession();
		answerCategoryDao.setSession(session);
		Transaction transaction = session.beginTransaction();

		AnswerCategory answerCategory = new AnswerCategory("skupinova odpoved");
		answerCategoryDao.create(answerCategory);

		List<AnswerCategory> answerCategories = answerCategoryDao.findAll();

		assertEquals("There should be 1 AnswerCategory", 1, answerCategories.size());

		AnswerCategory answerCategory2 = new AnswerCategory("skupinova odpoved2");
		answerCategoryDao.create(answerCategory2);

		answerCategories = answerCategoryDao.findAll();

		transaction.commit();
		session.close();

		assertEquals("There should be 2 AnswerCategories", 2, answerCategories.size());
		assertEquals("Created AnswerCategory should be identical to retrieved", answerCategory2,
				answerCategories.get(1));
	}

	@After
	public void cleanMess() {
		init();
	}
}
