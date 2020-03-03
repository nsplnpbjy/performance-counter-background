package ct.service;

import net.sf.json.JSONObject;

public interface voteService {

	public JSONObject voteRecPerson(String id,String reason);//返回一个投票成功的信息
	public JSONObject voteForUserInPerf(String id);
}
