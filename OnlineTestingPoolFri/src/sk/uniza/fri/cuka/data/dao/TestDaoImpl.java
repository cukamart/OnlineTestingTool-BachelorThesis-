package sk.uniza.fri.cuka.data.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.dao.interfaces.TestDao;
import sk.uniza.fri.cuka.data.entity.Question;
import sk.uniza.fri.cuka.data.entity.Test;

@Repository("testDao")
public class TestDaoImpl extends AbstractDao<Test, Integer>implements TestDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTestBySubjectId(String subjectId) {
		Query query = session.createQuery("from Test t INNER JOIN t.subject s WHERE s.pr_id = :subjectId");
		query.setParameter("subjectId", subjectId);

		List<Object[]> objects = new ArrayList<>();
		objects.addAll(query.list());

		return objects;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Test> getAllCurrentYearTests(int year) {
		Query query = session.createQuery("FROM Test t INNER JOIN t.subjectTest st WHERE st.skrok = :year")
				.setParameter("year", year);

		List<Object[]> objects = new ArrayList<>();
		objects.addAll(query.list());

		List<Test> tests = new ArrayList<>();
		for (Object[] obj : objects) {
			tests.add((Test) obj[0]);
		}

		return tests;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Question> getQuestionsByTestId(int testId) {
		Query query = session.createQuery("FROM Test t INNER JOIN t.questions q WHERE t.te_id = :testId")
				.setParameter("testId", testId);

		List<Object[]> objects = new ArrayList<>();
		objects.addAll(query.list());

		List<Question> questions = new ArrayList<>();
		for (Object[] obj : objects) {
			questions.add((Question) obj[1]);
		}

		return questions;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Test> getAllTestsBySubjectId(String subjectId) {
		return session.createQuery("FROM Test t WHERE t.te_pr_id = :subjectId").setParameter("subjectId", subjectId)
				.list();
	}
}
