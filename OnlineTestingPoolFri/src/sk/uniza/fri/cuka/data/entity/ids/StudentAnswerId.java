package sk.uniza.fri.cuka.data.entity.ids;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StudentAnswerId implements Serializable {

	private Long sod_ste_id;
	private Integer sod_ot_id;
	private Integer sod_od_id;

	public StudentAnswerId() {

	}

	public StudentAnswerId(Long sod_ste_id, Integer sod_ot_id, Integer sod_od_id) {
		this.sod_ste_id = sod_ste_id;
		this.sod_ot_id = sod_ot_id;
		this.sod_od_id = sod_od_id;
	}

	public Long getSod_ste_id() {
		return sod_ste_id;
	}

	public Integer getSod_ot_id() {
		return sod_ot_id;
	}

	public Integer getSod_od_id() {
		return sod_od_id;
	}

}
