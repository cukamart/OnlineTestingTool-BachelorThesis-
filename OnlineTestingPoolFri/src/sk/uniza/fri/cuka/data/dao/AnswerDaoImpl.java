package sk.uniza.fri.cuka.data.dao;

import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.dao.interfaces.AnswerDao;
import sk.uniza.fri.cuka.data.entity.Answer;

@Repository("answerDao")
public class AnswerDaoImpl extends AbstractDao<Answer, Integer>implements AnswerDao {

	/**
	 * sko_id = 1 - spravne sko_id = 0 - nespravne
	 */
	@Override
	public int getNumberOfCorrectAnswersToQuestion(Integer questionId) {
		return session
				.createQuery(
						"SELECT od_znenie FROM Answer ans INNER JOIN ans.answerCategory ac WHERE ac.sko_id = :sko_id")
				.setParameter("sko_id", 1).list().size();
	}

}
