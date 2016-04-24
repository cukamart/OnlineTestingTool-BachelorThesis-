package sk.uniza.fri.cuka.data.dao.interfaces;

import java.util.List;

import sk.uniza.fri.cuka.data.entity.Subject;
import sk.uniza.fri.cuka.data.entity.SubjectTest;

public interface SubjectDao extends Dao<Subject, String> {

	List<SubjectTest> getSubjectTestsForSubjectById(String subjectId);
}
