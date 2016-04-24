package sk.uniza.fri.cuka.data.dao.interfaces;

import sk.uniza.fri.cuka.data.entity.Teacher;

public interface TeacherDao extends Dao<Teacher, Long>{

	Teacher getTeacherByLogin(String login);
}
