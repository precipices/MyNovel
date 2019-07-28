package com.wk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wk.entity.Character;
import com.wk.entity.Event;

public interface EventMapper {
	/**
	 * 根据事件id查询事件
	 * 
	 * @param evId
	 * @return
	 */
	Event getEventByEvId(int evId);

	/**
	 * 根据小说id查询事件列表
	 * 
	 * @param bookId
	 * @return
	 */
	List<Event> getEventListByBookId(int bookId);

	/**
	 * 根据人物id查询出场事件
	 * 
	 * @param chId
	 * @return
	 */
	List<Event> getEventListByChId(int chId);
	
	/**
	 * 将人物从事件参与人物中删除
	 * @param chId
	 * @return
	 */
	int deleteChFromEventByChId(@Param("chId")int chId,@Param("evId")int evId);

	/**
	 * 根据事件id查询出场人物
	 * 
	 * @param evId
	 * @return
	 */
	List<Character> getCharacterListByEvId(int evId);

	/**
	 * 更新事件信息
	 * 
	 * @param ev
	 * @return
	 */
	int updateEvent(Event ev);

	/**
	 * 插入新事件
	 * 
	 * @param ev
	 * @return
	 */
	int insertEvent(Event ev);

	/**
	 * 根据事件id删除事件
	 * 
	 * @param id
	 * @return
	 */
	int deleteEventByEvId(int id);
}
