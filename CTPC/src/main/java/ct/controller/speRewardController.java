package ct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ct.pojo.speRewardMana;
import ct.pojo.speRewardReq;
import ct.service.speRewardService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class speRewardController {

	@Autowired
	speRewardService srservice;
	
	@PostMapping("/newSpeReq")
	public JSONObject postNewSpeRewardReq(@RequestBody JSONObject json) {
		speRewardReq sprr = new speRewardReq();
		sprr.setRewardReason(json.getString("rewardReason"));
		sprr.setRewardSplit(json.getString("rewardSplit"));
		sprr.setRewardThings(json.getString("rewardThings"));
		sprr.setRewardToatle(json.getString("rewardToatle"));
		JSONObject returnJson = srservice.newSpeRewardReq(sprr);
		
		return returnJson;
		
	}
	
	@GetMapping("/getRewardReqList")
	public JSONArray getRewardReqList() {
		JSONArray returnJson = srservice.getSpeReqForMana();
		return returnJson;
		
	}
	
	@PostMapping("/signRewardReq")
	public JSONObject signReward(@RequestBody JSONObject json) {
		speRewardMana srm = new speRewardMana();
		srm.setIsProve(json.getString("isProve"));
		srm.setProvedMoney(json.getString("proveMoney"));
		srm.setProver(json.getString("prover"));
		srm.setRewardId(json.getString("rewardId"));
		//时间在service里面设置
		JSONObject returnJson = srservice.addSignedReward(srm);
		return returnJson;
		
	}
}
