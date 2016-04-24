package sk.uniza.fri.cuka.data.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "test")
public class Test {

	@Id
	@SequenceGenerator(name = "idTestGenerator", sequenceName = "te_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "idTestGenerator")
	private int te_id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "obsahuje", joinColumns = @JoinColumn(name = "ob_te_id", referencedColumnName = "te_id") , inverseJoinColumns = @JoinColumn(name = "ob_ot_id", referencedColumnName = "ot_id") )
	private List<Question> questions = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "predm_testy", joinColumns = @JoinColumn(name = "te_id") , inverseJoinColumns = @JoinColumn(name = "id") )
	private List<SubjectTest> subjectTests = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "te_pr_id", referencedColumnName = "pr_id", insertable = false, updatable = false)
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "te_typ", referencedColumnName = "id", insertable = false, updatable = false)
	private SubjectTest subjectTest;

	@OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
	private List<StudentTest> studentTests = new ArrayList<>();

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date te_datum_zac;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date te_datum_kon;

	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	private Date te_cas_zac;

	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	private Date te_cas_kon;

	@NotNull(message = "Zadajte názov testu")
	@Size(min = 3, message = "Názov testu musí obsahovať aspoň 3 znaky")
	private String te_nazov;

	private String te_pr_id;
	private String te_ip_min;
	private String te_ip_max;
	private Integer te_max_minut;
	private int te_spolu_otazok;
	private int te_moznych_odpov;
	private String te_pouz_skup_otazok;
	private int te_max_ot_skup;
	private String te_1_spravna;
	private String te_zapor_body;
	private String te_otazka_celok;
	private Integer te_pokusov;
	private String te_heslo = "password";
	private Boolean te_prerusit = false;
	private Boolean te_kontrola = false;
	private int skrok;
	private long te_typ;

	public Test() {

	}

	public Test(Date te_datum_zac, Date te_datum_kon, Date te_cas_zac, Date te_cas_kon, String te_pr_id,
			String te_nazov, String te_ip_min, String te_ip_max, Integer te_max_minut, int te_spolu_otazok,
			int te_moznych_odpov, String te_pouz_skup_otazok, int te_max_ot_skup, String te_1_spravna,
			String te_zapor_body, String te_otazka_celok, Integer te_pokusov, int skrok, long te_typ) {
		this.te_datum_zac = te_datum_zac;
		this.te_datum_kon = te_datum_kon;
		this.te_cas_zac = te_cas_zac;
		this.te_cas_kon = te_cas_kon;
		this.te_pr_id = te_pr_id;
		this.te_nazov = te_nazov;
		this.te_ip_min = te_ip_min;
		this.te_ip_max = te_ip_max;
		this.te_max_minut = te_max_minut;
		this.te_spolu_otazok = te_spolu_otazok;
		this.te_moznych_odpov = te_moznych_odpov;
		this.te_pouz_skup_otazok = te_pouz_skup_otazok;
		this.te_max_ot_skup = te_max_ot_skup;
		this.te_1_spravna = te_1_spravna;
		this.te_zapor_body = te_zapor_body;
		this.te_otazka_celok = te_otazka_celok;
		this.te_pokusov = te_pokusov;
		this.skrok = skrok;
		this.te_typ = te_typ;
	}

	public Test(Date te_datum_zac, Date te_datum_kon, Date te_cas_zac, Date te_cas_kon, String te_pr_id,
			String te_nazov, String te_ip_min, String te_ip_max, Integer te_max_minut, int te_spolu_otazok,
			int te_moznych_odpov, String te_pouz_skup_otazok, int te_max_ot_skup, String te_1_spravna,
			String te_zapor_body, String te_otazka_celok, Integer te_pokusov, Boolean te_prerusit, Boolean te_kontrola,
			int skrok, long te_typ) {
		this.te_datum_zac = te_datum_zac;
		this.te_datum_kon = te_datum_kon;
		this.te_cas_zac = te_cas_zac;
		this.te_cas_kon = te_cas_kon;
		this.te_pr_id = te_pr_id;
		this.te_nazov = te_nazov;
		this.te_ip_min = te_ip_min;
		this.te_ip_max = te_ip_max;
		this.te_max_minut = te_max_minut;
		this.te_spolu_otazok = te_spolu_otazok;
		this.te_moznych_odpov = te_moznych_odpov;
		this.te_pouz_skup_otazok = te_pouz_skup_otazok;
		this.te_max_ot_skup = te_max_ot_skup;
		this.te_1_spravna = te_1_spravna;
		this.te_zapor_body = te_zapor_body;
		this.te_otazka_celok = te_otazka_celok;
		this.te_pokusov = te_pokusov;
		this.te_prerusit = te_prerusit;
		this.te_kontrola = te_kontrola;
		this.skrok = skrok;
		this.te_typ = te_typ;
	}

	public Test(int te_id, Date te_datum_zac, Date te_datum_kon, Date te_cas_zac, Date te_cas_kon, String te_pr_id,
			String te_nazov, String te_ip_min, String te_ip_max, Integer te_max_minut, int te_spolu_otazok,
			int te_moznych_odpov, String te_pouz_skup_otazok, int te_max_ot_skup, String te_1_spravna,
			String te_zapor_body, String te_otazka_celok, Integer te_pokusov, Boolean te_prerusit, Boolean te_kontrola,
			int skrok, long te_typ) {
		this.te_id = te_id;
		this.te_datum_zac = te_datum_zac;
		this.te_datum_kon = te_datum_kon;
		this.te_cas_zac = te_cas_zac;
		this.te_cas_kon = te_cas_kon;
		this.te_pr_id = te_pr_id;
		this.te_nazov = te_nazov;
		this.te_ip_min = te_ip_min;
		this.te_ip_max = te_ip_max;
		this.te_max_minut = te_max_minut;
		this.te_spolu_otazok = te_spolu_otazok;
		this.te_moznych_odpov = te_moznych_odpov;
		this.te_pouz_skup_otazok = te_pouz_skup_otazok;
		this.te_max_ot_skup = te_max_ot_skup;
		this.te_1_spravna = te_1_spravna;
		this.te_zapor_body = te_zapor_body;
		this.te_otazka_celok = te_otazka_celok;
		this.te_pokusov = te_pokusov;
		this.te_prerusit = te_prerusit;
		this.te_kontrola = te_kontrola;
		this.skrok = skrok;
		this.te_typ = te_typ;
	}

	public Test(Date te_datum_zac, Date te_datum_kon, Date te_cas_zac, Date te_cas_kon, String te_pr_id,
			String te_nazov, String te_ip_min, String te_ip_max, Integer te_max_minut, int te_spolu_otazok,
			int te_moznych_odpov, String te_pouz_skup_otazok, int te_max_ot_skup, String te_1_spravna,
			String te_zapor_body, String te_otazka_celok, Integer te_pokusov, String te_heslo, Boolean te_prerusit,
			Boolean te_kontrola, int skrok, long te_typ) {
		this.te_datum_zac = te_datum_zac;
		this.te_datum_kon = te_datum_kon;
		this.te_cas_zac = te_cas_zac;
		this.te_cas_kon = te_cas_kon;
		this.te_pr_id = te_pr_id;
		this.te_nazov = te_nazov;
		this.te_ip_min = te_ip_min;
		this.te_ip_max = te_ip_max;
		this.te_max_minut = te_max_minut;
		this.te_spolu_otazok = te_spolu_otazok;
		this.te_moznych_odpov = te_moznych_odpov;
		this.te_pouz_skup_otazok = te_pouz_skup_otazok;
		this.te_max_ot_skup = te_max_ot_skup;
		this.te_1_spravna = te_1_spravna;
		this.te_zapor_body = te_zapor_body;
		this.te_otazka_celok = te_otazka_celok;
		this.te_pokusov = te_pokusov;
		this.te_heslo = te_heslo;
		this.te_prerusit = te_prerusit;
		this.te_kontrola = te_kontrola;
		this.skrok = skrok;
		this.te_typ = te_typ;
	}

	public int getTe_id() {
		return te_id;
	}

	public void setTe_id(int te_id) {
		this.te_id = te_id;
	}

	public Date getTe_datum_zac() {
		return te_datum_zac;
	}

	public void setTe_datum_zac(Date te_datum_zac) {
		this.te_datum_zac = te_datum_zac;
	}

	public Date getTe_datum_kon() {
		return te_datum_kon;
	}

	public void setTe_datum_kon(Date te_datum_kon) {
		this.te_datum_kon = te_datum_kon;
	}

	public Date getTe_cas_zac() {
		return te_cas_zac;
	}

	public void setTe_cas_zac(Date te_cas_zac) {
		this.te_cas_zac = te_cas_zac;
	}

	public Date getTe_cas_kon() {
		return te_cas_kon;
	}

	public void setTe_cas_kon(Date te_cas_kon) {
		this.te_cas_kon = te_cas_kon;
	}

	public String getTe_pr_id() {
		return te_pr_id;
	}

	public void setTe_pr_id(String te_pr_id) {
		this.te_pr_id = te_pr_id;
	}

	public String getTe_nazov() {
		return te_nazov;
	}

	public void setTe_nazov(String te_nazov) {
		this.te_nazov = te_nazov;
	}

	public String getTe_ip_min() {
		return te_ip_min;
	}

	public void setTe_ip_min(String te_ip_min) {
		this.te_ip_min = te_ip_min;
	}

	public String getTe_ip_max() {
		return te_ip_max;
	}

	public void setTe_ip_max(String te_ip_max) {
		this.te_ip_max = te_ip_max;
	}

	public Integer getTe_max_minut() {
		return te_max_minut;
	}

	public void setTe_max_minut(Integer te_max_minut) {
		this.te_max_minut = te_max_minut;
	}

	public int getTe_spolu_otazok() {
		return te_spolu_otazok;
	}

	public void setTe_spolu_otazok(int te_spolu_otazok) {
		this.te_spolu_otazok = te_spolu_otazok;
	}

	public int getTe_moznych_odpov() {
		return te_moznych_odpov;
	}

	public void setTe_moznych_odpov(int te_moznych_odpov) {
		this.te_moznych_odpov = te_moznych_odpov;
	}

	public String getTe_pouz_skup_otazok() {
		return te_pouz_skup_otazok;
	}

	public void setTe_pouz_skup_otazok(String te_pouz_skup_otazok) {
		this.te_pouz_skup_otazok = te_pouz_skup_otazok;
	}

	public int getTe_max_ot_skup() {
		return te_max_ot_skup;
	}

	public void setTe_max_ot_skup(int te_max_ot_skup) {
		this.te_max_ot_skup = te_max_ot_skup;
	}

	public String getTe_1_spravna() {
		return te_1_spravna;
	}

	public void setTe_1_spravna(String te_1_spravna) {
		this.te_1_spravna = te_1_spravna;
	}

	public String getTe_zapor_body() {
		return te_zapor_body;
	}

	public void setTe_zapor_body(String te_zapor_body) {
		this.te_zapor_body = te_zapor_body;
	}

	public String getTe_otazka_celok() {
		return te_otazka_celok;
	}

	public void setTe_otazka_celok(String te_otazka_celok) {
		this.te_otazka_celok = te_otazka_celok;
	}

	public Integer getTe_pokusov() {
		return te_pokusov;
	}

	public void setTe_pokusov(Integer te_pokusov) {
		this.te_pokusov = te_pokusov;
	}

	public Boolean getTe_prerusit() {
		return te_prerusit;
	}

	public void setTe_prerusit(Boolean te_prerusit) {
		this.te_prerusit = te_prerusit;
	}

	public Boolean getTe_kontrola() {
		return te_kontrola;
	}

	public void setTe_kontrola(Boolean te_kontrola) {
		this.te_kontrola = te_kontrola;
	}

	public int getSkrok() {
		return skrok;
	}

	public void setSkrok(int skrok) {
		this.skrok = skrok;
	}

	public long getTe_typ() {
		return te_typ;
	}

	public void setTe_typ(long te_typ) {
		this.te_typ = te_typ;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public SubjectTest getSubjectTest() {
		return subjectTest;
	}

	public void setSubjectTest(SubjectTest subjectTest) {
		this.subjectTest = subjectTest;
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

	public String getTe_heslo() {
		return te_heslo;
	}

	public void setTe_heslo(String te_heslo) {
		this.te_heslo = te_heslo;
	}

	public List<StudentTest> getStudentTests() {
		return studentTests;
	}

	public void setStudentTests(List<StudentTest> studentTests) {
		this.studentTests = studentTests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + skrok;
		result = prime * result + ((te_1_spravna == null) ? 0 : te_1_spravna.hashCode());
		result = prime * result + ((te_cas_kon == null) ? 0 : te_cas_kon.hashCode());
		result = prime * result + ((te_cas_zac == null) ? 0 : te_cas_zac.hashCode());
		result = prime * result + ((te_datum_kon == null) ? 0 : te_datum_kon.hashCode());
		result = prime * result + ((te_datum_zac == null) ? 0 : te_datum_zac.hashCode());
		result = prime * result + ((te_heslo == null) ? 0 : te_heslo.hashCode());
		result = prime * result + ((te_ip_max == null) ? 0 : te_ip_max.hashCode());
		result = prime * result + ((te_ip_min == null) ? 0 : te_ip_min.hashCode());
		result = prime * result + ((te_kontrola == null) ? 0 : te_kontrola.hashCode());
		result = prime * result + ((te_max_minut == null) ? 0 : te_max_minut.hashCode());
		result = prime * result + te_max_ot_skup;
		result = prime * result + te_moznych_odpov;
		result = prime * result + ((te_nazov == null) ? 0 : te_nazov.hashCode());
		result = prime * result + ((te_otazka_celok == null) ? 0 : te_otazka_celok.hashCode());
		result = prime * result + ((te_pokusov == null) ? 0 : te_pokusov.hashCode());
		result = prime * result + ((te_pouz_skup_otazok == null) ? 0 : te_pouz_skup_otazok.hashCode());
		result = prime * result + ((te_pr_id == null) ? 0 : te_pr_id.hashCode());
		result = prime * result + ((te_prerusit == null) ? 0 : te_prerusit.hashCode());
		result = prime * result + te_spolu_otazok;
		result = prime * result + (int) (te_typ ^ (te_typ >>> 32));
		result = prime * result + ((te_zapor_body == null) ? 0 : te_zapor_body.hashCode());
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
		Test other = (Test) obj;
		if (skrok != other.skrok)
			return false;
		if (te_1_spravna == null) {
			if (other.te_1_spravna != null)
				return false;
		} else if (!te_1_spravna.equals(other.te_1_spravna))
			return false;
		if (te_cas_kon == null) {
			if (other.te_cas_kon != null)
				return false;
		} else if (!te_cas_kon.equals(other.te_cas_kon))
			return false;
		if (te_cas_zac == null) {
			if (other.te_cas_zac != null)
				return false;
		} else if (!te_cas_zac.equals(other.te_cas_zac))
			return false;
		if (te_datum_kon == null) {
			if (other.te_datum_kon != null)
				return false;
		} else if (!te_datum_kon.equals(other.te_datum_kon))
			return false;
		if (te_datum_zac == null) {
			if (other.te_datum_zac != null)
				return false;
		} else if (!te_datum_zac.equals(other.te_datum_zac))
			return false;
		if (te_heslo == null) {
			if (other.te_heslo != null)
				return false;
		} else if (!te_heslo.equals(other.te_heslo))
			return false;
		if (te_ip_max == null) {
			if (other.te_ip_max != null)
				return false;
		} else if (!te_ip_max.equals(other.te_ip_max))
			return false;
		if (te_ip_min == null) {
			if (other.te_ip_min != null)
				return false;
		} else if (!te_ip_min.equals(other.te_ip_min))
			return false;
		if (te_kontrola == null) {
			if (other.te_kontrola != null)
				return false;
		} else if (!te_kontrola.equals(other.te_kontrola))
			return false;
		if (te_max_minut == null) {
			if (other.te_max_minut != null)
				return false;
		} else if (!te_max_minut.equals(other.te_max_minut))
			return false;
		if (te_max_ot_skup != other.te_max_ot_skup)
			return false;
		if (te_moznych_odpov != other.te_moznych_odpov)
			return false;
		if (te_nazov == null) {
			if (other.te_nazov != null)
				return false;
		} else if (!te_nazov.equals(other.te_nazov))
			return false;
		if (te_otazka_celok == null) {
			if (other.te_otazka_celok != null)
				return false;
		} else if (!te_otazka_celok.equals(other.te_otazka_celok))
			return false;
		if (te_pokusov == null) {
			if (other.te_pokusov != null)
				return false;
		} else if (!te_pokusov.equals(other.te_pokusov))
			return false;
		if (te_pouz_skup_otazok == null) {
			if (other.te_pouz_skup_otazok != null)
				return false;
		} else if (!te_pouz_skup_otazok.equals(other.te_pouz_skup_otazok))
			return false;
		if (te_pr_id == null) {
			if (other.te_pr_id != null)
				return false;
		} else if (!te_pr_id.equals(other.te_pr_id))
			return false;
		if (te_prerusit == null) {
			if (other.te_prerusit != null)
				return false;
		} else if (!te_prerusit.equals(other.te_prerusit))
			return false;
		if (te_spolu_otazok != other.te_spolu_otazok)
			return false;
		if (te_typ != other.te_typ)
			return false;
		if (te_zapor_body == null) {
			if (other.te_zapor_body != null)
				return false;
		} else if (!te_zapor_body.equals(other.te_zapor_body))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Test [questions=" + questions + ", subjectTests=" + subjectTests + ", subject=" + subject
				+ ", subjectTest=" + subjectTest + ", te_datum_zac=" + te_datum_zac + ", te_datum_kon=" + te_datum_kon
				+ ", te_cas_zac=" + te_cas_zac + ", te_cas_kon=" + te_cas_kon + ", te_pr_id=" + te_pr_id + ", te_nazov="
				+ te_nazov + ", te_ip_min=" + te_ip_min + ", te_ip_max=" + te_ip_max + ", te_max_minut=" + te_max_minut
				+ ", te_spolu_otazok=" + te_spolu_otazok + ", te_moznych_odpov=" + te_moznych_odpov
				+ ", te_pouz_skup_otazok=" + te_pouz_skup_otazok + ", te_max_ot_skup=" + te_max_ot_skup
				+ ", te_1_spravna=" + te_1_spravna + ", te_zapor_body=" + te_zapor_body + ", te_otazka_celok="
				+ te_otazka_celok + ", te_pokusov=" + te_pokusov + ", te_prerusit=" + te_prerusit + ", te_kontrola="
				+ te_kontrola + ", skrok=" + skrok + ", te_typ=" + te_typ + "]";
	}

}
