package ct.Dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ct.pojo.Perf;
import ct.pojo.User;
import ct.pojo.UserInPerf;

@Mapper
public interface perfDao {

	@Select("select * from performancelist where id = #{id} and time between #{start} and #{end}")//真正发布时要把end剪掉一个月，不能展示本月实时绩效
	public List<Perf> selectPefHalfYearById(@Param(value="id")String id,@Param(value="start")Date start,@Param(value="end")Date end);
	
	@Select("select * from performancelist where id =#{id} and time =#{date}")
	public Perf isUserInPerfList(@Param(value="id")String id,@Param(value="date")Date date);
	
	@Insert("insert into performancelist (id,time) values(#{id},#{date})")
	public void insertIntoPerfListById(@Param(value="id")String id,@Param(value="date")Date date);
	
	@Update("update performancelist set rankForNow = #{rankForNow} where id =#{id} and time =#{date}")
	public void updateRankById(@Param(value="rankForNow")int rankForNow,@Param(value="id")String id,@Param(value="date")Date date);
	
	@Select("select * from userinperf where time = #{date}")
	public List<UserInPerf> selectUserInPerfByDate(@Param(value="date")Date date);
	
	@Select("select b.id from userinperf a,user b where a.time=#{date} and (b.role='领导' or b.role='团队长') ")
	public List<String> isLeaderAndTeamLeaderInUserInPerf(@Param(value="date")Date date);
	
	@Insert("insert into userinperf (id,time,vote) values(#{id},#{date},0)")
	public void insertIntoUserInPerfById(@Param(value="id")String id,@Param(value="date")Date date);
}
