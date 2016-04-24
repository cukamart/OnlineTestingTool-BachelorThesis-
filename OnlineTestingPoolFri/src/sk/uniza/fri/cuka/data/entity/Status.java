package sk.uniza.fri.cuka.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "uroven")
public class Status {

	@Id
	private int ur_id;
	private String ur_nazov;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "status", cascade = CascadeType.ALL)
	private List<Manage> manage = new ArrayList<>();

	public Status(int ur_id, String ur_nazov) {
		this.ur_id = ur_id;
		this.ur_nazov = ur_nazov;
	}

	public Status(String ur_nazov) {
		this.ur_nazov = ur_nazov;
	}

	public Status() {

	}

	public int getUr_id() {
		return ur_id;
	}

	public void setUr_id(int ur_id) {
		this.ur_id = ur_id;
	}

	public String getUr_nazov() {		
		return ur_nazov;
	}

	public void setUr_nazov(String ur_nazov) {
		this.ur_nazov = ur_nazov;
	}

	public List<Manage> getManage() {
		return manage;
	}

	public void setManage(List<Manage> manage) {
		this.manage = manage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ur_id;
		result = prime * result + ((ur_nazov == null) ? 0 : ur_nazov.hashCode());
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
		Status other = (Status) obj;
		if (ur_id != other.ur_id)
			return false;
		if (ur_nazov == null) {
			if (other.ur_nazov != null)
				return false;
		} else if (!ur_nazov.equals(other.ur_nazov))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Status [ur_id=" + ur_id + ", ur_nazov=" + ur_nazov + "]";
	}

}
