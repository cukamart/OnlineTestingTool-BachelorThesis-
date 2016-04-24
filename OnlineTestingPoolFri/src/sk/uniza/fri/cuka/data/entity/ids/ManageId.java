package sk.uniza.fri.cuka.data.entity.ids;

import java.io.Serializable;

/**
 * 
 * @author Martin Cuka
 * 
 * Holding Compound Primary Key for Entity Manage
 *
 */
@SuppressWarnings("serial")
public class ManageId implements Serializable{

	private long sp_uc_id;
	private String sp_pr_id;
	private int sp_skrok;

	public ManageId(){
		
	}
	
	public ManageId(long sp_uc_id, String sp_pr_id, int sp_skrok) {
		this.sp_uc_id = sp_uc_id;
		this.sp_pr_id = sp_pr_id;
		this.sp_skrok = sp_skrok;
	}

	public long getSp_uc_id() {
		return sp_uc_id;
	}

	public String getSp_pr_id() {
		return sp_pr_id;
	}

	public int getSp_skrok() {
		return sp_skrok;
	}

}
