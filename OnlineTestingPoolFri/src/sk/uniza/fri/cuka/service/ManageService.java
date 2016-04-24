package sk.uniza.fri.cuka.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.uniza.fri.cuka.data.dao.ManageDaoImpl;
import sk.uniza.fri.cuka.data.entity.Subject;

@Service
public class ManageService {

	@Autowired
	ManageDaoImpl manageDaoImpl;

	@Autowired
	private SessionFactory sessionFactory;

	public List<String> getSubjectNameByTeacherId(long uc_id) {
		Session session = sessionFactory.openSession();
		manageDaoImpl.setSession(session);

		List<String> subjectName = manageDaoImpl.getSubjectNameByTeacherId(uc_id);

		session.close();

		return subjectName;
	}

	public List<Subject> getSubjectByTeacherIdAndYear(long uc_id, int year) {
		Session session = sessionFactory.openSession();
		manageDaoImpl.setSession(session);

		List<Object[]> objects = manageDaoImpl.getSubjectByTeacherIdAndYear(uc_id, year);

		session.close();

		List<Subject> subjects = new ArrayList<>();

		for (Object[] obj : objects) {
			subjects.add((Subject) obj[0]);
		}

		return subjects;
	}
}
