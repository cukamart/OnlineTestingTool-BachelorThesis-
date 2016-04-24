package sk.uniza.fri.cuka.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.uniza.fri.cuka.data.dao.SubjectDaoImpl;
import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.SubjectTest;

@Service
public class SubjectService {

	@Autowired
	SubjectDaoImpl subjectDaoImpl;

	@Autowired
	private SessionFactory sessionFactory;

	public Subject getSubjectById(String subjectId) {
		Session session = sessionFactory.openSession();
		subjectDaoImpl.setSession(session);

		Subject subject = subjectDaoImpl.findById(subjectId);

		session.close();

		return subject;
	}

	public List<SubjectTest> getSubjectTestsForSubjectById(String subjectId) {
		Session session = sessionFactory.openSession();
		subjectDaoImpl.setSession(session);
		
		List<SubjectTest> subjectTests = subjectDaoImpl.getSubjectTestsForSubjectById(subjectId);
		
		session.close();
		
		return subjectTests;
	}
}
