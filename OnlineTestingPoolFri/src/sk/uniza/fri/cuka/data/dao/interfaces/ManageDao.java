package sk.uniza.fri.cuka.data.dao.interfaces;

import java.util.List;

import sk.uniza.fri.cuka.data.entity.Manage;
import sk.uniza.fri.cuka.data.entity.ids.ManageId;

public interface ManageDao extends Dao<Manage, ManageId>{

	List<String> getSubjectNameByTeacherId(long uc_id);
	List<Object[] > getSubjectByTeacherIdAndYear(long uc_id, int year);
}
