package ct.service;

import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ct.Dao.perfDao;
import ct.Dao.userDao;
import ct.Dao.voteDao;
import ct.pojo.User;
import ct.pojo.UserInPerf;
import net.sf.json.JSONObject;

@Service
public class voteServiceImpl implements voteService{

	@Autowired
	private perfDao perfdao;
	@Autowired
	private voteDao votedao;
	@Autowired
	private userDao userdao;
	@SuppressWarnings("deprecation")
	@Override
	public JSONObject voteRecPerson(String id,String reason) {

		Date date = new Date(System.currentTimeMillis());
		date.setDate(1);
		Map<String,String> returnMap = new HashMap<String,String>();
		User user = userdao.selectUserById(id);
		if(votedao.isVoteFromReclistExist(id, date)!=null) {
			votedao.updateReclistVote(id,reason, date);
			
		}
		else {
			votedao.insertReclistNewPerson(user.getId(), user.getName(), reason,date);//同时要插入userInPerf表
			System.out.println(user.getId());
			votedao.insertUserinperfNewPerson(user.getId(), date);
		}
		returnMap.put("statu", "successed");
		return JSONObject.fromObject(returnMap);
	}
	@SuppressWarnings("deprecation")
	@Override
	public JSONObject voteForUserInPerf(String id) {

		Date date = new Date(System.currentTimeMillis());
		date.setDate(1);
		votedao.updateVoteUserInPerf(id, date);//更新UserInPerf表中对应ID的投票数
		List<UserInPerf>uipl = perfdao.selectUserInPerfByDate(date);//从此句开始到wihle结束，更新当月performanceList表中的排名
		Collections.sort(uipl);
		Iterator<UserInPerf> uipi = uipl.iterator();
		int rankForNow = 0;
		while(uipi.hasNext()) {
			rankForNow++;
			UserInPerf uip = uipi.next();
			if(perfdao.isUserInPerfList(uip.getId(), date)==null)
				perfdao.insertIntoPerfListById(uip.getId(), date);
			perfdao.updateRankById(rankForNow, uip.getId(), date);
		}//更新rank结束
		Map<String,String> map = new HashMap<String,String>();
		map.put("statu", "successed");
		return JSONObject.fromObject(map);
	}

}
