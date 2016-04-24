package sk.uniza.fri.cuka.data.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.dao.interfaces.TeacherDao;
import sk.uniza.fri.cuka.data.entity.Teacher;

@Repository("teacherDao")
public class TeacherDaoImpl extends AbstractDao<Teacher, Long> implements TeacherDao {
	
	@Override
	public Teacher getTeacherByLogin(String login) {
		Query query = session.createQuery("from Teacher where uc_login = :login");
		query.setParameter("login", login);
		
		Teacher teacher = (Teacher) query.uniqueResult();

		return teacher;
	}
}
