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
@Table(name = "skup_odp")
public class AnswerCategory {

	@Id
	@SequenceGenerator(name = "mysko_id_seq", sequenceName = "sko_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "mysko_id_seq")
	private int sko_id;
	
	//fetch = FetchType.EAGER, 
	@OneToMany(mappedBy = "answerCategory", cascade = CascadeType.ALL)
	private List<Answer> answers = new ArrayList<>();

	private String sko_nazov;

	public AnswerCategory() {

	}

	public AnswerCategory(String sko_nazov) {
		this.sko_nazov = sko_nazov;
	}

	public AnswerCategory(int sko_id, String sko_nazov) {
		this.sko_id = sko_id;
		this.sko_nazov = sko_nazov;
	}

	public int getSko_id() {
		return sko_id;
	}

	public void setSko_id(int sko_id) {
		this.sko_id = sko_id;
	}

	public String getSko_nazov() {
		return sko_nazov;
	}

	public void setSko_nazov(String sko_nazov) {
		this.sko_nazov = sko_nazov;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sko_nazov == null) ? 0 : sko_nazov.hashCode());
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
		AnswerCategory other = (AnswerCategory) obj;
		if (sko_nazov == null) {
			if (other.sko_nazov != null)
				return false;
		} else if (!sko_nazov.equals(other.sko_nazov))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AnswerCategory [sko_id=" + sko_id + ", sko_nazov=" + sko_nazov + "]";
	}
}
