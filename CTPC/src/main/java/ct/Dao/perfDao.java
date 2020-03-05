package ct.Dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ct.pojo.Perf;
import ct.pojo.User;
import ct.pojo.UserInPerf;

@Mapper
public interface perfDao {

	@Select("select * from performancelist where id = #{id} and time between #{start} and #{end}")//真正发布时要把end剪掉一个月，不能展示本月实时绩效
	public List<Perf> selectPefHalfYearById(String id,Date start,Date end);
	
	@Select("select * from performancelist where id =#{id} and time =#{date}")
	public Perf isUserInPerfList(String id,Date date);
	
	@Insert("insert into performancelist (id,time) values(#{id},#{date})")
	public void insertIntoPerfListById(String id,Date date);
	
	@Update("update performancelist set rankForNow = #{rankForNow} where id =#{id} and time =#{date}")
	public void updateRankById(int rankForNow,String id,Date date);
	
	@Select("select * from userinperf where time = #{date}")
	public List<UserInPerf> selectUserInPerfByDate(Date date);
	
	@Select("select b.id from userinperf a,user b where a.time=#{date} and (b.role='领导' or b.role='团队长') ")
	public List<String> isLeaderAndTeamLeaderInUserInPerf(Date date);
	
	@Insert("insert into userinperf (id,time,vote) values(#{id},#{date},0)")
	public void insertIntoUserInPerfById(String id,Date date);
}
