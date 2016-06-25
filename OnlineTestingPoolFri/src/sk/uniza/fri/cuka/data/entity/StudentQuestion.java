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

import sk.uniza.fri.cuka.data.entity.ids.StudentQuestionId;

@Entity
@IdClass(StudentQuestionId.class)
@Table(name = "stud_otazka")
public class StudentQuestion {

	@Id
	@Column(name = "sot_ste_id")
	private Long sot_ste_id;

	@Id
	@Column(name = "sot_ot_id")
	private Integer sot_ot_id;

	@ManyToOne
	@JoinColumn(name = "sot_ste_id", referencedColumnName = "ste_id", insertable = false, updatable = false)
	private StudentTest studentTest;

	@ManyToOne
	@JoinColumn(name = "sot_ot_id", referencedColumnName = "ot_id", insertable = false, updatable = false)
	private Question question;

	@OneToMany(mappedBy = "studentQuestion", cascade = CascadeType.ALL)
	private List<StudentAnswer> studentAnswers = new ArrayList<>();

	private Integer sot_body = 0;
	private Integer sot_poradie;
	private String sot_textodpoved;
	private Integer sot_body_new = 0;
	private String sot_uc_text;
	private Integer sot_review = 0;
	private Integer sot_zodpovedana = 0;

	public StudentQuestion() {

	}

	public StudentQuestion(Long sot_ste_id, Integer sot_ot_id, Integer sot_body, Integer sot_poradie,
			String sot_textodpoved, Integer sot_body_new, String sot_uc_text, Integer sot_review,
			Integer sot_zodpovedana) {
		this.sot_ste_id = sot_ste_id;
		this.sot_ot_id = sot_ot_id;
		this.sot_body = sot_body;
		this.sot_poradie = sot_poradie;
		this.sot_textodpoved = sot_textodpoved;
		this.sot_body_new = sot_body_new;
		this.sot_uc_text = sot_uc_text;
		this.sot_review = sot_review;
		this.sot_zodpovedana = sot_zodpovedana;
	}

	public Long getSot_ste_id() {
		return sot_ste_id;
	}

	public void setSot_ste_id(Long sot_ste_id) {
		this.sot_ste_id = sot_ste_id;
	}

	public Integer getSot_ot_id() {
		return sot_ot_id;
	}

	public void setSot_ot_id(Integer sot_ot_id) {
		this.sot_ot_id = sot_ot_id;
	}

	public Integer getSot_body() {
		return sot_body;
	}

	public void setSot_body(Integer sot_body) {
		this.sot_body = sot_body;
	}

	public Integer getSot_poradie() {
		return sot_poradie;
	}

	public void setSot_poradie(Integer sot_poradie) {
		this.sot_poradie = sot_poradie;
	}

	public String getSot_textodpoved() {
		return sot_textodpoved;
	}

	public void setSot_textodpoved(String sot_textodpoved) {
		this.sot_textodpoved = sot_textodpoved;
	}

	public Integer getSot_body_new() {
		return sot_body_new;
	}

	public void setSot_body_new(Integer sot_body_new) {
		this.sot_body_new = sot_body_new;
	}

	public String getSot_uc_text() {
		return sot_uc_text;
	}

	public void setSot_uc_text(String sot_uc_text) {
		this.sot_uc_text = sot_uc_text;
	}

	public Integer getSot_review() {
		return sot_review;
	}

	public void setSot_review(Integer sot_review) {
		this.sot_review = sot_review;
	}

	public Integer getSot_zodpovedana() {
		return sot_zodpovedana;
	}

	public void setSot_zodpovedana(Integer sot_zodpovedana) {
		this.sot_zodpovedana = sot_zodpovedana;
	}

	public StudentTest getStudentTest() {
		return studentTest;
	}

	public void setStudentTest(StudentTest studentTest) {
		this.studentTest = studentTest;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<StudentAnswer> getStudentAnswers() {
		return studentAnswers;
	}

	public void setStudentAnswers(List<StudentAnswer> studentAnswers) {
		this.studentAnswers = studentAnswers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sot_body == null) ? 0 : sot_body.hashCode());
		result = prime * result + ((sot_body_new == null) ? 0 : sot_body_new.hashCode());
		result = prime * result + ((sot_ot_id == null) ? 0 : sot_ot_id.hashCode());
		result = prime * result + ((sot_poradie == null) ? 0 : sot_poradie.hashCode());
		result = prime * result + ((sot_review == null) ? 0 : sot_review.hashCode());
		result = prime * result + ((sot_ste_id == null) ? 0 : sot_ste_id.hashCode());
		result = prime * result + ((sot_textodpoved == null) ? 0 : sot_textodpoved.hashCode());
		result = prime * result + ((sot_uc_text == null) ? 0 : sot_uc_text.hashCode());
		result = prime * result + ((sot_zodpovedana == null) ? 0 : sot_zodpovedana.hashCode());
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
		StudentQuestion other = (StudentQuestion) obj;
		if (sot_body == null) {
			if (other.sot_body != null)
				return false;
		} else if (!sot_body.equals(other.sot_body))
			return false;
		if (sot_body_new == null) {
			if (other.sot_body_new != null)
				return false;
		} else if (!sot_body_new.equals(other.sot_body_new))
			return false;
		if (sot_ot_id == null) {
			if (other.sot_ot_id != null)
				return false;
		} else if (!sot_ot_id.equals(other.sot_ot_id))
			return false;
		if (sot_poradie == null) {
			if (other.sot_poradie != null)
				return false;
		} else if (!sot_poradie.equals(other.sot_poradie))
			return false;
		if (sot_review == null) {
			if (other.sot_review != null)
				return false;
		} else if (!sot_review.equals(other.sot_review))
			return false;
		if (sot_ste_id == null) {
			if (other.sot_ste_id != null)
				return false;
		} else if (!sot_ste_id.equals(other.sot_ste_id))
			return false;
		if (sot_textodpoved == null) {
			if (other.sot_textodpoved != null)
				return false;
		} else if (!sot_textodpoved.equals(other.sot_textodpoved))
			return false;
		if (sot_uc_text == null) {
			if (other.sot_uc_text != null)
				return false;
		} else if (!sot_uc_text.equals(other.sot_uc_text))
			return false;
		if (sot_zodpovedana == null) {
			if (other.sot_zodpovedana != null)
				return false;
		} else if (!sot_zodpovedana.equals(other.sot_zodpovedana))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentQuestion [sot_ste_id=" + sot_ste_id + ", sot_ot_id=" + sot_ot_id + ", sot_body=" + sot_body
				+ ", sot_poradie=" + sot_poradie + ", sot_textodpoved=" + sot_textodpoved + ", sot_body_new="
				+ sot_body_new + ", sot_uc_text=" + sot_uc_text + ", sot_review=" + sot_review + ", sot_zodpovedana="
				+ sot_zodpovedana + "]";
	}

}
