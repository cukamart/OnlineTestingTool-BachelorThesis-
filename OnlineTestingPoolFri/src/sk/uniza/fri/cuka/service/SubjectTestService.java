package sk.uniza.fri.cuka.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.uniza.fri.cuka.data.dao.SubjectDaoImpl;
import sk.uniza.fri.cuka.data.dao.SubjectTestDaoImpl;
import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.SubjectTest;

@Service
public class SubjectTestService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private SubjectTestDaoImpl subjectTestDao;

	@Autowired
	private SubjectDaoImpl subjectDaoImpl;

	public void createSubjectTest(SubjectTest subjectTest) {
		Session session = sessionFactory.openSession();
		subjectTestDao.setSession(session);
		subjectDaoImpl.setSession(session);

		Transaction tx = session.beginTransaction();

		Subject subject = subjectDaoImpl.findById(subjectTest.getPr_id());

		subjectTest.setSubject(subject);
		subject.getSubjectTests().add(subjectTest);

		subjectDaoImpl.create(subject);

		tx.commit();
		session.close();
	}

	public List<SubjectTest> findAll() {
		Session session = sessionFactory.openSession();
		subjectTestDao.setSession(session);

		List<SubjectTest> subjectTests = subjectTestDao.findAll();

		session.close();

		return subjectTests;
	}

	public void delete(long testId) {
		Session session = sessionFactory.openSession();
		subjectTestDao.setSession(session);
		Transaction tx = session.beginTransaction();

		subjectTestDao.delete(subjectTestDao.findById(testId));

		tx.commit();
		session.close();
	}

	public SubjectTest findById(Long id) {
		Session session = sessionFactory.openSession();
		subjectTestDao.setSession(session);

		SubjectTest subjectTest = subjectTestDao.findById(id);

		session.close();

		return subjectTest;
	}

	public List<SubjectTest> findBySubjectId(String subjectId) {
		Session session = sessionFactory.openSession();
		subjectTestDao.setSession(session);

		List<SubjectTest> subjectTests = subjectTestDao.findBySubjectId(subjectId);

		session.close();

		return subjectTests;
	}

	public int getSizeOfStudentTestsBySubjectTestId(long id) {
		Session session = sessionFactory.openSession();
		subjectTestDao.setSession(session);
		
		int size = subjectTestDao.getSizeOfStudentTestsBySubjectTestId(id);
		
		session.close();
		
		return size;
	}

}
