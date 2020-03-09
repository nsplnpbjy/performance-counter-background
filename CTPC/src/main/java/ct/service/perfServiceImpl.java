package ct.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ct.Dao.perfDao;
import ct.Dao.userDao;
import ct.pojo.Perf;
import ct.pojo.PerfForShow;
import ct.pojo.User;
import net.sf.json.JSONArray;

@Service
public class perfServiceImpl implements perfService{

	@Autowired
	private userDao userdao;
	@Autowired
	private perfDao perfdao;
	@SuppressWarnings("deprecation")
	@Override
	public JSONArray returnPerfInHalf(String id) {

		Date endDate = new Date(System.currentTimeMillis());
		endDate.setDate(1);
		Date startDate = (Date) endDate.clone();
		int month = startDate.getMonth();

		int re = month - 6;
		if(re>=0)
			startDate.setMonth(re);
		else {
			startDate.setYear(startDate.getYear()-1);
			startDate.setMonth(12+re);
		}
		List<PerfForShow> pfsl = new ArrayList<PerfForShow>();
		List<Perf> perfList = perfdao.selectPefHalfYearById(id, startDate, endDate);
		Iterator<Perf> it = perfList.iterator();
		while(it.hasNext()) {
			Perf perf = it.next();
			String time = new SimpleDateFormat("YYYY-MM").format(perf.getTime());
			PerfForShow pfs = new PerfForShow();
			pfs.setRank(perf.getRankForNow());
			pfs.setTime(time);
			pfsl.add(pfs);
		}
		JSONArray returnJson = JSONArray.fromObject(pfsl);
		return returnJson;
	}
	
	@SuppressWarnings("deprecation")//确定领导和团队长在本月绩效考核名单中,这个方法有缺陷，在没有增加人员（即user表）的情况下不会出现bug
	//在有人员增加的情况下，必须清空userInPerf表才能正常运行
	//暂时不去解决这个bug
	@Override
	public void confirmTeamLeaderAndLeaderUserInPerf() {
		Date date = new Date(System.currentTimeMillis());
		date.setDate(1);
		List<String> sl = perfdao.isLeaderAndTeamLeaderInUserInPerf(date);
		if(sl.isEmpty()) {
			List<User> leaders = userdao.selectAllLeaderAndTeamLeader();
			Iterator<User> leadersi = leaders.iterator();
			while(leadersi.hasNext()) {
				String id = leadersi.next().getId();
				perfdao.insertIntoUserInPerfById(id, date);
			}
		}
	}

}
