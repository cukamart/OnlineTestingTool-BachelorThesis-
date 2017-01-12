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

	/**
	 * Skupina odpovedi, ma priamu vazbu na entitu odpoved (otazka moze mat viac spravnych / nespravnych odpovedi)
	 * a v pripade, ze je otazka zodpoveda len ciastocne tak sa komplikovanejsim sposobom vyratavaju body.
	 * Z toho dovodu existuje Entita sk_odpovedi, ktora nadobuda hodnoty spravna / nespravna.
	 * Testom odtestujeme konzistenciu dat.
	 * 1) Create odtestujem ci sa naozaj entita vytvorila a ci je identicka s objektom podla ktorej som ju tvoril.
	 * 2) Vytvorim druhu druhu instanciu odtestujem ci sa vytvorila spravne, nasledne updatnem a znova skontrolujem ci sa updatla ocakavane.
	 * 3) Vymazem data z ktorymi pracujem (pri zlom vymazani by padol nasledujuci test)
	 * - zlozitejsie dotazy nad touto entitou sa v aplikacii zatial nevyskutuju....
	 */
	@Test
	public void testCreateandGetAnswerCategory() {
		Session session = sessionFactory.openSession();
		answerCategoryDao.setSession(session);
		Transaction transaction = session.beginTransaction();

		AnswerCategory answerCategory = new AnswerCategory("správna");
		answerCategoryDao.create(answerCategory);

		List<AnswerCategory> answerCategories = answerCategoryDao.findAll();

		assertEquals("There should be 1 AnswerCategory", 1, answerCategories.size());

		AnswerCategory answerCategory2 = new AnswerCategory("nesprávna");
		answerCategoryDao.create(answerCategory2);

		answerCategories = answerCategoryDao.findAll();

		assertEquals("There should be 2 AnswerCategories", 2, answerCategories.size());
		assertEquals("Created AnswerCategory should be identical to retrieved", answerCategory2,
				answerCategories.get(1));
		
		answerCategory2.setSko_nazov("updated");
		assertEquals("Updated AnswerCategory should contain string \"updated\"", "updated", answerCategories.get(1).getSko_nazov());
				
		transaction.commit();
		session.close();
	}

	@After
	public void cleanMess() {
		init();
	}
}
