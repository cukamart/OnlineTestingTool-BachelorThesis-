package sk.uniza.fri.cuka.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import sk.uniza.fri.cuka.data.entity.ids.StudentAnswerId;

@Entity
@IdClass(StudentAnswerId.class)
@Table(name = "stud_odpoved")
public class StudentAnswer {

	@Id
	@Column(name = "sod_ste_id")
	private Long sod_ste_id;

	@Id
	@Column(name = "sod_ot_id")
	private Integer sod_ot_id;

	@Id
	@Column(name = "sod_od_id")
	private Integer sod_od_id;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "sod_ste_id", referencedColumnName = "sot_ste_id", insertable = false, updatable = false),
		@JoinColumn(name = "sod_ot_id", referencedColumnName = "sot_ot_id", insertable = false, updatable = false)
	})
	private StudentQuestion studentQuestion;

	@ManyToOne
	@JoinColumn(name = "sod_od_id", referencedColumnName = "od_id", insertable = false, updatable = false)
	private Answer answer;

	private String sod_vybral;
	private Integer sod_poradie;

	public StudentAnswer() {

	}

	public StudentAnswer(Long sod_ste_id, Integer sod_ot_id, Integer sod_od_id, String sod_vybral,
			Integer sod_poradie) {
		this.sod_ste_id = sod_ste_id;
		this.sod_ot_id = sod_ot_id;
		this.sod_od_id = sod_od_id;
		this.sod_vybral = sod_vybral;
		this.sod_poradie = sod_poradie;
	}

	public Long getSod_ste_id() {
		return sod_ste_id;
	}

	public void setSod_ste_id(Long sod_ste_id) {
		this.sod_ste_id = sod_ste_id;
	}

	public Integer getSod_ot_id() {
		return sod_ot_id;
	}

	public void setSod_ot_id(Integer sod_ot_id) {
		this.sod_ot_id = sod_ot_id;
	}

	public Integer getSod_od_id() {
		return sod_od_id;
	}

	public void setSod_od_id(Integer sod_od_id) {
		this.sod_od_id = sod_od_id;
	}

	public String getSod_vybral() {
		return sod_vybral;
	}

	public void setSod_vybral(String sod_vybral) {
		this.sod_vybral = sod_vybral;
	}

	public Integer getSod_poradie() {
		return sod_poradie;
	}

	public void setSod_poradie(Integer sod_poradie) {
		this.sod_poradie = sod_poradie;
	}

	public StudentQuestion getStudentQuestion() {
		return studentQuestion;
	}

	public void setStudentQuestion(StudentQuestion studentQuestion) {
		this.studentQuestion = studentQuestion;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sod_od_id == null) ? 0 : sod_od_id.hashCode());
		result = prime * result + ((sod_ot_id == null) ? 0 : sod_ot_id.hashCode());
		result = prime * result + ((sod_poradie == null) ? 0 : sod_poradie.hashCode());
		result = prime * result + ((sod_ste_id == null) ? 0 : sod_ste_id.hashCode());
		result = prime * result + ((sod_vybral == null) ? 0 : sod_vybral.hashCode());
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
		StudentAnswer other = (StudentAnswer) obj;
		if (sod_od_id == null) {
			if (other.sod_od_id != null)
				return false;
		} else if (!sod_od_id.equals(other.sod_od_id))
			return false;
		if (sod_ot_id == null) {
			if (other.sod_ot_id != null)
				return false;
		} else if (!sod_ot_id.equals(other.sod_ot_id))
			return false;
		if (sod_poradie == null) {
			if (other.sod_poradie != null)
				return false;
		} else if (!sod_poradie.equals(other.sod_poradie))
			return false;
		if (sod_ste_id == null) {
			if (other.sod_ste_id != null)
				return false;
		} else if (!sod_ste_id.equals(other.sod_ste_id))
			return false;
		if (sod_vybral == null) {
			if (other.sod_vybral != null)
				return false;
		} else if (!sod_vybral.equals(other.sod_vybral))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentAnswer [sod_ste_id=" + sod_ste_id + ", sod_ot_id=" + sod_ot_id + ", sod_od_id=" + sod_od_id
				+ ", sod_vybral=" + sod_vybral + ", sod_poradie=" + sod_poradie + "]";
	}

}
