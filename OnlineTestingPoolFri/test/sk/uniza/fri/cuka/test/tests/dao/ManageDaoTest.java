package sk.uniza.fri.cuka.test.tests.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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

import sk.uniza.fri.cuka.data.dao.ManageDaoImpl;
import sk.uniza.fri.cuka.data.dao.StatusDao;
import sk.uniza.fri.cuka.data.dao.SubjectDaoImpl;
import sk.uniza.fri.cuka.data.dao.TeacherDaoImpl;
import sk.uniza.fri.cuka.data.entity.Manage;
import sk.uniza.fri.cuka.data.entity.Status;
import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.Teacher;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ManageDaoTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private TeacherDaoImpl teacherDao;

	@Autowired
	private SubjectDaoImpl subjectDao;

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private ManageDaoImpl manageDao;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		manageDao.deleteTable();
		statusDao.deleteTable();
		teacherDao.deleteTable();
		subjectDao.deleteTable();

		transaction.commit();
		session.close();
	}

	/**
	 * Entita Spravuje vravi o tom ktory ucitel ake predmety vyucuje v dany sk. rok.
	 * Vytvorim 2 ucitelov (jeden prednasajuci druhy cviciaci)
	 * Vytvorim 2 predmety, jednemu ucitelovi nastavim ze oba vyucuje v roku 2016/2017
	 * Druhemu ucitelovi nastavim ze vyucuje 1 v roku 2016/2017 druhy v roku 2015/2016
	 * Skontrolujeme ci spravne vyhlada podla ID ucitela, k ktorym predmetom ma pristup
	 * Taktiez skontrolujeme ci spravne vyhlada ktore predmety v ktory sk. rok ucitel
	 */
	@Test
	public void testTeachersAndSubjects() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		Status status = new Status(1, "Prednasajuci");
		Status status2 = new Status(2, "Cviciaci");

		Subject subject = new Subject("5S003", "Softvérové inžinierstvo", "SI", "Software Engineering", "A");
		Subject subject2 = new Subject("5S001", "Operačné systémy", "OS", "Operating Systems", "A");

		Teacher teacher = new Teacher("Vagner", "Dusan", "vagner10", "letmein", "druzby", "Zilina", "97404",
				"vagner@gmail.com", "", "0904112355", "Y", "Y", "5ZI03");
		Teacher teacher2 = new Teacher("Adamec", "Dusan", "adamec0", "letmein", "druzby", "Zilina", "97404",
				"adamec@gmail.com", "", "0904112355", "Y", "Y", "5ZI03");

		statusDao.create(status);
		statusDao.create(status2);
		subjectDao.create(subject);
		subjectDao.create(subject2);
		teacherDao.create(teacher);
		teacherDao.create(teacher2);

		Manage manage = new Manage();

		manage.setTeacher(teacher);
		manage.setSubject(subject);
		manage.setStatus(status);
		manage.setSp_uc_id(teacherDao.findAll().get(0).getUc_id());
		manage.setSp_pr_id(subjectDao.findAll().get(0).getPr_id());
		manage.setSp_skrok(2016);
		manage.setSp_uc_boss(123);
		manage.setSp_ur_id(statusDao.findAll().get(0).getUr_id());

		teacher.getManage().add(manage);
		subject.getManage().add(manage);
		status.getManage().add(manage);

		manageDao.create(manage);

		Manage manage2 = new Manage();

		manage2.setTeacher(teacher);
		manage2.setSubject(subject);
		manage2.setStatus(status2);
		manage2.setSp_uc_id(teacherDao.findAll().get(0).getUc_id());
		manage2.setSp_pr_id(subjectDao.findAll().get(1).getPr_id());
		manage2.setSp_skrok(2016);
		manage2.setSp_uc_boss(123);
		manage2.setSp_ur_id(statusDao.findAll().get(0).getUr_id());

		teacher.getManage().add(manage2);
		subject.getManage().add(manage2);
		status.getManage().add(manage2);

		manageDao.create(manage2);

		Manage manage3 = new Manage();

		manage3.setTeacher(teacher);
		manage3.setSubject(subject);
		manage3.setStatus(status);
		manage3.setSp_uc_id(teacherDao.findAll().get(1).getUc_id());
		manage3.setSp_pr_id(subjectDao.findAll().get(0).getPr_id());
		manage3.setSp_skrok(2016);
		manage3.setSp_uc_boss(123);
		manage3.setSp_ur_id(statusDao.findAll().get(0).getUr_id());

		teacher.getManage().add(manage3);
		subject.getManage().add(manage3);
		status.getManage().add(manage3);

		manageDao.create(manage3);
		
		Manage manage4 = new Manage();

		manage4.setTeacher(teacher);
		manage4.setSubject(subject);
		manage4.setStatus(status);
		manage4.setSp_uc_id(teacherDao.findAll().get(1).getUc_id());
		manage4.setSp_pr_id(subjectDao.findAll().get(0).getPr_id());
		manage4.setSp_skrok(2015);
		manage4.setSp_uc_boss(123);
		manage4.setSp_ur_id(statusDao.findAll().get(0).getUr_id());

		teacher.getManage().add(manage4);
		subject.getManage().add(manage4);
		status.getManage().add(manage4);

		manageDao.create(manage3);

		List<String> subjects = manageDao.getSubjectNameByTeacherId(teacherDao.findAll().get(0).getUc_id());
		assertEquals("Teacher (0) should have 2 subjects for year 2016/2017", 2, subjects.size());

		subjects = manageDao.getSubjectNameByTeacherId(teacherDao.findAll().get(1).getUc_id());
		assertEquals("Teacher (1) should have 1 subject for year 2016/2017", 2, subjects.size());
		
		List<Object[]> subjectByTeacherIdAndYear = manageDao.getSubjectByTeacherIdAndYear(teacherDao.findAll().get(1).getUc_id(), 2015);
		
		List<Subject> subjectForYear2015 = new ArrayList<>();
		for (Object[] obj : subjectByTeacherIdAndYear) {
			subjectForYear2015.add((Subject) obj[0]);
		}
		
		assertEquals("Teacher (1) should have 1 subject for year 2015/2016", 1, subjectForYear2015.size());
		
		subjectByTeacherIdAndYear = manageDao.getSubjectByTeacherIdAndYear(teacherDao.findAll().get(0).getUc_id(), 2016);
		
		List<Subject> subjectForYear2016 = new ArrayList<>();
		for (Object[] obj : subjectByTeacherIdAndYear) {
			subjectForYear2016.add((Subject) obj[0]);
		}
		
		assertEquals("Teacher (0) should have 2 subject for year 2016/2017", 2, subjectForYear2016.size());

		transaction.commit();
		session.close();
	}

	@After
	public void cleanMess() {
		init();
	}

	private void shareSession(Session session) {
		manageDao.setSession(session);
		statusDao.setSession(session);
		teacherDao.setSession(session);
		subjectDao.setSession(session);
	}

}
