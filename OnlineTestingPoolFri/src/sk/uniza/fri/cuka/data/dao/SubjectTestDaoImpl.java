package sk.uniza.fri.cuka.data.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.dao.interfaces.SubjectTestDao;
import sk.uniza.fri.cuka.data.entity.SubjectTest;

@Repository("subjectTestDao")
public class SubjectTestDaoImpl extends AbstractDao<SubjectTest, Long>implements SubjectTestDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<SubjectTest> findBySubjectId(String subjectId) {
		return session.createQuery("FROM SubjectTest st WHERE st.pr_id = :subjectId")
				.setParameter("subjectId", subjectId).list();
	}

	@Override
	public int getSizeOfStudentTestsBySubjectTestId(long id) {
		return session
				.createQuery("FROM SubjectTest st INNER JOIN st.tests t INNER JOIN t.studentTests stud where id = :id")
				.setParameter("id", id).list().size();
	}

}