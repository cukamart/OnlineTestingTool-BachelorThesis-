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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "stud_test")
public class StudentTest {

	@Id
	@SequenceGenerator(name = "sTestGenerator", sequenceName = "ste_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sTestGenerator")
	private Long ste_id;

	@ManyToOne
	@JoinColumn(name = "ste_st_id", referencedColumnName = "st_id", insertable = false, updatable = false)
	private Student student;

	@ManyToOne
	@JoinColumn(name = "ste_te_id", referencedColumnName = "te_id", insertable = false, updatable = false)
	private Test test;

	@OneToMany(mappedBy = "studentTest", cascade = CascadeType.ALL)
	private List<StudentQuestion> studentQuestions = new ArrayList<>();

	@Temporal(TemporalType.TIMESTAMP)
	private Date ste_zaciatok;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ste_koniec;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ste_datum_zm;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ste_zaciatok_sk;

	private Long ste_st_id;
	private Integer ste_te_id;
	private Integer ste_skrok;
	private Integer ste_vysledok;
	private Integer ste_plnypocet;
	private String ste_zverejnit = "N";
	private String ste_vypracovany = "N";
	private boolean ste_kontrola = false;

	public StudentTest() {
	}

	public StudentTest(Long ste_st_id, Integer ste_te_id, Integer skrok, Date ste_zaciatok, Date ste_koniec,
			Integer ste_vysledok, Integer ste_plnypocet, String ste_zverejnit, String ste_vypracovany,
			boolean ste_kontrola, Date ste_datum_zm, Date ste_zaciatok_sk) {
		this.ste_st_id = ste_st_id;
		this.ste_te_id = ste_te_id;
		this.ste_skrok = skrok;
		this.ste_zaciatok = ste_zaciatok;
		this.ste_koniec = ste_koniec;
		this.ste_vysledok = ste_vysledok;
		this.ste_plnypocet = ste_plnypocet;
		this.ste_zverejnit = ste_zverejnit;
		this.ste_vypracovany = ste_vypracovany;
		this.ste_kontrola = ste_kontrola;
		this.ste_datum_zm = ste_datum_zm;
		this.ste_zaciatok_sk = ste_zaciatok_sk;
	}

	public StudentTest(Long ste_id, Long ste_st_id, Integer ste_te_id, Integer skrok, Date ste_zaciatok,
			Date ste_koniec, Integer ste_vysledok, Integer ste_plnypocet, String ste_zverejnit, String ste_vypracovany,
			boolean ste_kontrola, Date ste_datum_zm, Date ste_zaciatok_sk) {
		this.ste_id = ste_id;
		this.ste_st_id = ste_st_id;
		this.ste_te_id = ste_te_id;
		this.ste_skrok = skrok;
		this.ste_zaciatok = ste_zaciatok;
		this.ste_koniec = ste_koniec;
		this.ste_vysledok = ste_vysledok;
		this.ste_plnypocet = ste_plnypocet;
		this.ste_zverejnit = ste_zverejnit;
		this.ste_vypracovany = ste_vypracovany;
		this.ste_kontrola = ste_kontrola;
		this.ste_datum_zm = ste_datum_zm;
		this.ste_zaciatok_sk = ste_zaciatok_sk;
	}

	public Long getSte_id() {
		return ste_id;
	}

	public void setSte_id(Long ste_id) {
		this.ste_id = ste_id;
	}

	public Long getSte_st_id() {
		return ste_st_id;
	}

	public void setSte_st_id(Long ste_st_id) {
		this.ste_st_id = ste_st_id;
	}

	public Integer getSte_te_id() {
		return ste_te_id;
	}

	public void setSte_te_id(Integer ste_te_id) {
		this.ste_te_id = ste_te_id;
	}

	public Integer getSte_skrok() {
		return ste_skrok;
	}

	public void setSte_skrok(Integer ste_skrok) {
		this.ste_skrok = ste_skrok;
	}

	public Date getSte_zaciatok() {
		return ste_zaciatok;
	}

	public void setSte_zaciatok(Date ste_zaciatok) {
		this.ste_zaciatok = ste_zaciatok;
	}

	public Date getSte_koniec() {
		return ste_koniec;
	}

	public void setSte_koniec(Date ste_koniec) {
		this.ste_koniec = ste_koniec;
	}

	public Integer getSte_vysledok() {
		return ste_vysledok;
	}

	public void setSte_vysledok(Integer ste_vysledok) {
		this.ste_vysledok = ste_vysledok;
	}

	public Integer getSte_plnypocet() {
		return ste_plnypocet;
	}

	public void setSte_plnypocet(Integer ste_plnypocet) {
		this.ste_plnypocet = ste_plnypocet;
	}

	public String getSte_zverejnit() {
		return ste_zverejnit;
	}

	public void setSte_zverejnit(String ste_zverejnit) {
		this.ste_zverejnit = ste_zverejnit;
	}

	public String getSte_vypracovany() {
		return ste_vypracovany;
	}

	public void setSte_vypracovany(String ste_vypracovany) {
		this.ste_vypracovany = ste_vypracovany;
	}

	public boolean isSte_kontrola() {
		return ste_kontrola;
	}

	public void setSte_kontrola(boolean ste_kontrola) {
		this.ste_kontrola = ste_kontrola;
	}

	public Date getSte_datum_zm() {
		return ste_datum_zm;
	}

	public void setSte_datum_zm(Date ste_datum_zm) {
		this.ste_datum_zm = ste_datum_zm;
	}

	public Date getSte_zaciatok_sk() {
		return ste_zaciatok_sk;
	}

	public void setSte_zaciatok_sk(Date ste_zaciatok_sk) {
		this.ste_zaciatok_sk = ste_zaciatok_sk;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
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
		result = prime * result + ((ste_skrok == null) ? 0 : ste_skrok.hashCode());
		result = prime * result + ((ste_datum_zm == null) ? 0 : ste_datum_zm.hashCode());
		result = prime * result + ((ste_koniec == null) ? 0 : ste_koniec.hashCode());
		result = prime * result + (ste_kontrola ? 1231 : 1237);
		result = prime * result + ((ste_plnypocet == null) ? 0 : ste_plnypocet.hashCode());
		result = prime * result + ((ste_st_id == null) ? 0 : ste_st_id.hashCode());
		result = prime * result + ((ste_te_id == null) ? 0 : ste_te_id.hashCode());
		result = prime * result + ((ste_vypracovany == null) ? 0 : ste_vypracovany.hashCode());
		result = prime * result + ((ste_vysledok == null) ? 0 : ste_vysledok.hashCode());
		result = prime * result + ((ste_zaciatok == null) ? 0 : ste_zaciatok.hashCode());
		result = prime * result + ((ste_zaciatok_sk == null) ? 0 : ste_zaciatok_sk.hashCode());
		result = prime * result + ((ste_zverejnit == null) ? 0 : ste_zverejnit.hashCode());
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
		StudentTest other = (StudentTest) obj;
		if (ste_skrok == null) {
			if (other.ste_skrok != null)
				return false;
		} else if (!ste_skrok.equals(other.ste_skrok))
			return false;
		if (ste_datum_zm == null) {
			if (other.ste_datum_zm != null)
				return false;
		} else if (!ste_datum_zm.equals(other.ste_datum_zm))
			return false;
		if (ste_koniec == null) {
			if (other.ste_koniec != null)
				return false;
		} else if (!ste_koniec.equals(other.ste_koniec))
			return false;
		if (ste_kontrola != other.ste_kontrola)
			return false;
		if (ste_plnypocet == null) {
			if (other.ste_plnypocet != null)
				return false;
		} else if (!ste_plnypocet.equals(other.ste_plnypocet))
			return false;
		if (ste_st_id == null) {
			if (other.ste_st_id != null)
				return false;
		} else if (!ste_st_id.equals(other.ste_st_id))
			return false;
		if (ste_te_id == null) {
			if (other.ste_te_id != null)
				return false;
		} else if (!ste_te_id.equals(other.ste_te_id))
			return false;
		if (ste_vypracovany == null) {
			if (other.ste_vypracovany != null)
				return false;
		} else if (!ste_vypracovany.equals(other.ste_vypracovany))
			return false;
		if (ste_vysledok == null) {
			if (other.ste_vysledok != null)
				return false;
		} else if (!ste_vysledok.equals(other.ste_vysledok))
			return false;
		if (ste_zaciatok == null) {
			if (other.ste_zaciatok != null)
				return false;
		} else if (!ste_zaciatok.equals(other.ste_zaciatok))
			return false;
		if (ste_zaciatok_sk == null) {
			if (other.ste_zaciatok_sk != null)
				return false;
		} else if (!ste_zaciatok_sk.equals(other.ste_zaciatok_sk))
			return false;
		if (ste_zverejnit == null) {
			if (other.ste_zverejnit != null)
				return false;
		} else if (!ste_zverejnit.equals(other.ste_zverejnit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentTest [ste_id=" + ste_id + ", ste_st_id=" + ste_st_id + ", ste_te_id=" + ste_te_id + ", skrok="
				+ ste_skrok + ", ste_zaciatok=" + ste_zaciatok + ", ste_koniec=" + ste_koniec + ", ste_vysledok="
				+ ste_vysledok + ", ste_plnypocet=" + ste_plnypocet + ", ste_zverejnit=" + ste_zverejnit
				+ ", ste_vypracovany=" + ste_vypracovany + ", ste_kontrola=" + ste_kontrola + ", ste_datum_zm="
				+ ste_datum_zm + ", ste_zaciatok_sk=" + ste_zaciatok_sk + "]";
	}

}
