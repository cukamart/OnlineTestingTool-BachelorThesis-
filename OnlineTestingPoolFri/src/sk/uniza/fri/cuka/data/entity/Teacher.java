package sk.uniza.fri.cuka.data.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ucitel")
public class Teacher {

	@Id
	@SequenceGenerator(name = "myuc_id_seq", sequenceName = "uc_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "myuc_id_seq")
	@Column(name = "uc_id")
	private long uc_id;

	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	List<Manage> manage = new ArrayList<>();

	private String uc_priezv;
	private String uc_meno;
	private String uc_login;
	private String uc_heslo;
	private String uc_ulica;
	private String uc_obec;
	private String uc_psc;
	private String uc_email;
	private String uc_telefon;
	private String uc_mobil;
	private String uc_admin;
	private String uc_skratka;
	private String uc_pr_id;

	public Teacher(long uc_id, String uc_priezv, String uc_meno, String uc_login, String uc_heslo, String uc_ulica,
			String uc_obec, String uc_psc, String uc_email, String uc_telefon, String uc_mobil, String uc_admin,
			String uc_skratka, String uc_pr_id) {
		this.uc_id = uc_id;
		this.uc_priezv = uc_priezv;
		this.uc_meno = uc_meno;
		this.uc_login = uc_login;
		this.uc_heslo = uc_heslo;
		this.uc_ulica = uc_ulica;
		this.uc_obec = uc_obec;
		this.uc_psc = uc_psc;
		this.uc_email = uc_email;
		this.uc_telefon = uc_telefon;
		this.uc_mobil = uc_mobil;
		this.uc_admin = uc_admin;
		this.uc_skratka = uc_skratka;
		this.uc_pr_id = uc_pr_id;
	}

	public Teacher(String uc_priezv, String uc_meno, String uc_login, String uc_heslo, String uc_ulica, String uc_obec,
			String uc_psc, String uc_email, String uc_telefon, String uc_mobil, String uc_admin, String uc_skratka,
			String uc_pr_id) {
		this.uc_priezv = uc_priezv;
		this.uc_meno = uc_meno;
		this.uc_login = uc_login;
		this.uc_heslo = uc_heslo;
		this.uc_ulica = uc_ulica;
		this.uc_obec = uc_obec;
		this.uc_psc = uc_psc;
		this.uc_email = uc_email;
		this.uc_telefon = uc_telefon;
		this.uc_mobil = uc_mobil;
		this.uc_admin = uc_admin;
		this.uc_skratka = uc_skratka;
		this.uc_pr_id = uc_pr_id;
	}

	public Teacher() {

	}

	public long getUc_id() {
		return uc_id;
	}

	public void setUc_id(long uc_id) {
		this.uc_id = uc_id;
	}

	public String getUc_priezv() {
		return uc_priezv;
	}

	public void setUc_priezv(String uc_priezv) {
		this.uc_priezv = uc_priezv;
	}

	public String getUc_meno() {
		return uc_meno;
	}

	public void setUc_meno(String uc_meno) {
		this.uc_meno = uc_meno;
	}

	public String getUc_login() {
		return uc_login;
	}

	public void setUc_login(String uc_login) {
		this.uc_login = uc_login;
	}

	public String getUc_heslo() {
		return uc_heslo;
	}

	public void setUc_heslo(String uc_heslo) {
		this.uc_heslo = uc_heslo;
	}

	public String getUc_ulica() {
		return uc_ulica;
	}

	public void setUc_ulica(String uc_ulica) {
		this.uc_ulica = uc_ulica;
	}

	public String getUc_obec() {
		return uc_obec;
	}

	public void setUc_obec(String uc_obec) {
		this.uc_obec = uc_obec;
	}

	public String getUc_psc() {
		return uc_psc;
	}

	public void setUc_psc(String uc_psc) {
		this.uc_psc = uc_psc;
	}

	public String getUc_email() {
		return uc_email;
	}

	public void setUc_email(String uc_email) {
		this.uc_email = uc_email;
	}

	public String getUc_telefon() {
		return uc_telefon;
	}

	public void setUc_telefon(String uc_telefon) {
		this.uc_telefon = uc_telefon;
	}

	public String getUc_mobil() {
		return uc_mobil;
	}

	public void setUc_mobil(String uc_mobil) {
		this.uc_mobil = uc_mobil;
	}

	public String getUc_admin() {
		return uc_admin;
	}

	public void setUc_admin(String uc_admin) {
		this.uc_admin = uc_admin;
	}

	public String getUc_skratka() {
		return uc_skratka;
	}

	public void setUc_skratka(String uc_skratka) {
		this.uc_skratka = uc_skratka;
	}

	public String getUc_pr_id() {
		return uc_pr_id;
	}

	public void setUc_pr_id(String uc_pr_id) {
		this.uc_pr_id = uc_pr_id;
	}

	public List<Manage> getManage() {
		return manage;
	}

	public void setManage(List<Manage> manage) {
		this.manage = manage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uc_admin == null) ? 0 : uc_admin.hashCode());
		result = prime * result + ((uc_email == null) ? 0 : uc_email.hashCode());
		result = prime * result + ((uc_login == null) ? 0 : uc_login.hashCode());
		result = prime * result + ((uc_meno == null) ? 0 : uc_meno.hashCode());
		result = prime * result + ((uc_mobil == null) ? 0 : uc_mobil.hashCode());
		result = prime * result + ((uc_obec == null) ? 0 : uc_obec.hashCode());
		result = prime * result + ((uc_pr_id == null) ? 0 : uc_pr_id.hashCode());
		result = prime * result + ((uc_priezv == null) ? 0 : uc_priezv.hashCode());
		result = prime * result + ((uc_psc == null) ? 0 : uc_psc.hashCode());
		result = prime * result + ((uc_skratka == null) ? 0 : uc_skratka.hashCode());
		result = prime * result + ((uc_telefon == null) ? 0 : uc_telefon.hashCode());
		result = prime * result + ((uc_ulica == null) ? 0 : uc_ulica.hashCode());
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
		Teacher other = (Teacher) obj;
		if (uc_admin == null) {
			if (other.uc_admin != null)
				return false;
		} else if (!uc_admin.equals(other.uc_admin))
			return false;
		if (uc_email == null) {
			if (other.uc_email != null)
				return false;
		} else if (!uc_email.equals(other.uc_email))
			return false;
		if (uc_login == null) {
			if (other.uc_login != null)
				return false;
		} else if (!uc_login.equals(other.uc_login))
			return false;
		if (uc_meno == null) {
			if (other.uc_meno != null)
				return false;
		} else if (!uc_meno.equals(other.uc_meno))
			return false;
		if (uc_mobil == null) {
			if (other.uc_mobil != null)
				return false;
		} else if (!uc_mobil.equals(other.uc_mobil))
			return false;
		if (uc_obec == null) {
			if (other.uc_obec != null)
				return false;
		} else if (!uc_obec.equals(other.uc_obec))
			return false;
		if (uc_pr_id == null) {
			if (other.uc_pr_id != null)
				return false;
		} else if (!uc_pr_id.equals(other.uc_pr_id))
			return false;
		if (uc_priezv == null) {
			if (other.uc_priezv != null)
				return false;
		} else if (!uc_priezv.equals(other.uc_priezv))
			return false;
		if (uc_psc == null) {
			if (other.uc_psc != null)
				return false;
		} else if (!uc_psc.equals(other.uc_psc))
			return false;
		if (uc_skratka == null) {
			if (other.uc_skratka != null)
				return false;
		} else if (!uc_skratka.equals(other.uc_skratka))
			return false;
		if (uc_telefon == null) {
			if (other.uc_telefon != null)
				return false;
		} else if (!uc_telefon.equals(other.uc_telefon))
			return false;
		if (uc_ulica == null) {
			if (other.uc_ulica != null)
				return false;
		} else if (!uc_ulica.equals(other.uc_ulica))
			return false;
		return true;
	}

}
