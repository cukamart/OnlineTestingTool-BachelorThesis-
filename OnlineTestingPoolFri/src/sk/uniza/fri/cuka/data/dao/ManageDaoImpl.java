package sk.uniza.fri.cuka.data.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import sk.uniza.fri.cuka.data.dao.interfaces.ManageDao;
import sk.uniza.fri.cuka.data.entity.Manage;
import sk.uniza.fri.cuka.data.entity.ids.ManageId;

@Repository("manageDao")
public class ManageDaoImpl extends AbstractDao<Manage, ManageId> implements ManageDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getSubjectNameByTeacherId(long uc_id) {
		Query query = session
				.createQuery("SELECT s.pr_nazov from Subject s INNER JOIN s.manage m WHERE m.sp_uc_id = :teacherId");
		query.setParameter("teacherId", uc_id);

		List<String> subjects = new ArrayList<>();
		subjects.addAll(query.list());

		return subjects;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getSubjectByTeacherIdAndYear(long uc_id, int year) {
		Query query = session
				.createQuery("from Subject s INNER JOIN s.manage m WHERE m.sp_uc_id = :teacherId AND m.sp_skrok = :year");
		query.setParameter("teacherId", uc_id);
		query.setParameter("year", year);

		List<Object[] > objs = new ArrayList<>();
		objs.addAll(query.list());

		return objs;
	}

	/*public Manage getAll(ManageId id) {
		Query query = session.createQuery("from Manage m WHERE m.sp_uc_id = :uc_id AND m.sp_pr_id = :sp_pr_id AND sp_skrok = :sp_skrok");
		query.setParameter("uc_id", id.getSp_uc_id());
		query.setParameter("sp_pr_id", id.getSp_pr_id());
		query.setParameter("sp_skrok", id.getSp_skrok());
		
		Manage mg = (Manage) query.uniqueResult();
		
		return mg;
	}*/
}
