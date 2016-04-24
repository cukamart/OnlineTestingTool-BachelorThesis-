package sk.uniza.fri.cuka.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.uniza.fri.cuka.data.dao.AnswerCategoryDao;
import sk.uniza.fri.cuka.data.dao.AnswerDaoImpl;
import sk.uniza.fri.cuka.data.dao.QuestionDaoImpl;
import sk.uniza.fri.cuka.data.entity.Answer;
import sk.uniza.fri.cuka.data.entity.AnswerCategory;
import sk.uniza.fri.cuka.data.entity.Question;

@Service
public class AnswerService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private AnswerDaoImpl answerDaoImpl;

	@Autowired
	private AnswerCategoryDao answerCategoryDao;

	@Autowired
	private QuestionDaoImpl questionDaoImpl;

	public int getNumberOfCorrectAnswersToQuestion(Integer questionId) {
		Session session = sessionFactory.openSession();
		answerDaoImpl.setSession(session);

		Transaction tx = session.beginTransaction();

		int rightAnswers = answerDaoImpl.getNumberOfCorrectAnswersToQuestion(questionId);

		tx.commit();
		session.close();

		return rightAnswers;
	}

	public void create(Answer answer, Question question) {
		Session session = sessionFactory.openSession();
		answerDaoImpl.setSession(session);
		answerCategoryDao.setSession(session);
		questionDaoImpl.setSession(session);

		Transaction tx = session.beginTransaction();

		AnswerCategory answerCategory = answerCategoryDao.findById(answer.getOd_sk_id());

		answer.setAnswerCategory(answerCategory);
		answerCategory.getAnswers().add(answer);

		answer.setQuestion(question);
		question.getAnswers().add(answer);

		answerDaoImpl.create(answer);

		tx.commit();
		session.close();
	}

	public Answer findById(int questionId) {
		Session session = sessionFactory.openSession();
		answerDaoImpl.setSession(session);

		Answer answer = answerDaoImpl.findById(questionId);

		session.close();

		return answer;
	}
}
