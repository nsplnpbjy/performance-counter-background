package ct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ct.service.perfService;
import ct.service.userService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class tableReturnController {
	@Autowired
	userService userservice;
	@Autowired
	perfService perfservice;
	
	@GetMapping("/getUserTable")
	public JSONArray userTable() {
		JSONArray returnJson = userservice.selectUserForShow();
		return returnJson;
		
	}

	@PostMapping("/getHalfYearPef")
	public JSONArray halfYearPefTable(@RequestBody JSONObject json) {
		String id = json.getString("id");
		JSONArray returnJson = perfservice.returnPerfInHalf(id);
		return returnJson;
		
	}
	@GetMapping("/getUserInPerf")
	public JSONArray getUserInPef() {
		perfservice.confirmTeamLeaderAndLeaderUserInPerf();//这个方法保证了团队长和领导会出现在每一个月的绩效考核人员名单中
		JSONArray returnJson = userservice.returnUserInPerf();
		return returnJson;
		
	}
	
	@PostMapping("/isInRecList")
	public JSONObject isInRecList(@RequestBody JSONObject json) {
		String id = json.getString("id");
		JSONObject returnJson = userservice.isInRecList(id);
		return returnJson;
		
	}
	
	@PostMapping("/searchUser")
	public JSONArray returnSearchUser(@RequestBody JSONObject json) {
		String searchName = json.getString("searchName");
		JSONArray returnJson = userservice.searchUser(searchName);
		return returnJson;
		
	}
}
