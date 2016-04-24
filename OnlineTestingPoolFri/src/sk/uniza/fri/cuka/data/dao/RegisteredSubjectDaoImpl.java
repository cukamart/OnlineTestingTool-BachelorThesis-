package sk.uniza.fri.cuka.data.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.dao.interfaces.RegisteredSubjectDao;
import sk.uniza.fri.cuka.data.entity.RegisteredSubject;
import sk.uniza.fri.cuka.data.entity.ids.RegisteredSubjectId;

@Repository("registeredSubjectDao")
public class RegisteredSubjectDaoImpl extends AbstractDao<RegisteredSubject, RegisteredSubjectId>
		implements RegisteredSubjectDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<RegisteredSubject> getStudentsRegisteredSubjectsCurrentSchoolYear(Long stId, Integer year) {
		return session.createQuery("FROM RegisteredSubject rs WHERE rs.zp_st_id = :stId AND rs.zp_skrok = :year")
				.setParameter("stId", stId).setParameter("year", year).list();
	}

}
