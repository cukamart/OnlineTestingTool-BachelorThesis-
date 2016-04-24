package sk.uniza.fri.cuka.data.dao.interfaces;

import java.util.List;

import sk.uniza.fri.cuka.data.entity.Student;
import sk.uniza.fri.cuka.data.entity.StudentTest;

public interface StudentDao extends Dao<Student, Long> {

	Student getStudentByLogin(String Login);

	List<StudentTest> getStudentTestsByTestId(Long studentId, int testId);
	
	List<StudentTest> getAllStudentTestsByStudentId(Long studentId);
}
