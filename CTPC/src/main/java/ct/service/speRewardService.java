package ct.service;

import ct.pojo.speRewardMana;
import ct.pojo.speRewardReq;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface speRewardService {

	public JSONObject newSpeRewardReq(speRewardReq sprr);
	public JSONArray getSpeReqForMana();
	public JSONObject addSignedReward(speRewardMana srm);
}
