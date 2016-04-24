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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "otazka")
public class Question {

	@Id
	@SequenceGenerator(name = "myot_id_seq", sequenceName = "ot_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "myot_id_seq")
	private int ot_id;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
	private List<Answer> answers = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "ot_sk_id", referencedColumnName = "so_id", insertable = false, updatable = false)
	private QuestionCategory questionCategory;

	@ManyToOne
	@JoinColumn(name = "ot_pr_id", referencedColumnName = "pr_id", insertable = false, updatable = false)
	private Subject subject;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "questions")
	private List<Test> tests = new ArrayList<>();

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<StudentQuestion> studentQuestions = new ArrayList<>();

	private String ot_pr_id;
	private Integer ot_sk_id;

	@NotNull(message = "Zadajte znenie otázky")
	@Size(min = 3, message = "Znenie otázky musí obsahovať aspoň 3 znaky")
	private String ot_znenie;

	@NotNull(message = "Zadajte počet bodov za správnu odpoveď")
	@Min(value = 0, message = "Minimálny počet bodov za správnu odpoveď je 0")
	private Integer ot_body;

	@NotNull(message = "Zadajte typ otázky")
	@NotBlank(message = "Vyberte typ otázky")
	private String ot_typ;

	@NotNull(message = "Vyberte ku ktorému testu sa bude otázka generovať")
	private Long ot_pr_te_id;

	private String ot_aktivna;
	private Integer ot_oprava;
	private String ot_obrazok_meno;
	private String ot_obrazok_type;
	private String ot_model;

	public Question() {

	}

	public Question(String ot_znenie, String ot_pr_id, String ot_aktivna, Integer ot_oprava, String ot_typ,
			Integer ot_body, Integer ot_sk_id, String ot_obrazok_meno, String ot_obrazok_type, Long pom,
			String ot_model) {
		this.ot_znenie = ot_znenie;
		this.ot_pr_id = ot_pr_id;
		this.ot_aktivna = ot_aktivna;
		this.ot_oprava = ot_oprava;
		this.ot_typ = ot_typ;
		this.ot_body = ot_body;
		this.ot_sk_id = ot_sk_id;
		this.ot_obrazok_meno = ot_obrazok_meno;
		this.ot_obrazok_type = ot_obrazok_type;
		this.ot_pr_te_id = pom;
		this.ot_model = ot_model;
	}

	public Question(int ot_id, String ot_znenie, String ot_pr_id, String ot_aktivna, Integer ot_oprava, String ot_typ,
			Integer ot_body, Integer ot_sk_id, String ot_obrazok_meno, String ot_obrazok_type, Long pom,
			String ot_model) {
		this.ot_id = ot_id;
		this.ot_znenie = ot_znenie;
		this.ot_pr_id = ot_pr_id;
		this.ot_aktivna = ot_aktivna;
		this.ot_oprava = ot_oprava;
		this.ot_typ = ot_typ;
		this.ot_body = ot_body;
		this.ot_sk_id = ot_sk_id;
		this.ot_obrazok_meno = ot_obrazok_meno;
		this.ot_obrazok_type = ot_obrazok_type;
		this.ot_pr_te_id = pom;
		this.ot_model = ot_model;
	}

	public int getOt_id() {
		return ot_id;
	}

	public void setOt_id(int ot_id) {
		this.ot_id = ot_id;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public QuestionCategory getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(QuestionCategory questionCategory) {
		this.questionCategory = questionCategory;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public String getOt_pr_id() {
		return ot_pr_id;
	}

	public void setOt_pr_id(String ot_pr_id) {
		this.ot_pr_id = ot_pr_id;
	}

	public Integer getOt_sk_id() {
		return ot_sk_id;
	}

	public void setOt_sk_id(Integer ot_sk_id) {
		this.ot_sk_id = ot_sk_id;
	}

	public String getOt_znenie() {
		return ot_znenie;
	}

	public void setOt_znenie(String ot_znenie) {
		this.ot_znenie = ot_znenie;
	}

	public String getOt_aktivna() {
		return ot_aktivna;
	}

	public void setOt_aktivna(String ot_aktivna) {
		this.ot_aktivna = ot_aktivna;
	}

	public Integer getOt_oprava() {
		return ot_oprava;
	}

	public void setOt_oprava(Integer ot_oprava) {
		this.ot_oprava = ot_oprava;
	}

	public String getOt_typ() {
		return ot_typ;
	}

	public void setOt_typ(String ot_typ) {
		this.ot_typ = ot_typ;
	}

	public Integer getOt_body() {
		return ot_body;
	}

	public void setOt_body(Integer ot_body) {
		this.ot_body = ot_body;
	}

	public String getOt_obrazok_meno() {
		return ot_obrazok_meno;
	}

	public void setOt_obrazok_meno(String ot_obrazok_meno) {
		this.ot_obrazok_meno = ot_obrazok_meno;
	}

	public String getOt_obrazok_type() {
		return ot_obrazok_type;
	}

	public void setOt_obrazok_type(String ot_obrazok_type) {
		this.ot_obrazok_type = ot_obrazok_type;
	}

	public Long getOt_pr_te_id() {
		return ot_pr_te_id;
	}

	public void setOt_pr_te_id(Long ot_pr_te_id) {
		this.ot_pr_te_id = ot_pr_te_id;
	}

	public String getOt_model() {
		return ot_model;
	}

	public void setOt_model(String ot_model) {
		this.ot_model = ot_model;
	}

	public List<StudentQuestion> getStudentQuestions() {
		return studentQuestions;
	}

	public void setStudentQuestions(List<StudentQuestion> studentQuestions) {
		this.studentQuestions = studentQuestions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((ot_aktivna == null) ? 0 : ot_aktivna.hashCode());
		result = prime * result + ((ot_body == null) ? 0 : ot_body.hashCode());
		result = prime * result + ((ot_model == null) ? 0 : ot_model.hashCode());
		result = prime * result + ((ot_obrazok_meno == null) ? 0 : ot_obrazok_meno.hashCode());
		result = prime * result + ((ot_obrazok_type == null) ? 0 : ot_obrazok_type.hashCode());
		result = prime * result + ((ot_oprava == null) ? 0 : ot_oprava.hashCode());
		result = prime * result + ((ot_pr_id == null) ? 0 : ot_pr_id.hashCode());
		result = prime * result + ((ot_pr_te_id == null) ? 0 : ot_pr_te_id.hashCode());
		result = prime * result + ((ot_sk_id == null) ? 0 : ot_sk_id.hashCode());
		result = prime * result + ((ot_typ == null) ? 0 : ot_typ.hashCode());
		result = prime * result + ((ot_znenie == null) ? 0 : ot_znenie.hashCode());
		result = prime * result + ((questionCategory == null) ? 0 : questionCategory.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((tests == null) ? 0 : tests.hashCode());
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
		Question other = (Question) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (ot_aktivna == null) {
			if (other.ot_aktivna != null)
				return false;
		} else if (!ot_aktivna.equals(other.ot_aktivna))
			return false;
		if (ot_body == null) {
			if (other.ot_body != null)
				return false;
		} else if (!ot_body.equals(other.ot_body))
			return false;
		if (ot_model == null) {
			if (other.ot_model != null)
				return false;
		} else if (!ot_model.equals(other.ot_model))
			return false;
		if (ot_obrazok_meno == null) {
			if (other.ot_obrazok_meno != null)
				return false;
		} else if (!ot_obrazok_meno.equals(other.ot_obrazok_meno))
			return false;
		if (ot_obrazok_type == null) {
			if (other.ot_obrazok_type != null)
				return false;
		} else if (!ot_obrazok_type.equals(other.ot_obrazok_type))
			return false;
		if (ot_oprava == null) {
			if (other.ot_oprava != null)
				return false;
		} else if (!ot_oprava.equals(other.ot_oprava))
			return false;
		if (ot_pr_id == null) {
			if (other.ot_pr_id != null)
				return false;
		} else if (!ot_pr_id.equals(other.ot_pr_id))
			return false;
		if (ot_pr_te_id == null) {
			if (other.ot_pr_te_id != null)
				return false;
		} else if (!ot_pr_te_id.equals(other.ot_pr_te_id))
			return false;
		if (ot_sk_id == null) {
			if (other.ot_sk_id != null)
				return false;
		} else if (!ot_sk_id.equals(other.ot_sk_id))
			return false;
		if (ot_typ == null) {
			if (other.ot_typ != null)
				return false;
		} else if (!ot_typ.equals(other.ot_typ))
			return false;
		if (ot_znenie == null) {
			if (other.ot_znenie != null)
				return false;
		} else if (!ot_znenie.equals(other.ot_znenie))
			return false;
		if (questionCategory == null) {
			if (other.questionCategory != null)
				return false;
		} else if (!questionCategory.equals(other.questionCategory))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (tests == null) {
			if (other.tests != null)
				return false;
		} else if (!tests.equals(other.tests))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [answers=" + answers + ", questionCategory=" + questionCategory + ", subject=" + subject
				+ ", tests=" + tests + ", ot_pr_id=" + ot_pr_id + ", ot_sk_id=" + ot_sk_id + ", ot_znenie=" + ot_znenie
				+ ", ot_aktivna=" + ot_aktivna + ", ot_oprava=" + ot_oprava + ", ot_typ=" + ot_typ + ", ot_body="
				+ ot_body + ", ot_obrazok_meno=" + ot_obrazok_meno + ", ot_obrazok_type=" + ot_obrazok_type + ", pom="
				+ ot_pr_te_id + ", ot_model=" + ot_model + "]";
	}

}
