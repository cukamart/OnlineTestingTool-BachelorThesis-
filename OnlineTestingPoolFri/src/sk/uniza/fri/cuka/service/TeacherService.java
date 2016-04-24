package sk.uniza.fri.cuka.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.uniza.fri.cuka.data.dao.TeacherDaoImpl;
import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.Teacher;
import sk.uniza.fri.cuka.model.CurrentSchoolYear;

@Service
public class TeacherService {

	@Autowired
	private TeacherDaoImpl teacherDaoImpl;

	@Autowired
	private ManageService manageService;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CurrentSchoolYear currentSchoolYear;

	public Teacher getTeacherByLogin(String login) {
		Session session = sessionFactory.openSession();
		teacherDaoImpl.setSession(session);

		Teacher teacher = teacherDaoImpl.getTeacherByLogin(login);

		session.close();

		if (isImplicitSubjectSet(teacher)) {
			return teacher;
		} else {
			setImplicitSubject(teacher);
			return teacher;
		}

	}

	/**
	 * Zisti ci ma ucitel nastaveny defaultny predmet
	 * 
	 * @return true - ak ma nastaveny
	 */
	private boolean isImplicitSubjectSet(Teacher teacher) {
		if (teacher.getUc_pr_id() == null) {
			return false;
		}

		return true;
	}

	/**
	 * Nastavi ucitelovi defaultny predmet (vyberie nejaky predmet, ktory spravuje)
	 */
	private void setImplicitSubject(Teacher teacher) {
		List<Subject> subjects = manageService.getSubjectByTeacherIdAndYear(teacher.getUc_id(),
				currentSchoolYear.getCurrentSchoolYear());

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		teacherDaoImpl.setSession(session);

		teacher.setUc_pr_id(subjects.get(0).getPr_id());

		teacherDaoImpl.create(teacher);

		tx.commit();
		session.close();
	}

	public void setImplicitSubject(Teacher teacher, String subjectId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		teacherDaoImpl.setSession(session);

		teacher.setUc_pr_id(subjectId);

		teacherDaoImpl.create(teacher);

		tx.commit();
		session.close();
	}

}
