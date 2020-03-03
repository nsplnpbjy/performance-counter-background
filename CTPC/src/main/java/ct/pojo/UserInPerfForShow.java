package ct.pojo;

public class UserInPerfForShow implements Comparable<UserInPerfForShow> {

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
	}
	private String name;
	private String id;
	private String reason;
	private int vote;
	@Override
	public int compareTo(UserInPerfForShow arg0) {
		int i = arg0.getVote()-this.getVote();
		if(i==0)
			i=-1;
		return i;
	}
}
