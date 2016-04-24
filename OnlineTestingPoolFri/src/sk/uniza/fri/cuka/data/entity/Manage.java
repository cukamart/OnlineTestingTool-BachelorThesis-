package sk.uniza.fri.cuka.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import sk.uniza.fri.cuka.data.entity.ids.ManageId;

/**
 * 
 * @author Martin Cuka
 * 
 *         Manage - Owning Entity Teacher - Source Entity One teacher to Many manage (bidirectional)
 *
 */
@Entity
@IdClass(ManageId.class) // Compound Primary Key
@Table(name = "spravuje")
public class Manage {

	@Id
	@Column(name = "sp_uc_id")
	private long sp_uc_id;

	@Id
	@Column(name = "sp_pr_id")
	private String sp_pr_id;

	@Id
	@Column(name = "sp_skrok")
	private int sp_skrok;

	@ManyToOne
	@JoinColumn(name = "sp_uc_id", referencedColumnName = "uc_id", insertable = false, updatable = false)
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(name = "sp_pr_id", referencedColumnName = "pr_id", insertable = false, updatable = false)
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "sp_ur_id", referencedColumnName = "ur_id", insertable = false, updatable = false)
	private Status status;

	@OneToMany(mappedBy = "manage", cascade = CascadeType.ALL)
	private List<RegisteredSubject> registeredSubjects = new ArrayList<>();

	private int sp_uc_boss;
	private int sp_ur_id;

	public Manage(long sp_uc_id, String sp_pr_id, int sp_skrok, int sp_uc_boss, int sp_ur_id) {
		this.sp_uc_id = sp_uc_id;
		this.sp_pr_id = sp_pr_id;
		this.sp_skrok = sp_skrok;
		this.sp_uc_boss = sp_uc_boss;
		this.sp_ur_id = sp_ur_id;
	}

	public Manage() {

	}

	public long getSp_uc_id() {
		return sp_uc_id;
	}

	public void setSp_uc_id(long sp_uc_id) {
		this.sp_uc_id = sp_uc_id;
	}

	public String getSp_pr_id() {
		return sp_pr_id;
	}

	public void setSp_pr_id(String sp_pr_id) {
		this.sp_pr_id = sp_pr_id;
	}

	public int getSp_skrok() {
		return sp_skrok;
	}

	public void setSp_skrok(int sp_skrok) {
		this.sp_skrok = sp_skrok;
	}

	public int getSp_uc_boss() {
		return sp_uc_boss;
	}

	public void setSp_uc_boss(int sp_uc_boss) {
		this.sp_uc_boss = sp_uc_boss;
	}

	public int getSp_ur_id() {
		return sp_ur_id;
	}

	public void setSp_ur_id(int sp_ur_id) {
		this.sp_ur_id = sp_ur_id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<RegisteredSubject> getRegisteredSubjects() {
		return registeredSubjects;
	}

	public void setRegisteredSubjects(List<RegisteredSubject> registeredSubjects) {
		this.registeredSubjects = registeredSubjects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sp_pr_id == null) ? 0 : sp_pr_id.hashCode());
		result = prime * result + sp_skrok;
		result = prime * result + sp_uc_boss;
		result = prime * result + (int) (sp_uc_id ^ (sp_uc_id >>> 32));
		result = prime * result + sp_ur_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manage other = (Manage) obj;
		if (sp_pr_id == null) {
			if (other.sp_pr_id != null)
				return false;
		} else if (!sp_pr_id.equals(other.sp_pr_id))
			return false;
		if (sp_skrok != other.sp_skrok)
			return false;
		if (sp_uc_boss != other.sp_uc_boss)
			return false;
		if (sp_uc_id != other.sp_uc_id)
			return false;
		if (sp_ur_id != other.sp_ur_id)
			return false;
		return true;
	}

}
