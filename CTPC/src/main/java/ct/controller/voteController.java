package ct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ct.service.voteService;
import net.sf.json.JSONObject;

@RestController
public class voteController {

	@Autowired
	private voteService voteservice;

	@PostMapping("/recVote")
	public JSONObject recVote(@RequestBody JSONObject json) {
		String id = json.getString("id");
		String recReason = json.getString("recReason");
		return voteservice.voteRecPerson(id, recReason);
		
	}
	
	@PostMapping("/voteForUserInPerf")
	public JSONObject voteForUserInPerf(@RequestBody JSONObject json) {
		String id = json.getString("id");
		JSONObject returnJson = voteservice.voteForUserInPerf(id);
		return returnJson;
		
	}
}
