package sk.uniza.fri.cuka.data.entity.ids;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RegisteredSubjectId implements Serializable {

	private Long zp_st_id;
	private String zp_pr_id;
	private Integer zp_skrok;

	public RegisteredSubjectId() {

	}

	public RegisteredSubjectId(Long zp_st_id, String zp_pr_id, Integer zp_skrok) {
		this.zp_st_id = zp_st_id;
		this.zp_pr_id = zp_pr_id;
		this.zp_skrok = zp_skrok;
	}

	public Long getZp_st_id() {
		return zp_st_id;
	}

	public String getZp_pr_id() {
		return zp_pr_id;
	}

	public Integer getZp_skrok() {
		return zp_skrok;
	}

}
