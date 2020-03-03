package ct.pojo;

import java.sql.Date;

public class Perf {
	private String id;
	private int rankForNow;
	private Date time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRankForNow() {
		return rankForNow;
	}
	public void setRankForNow(int rankForNow) {
		this.rankForNow = rankForNow;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
