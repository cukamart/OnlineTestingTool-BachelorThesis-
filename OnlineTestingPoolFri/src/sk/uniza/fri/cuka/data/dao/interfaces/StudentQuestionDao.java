package sk.uniza.fri.cuka.data.dao.interfaces;

import java.util.List;

import sk.uniza.fri.cuka.data.entity.StudentQuestion;
import sk.uniza.fri.cuka.data.entity.ids.StudentQuestionId;

public interface StudentQuestionDao extends Dao<StudentQuestion, StudentQuestionId> {

	List<StudentQuestion> getAllStudentQuestionsByTestId(Long ste_id);
}
