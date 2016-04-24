package sk.uniza.fri.cuka.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sk.uniza.fri.cuka.data.dao.TestDaoImpl;
import sk.uniza.fri.cuka.data.entity.Question;
import sk.uniza.fri.cuka.data.entity.SubjectTest;
import sk.uniza.fri.cuka.data.entity.Test;

@Service
public class TestService {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private TestDaoImpl testDaoImpl;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private SubjectTestService subjectTestService;

	public boolean createTestAndGenerateQuestions(Test test) {
		List<Question> questions = questionService.findQuestionByTestType(test.getTe_typ()); // otazky ktore mozu byt do testu generovane
		SubjectTest subjectTest = subjectTestService.findById(test.getTe_typ()); // kolko bodovy je test ?

		int maxScore = subjectTest.getMax();

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		testDaoImpl.setSession(session);

		for (int i = 0; i < questions.size(); i++) {
			if (maxScore == 0)
				break;
			if (maxScore - questions.get(i).getOt_body() < 0)
				continue;

			maxScore -= questions.get(i).getOt_body();
			test.getQuestions().add(questions.get(i));
		}

		if (maxScore != 0) {
			tx.commit();
			session.close();
			return false;
		}

		testDaoImpl.create(test);
		tx.commit();
		session.close();

		return true;
	}

	public List<Test> findAll() {
		Session session = sessionFactory.openSession();
		testDaoImpl.setSession(session);

		List<Test> tests = testDaoImpl.findAll();

		session.close();

		return tests;
	}

	public List<Test> getAllCurrentYearTests(int year) {
		Session session = sessionFactory.openSession();
		testDaoImpl.setSession(session);

		List<Test> tests = testDaoImpl.getAllCurrentYearTests(year);

		session.close();

		return tests;
	}

	public Test findById(int testId) {
		Session session = sessionFactory.openSession();
		testDaoImpl.setSession(session);

		Test test = testDaoImpl.findById(testId);

		session.close();

		return test;
	}

	public List<Question> getQuestionsByTestId(int testId) {
		Session session = sessionFactory.openSession();
		testDaoImpl.setSession(session);
		
		List<Question> questions = testDaoImpl.getQuestionsByTestId(testId);
		
		session.close();
		
		return questions;
	}
	
	public List<Test> getAllTestsBySubjectId(String subjectId) {
		Session session = sessionFactory.openSession();
		testDaoImpl.setSession(session);
		
		List<Test> tests = testDaoImpl.getAllTestsBySubjectId(subjectId);
		
		session.close();
		
		return tests;
	}

}
