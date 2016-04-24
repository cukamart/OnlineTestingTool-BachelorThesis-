package sk.uniza.fri.cuka.data.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.dao.interfaces.StudentDao;
import sk.uniza.fri.cuka.data.entity.Student;
import sk.uniza.fri.cuka.data.entity.StudentTest;

@Repository("studentDao")
public class StudentDaoImpl extends AbstractDao<Student, Long>implements StudentDao {

	@Override
	public Student getStudentByLogin(String login) {
		return (Student) session.createQuery("from Student where st_login = :login").setParameter("login", login)
				.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<StudentTest> getStudentTestsByTestId(Long studentId, int testId) {
		return session.createQuery("FROM StudentTest st WHERE st.ste_st_id = :studentId AND st.ste_te_id =:testId")
				.setParameter("studentId", studentId).setParameter("testId", testId).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<StudentTest> getAllStudentTestsByStudentId(Long studentId) {
		return session.createQuery("FROM StudentTest st WHERE st.ste_st_id = :studentId")
				.setParameter("studentId", studentId).list();
	}

}
