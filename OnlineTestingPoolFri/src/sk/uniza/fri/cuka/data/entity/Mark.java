package sk.uniza.fri.cuka.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dom_znamka")
public class Mark {

	@Id
	private String znamka;
	private String popis;

	public Mark() {

	}

	public Mark(String znamka, String popis) {
		this.znamka = znamka;
		this.popis = popis;
	}

	public String getZnamka() {
		return znamka;
	}

	public void setZnamka(String znamka) {
		this.znamka = znamka;
	}

	public String getPopis() {
		return popis;
	}

	public void setPopis(String popis) {
		this.popis = popis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((popis == null) ? 0 : popis.hashCode());
		result = prime * result + ((znamka == null) ? 0 : znamka.hashCode());
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
		Mark other = (Mark) obj;
		if (popis == null) {
			if (other.popis != null)
				return false;
		} else if (!popis.equals(other.popis))
			return false;
		if (znamka == null) {
			if (other.znamka != null)
				return false;
		} else if (!znamka.equals(other.znamka))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mark [znamka=" + znamka + ", popis=" + popis + "]";
	}

}
