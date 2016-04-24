package sk.uniza.fri.cuka.data.dao.interfaces;

import java.util.List;

import sk.uniza.fri.cuka.data.entity.Question;
import sk.uniza.fri.cuka.data.entity.Test;

public interface TestDao extends Dao<Test, Integer> {

	List<Object[]> getTestBySubjectId(String subjectId);
	List<Test> getAllCurrentYearTests(int year);
	List<Question> getQuestionsByTestId(int testId);
	List<Test> getAllTestsBySubjectId(String subjectId);
}
