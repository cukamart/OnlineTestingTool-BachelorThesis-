package sk.uniza.fri.cuka.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

@Entity
@Table(name = "predm_test")
public class SubjectTest {

	@Id
	@SequenceGenerator(name = "postgreGenerator", sequenceName = "predm_test_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "postgreGenerator")
	private long id;

	@ManyToOne
	@JoinColumn(name = "pr_id", insertable = false, updatable = false)
	private Subject subject;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectTest")
	private List<Test> tests = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "subjectTests")
	private List<Test> testsMany = new ArrayList<>();

	@NotNull(message = "Zadajte školský rok")
	@Min(value = 2000, message = "Školský rok môže byť min 2000")
	private Integer skrok;

	@NotNull(message = "Učiteľ nemá nastavený predmet (neučí žiaden predmet)")
	private String pr_id;

	@NotNull(message = "Zadajte názov testu")
	@Size(min = 3, max = 200, message = "Názov musí obsahovať minimálne 3 znaky")
	private String nazov;

	@NotNull(message = "Zadajte poradie testu")
	@Min(value = 0, message = "Zadajte kladné číslo")
	private Integer por;

	@Min(value = 0, message = "Zadajte kladné číslo")
	private Integer min;
	
	@Min(value = 0, message = "Zadajte kladné číslo")
	private Double penale;
	
	@NotNull(message = "Zadajte maximálny počet bodov pre test")
	@Min(value = 1, message = "Zadajte kladné číslo väčšie ako 0")
	private Integer max;
	
	private Integer te_type;
	private boolean sum = true;

	public SubjectTest() {

	}

	public SubjectTest(long id, Integer skrok, String pr_id, String nazov, Integer por, Integer min, boolean sum,
			Double penale, Integer te_typ, Integer max) {
		this.id = id;
		this.skrok = skrok;
		this.pr_id = pr_id;
		this.nazov = nazov;
		this.por = por;
		this.min = min;
		this.sum = sum;
		this.penale = penale;
		this.te_type = te_typ;
		this.max = max;
	}

	public SubjectTest(Integer skrok, String pr_id, String nazov, Integer por, Integer min, boolean sum, Double penale,
			Integer te_typ, Integer max) {
		this.skrok = skrok;
		this.pr_id = pr_id;
		this.nazov = nazov;
		this.por = por;
		this.min = min;
		this.sum = sum;
		this.penale = penale;
		this.te_type = te_typ;
		this.max = max;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<Test> getTestsMany() {
		return testsMany;
	}

	public void setTestsMany(List<Test> testsMany) {
		this.testsMany = testsMany;
	}

	public Integer getSkrok() {
		return skrok;
	}

	public void setSkrok(Integer skrok) {
		this.skrok = skrok;
	}

	public String getPr_id() {
		return pr_id;
	}

	public void setPr_id(String pr_id) {
		this.pr_id = pr_id;
	}

	public String getNazov() {
		return nazov;
	}

	public void setNazov(String nazov) {
		this.nazov = nazov;
	}

	public Integer getPor() {
		return por;
	}

	public void setPor(Integer por) {
		this.por = por;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public boolean isSum() {
		return sum;
	}

	public void setSum(boolean sum) {
		this.sum = sum;
	}

	public Double getPenale() {
		return penale;
	}

	public void setPenale(Double penale) {
		this.penale = penale;
	}

	public Integer getTe_type() {
		return te_type;
	}

	public void setTe_type(Integer te_type) {
		this.te_type = te_type;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
		result = prime * result + ((nazov == null) ? 0 : nazov.hashCode());
		result = prime * result + ((penale == null) ? 0 : penale.hashCode());
		result = prime * result + ((por == null) ? 0 : por.hashCode());
		result = prime * result + ((pr_id == null) ? 0 : pr_id.hashCode());
		result = prime * result + ((skrok == null) ? 0 : skrok.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + (sum ? 1231 : 1237);
		result = prime * result + ((te_type == null) ? 0 : te_type.hashCode());
		result = prime * result + ((tests == null) ? 0 : tests.hashCode());
		result = prime * result + ((testsMany == null) ? 0 : testsMany.hashCode());
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
		SubjectTest other = (SubjectTest) obj;
		if (max == null) {
			if (other.max != null)
				return false;
		} else if (!max.equals(other.max))
			return false;
		if (min == null) {
			if (other.min != null)
				return false;
		} else if (!min.equals(other.min))
			return false;
		if (nazov == null) {
			if (other.nazov != null)
				return false;
		} else if (!nazov.equals(other.nazov))
			return false;
		if (penale == null) {
			if (other.penale != null)
				return false;
		} else if (!penale.equals(other.penale))
			return false;
		if (por == null) {
			if (other.por != null)
				return false;
		} else if (!por.equals(other.por))
			return false;
		if (pr_id == null) {
			if (other.pr_id != null)
				return false;
		} else if (!pr_id.equals(other.pr_id))
			return false;
		if (skrok == null) {
			if (other.skrok != null)
				return false;
		} else if (!skrok.equals(other.skrok))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (sum != other.sum)
			return false;
		if (te_type == null) {
			if (other.te_type != null)
				return false;
		} else if (!te_type.equals(other.te_type))
			return false;
		if (tests == null) {
			if (other.tests != null)
				return false;
		} else if (!tests.equals(other.tests))
			return false;
		if (testsMany == null) {
			if (other.testsMany != null)
				return false;
		} else if (!testsMany.equals(other.testsMany))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubjectTest [id=" + id + ", skrok=" + skrok + ", pr_id=" + pr_id + ", nazov=" + nazov + ", por=" + por
				+ ", min=" + min + ", sum=" + sum + ", penale=" + penale + ", te_typ=" + te_type + ", max=" + max + "]";
	}

}
