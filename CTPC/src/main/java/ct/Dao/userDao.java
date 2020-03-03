package ct.Dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import ct.pojo.User;
import ct.pojo.UserInPerf;

@Mapper
public interface userDao {

	@Select("select * from user where name = #{name}")
	public User selectUserByName(String name);
	
	@Select("select * from user where name like '%${name}%'")
	public List<User> fuzzySelectUserByName(@Param(value="name")String name);//在出现拼接时，需要手动指定对应的Param
	
	@Select("select * from user where openid = #{openid}")
	public User selectUserByOpenid(String openid);
	
	@Update("update user set openid = #{openid} where id = #{id}")
	public void updateUserOpenid(String openid,String id);
	
	@Select("select * from user where id = #{id}")
	public User selectUserById(String id);
	
	@Select("select * from user")
	public List<User> selectUser();
	
	@Select("select * from user where role!='领导' and role!='团队长'")
	public List<User> selectUserWithoutTeamLeaderAndLeader();
	
	@Select("select * from userinperf where time = #{time}")
	public List<UserInPerf> selectUserInPerf(Date time);
	
	@Select("select id from reclist where id = #{id} and time =#{date}")
	public String isInRecList(String id,Date date);
	
	@Select("select * from user where role='领导' or role='团队长'")
	public List<User> selectAllLeaderAndTeamLeader();
}
