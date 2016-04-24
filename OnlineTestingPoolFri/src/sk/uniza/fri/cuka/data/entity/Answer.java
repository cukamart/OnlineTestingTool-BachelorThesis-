package sk.uniza.fri.cuka.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "odpoved")
public class Answer {

	@Id
	@SequenceGenerator(name = "myod_id_seq", sequenceName = "od_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "myod_id_seq")
	private int od_id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "od_sk_id", referencedColumnName = "sko_id", insertable = false, updatable = false)
	private AnswerCategory answerCategory;

	@ManyToOne
	@JoinColumn(name = "od_ot_id", referencedColumnName = "ot_id", insertable = false, updatable = false)
	private Question question;

	@OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
	private List<StudentAnswer> studentAnswers = new ArrayList<>();

	@NotNull(message = "Zadajte znenie odpovede")
	@Size(min = 1, message = "Znenie odpovede musí obsahovať minimálne 1 znak")
	private String od_znenie;
	private int od_ot_id;
	private String od_aktivna;
	private int od_oprava;
	private int od_sk_id;
	private String od_obrazok_meno;
	private String od_obrazok_type;

	public Answer() {

	}

	public Answer(int od_id, String od_znenie, int od_ot_id, String od_aktivna, int od_oprava, int od_sk_id,
			String od_obrazok_meno, String od_obrazok_type) {
		this.od_id = od_id;
		this.od_znenie = od_znenie;
		this.od_ot_id = od_ot_id;
		this.od_aktivna = od_aktivna;
		this.od_oprava = od_oprava;
		this.od_sk_id = od_sk_id;
		this.od_obrazok_meno = od_obrazok_meno;
		this.od_obrazok_type = od_obrazok_type;
	}

	public Answer(String od_znenie, int od_ot_id, String od_aktivna, int od_oprava, int od_sk_id,
			String od_obrazok_meno, String od_obrazok_type) {
		this.od_znenie = od_znenie;
		this.od_ot_id = od_ot_id;
		this.od_aktivna = od_aktivna;
		this.od_oprava = od_oprava;
		this.od_sk_id = od_sk_id;
		this.od_obrazok_meno = od_obrazok_meno;
		this.od_obrazok_type = od_obrazok_type;
	}

	public int getOd_id() {
		return od_id;
	}

	public void setOd_id(int od_id) {
		this.od_id = od_id;
	}

	public String getOd_znenie() {
		return od_znenie;
	}

	public void setOd_znenie(String od_znenie) {
		this.od_znenie = od_znenie;
	}

	public int getOd_ot_id() {
		return od_ot_id;
	}

	public void setOd_ot_id(int od_ot_id) {
		this.od_ot_id = od_ot_id;
	}

	public String getOd_aktivna() {
		return od_aktivna;
	}

	public void setOd_aktivna(String od_aktivna) {
		this.od_aktivna = od_aktivna;
	}

	public int getOd_oprava() {
		return od_oprava;
	}

	public void setOd_oprava(int od_oprava) {
		this.od_oprava = od_oprava;
	}

	public int getOd_sk_id() {
		return od_sk_id;
	}

	public void setOd_sk_id(int od_sk_id) {
		this.od_sk_id = od_sk_id;
	}

	public String getOd_obrazok_meno() {
		return od_obrazok_meno;
	}

	public void setOd_obrazok_meno(String od_obrazok_meno) {
		this.od_obrazok_meno = od_obrazok_meno;
	}

	public String getOd_obrazok_type() {
		return od_obrazok_type;
	}

	public void setOd_obrazok_type(String od_obrazok_type) {
		this.od_obrazok_type = od_obrazok_type;
	}

	public AnswerCategory getAnswerCategory() {
		return answerCategory;
	}

	public void setAnswerCategory(AnswerCategory answerCategory) {
		this.answerCategory = answerCategory;
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
		result = prime * result + ((od_aktivna == null) ? 0 : od_aktivna.hashCode());
		result = prime * result + ((od_obrazok_meno == null) ? 0 : od_obrazok_meno.hashCode());
		result = prime * result + ((od_obrazok_type == null) ? 0 : od_obrazok_type.hashCode());
		result = prime * result + od_oprava;
		result = prime * result + od_ot_id;
		result = prime * result + od_sk_id;
		result = prime * result + ((od_znenie == null) ? 0 : od_znenie.hashCode());
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
		Answer other = (Answer) obj;
		if (od_aktivna == null) {
			if (other.od_aktivna != null)
				return false;
		} else if (!od_aktivna.equals(other.od_aktivna))
			return false;
		if (od_obrazok_meno == null) {
			if (other.od_obrazok_meno != null)
				return false;
		} else if (!od_obrazok_meno.equals(other.od_obrazok_meno))
			return false;
		if (od_obrazok_type == null) {
			if (other.od_obrazok_type != null)
				return false;
		} else if (!od_obrazok_type.equals(other.od_obrazok_type))
			return false;
		if (od_oprava != other.od_oprava)
			return false;
		if (od_ot_id != other.od_ot_id)
			return false;
		if (od_sk_id != other.od_sk_id)
			return false;
		if (od_znenie == null) {
			if (other.od_znenie != null)
				return false;
		} else if (!od_znenie.equals(other.od_znenie))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Answer [od_id=" + od_id + ", od_znenie=" + od_znenie + ", od_ot_id=" + od_ot_id + ", od_aktivna="
				+ od_aktivna + ", od_oprava=" + od_oprava + ", od_sk_id=" + od_sk_id + ", od_obrazok_meno="
				+ od_obrazok_meno + ", od_obrazok_type=" + od_obrazok_type + "]";
	}

}
