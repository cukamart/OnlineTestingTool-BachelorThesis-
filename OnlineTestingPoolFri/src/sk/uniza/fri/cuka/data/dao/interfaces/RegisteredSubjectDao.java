package sk.uniza.fri.cuka.data.dao.interfaces;

import java.util.List;

import sk.uniza.fri.cuka.data.entity.RegisteredSubject;
import sk.uniza.fri.cuka.data.entity.ids.RegisteredSubjectId;

public interface RegisteredSubjectDao extends Dao<RegisteredSubject, RegisteredSubjectId> {

	List<RegisteredSubject> getStudentsRegisteredSubjectsCurrentSchoolYear(Long stId, Integer year);
}
