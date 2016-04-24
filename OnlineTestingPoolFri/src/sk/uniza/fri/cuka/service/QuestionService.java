package sk.uniza.fri.cuka.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.uniza.fri.cuka.data.dao.QuestionDaoImpl;
import sk.uniza.fri.cuka.data.dao.SubjectDaoImpl;
import sk.uniza.fri.cuka.data.entity.Question;
import sk.uniza.fri.cuka.data.entity.Subject;

@Service
public class QuestionService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private QuestionDaoImpl questionDaoImpl;

	@Autowired
	private SubjectDaoImpl subjectDaoImpl;

	public int createQuestion(Question question) {
		Session session = sessionFactory.openSession();
		questionDaoImpl.setSession(session);
		subjectDaoImpl.setSession(session);

		Transaction tx = session.beginTransaction();

		Subject subject = subjectDaoImpl.findById(question.getOt_pr_id());

		question.setSubject(subject);
		subject.getQuestions().add(question);

		question = questionDaoImpl.create(question);

		tx.commit();
		session.close();

		return question.getOt_id();
	}

	public Question findById(int questionId) {
		Session session = sessionFactory.openSession();
		questionDaoImpl.setSession(session);

		Question question = questionDaoImpl.findById(questionId);

		session.close();

		return question;
	}

	public List<Question> findBySubject(String prId) {
		Session session = sessionFactory.openSession();
		questionDaoImpl.setSession(session);

		List<Question> questions = questionDaoImpl.findBySubject(prId);

		session.close();

		return questions;
	}

	public void delete(int questionId) {
		Session session = sessionFactory.openSession();
		questionDaoImpl.setSession(session);
		Transaction tx = session.beginTransaction();

		questionDaoImpl.delete(questionDaoImpl.findById(questionId));

		tx.commit();
		session.close();
	}

	public void deleteByTestId(long testId) {
		Session session = sessionFactory.openSession();
		questionDaoImpl.setSession(session);
		Transaction tx = session.beginTransaction();

		List<Question> questions = questionDaoImpl.findByTestId(testId);

		for (Question question : questions) {
			questionDaoImpl.delete(question);
			session.flush();
		}

		tx.commit();
		session.close();

	}

	public List<Question> findQuestionByTestType(Long testType) {
		Session session = sessionFactory.openSession();
		questionDaoImpl.setSession(session);

		List<Question> questions = questionDaoImpl.findByTestId(testType);

		session.close();

		return questions;
	}

	public void changeQuestionTestType(Question q, long testType) {
		Session session = sessionFactory.openSession();
		questionDaoImpl.setSession(session);
		Transaction tx = session.beginTransaction();

		Question question = questionDaoImpl.findById(q.getOt_id());
		question.setOt_pr_te_id(testType);

		questionDaoImpl.create(question);

		tx.commit();
		session.close();
	}
}
