package sk.uniza.fri.cuka.data.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.dao.interfaces.StudentTestDao;
import sk.uniza.fri.cuka.data.entity.StudentTest;

@Repository("studentTestDao")
public class StudentTestDaoImpl extends AbstractDao<StudentTest, Long>implements StudentTestDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<StudentTest> getAllActiveStudentTestsByTestId(int testId) {
		return session.createQuery("FROM StudentTest st WHERE st.ste_te_id = :testId AND ste_kontrola = 'false'")
				.setParameter("testId", testId).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<StudentTest> getArchiveStudentTestsByTestId(int testId) {
		return session.createQuery("FROM StudentTest st WHERE st.ste_te_id = :testId AND ste_kontrola = 'true'")
				.setParameter("testId", testId).list();
	}

}
