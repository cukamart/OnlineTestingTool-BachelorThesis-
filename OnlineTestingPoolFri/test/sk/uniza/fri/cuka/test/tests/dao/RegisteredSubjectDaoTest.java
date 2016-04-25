package sk.uniza.fri.cuka.test.tests.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
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
import sk.uniza.fri.cuka.data.dao.RegisteredSubjectDaoImpl;
import sk.uniza.fri.cuka.data.dao.StatusDao;
import sk.uniza.fri.cuka.data.dao.StudentDaoImpl;
import sk.uniza.fri.cuka.data.dao.SubjectDaoImpl;
import sk.uniza.fri.cuka.data.dao.TeacherDaoImpl;
import sk.uniza.fri.cuka.data.dao.ValuationDao;
import sk.uniza.fri.cuka.data.entity.Manage;
import sk.uniza.fri.cuka.data.entity.RegisteredSubject;
import sk.uniza.fri.cuka.data.entity.Status;
import sk.uniza.fri.cuka.data.entity.Student;
import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.Teacher;
import sk.uniza.fri.cuka.data.entity.Valuation;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "classpath:sk/uniza/fri/cuka/config/dao-context.xml",
		"classpath:sk/uniza/fri/cuka/config/security-context.xml",
		"classpath:sk/uniza/fri/cuka/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class RegisteredSubjectDaoTest {

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

	@Autowired
	private ValuationDao valuationDao;

	@Autowired
	private RegisteredSubjectDaoImpl registeredSubjectDao;

	@Autowired
	private StudentDaoImpl studentDao;

	@Before
	public void init() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		registeredSubjectDao.deleteTable();
		valuationDao.deleteTable();
		manageDao.deleteTable();
		statusDao.deleteTable();
		teacherDao.deleteTable();
		subjectDao.deleteTable();
		studentDao.deleteTable();

		transaction.commit();
		session.close();
	}

	@Test
	public void testRegisteredSubjectDao() {
		Session session = sessionFactory.openSession();
		shareSession(session);
		Transaction transaction = session.beginTransaction();

		Status status = new Status(1, "VIP");

		Subject subject = new Subject("5S003", "Softvérové inžinierstvo", "SI", "Software Engineering", "A");
		Subject subject2 = new Subject("5S001", "Operačné systémy", "OS", "Operating Systems", "A");

		Teacher teacher = new Teacher("Vagner", "Dusan", "vagner10", "letmein", "druzby", "Zilina", "97404",
				"vagner@gmail.com", "", "0904112355", "Y", "Y", "5ZI03");
		Teacher teacher2 = new Teacher("Adamec", "Dusan", "adamec0", "letmein", "druzby", "Zilina", "97404",
				"adamec@gmail.com", "", "0904112355", "Y", "Y", "5ZI03");

		statusDao.create(status);
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
		manage2.setStatus(status);
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

		Valuation valuation = new Valuation("A", null, null, 91);
		valuationDao.create(valuation);

		Student student = new Student("cuka", "martin", "cuka666", "letmein", 1, 1, "druzby", "Zilina", "97404",
				"cukamartin@gmail.com", "", "0904112355", "S", "5ZI031");
		studentDao.create(student);

		RegisteredSubject registeredSubject = new RegisteredSubject(student.getSt_id(), manage.getSp_pr_id(),
				manage.getSp_skrok(), new Date(), new Date(), new Date(), new Date(), manage.getSp_uc_id(),
				valuation.getHo_id(), null, null, null, null);

		registeredSubject.setValuation(valuation);
		valuation.getRegisteredSubjects().add(registeredSubject);
		registeredSubject.setManage(manage);
		manage.getRegisteredSubjects().add(registeredSubject);

		registeredSubjectDao.create(registeredSubject);

		List<RegisteredSubject> registeredSubjects = registeredSubjectDao.findAll();

		assertEquals("Number of statuses should be 1", 1, registeredSubjects.size());

		assertEquals("Created RegisteredSubject should be identical to retrieved", registeredSubject,
				registeredSubjects.get(0));

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
		registeredSubjectDao.setSession(session);
		valuationDao.setSession(session);
		studentDao.setSession(session);
	}
}
