package ct.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ct.Dao.speRewardDao;
import ct.pojo.speRewardMana;
import ct.pojo.speRewardReq;
import ct.pojo.speRewardReqForShow;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class speRewardServiceImpl implements speRewardService{
	
	@Autowired
	speRewardDao srdao;
	@Override
	public JSONObject newSpeRewardReq(speRewardReq sprr) {
		Date date = new Date(System.currentTimeMillis());
		String rewardId = UUID.randomUUID().toString();
		rewardId = rewardId.replaceAll("-", "");
		srdao.insertIntoSpeRewardReq(rewardId, sprr.getRewardReason(), sprr.getRewardThings(), sprr.getRewardToatle(), sprr.getRewardSplit(),date);
		Map<String,String> map = new HashMap<String,String>();
		map.put("statu", "done");
		return JSONObject.fromObject(map);
	}
	@SuppressWarnings("deprecation")
	@Override
	public JSONArray getSpeReqForMana() {

		List<speRewardReq> srrl = srdao.returnAllSpeRewardReqOrderByTime();
		Iterator<speRewardReq> srri = srrl.iterator();
		List<speRewardReqForShow> srrfsl = new ArrayList<speRewardReqForShow>();
		while(srri.hasNext()) {
			speRewardReq srr = srri.next();
			speRewardReqForShow srrfs = new speRewardReqForShow();
			srrfs.setId(srr.getRewardId());
			srrfs.setReason(srr.getRewardReason());
			srrfs.setTime(srr.getTime().toLocaleString().substring(0, 8));
			srrfs.setSplit(srr.getRewardSplit());
			srrfs.setThings(srr.getRewardThings());
			srrfs.setToatle(srr.getRewardToatle());
			srrfsl.add(srrfs);
			}
		JSONArray returnJson = JSONArray.fromObject(srrfsl);
		return returnJson;
	}
	@Override
	public JSONObject addSignedReward(speRewardMana srm) {

		Date date = new Date(System.currentTimeMillis());
		srm.setTime(date);
		srdao.insertIntoSpeRewardMana(srm);
		
		return null;
	}
	
	

}
