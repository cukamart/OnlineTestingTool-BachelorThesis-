package sk.uniza.fri.cuka.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hodnotenie")
public class Valuation {

	@Id
	private String ho_id;

	@OneToMany(mappedBy = "valuation", cascade = CascadeType.ALL)
	private List<RegisteredSubject> registeredSubjects = new ArrayList<>();

	private String ho_nazov;
	private String ho_popis;
	private Integer ho_percento;

	public Valuation(String ho_id, String ho_nazov, String ho_popis, Integer ho_percento) {
		this.ho_id = ho_id;
		this.ho_nazov = ho_nazov;
		this.ho_popis = ho_popis;
		this.ho_percento = ho_percento;
	}

	public Valuation() {

	}

	public String getHo_id() {
		return ho_id;
	}

	public void setHo_id(String ho_id) {
		this.ho_id = ho_id;
	}

	public String getHo_nazov() {
		return ho_nazov;
	}

	public void setHo_nazov(String ho_nazov) {
		this.ho_nazov = ho_nazov;
	}

	public String getHo_popis() {
		return ho_popis;
	}

	public void setHo_popis(String ho_popis) {
		this.ho_popis = ho_popis;
	}

	public Integer getHo_percento() {
		return ho_percento;
	}

	public void setHo_percento(Integer ho_percento) {
		this.ho_percento = ho_percento;
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
		result = prime * result + ((ho_id == null) ? 0 : ho_id.hashCode());
		result = prime * result + ((ho_nazov == null) ? 0 : ho_nazov.hashCode());
		result = prime * result + ((ho_percento == null) ? 0 : ho_percento.hashCode());
		result = prime * result + ((ho_popis == null) ? 0 : ho_popis.hashCode());
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
		Valuation other = (Valuation) obj;
		if (ho_id == null) {
			if (other.ho_id != null)
				return false;
		} else if (!ho_id.equals(other.ho_id))
			return false;
		if (ho_nazov == null) {
			if (other.ho_nazov != null)
				return false;
		} else if (!ho_nazov.equals(other.ho_nazov))
			return false;
		if (ho_percento == null) {
			if (other.ho_percento != null)
				return false;
		} else if (!ho_percento.equals(other.ho_percento))
			return false;
		if (ho_popis == null) {
			if (other.ho_popis != null)
				return false;
		} else if (!ho_popis.equals(other.ho_popis))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Valuation [ho_id=" + ho_id + ", ho_nazov=" + ho_nazov + ", ho_popis=" + ho_popis + ", ho_percento="
				+ ho_percento + "]";
	}

}
