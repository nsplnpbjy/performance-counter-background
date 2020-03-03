package ct.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ct.Dao.userDao;
import ct.Dao.voteDao;
import ct.pojo.User;
import ct.pojo.UserInPerf;
import ct.pojo.UserInPerfForShow;
import ct.pojo.userForShow;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class userServiceImpl implements userService{

	@Autowired
	private userDao userdao;

	@Autowired
	private voteDao votedao;
	@Override
	public boolean isUserHasOpenId(String openid) {
		User user = userdao.selectUserByOpenid(openid);
		if(user!=null)
			return true;
		return false;
	}

	@Override
	public JSONObject userToJson(User user) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", user.getId());
		map.put("name",user.getName());
		map.put("role", user.getRole());
		map.put("team", user.getTeam());
		
		return JSONObject.fromObject(map);
	}

	@Override
	public boolean updateUserOpenid(String openid,String id) {
		userdao.updateUserOpenid(openid, id);
		User user = userdao.selectUserById(id);
		if(user!=null)
			if(user.getOpenid()!=null)
				return true;
		
		return false;
	}

	@Override
	public User selectUserByOpenid(String openid) {
		return userdao.selectUserByOpenid(openid);
	}

	@SuppressWarnings("deprecation")
	@Override
	public JSONArray selectUserForShow() {
		Date date = new Date(System.currentTimeMillis());
		date.setDate(1);
		List<userForShow> listReady = new ArrayList<userForShow>();//拿来真实返回的List
		List<User> userList = userdao.selectUserWithoutTeamLeaderAndLeader();
		Iterator<User> userIt = userList.iterator();
		while(userIt.hasNext()) {
			userForShow ufs = new userForShow();
			User user = userIt.next();
			ufs.setUser(user);
			if(votedao.isVoteFromReclistExist(user.getId(),date)!=null)
				ufs.setRecVote(votedao.getVoteFromReclist(user.getId(),date));
			else
				ufs.setRecVote(0);
			listReady.add(ufs);
		}
		Collections.sort(listReady);
		JSONArray returnJA = JSONArray.fromObject(listReady);
		return returnJA;
	}

	@SuppressWarnings("deprecation")
	@Override
	public JSONArray returnUserInPerf() {

		Date date = new Date(System.currentTimeMillis());
		date.setDate(1);
		List<UserInPerf> uipl = userdao.selectUserInPerf(date);
		List<UserInPerfForShow> uipfsl = new ArrayList<UserInPerfForShow>();
		Iterator<UserInPerf> uipi = uipl.iterator();
		while(uipi.hasNext()) {
			UserInPerf uip = uipi.next();
			UserInPerfForShow uipfs = new UserInPerfForShow();
			uipfs.setId(uip.getId());
			uipfs.setName(userdao.selectUserById(uip.getId()).getName());
			String reason = votedao.selectReasonFromReclist(uip.getId(), date);
			if(reason==null){
				String id = uipfs.getId();
				User user = userdao.selectUserById(id);
				uipfs.setReason(user.getRole());
			}
			else
				uipfs.setReason(reason);
			uipfs.setVote(uip.getVote());
			uipfsl.add(uipfs);
		}
		Collections.sort(uipfsl);
		JSONArray returnJson = JSONArray.fromObject(uipfsl);
		return returnJson;
	}

	@SuppressWarnings("deprecation")
	@Override
	public JSONObject isInRecList(String id) {

		Date date = new Date(System.currentTimeMillis());
		date.setDate(1);
		String uid = userdao.isInRecList(id, date);
		Map<String,String> map = new HashMap<String,String>();
		if(uid==null)
			map.put("isRecList", "false");
		else
			map.put("isRecList", "true");
		return JSONObject.fromObject(map);
	}

	@Override
	public JSONArray searchUser(String name) {

		List<User> userl = userdao.fuzzySelectUserByName(name);
		return JSONArray.fromObject(userl);
	}


}
