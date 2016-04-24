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
@Table(name = "student")
public class Student {

	// DO NOT USE GenerationType.SEQUENCE for Postgres sequences! (Bug Hibernate 3)
	@Id
	@SequenceGenerator(name = "postgreGenerator", sequenceName = "st_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "postgreGenerator")
	private long st_id;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<StudentTest> studentTests = new ArrayList<>();

	private String st_priezv;
	private String st_meno;
	private String st_login;
	private String st_heslo;
	private int st_special;
	private int st_odbor;
	private String st_ulica;
	private String st_obec;
	private String st_psc;
	private String st_email;
	private String st_telefon;
	private String st_mobil;
	private String st_stav;
	private String st_skupina;

	public Student() {

	}

	public Student(long st_id, String st_priezv, String st_meno, String st_login, String st_heslo, int st_special,
			int st_odbor, String st_ulica, String st_obec, String st_psc, String st_email, String st_telefon,
			String st_mobil, String st_stav, String st_skupina) {
		this.st_id = st_id;
		this.st_priezv = st_priezv;
		this.st_meno = st_meno;
		this.st_login = st_login;
		this.st_heslo = st_heslo;
		this.st_special = st_special;
		this.st_odbor = st_odbor;
		this.st_ulica = st_ulica;
		this.st_obec = st_obec;
		this.st_psc = st_psc;
		this.st_email = st_email;
		this.st_telefon = st_telefon;
		this.st_mobil = st_mobil;
		this.st_stav = st_stav;
		this.st_skupina = st_skupina;
	}

	public Student(String st_priezv, String st_meno, String st_login, String st_heslo, int st_special, int st_odbor,
			String st_ulica, String st_obec, String st_psc, String st_email, String st_telefon, String st_mobil,
			String st_stav, String st_skupina) {
		this.st_priezv = st_priezv;
		this.st_meno = st_meno;
		this.st_login = st_login;
		this.st_heslo = st_heslo;
		this.st_special = st_special;
		this.st_odbor = st_odbor;
		this.st_ulica = st_ulica;
		this.st_obec = st_obec;
		this.st_psc = st_psc;
		this.st_email = st_email;
		this.st_telefon = st_telefon;
		this.st_mobil = st_mobil;
		this.st_stav = st_stav;
		this.st_skupina = st_skupina;
	}

	public long getSt_id() {
		return st_id;
	}

	public void setSt_id(long st_id) {
		this.st_id = st_id;
	}

	public String getSt_priezv() {
		return st_priezv;
	}

	public void setSt_priezv(String st_priezv) {
		this.st_priezv = st_priezv;
	}

	public String getSt_meno() {
		return st_meno;
	}

	public void setSt_meno(String st_meno) {
		this.st_meno = st_meno;
	}

	public String getSt_login() {
		return st_login;
	}

	public void setSt_login(String st_login) {
		this.st_login = st_login;
	}

	public String getSt_heslo() {
		return st_heslo;
	}

	public void setSt_heslo(String st_heslo) {
		this.st_heslo = st_heslo;
	}

	public int getSt_special() {
		return st_special;
	}

	public void setSt_special(int st_special) {
		this.st_special = st_special;
	}

	public int getSt_odbor() {
		return st_odbor;
	}

	public void setSt_odbor(int st_odbor) {
		this.st_odbor = st_odbor;
	}

	public String getSt_ulica() {
		return st_ulica;
	}

	public void setSt_ulica(String st_ulica) {
		this.st_ulica = st_ulica;
	}

	public String getSt_obec() {
		return st_obec;
	}

	public void setSt_obec(String st_obec) {
		this.st_obec = st_obec;
	}

	public String getSt_psc() {
		return st_psc;
	}

	public void setSt_psc(String st_psc) {
		this.st_psc = st_psc;
	}

	public String getSt_email() {
		return st_email;
	}

	public void setSt_email(String st_email) {
		this.st_email = st_email;
	}

	public String getSt_telefon() {
		return st_telefon;
	}

	public void setSt_telefon(String st_telefon) {
		this.st_telefon = st_telefon;
	}

	public String getSt_mobil() {
		return st_mobil;
	}

	public void setSt_mobil(String st_mobil) {
		this.st_mobil = st_mobil;
	}

	public String getSt_stav() {
		return st_stav;
	}

	public void setSt_stav(String st_stav) {
		this.st_stav = st_stav;
	}

	public String getSt_skupina() {
		return st_skupina;
	}

	public void setSt_skupina(String st_skupina) {
		this.st_skupina = st_skupina;
	}

	public List<StudentTest> getStudentTests() {
		return studentTests;
	}

	public void setStudentTests(List<StudentTest> studentTests) {
		this.studentTests = studentTests;
	}

	@Override
	public String toString() {
		return "Student [st_id=" + st_id + ", st_priezv=" + st_priezv + ", st_meno=" + st_meno + ", st_login="
				+ st_login + ", st_heslo=" + st_heslo + ", st_special=" + st_special + ", st_odbor=" + st_odbor
				+ ", st_ulica=" + st_ulica + ", st_obec=" + st_obec + ", st_psc=" + st_psc + ", st_email=" + st_email
				+ ", st_telefon=" + st_telefon + ", st_mobil=" + st_mobil + ", st_stav=" + st_stav + ", st_skupina="
				+ st_skupina + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((st_email == null) ? 0 : st_email.hashCode());
		result = prime * result + ((st_login == null) ? 0 : st_login.hashCode());
		result = prime * result + ((st_meno == null) ? 0 : st_meno.hashCode());
		result = prime * result + ((st_mobil == null) ? 0 : st_mobil.hashCode());
		result = prime * result + ((st_obec == null) ? 0 : st_obec.hashCode());
		result = prime * result + st_odbor;
		result = prime * result + ((st_priezv == null) ? 0 : st_priezv.hashCode());
		result = prime * result + ((st_psc == null) ? 0 : st_psc.hashCode());
		result = prime * result + ((st_skupina == null) ? 0 : st_skupina.hashCode());
		result = prime * result + st_special;
		result = prime * result + ((st_stav == null) ? 0 : st_stav.hashCode());
		result = prime * result + ((st_telefon == null) ? 0 : st_telefon.hashCode());
		result = prime * result + ((st_ulica == null) ? 0 : st_ulica.hashCode());
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
		Student other = (Student) obj;
		if (st_email == null) {
			if (other.st_email != null)
				return false;
		} else if (!st_email.equals(other.st_email))
			return false;
		if (st_login == null) {
			if (other.st_login != null)
				return false;
		} else if (!st_login.equals(other.st_login))
			return false;
		if (st_meno == null) {
			if (other.st_meno != null)
				return false;
		} else if (!st_meno.equals(other.st_meno))
			return false;
		if (st_mobil == null) {
			if (other.st_mobil != null)
				return false;
		} else if (!st_mobil.equals(other.st_mobil))
			return false;
		if (st_obec == null) {
			if (other.st_obec != null)
				return false;
		} else if (!st_obec.equals(other.st_obec))
			return false;
		if (st_odbor != other.st_odbor)
			return false;
		if (st_priezv == null) {
			if (other.st_priezv != null)
				return false;
		} else if (!st_priezv.equals(other.st_priezv))
			return false;
		if (st_psc == null) {
			if (other.st_psc != null)
				return false;
		} else if (!st_psc.equals(other.st_psc))
			return false;
		if (st_skupina == null) {
			if (other.st_skupina != null)
				return false;
		} else if (!st_skupina.equals(other.st_skupina))
			return false;
		if (st_special != other.st_special)
			return false;
		if (st_stav == null) {
			if (other.st_stav != null)
				return false;
		} else if (!st_stav.equals(other.st_stav))
			return false;
		if (st_telefon == null) {
			if (other.st_telefon != null)
				return false;
		} else if (!st_telefon.equals(other.st_telefon))
			return false;
		if (st_ulica == null) {
			if (other.st_ulica != null)
				return false;
		} else if (!st_ulica.equals(other.st_ulica))
			return false;
		return true;
	}

}
