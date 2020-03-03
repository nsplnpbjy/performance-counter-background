package ct.Dao;

import java.sql.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface voteDao {

	@Select("select vote from reclist where id = #{id} and time = #{date}")
	public int getVoteFromReclist(String id,Date date);
	
	@Select("select id from reclist where id=#{id} and time = #{date}")
	public String isVoteFromReclistExist(String id,Date date);
	
	@Insert("insert into reclist values(#{id},#{name},#{reason},#{date},1)")//同时加入绩效考核表
	public void insertReclistNewPerson(String id,String name,String reason,Date date);
	
	@Insert("insert into userinperf (id,time,vote)  values(#{id},#{date},0)")
	public void insertUserinperfNewPerson(@Param(value="id")String id,@Param(value="date")Date date);
	
	@Update("update reclist set vote=vote+1,reason=CONCAT(reason,#{reason}) where id = #{id} and time=#{date}")
	public void updateReclistVote(String id,String reason,Date date);
	
	@Select("select reason from reclist where id=#{id} and time =#{date}")
	public String selectReasonFromReclist(String id,Date date);
	
	@Update("update userinperf set vote = vote+1 where id =#{id} and time = #{date}")
	public void updateVoteUserInPerf(String id,Date date);
}
