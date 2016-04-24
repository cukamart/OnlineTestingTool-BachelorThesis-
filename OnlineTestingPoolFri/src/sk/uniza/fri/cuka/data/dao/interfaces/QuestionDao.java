package sk.uniza.fri.cuka.data.dao.interfaces;

import java.util.List;

import sk.uniza.fri.cuka.data.entity.Question;

public interface QuestionDao extends Dao<Question, Integer> {
	
	List<Object[]> getQuestionsRelatedToTestId(int testId);
	List<Question> findBySubject(String prId);
	List<Question> findByTestId(long testId);
}
