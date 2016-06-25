package sk.uniza.fri.cuka.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.uniza.fri.cuka.data.dao.QuestionDaoImpl;
import sk.uniza.fri.cuka.data.dao.StudentQuestionDaoImpl;
import sk.uniza.fri.cuka.data.entity.Question;
import sk.uniza.fri.cuka.data.entity.StudentQuestion;
import sk.uniza.fri.cuka.data.entity.StudentTest;
import sk.uniza.fri.cuka.data.entity.ids.StudentQuestionId;

@Service
public class StudentQuestionService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private StudentQuestionDaoImpl studentQuestionDao;

	@Autowired
	private QuestionDaoImpl questionDaoImpl;

	public StudentQuestion create(Question myQuestion, StudentTest studentTest, int por) {
		Session session = sessionFactory.openSession();
		studentQuestionDao.setSession(session);
		questionDaoImpl.setSession(session);
		Transaction tx = session.beginTransaction();

		Question question = questionDaoImpl.findById(myQuestion.getOt_id());

		// body - 0, por, studentOdpoved=null, bodyOprava=null ..... zodpovenada = 0 (nieje zodpovedana)
		StudentQuestion studentQuestion = new StudentQuestion(studentTest.getSte_id(), question.getOt_id(), 0, por,
				null, null, null, null, 0);

		studentQuestion.setQuestion(question);
		studentQuestion.setStudentTest(studentTest);
		question.getStudentQuestions().add(studentQuestion);
		studentTest.getStudentQuestions().add(studentQuestion);

		studentQuestionDao.create(studentQuestion);

		tx.commit();
		session.close();

		return studentQuestion;
	}

	public StudentQuestion findById(StudentQuestionId studentQuestionId) {
		Session session = sessionFactory.openSession();
		studentQuestionDao.setSession(session);

		StudentQuestion studentQuestion = studentQuestionDao.findById(studentQuestionId);

		session.close();

		return studentQuestion;
	}

	public void addPoints(StudentQuestionId studentQuestionId, Integer points, String odpoved) {
		Session session = sessionFactory.openSession();
		studentQuestionDao.setSession(session);
		Transaction tx = session.beginTransaction();

		StudentQuestion studentQuestion = studentQuestionDao.findById(studentQuestionId);

		studentQuestion.setSot_body(points);
		studentQuestion.setSot_zodpovedana(1);
		studentQuestion.setSot_textodpoved(odpoved);
		studentQuestionDao.create(studentQuestion);

		tx.commit();
		session.close();
	}

	public List<StudentQuestion> getAllStudentQuestionsByTestId(Long ste_id) {
		Session session = sessionFactory.openSession();
		studentQuestionDao.setSession(session);

		List<StudentQuestion> studentQuestions = studentQuestionDao.getAllStudentQuestionsByTestId(ste_id);

		session.close();

		return studentQuestions;
	}

	public void setPoints(StudentQuestionId id, int points) {
		Session session = sessionFactory.openSession();
		studentQuestionDao.setSession(session);
		Transaction tx = session.beginTransaction();

		StudentQuestion studentQuestion = studentQuestionDao.findById(id);
		studentQuestion.setSot_body_new(points);

		studentQuestionDao.create(studentQuestion);

		tx.commit();
		session.close();
	}

	public void setUcText(StudentQuestionId id, String ucText) {
		Session session = sessionFactory.openSession();
		studentQuestionDao.setSession(session);
		Transaction tx = session.beginTransaction();

		StudentQuestion studentQuestion = studentQuestionDao.findById(id);
		studentQuestion.setSot_uc_text(ucText);

		studentQuestionDao.create(studentQuestion);

		tx.commit();
		session.close();
	}
}
