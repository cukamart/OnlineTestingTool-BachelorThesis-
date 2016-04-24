package sk.uniza.fri.cuka.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "predmet")
public class Subject {

	@Id
	private String pr_id;

	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private List<Manage> manage = new ArrayList<>();

	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private List<Question> questions = new ArrayList<>();

	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private List<SubjectTest> subjectTests = new ArrayList<>();

	@OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
	private List<Test> tests = new ArrayList<>();

	private String pr_nazov;
	private String pr_skratka;
	private String pr_nazov_eng;
	private String stav;

	public Subject(String pr_id, String pr_nazov, String pr_skratka, String pr_nazov_eng, String stav) {
		this.pr_id = pr_id;
		this.pr_nazov = pr_nazov;
		this.pr_skratka = pr_skratka;
		this.pr_nazov_eng = pr_nazov_eng;
		this.stav = stav;
	}

	public Subject(String pr_nazov, String pr_skratka, String pr_nazov_eng, String stav) {
		this.pr_nazov = pr_nazov;
		this.pr_skratka = pr_skratka;
		this.pr_nazov_eng = pr_nazov_eng;
		this.stav = stav;
	}

	public Subject() {

	}

	public String getPr_id() {
		return pr_id;
	}

	public void setPr_id(String pr_id) {
		this.pr_id = pr_id;
	}

	public String getPr_nazov() {
		return pr_nazov;
	}

	public void setPr_nazov(String pr_nazov) {
		this.pr_nazov = pr_nazov;
	}

	public String getPr_skratka() {
		return pr_skratka;
	}

	public void setPr_skratka(String pr_skratka) {
		this.pr_skratka = pr_skratka;
	}

	public String getPr_nazov_eng() {
		return pr_nazov_eng;
	}

	public void setPr_nazov_eng(String pr_nazov_eng) {
		this.pr_nazov_eng = pr_nazov_eng;
	}

	public String getStav() {
		return stav;
	}

	public void setStav(String stav) {
		this.stav = stav;
	}

	public List<Manage> getManage() {
		return manage;
	}

	public void setManage(List<Manage> manage) {
		this.manage = manage;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<SubjectTest> getSubjectTests() {
		return subjectTests;
	}

	public void setSubjectTests(List<SubjectTest> subjectTests) {
		this.subjectTests = subjectTests;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pr_id == null) ? 0 : pr_id.hashCode());
		result = prime * result + ((pr_nazov == null) ? 0 : pr_nazov.hashCode());
		result = prime * result + ((pr_nazov_eng == null) ? 0 : pr_nazov_eng.hashCode());
		result = prime * result + ((pr_skratka == null) ? 0 : pr_skratka.hashCode());
		result = prime * result + ((stav == null) ? 0 : stav.hashCode());
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
		Subject other = (Subject) obj;
		if (pr_id == null) {
			if (other.pr_id != null)
				return false;
		} else if (!pr_id.equals(other.pr_id))
			return false;
		if (pr_nazov == null) {
			if (other.pr_nazov != null)
				return false;
		} else if (!pr_nazov.equals(other.pr_nazov))
			return false;
		if (pr_nazov_eng == null) {
			if (other.pr_nazov_eng != null)
				return false;
		} else if (!pr_nazov_eng.equals(other.pr_nazov_eng))
			return false;
		if (pr_skratka == null) {
			if (other.pr_skratka != null)
				return false;
		} else if (!pr_skratka.equals(other.pr_skratka))
			return false;
		if (stav == null) {
			if (other.stav != null)
				return false;
		} else if (!stav.equals(other.stav))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subject [pr_id=" + pr_id + ", pr_nazov=" + pr_nazov + ", pr_skratka=" + pr_skratka + ", pr_nazov_eng="
				+ pr_nazov_eng + ", stav=" + stav + "]";
	}

}
