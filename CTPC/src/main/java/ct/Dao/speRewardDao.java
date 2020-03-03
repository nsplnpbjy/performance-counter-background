package ct.Dao;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import ct.pojo.speRewardMana;
import ct.pojo.speRewardReq;

@Mapper
public interface speRewardDao {

	@Insert("insert into sperewardreq values(#{rewardId},#{rewardReason},#{rewardThings},#{rewardToatle},#{rewardSplit},#{date})")
	public void insertIntoSpeRewardReq(String rewardId,String rewardReason,String rewardThings,String rewardToatle,String rewardSplit,Date date);
	
	@Select("select * from sperewardreq where rewardId not in (select rewardId from sperewardmana) order by time desc")
	public List<speRewardReq> returnAllSpeRewardReqOrderByTime();
	
	@Insert("insert into sperewardmana values(#{rewardId},#{isProve},#{provedMoney},#{prover},#{time})")
	public void insertIntoSpeRewardMana(speRewardMana object);
}
