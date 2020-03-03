package ct.service;

import ct.pojo.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface userService {

	public boolean isUserHasOpenId(String openid);
	public JSONObject userToJson(User user);
	public boolean updateUserOpenid(String openid,String id);
	public User selectUserByOpenid(String openid);
	public JSONArray selectUserForShow();//返回的是一个JSONArray,里面包含了userForShow对象,它其中又包含了User和recVote
	public JSONArray returnUserInPerf();
	public JSONObject isInRecList(String id);
	public JSONArray searchUser(String name);
}
