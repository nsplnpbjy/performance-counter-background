package ct.pojo;

import java.sql.Date;

public class speRewardMana {

	private String rewardId;
	private String isProve;
	private String provedMoney;
	private String prover;
	private Date time;
	public String getProver() {
		return prover;
	}
	public void setProver(String prover) {
		this.prover = prover;
	}
	public String getRewardId() {
		return rewardId;
	}
	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}
	public String getIsProve() {
		return isProve;
	}
	public void setIsProve(String isProve) {
		this.isProve = isProve;
	}
	public String getProvedMoney() {
		return provedMoney;
	}
	public void setProvedMoney(String provedMoney) {
		this.provedMoney = provedMoney;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
