package sk.uniza.fri.cuka.data.entity.ids;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StudentQuestionId implements Serializable {

	private Long sot_ste_id;
	private Integer sot_ot_id;

	public StudentQuestionId() {

	}

	public StudentQuestionId(Long sot_ste_id, Integer sot_ot_id) {
		this.sot_ste_id = sot_ste_id;
		this.sot_ot_id = sot_ot_id;
	}

	public Long getSot_ste_id() {
		return sot_ste_id;
	}

	public Integer getSot_ot_id() {
		return sot_ot_id;
	}

}
