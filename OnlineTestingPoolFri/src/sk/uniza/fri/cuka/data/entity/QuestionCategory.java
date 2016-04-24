package sk.uniza.fri.cuka.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "skup_ot")
public class QuestionCategory {

	@Id
	@SequenceGenerator(name = "myso_id_seq", sequenceName = "so_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "myso_id_seq")
	private int so_id;
	
	@OneToMany(mappedBy = "questionCategory", cascade = CascadeType.ALL)
	private List<Question> questions = new ArrayList<>();
	
	private String so_nazov;
	private String so_pr_id;
	private String so_model;
	private String so_pouzi_model;
	private int so_body;
	private String so_popis;
	private int so_typ_testu;
	private String so_skupina;

	public QuestionCategory() {

	}

	public QuestionCategory(String so_nazov, String so_pr_id, String so_model, String so_pouzi_model, int body,
			String so_popis, int so_typ_testu, String so_skupina) {
		this.so_nazov = so_nazov;
		this.so_pr_id = so_pr_id;
		this.so_model = so_model;
		this.so_pouzi_model = so_pouzi_model;
		this.so_body = body;
		this.so_popis = so_popis;
		this.so_typ_testu = so_typ_testu;
		this.so_skupina = so_skupina;
	}

	public QuestionCategory(int so_id, String so_nazov, String so_pr_id, String so_model, String so_pouzi_model,
			int body, String so_popis, int so_typ_testu, String so_skupina) {
		this.so_id = so_id;
		this.so_nazov = so_nazov;
		this.so_pr_id = so_pr_id;
		this.so_model = so_model;
		this.so_pouzi_model = so_pouzi_model;
		this.so_body = body;
		this.so_popis = so_popis;
		this.so_typ_testu = so_typ_testu;
		this.so_skupina = so_skupina;
	}

	public int getSo_id() {
		return so_id;
	}

	public void setSo_id(int so_id) {
		this.so_id = so_id;
	}

	public String getSo_nazov() {
		return so_nazov;
	}

	public void setSo_nazov(String so_nazov) {
		this.so_nazov = so_nazov;
	}

	public String getSo_pr_id() {
		return so_pr_id;
	}

	public void setSo_pr_id(String so_pr_id) {
		this.so_pr_id = so_pr_id;
	}

	public String getSo_model() {
		return so_model;
	}

	public void setSo_model(String so_model) {
		this.so_model = so_model;
	}

	public String getSo_pouzi_model() {
		return so_pouzi_model;
	}

	public void setSo_pouzi_model(String so_pouzi_model) {
		this.so_pouzi_model = so_pouzi_model;
	}

	public int getBody() {
		return so_body;
	}

	public void setBody(int body) {
		this.so_body = body;
	}

	public String getSo_popis() {
		return so_popis;
	}

	public void setSo_popis(String so_popis) {
		this.so_popis = so_popis;
	}

	public int getSo_typ_testu() {
		return so_typ_testu;
	}

	public void setSo_typ_testu(int so_typ_testu) {
		this.so_typ_testu = so_typ_testu;
	}

	public String getSo_skupina() {
		return so_skupina;
	}

	public void setSo_skupina(String so_skupina) {
		this.so_skupina = so_skupina;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + so_body;
		result = prime * result + ((so_model == null) ? 0 : so_model.hashCode());
		result = prime * result + ((so_nazov == null) ? 0 : so_nazov.hashCode());
		result = prime * result + ((so_popis == null) ? 0 : so_popis.hashCode());
		result = prime * result + ((so_pouzi_model == null) ? 0 : so_pouzi_model.hashCode());
		result = prime * result + ((so_pr_id == null) ? 0 : so_pr_id.hashCode());
		result = prime * result + ((so_skupina == null) ? 0 : so_skupina.hashCode());
		result = prime * result + so_typ_testu;
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
		QuestionCategory other = (QuestionCategory) obj;
		if (so_body != other.so_body)
			return false;
		if (so_model == null) {
			if (other.so_model != null)
				return false;
		} else if (!so_model.equals(other.so_model))
			return false;
		if (so_nazov == null) {
			if (other.so_nazov != null)
				return false;
		} else if (!so_nazov.equals(other.so_nazov))
			return false;
		if (so_popis == null) {
			if (other.so_popis != null)
				return false;
		} else if (!so_popis.equals(other.so_popis))
			return false;
		if (so_pouzi_model == null) {
			if (other.so_pouzi_model != null)
				return false;
		} else if (!so_pouzi_model.equals(other.so_pouzi_model))
			return false;
		if (so_pr_id == null) {
			if (other.so_pr_id != null)
				return false;
		} else if (!so_pr_id.equals(other.so_pr_id))
			return false;
		if (so_skupina == null) {
			if (other.so_skupina != null)
				return false;
		} else if (!so_skupina.equals(other.so_skupina))
			return false;
		if (so_typ_testu != other.so_typ_testu)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QuestionCategory [so_id=" + so_id + ", so_nazov=" + so_nazov + ", so_pr_id=" + so_pr_id + ", so_model="
				+ so_model + ", so_pouzi_model=" + so_pouzi_model + ", body=" + so_body + ", so_popis=" + so_popis
				+ ", so_typ_testu=" + so_typ_testu + ", so_skupina=" + so_skupina + "]";
	}

}
