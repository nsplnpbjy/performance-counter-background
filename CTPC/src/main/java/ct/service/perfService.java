package ct.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface perfService {

	public JSONArray returnPerfInHalf(String id);
	public void confirmTeamLeaderAndLeaderUserInPerf();
}
