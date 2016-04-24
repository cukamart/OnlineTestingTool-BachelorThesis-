package sk.uniza.fri.cuka.data.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.dao.interfaces.QuestionDao;
import sk.uniza.fri.cuka.data.entity.Question;

@Repository("questionDao")
public class QuestionDaoImpl extends AbstractDao<Question, Integer>implements QuestionDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getQuestionsRelatedToTestId(int testId) {
		Query query = session.createQuery("from Question q INNER JOIN q.tests t WHERE t.te_id = :testId");
		query.setParameter("testId", testId);

		List<Object[]> questions = new ArrayList<>();
		questions.addAll(query.list());

		return questions;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Question> findBySubject(String prId) {
		return session.createQuery("from Question q WHERE q.ot_pr_id = :prId").setParameter("prId", prId).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Question> findByTestId(long testId) {
		return session.createQuery("from Question q WHERE q.ot_pr_te_id = :testId").setParameter("testId", testId)
				.list();
	}
}