package sk.uniza.fri.cuka.data.dao.interfaces;

import sk.uniza.fri.cuka.data.entity.Answer;

public interface AnswerDao extends Dao<Answer, Integer> {

	int getNumberOfCorrectAnswersToQuestion(Integer questionId);
}
