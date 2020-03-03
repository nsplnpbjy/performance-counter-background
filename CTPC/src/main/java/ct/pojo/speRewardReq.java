package ct.pojo;

import java.sql.Date;

public class speRewardReq {

	private String rewardId;
	private String rewardReason;
	private String rewardThings;
	private String rewardToatle;
	private String rewardSplit;
	private Date time;
	public String getRewardId() {
		return rewardId;
	}
	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}
	public String getRewardReason() {
		return rewardReason;
	}
	public void setRewardReason(String rewardReason) {
		this.rewardReason = rewardReason;
	}
	public String getRewardThings() {
		return rewardThings;
	}
	public void setRewardThings(String rewardThings) {
		this.rewardThings = rewardThings;
	}
	public String getRewardToatle() {
		return rewardToatle;
	}
	public void setRewardToatle(String rewardToatle) {
		this.rewardToatle = rewardToatle;
	}
	public String getRewardSplit() {
		return rewardSplit;
	}
	public void setRewardSplit(String rewardSplit) {
		this.rewardSplit = rewardSplit;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
