package com.wk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wk.dao.CharacterMapper;
import com.wk.dao.EventMapper;
import com.wk.entity.Character;
import com.wk.entity.Event;

@Service
public class EventService {
	@Autowired
	EventMapper eventMapper;
	@Autowired
	CharacterMapper characterMapper;

	/**
	 * 根据事件id查询事件(同时将首次出场人物名放入pojo中)(可优化)
	 * 
	 * @param evId
	 * @return
	 */
	public Event getEventByEvId(int evId) {
		if (evId <= 0)
			return null;
		Event ev = eventMapper.getEventByEvId(evId);
		if (ev == null)
			return null;
		List<Character> chList=this.getCharacterListByEvId(evId);
		ev.setChList(chList);
		return ev;
	}

	/**
	 * 根据小说id查询事件列表
	 * 
	 * @param bookId
	 * @return
	 */
	public List<Event> getEventListByBookId(int bookId) {
		if (bookId <= 0)
			return null;
		List<Event> chs = eventMapper.getEventListByBookId(bookId);
		if (chs == null || chs.size() == 0)
			return null;
		return chs;
	}

	/**
	 * 根据人物id查询出场事件
	 * 
	 * @param characterId
	 * @return
	 */
	public List<Event> getEventListByChId(int chId) {
		if (chId <= 0)
			return null;
		List<Event> chs = eventMapper.getEventListByChId(chId);
		if (chs == null || chs.size() == 0)
			return null;
		return chs;
	}

	/**
	 * 将人物从事件参与人物中删除
	 * @param chId
	 * @return
	 */
	public int deleteChFromEventByChId(int chId,int evId) {
		if (chId <= 0)
			return -1;
		return eventMapper.deleteChFromEventByChId(chId,evId);
	}
	/**
	 * 根据事件id查询出场人物
	 * 
	 * @param evId
	 * @return
	 */
	public List<Character> getCharacterListByEvId(int evId) {
		if (evId <= 0)
			return null;
		List<Character> chps = eventMapper.getCharacterListByEvId(evId);
		if (chps == null || chps.size() == 0)
			return null;
		return chps;
	}

	/**
	 * 更新事件信息
	 * 
	 * @param ch
	 * @return
	 */
	public int updateEvent(Event ev) {
		if (ev == null)
			return -1;
		return eventMapper.updateEvent(ev);
	}

	/**
	 * 插入新事件
	 * 
	 * @param ev
	 * @return
	 */
	public int insertEvent(Event ev) {
		if (ev == null)
			return -1;
		return eventMapper.insertEvent(ev);
	}

	/**
	 * 根据事件id删除事件
	 * 
	 * @param id
	 * @return
	 */
	public int deleteEventByEvId(int id) {
		if (id <= 0)
			return -1;
		return eventMapper.deleteEventByEvId(id);
	}
}
