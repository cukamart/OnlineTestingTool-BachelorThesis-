package sk.uniza.fri.cuka.data.dao.interfaces;

import java.util.List;

import sk.uniza.fri.cuka.data.entity.StudentTest;

public interface StudentTestDao extends Dao<StudentTest, Long> {

	List<StudentTest> getAllActiveStudentTestsByTestId(int testId);

	List<StudentTest> getArchiveStudentTestsByTestId(int testId);
}
