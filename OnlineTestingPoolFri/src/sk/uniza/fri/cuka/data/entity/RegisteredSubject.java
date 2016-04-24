package sk.uniza.fri.cuka.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import sk.uniza.fri.cuka.data.entity.ids.RegisteredSubjectId;

@Entity
@IdClass(RegisteredSubjectId.class)
@Table(name = "zap_predm")
public class RegisteredSubject {

	@Id
	@Column(name = "zp_st_id")
	private Long zp_st_id;

	@Id
	@Column(name = "zp_pr_id")
	private String zp_pr_id;

	@Id
	@Column(name = "zp_skrok")
	private Integer zp_skrok;

	@ManyToOne
	@JoinColumn(name = "zp_vysl", referencedColumnName = "ho_id", insertable = false, updatable = false)
	private Valuation valuation;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "zp_uc_id", referencedColumnName = "sp_uc_id", insertable = false, updatable = false),
			@JoinColumn(name = "zp_pr_id", referencedColumnName = "sp_pr_id", insertable = false, updatable = false),
			@JoinColumn(name = "zp_skrok", referencedColumnName = "sp_skrok", insertable = false, updatable = false) })
	private Manage manage;

	@Temporal(TemporalType.DATE)
	private Date zp_datum1;

	@Temporal(TemporalType.DATE)
	private Date zp_datum2;

	@Temporal(TemporalType.DATE)
	private Date zp_datum3;

	@Temporal(TemporalType.DATE)
	private Date zp_zap_datum;

	private Long zp_uc_id;
	private String zp_vysl;
	private Integer zp_blok;
	private Integer zp_skupina_bl;
	private Integer zp_testy;
	private Integer zp_min_testy;

	public RegisteredSubject() {

	}

	public RegisteredSubject(Long zp_st_id, String zp_pr_id, Integer zp_skrok, Date zp_datum1, Date zp_datum2,
			Date zp_datum3, Date zap_datum, Long zp_uc_id, String zp_vysl, Integer zp_blok, Integer zp_skupina_bl,
			Integer zp_testy, Integer zp_min_testy) {
		this.zp_st_id = zp_st_id;
		this.zp_pr_id = zp_pr_id;
		this.zp_skrok = zp_skrok;
		this.zp_datum1 = zp_datum1;
		this.zp_datum2 = zp_datum2;
		this.zp_datum3 = zp_datum3;
		this.zp_zap_datum = zap_datum;
		this.zp_uc_id = zp_uc_id;
		this.zp_vysl = zp_vysl;
		this.zp_blok = zp_blok;
		this.zp_skupina_bl = zp_skupina_bl;
		this.zp_testy = zp_testy;
		this.zp_min_testy = zp_min_testy;
	}

	public Long getZp_st_id() {
		return zp_st_id;
	}

	public void setZp_st_id(Long zp_st_id) {
		this.zp_st_id = zp_st_id;
	}

	public String getZp_pr_id() {
		return zp_pr_id;
	}

	public void setZp_pr_id(String zp_pr_id) {
		this.zp_pr_id = zp_pr_id;
	}

	public Integer getZp_skrok() {
		return zp_skrok;
	}

	public void setZp_skrok(Integer zp_skrok) {
		this.zp_skrok = zp_skrok;
	}

	public Date getZp_datum1() {
		return zp_datum1;
	}

	public void setZp_datum1(Date zp_datum1) {
		this.zp_datum1 = zp_datum1;
	}

	public Date getZp_datum2() {
		return zp_datum2;
	}

	public void setZp_datum2(Date zp_datum2) {
		this.zp_datum2 = zp_datum2;
	}

	public Date getZp_datum3() {
		return zp_datum3;
	}

	public void setZp_datum3(Date zp_datum3) {
		this.zp_datum3 = zp_datum3;
	}

	public Date getZp_zap_datum() {
		return zp_zap_datum;
	}

	public void setZp_zap_datum(Date zp_zap_datum) {
		this.zp_zap_datum = zp_zap_datum;
	}

	public Long getZp_uc_id() {
		return zp_uc_id;
	}

	public void setZp_uc_id(Long zp_uc_id) {
		this.zp_uc_id = zp_uc_id;
	}

	public String getZp_vysl() {
		return zp_vysl;
	}

	public void setZp_vysl(String zp_vysl) {
		this.zp_vysl = zp_vysl;
	}

	public Integer getZp_blok() {
		return zp_blok;
	}

	public void setZp_blok(Integer zp_blok) {
		this.zp_blok = zp_blok;
	}

	public Integer getZp_skupina_bl() {
		return zp_skupina_bl;
	}

	public void setZp_skupina_bl(Integer zp_skupina_bl) {
		this.zp_skupina_bl = zp_skupina_bl;
	}

	public Integer getZp_testy() {
		return zp_testy;
	}

	public void setZp_testy(Integer zp_testy) {
		this.zp_testy = zp_testy;
	}

	public Integer getZp_min_testy() {
		return zp_min_testy;
	}

	public void setZp_min_testy(Integer zp_min_testy) {
		this.zp_min_testy = zp_min_testy;
	}

	public Valuation getValuation() {
		return valuation;
	}

	public void setValuation(Valuation valuation) {
		this.valuation = valuation;
	}

	public Manage getManage() {
		return manage;
	}

	public void setManage(Manage manage) {
		this.manage = manage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zp_zap_datum == null) ? 0 : zp_zap_datum.hashCode());
		result = prime * result + ((zp_blok == null) ? 0 : zp_blok.hashCode());
		result = prime * result + ((zp_datum1 == null) ? 0 : zp_datum1.hashCode());
		result = prime * result + ((zp_datum2 == null) ? 0 : zp_datum2.hashCode());
		result = prime * result + ((zp_datum3 == null) ? 0 : zp_datum3.hashCode());
		result = prime * result + ((zp_min_testy == null) ? 0 : zp_min_testy.hashCode());
		result = prime * result + ((zp_pr_id == null) ? 0 : zp_pr_id.hashCode());
		result = prime * result + ((zp_skrok == null) ? 0 : zp_skrok.hashCode());
		result = prime * result + ((zp_skupina_bl == null) ? 0 : zp_skupina_bl.hashCode());
		result = prime * result + ((zp_st_id == null) ? 0 : zp_st_id.hashCode());
		result = prime * result + ((zp_testy == null) ? 0 : zp_testy.hashCode());
		result = prime * result + ((zp_uc_id == null) ? 0 : zp_uc_id.hashCode());
		result = prime * result + ((zp_vysl == null) ? 0 : zp_vysl.hashCode());
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
		RegisteredSubject other = (RegisteredSubject) obj;
		if (zp_zap_datum == null) {
			if (other.zp_zap_datum != null)
				return false;
		} else if (!zp_zap_datum.equals(other.zp_zap_datum))
			return false;
		if (zp_blok == null) {
			if (other.zp_blok != null)
				return false;
		} else if (!zp_blok.equals(other.zp_blok))
			return false;
		if (zp_datum1 == null) {
			if (other.zp_datum1 != null)
				return false;
		} else if (!zp_datum1.equals(other.zp_datum1))
			return false;
		if (zp_datum2 == null) {
			if (other.zp_datum2 != null)
				return false;
		} else if (!zp_datum2.equals(other.zp_datum2))
			return false;
		if (zp_datum3 == null) {
			if (other.zp_datum3 != null)
				return false;
		} else if (!zp_datum3.equals(other.zp_datum3))
			return false;
		if (zp_min_testy == null) {
			if (other.zp_min_testy != null)
				return false;
		} else if (!zp_min_testy.equals(other.zp_min_testy))
			return false;
		if (zp_pr_id == null) {
			if (other.zp_pr_id != null)
				return false;
		} else if (!zp_pr_id.equals(other.zp_pr_id))
			return false;
		if (zp_skrok == null) {
			if (other.zp_skrok != null)
				return false;
		} else if (!zp_skrok.equals(other.zp_skrok))
			return false;
		if (zp_skupina_bl == null) {
			if (other.zp_skupina_bl != null)
				return false;
		} else if (!zp_skupina_bl.equals(other.zp_skupina_bl))
			return false;
		if (zp_st_id == null) {
			if (other.zp_st_id != null)
				return false;
		} else if (!zp_st_id.equals(other.zp_st_id))
			return false;
		if (zp_testy == null) {
			if (other.zp_testy != null)
				return false;
		} else if (!zp_testy.equals(other.zp_testy))
			return false;
		if (zp_uc_id == null) {
			if (other.zp_uc_id != null)
				return false;
		} else if (!zp_uc_id.equals(other.zp_uc_id))
			return false;
		if (zp_vysl == null) {
			if (other.zp_vysl != null)
				return false;
		} else if (!zp_vysl.equals(other.zp_vysl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegisteredSubject [zp_st_id=" + zp_st_id + ", zp_pr_id=" + zp_pr_id + ", zp_skrok=" + zp_skrok
				+ ", zp_datum1=" + zp_datum1 + ", zp_datum2=" + zp_datum2 + ", zp_datum3=" + zp_datum3 + ", zap_datum="
				+ zp_zap_datum + ", zp_uc_id=" + zp_uc_id + ", zp_vysl=" + zp_vysl + ", zp_blok=" + zp_blok
				+ ", zp_skupina_bl=" + zp_skupina_bl + ", zp_testy=" + zp_testy + ", zp_min_testy=" + zp_min_testy
				+ "]";
	}

}
