package ct.pojo;

import java.sql.Date;

public class UserInPerf implements Comparable<UserInPerf>{

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	private String id;
	private Date time;
	private int vote;
	@Override
	public int compareTo(UserInPerf o) {

		int i = o.getVote()-this.getVote();
		if(i==0)
			i=-1;
		return i;
	}
}
