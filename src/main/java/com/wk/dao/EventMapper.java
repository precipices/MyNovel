package com.wk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wk.entity.Character;
import com.wk.entity.Event;

public interface EventMapper {
	/**
	 * 根据事件id查询事件
	 * 
	 * @param evId
	 * @return
	 */
	@Select(value= {"select * from" + 
			"		`event`" + 
			"		where id=#{evId}"})
	Event getEventByEvId(int evId);

	/**
	 * 根据小说id查询事件列表
	 * 
	 * @param bookId
	 * @return
	 */
	@Select(value= {"select * from" + 
			"		`event` where book_id=#{bookId}"})
	List<Event> getEventListByBookId(int bookId);

	/**
	 * 根据人物id查询出场事件
	 * 
	 * @param chId
	 * @return
	 */
	@Select(value= {"select *" + 
			"		from `event`" + 
			"		where id in(select ev_id from `ev_ch` where" + 
			"		ch_id=#{chId});"})
	List<Event> getEventListByChId(int chId);
	
	/**
	 * 将人物从事件参与人物中删除
	 * @param chId
	 * @return
	 */
	@Delete(value= {"delete from `ev_ch` where ch_id = #{chId} and ev_id=#{evId}"})
	int deleteChFromEventByChId(@Param("chId")int chId,@Param("evId")int evId);

	/**
	 * 根据事件id查询出场人物
	 * 
	 * @param evId
	 * @return
	 */
	@Select(value= {"select * from" + 
			"		`character` where id in (select ch_id from ev_ch where" + 
			"		ev_id=#{evId}) ;"})
	List<Character> getCharacterListByEvId(int evId);

	/**
	 * 更新事件信息
	 * 
	 * @param ev
	 * @return
	 */
	@Update(value= {"<script/>update `event`" + 
			"		<trim prefix=\"set\" suffixOverrides=\",\">" + 
			"			<if test=\"bookId!=null and bookId!=0\">book_id=#{bookId},</if>" + 
			"			<if test=\"evName!=null and evName!=''\">ev_name=#{evName},</if>" + 
			"			<if test=\"intro!=null and intro!=''\">intro=#{intro},</if>" + 
			"			<if test=\"origin!=null and origin!=''\">origin=#{origin},</if>" + 
			"			<if test=\"result!=null and result!=''\">result=#{result},</if>" + 
			"			updated=NOW()," + 
			"		</trim>" + 
			"		where id=#{id};</script>"})
	int updateEvent(Event ev);

	/**
	 * 插入新事件
	 * 
	 * @param ev
	 * @return
	 */
	@Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
	@Insert(value= {"INSERT INTO" + 
			"		`event`(book_id,ev_name,intro,origin,result,created,updated)" + 
			"		values(#{bookId},#{evName},#{intro},#{origin},#{result},NOW(),NOW());"})
	int insertEvent(Event ev);

	/**
	 * 根据事件id删除事件
	 * 
	 * @param id
	 * @return
	 */
	@Delete(value= {"delete from `event` where id = #{evId}"})
	int deleteEventByEvId(int id);
}
