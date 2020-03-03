package ct.pojo;

public class userForShow implements Comparable<userForShow>{
	

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getRecVote() {
		return recVote;
	}
	public void setRecVote(int recVote) {
		this.recVote = recVote;
	}
	private User user;
	private int recVote;
	@Override
	public int compareTo(userForShow arg0) {//用作List中的比较方法

		int i = arg0.getRecVote()-this.getRecVote();
		if(i==0)
			i=-1;
		return i;
	}
}
