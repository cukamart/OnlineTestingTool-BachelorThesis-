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

import sk.uniza.fri.cuka.data.dao.QuestionCategoryDao;
import sk.uniza.fri.cuka.data.entity.QuestionCategory;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionCategoryDaoTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private QuestionCategoryDao questionCategoryDao;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		questionCategoryDao.setSession(session);
		Transaction transaction = session.beginTransaction();

		questionCategoryDao.deleteTable();

		transaction.commit();
		session.close();
	}

	/**
	 * Jednoducha entita QuestionCategory.
	 * Skontrolujeme konzistenciu dat nad CRUD operaciami nakolko tato entita zlozitejsie dotazy zatial nevyuziva
	 */
	@Test
	public void testCreateandGetQuestionCategory() {
		Session session = sessionFactory.openSession();
		questionCategoryDao.setSession(session);
		Transaction transaction = session.beginTransaction();

		QuestionCategory questionCategory = new QuestionCategory("Je monitor reentrantny?", "5S003", "monitor", "T",
				123, "Popis co vies o monitore", 13, "Y");

		QuestionCategory questionCategory2 = new QuestionCategory("Vyuziva semafor aktivne cakanie?", "5S003",
				"semafor", "T", 123, "Napis co vies o semafore", 13, "Y");

		List<QuestionCategory> questionCategories;

		questionCategoryDao.create(questionCategory);

		questionCategories = questionCategoryDao.findAll();
		assertEquals("Number of Question Categories should be 1", 1, questionCategories.size());

		questionCategoryDao.create(questionCategory2);
		questionCategories = questionCategoryDao.findAll();
		assertEquals("Number of Question Categories should be 2", 2, questionCategories.size());

		assertEquals("Created QuestionCategory should be identical to retrieved", questionCategory,
				questionCategories.get(0));
		
		questionCategory.setSo_nazov("updated");
		questionCategoryDao.create(questionCategory);
		
		questionCategories = questionCategoryDao.findAll();
		assertEquals("Number of Question Categories should be 2", 2, questionCategories.size());
		assertEquals("Updated question category name should be updated",  "updated", questionCategories.get(1).getSo_nazov());

		transaction.commit();
		session.close();
	}

	@After
	public void cleanMess() {
		init();
	}
}
