package sk.uniza.fri.cuka.data.dao.interfaces;

import java.util.List;

import sk.uniza.fri.cuka.data.entity.SubjectTest;

public interface SubjectTestDao extends Dao<SubjectTest, Long> {

	List<SubjectTest> findBySubjectId(String subjectId);

	int getSizeOfStudentTestsBySubjectTestId(long id);
}
