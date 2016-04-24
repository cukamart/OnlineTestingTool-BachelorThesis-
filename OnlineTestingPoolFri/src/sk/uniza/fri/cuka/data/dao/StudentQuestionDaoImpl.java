package sk.uniza.fri.cuka.data.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.dao.interfaces.StudentQuestionDao;
import sk.uniza.fri.cuka.data.entity.StudentQuestion;
import sk.uniza.fri.cuka.data.entity.ids.StudentQuestionId;

@Repository("studentQuestionDao")
public class StudentQuestionDaoImpl extends AbstractDao<StudentQuestion, StudentQuestionId>
		implements StudentQuestionDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<StudentQuestion> getAllStudentQuestionsByTestId(Long ste_id) {
		return session.createQuery("FROM StudentQuestion sq WHERE sot_ste_id = :ste_id").setParameter("ste_id", ste_id)
				.list();
	}

}
