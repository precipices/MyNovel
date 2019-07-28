package com.wk.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wk.entity.Character;
import com.wk.entity.Event;
import com.wk.service.EventService;
import com.wk.tool.Status;

@Controller
public class EventManageAction {
	private static Logger logger = Logger.getLogger(EventManageAction.class);

	@Autowired
	EventService evService;

	/**
	 * 根据事件id查询事件
	 * 
	 * @param evId
	 * @return
	 */
	@RequestMapping(value = "getEventByEvId.do", method = RequestMethod.POST)
	@ResponseBody
	public Event getEventByEvId(int evId) {
		logger.debug("从前端接收到的事件id:" + evId);
		Event ev = evService.getEventByEvId(evId);
		logger.debug("根据事件id查询事件:\n" + ev);
		return ev;
	}

	/**
	 * 根据小说id查询事件列表
	 * 
	 * @param bookId
	 * @return
	 */
	@RequestMapping(value = "getEventListByBookId.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Event> getEventListByBookId(int bookId) {
		List<Event> evs = evService.getEventListByBookId(bookId);
		logger.debug("根据小说id查询事件列表：\n" + evs);
		return evs;
	}

	/**
	 * 根据人物id查询出场事件
	 * 
	 * @param chId
	 * @return
	 */
	@RequestMapping(value = "getEventListByChId.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Event> getEventListByChId(int chId) {
		return evService.getEventListByChId(chId);
	}

	/**
	 * 将人物从事件参与人物中删除
	 * @param chId
	 * @return
	 */
	@RequestMapping(value = "deleteChFromEventByChId.do", method = RequestMethod.POST)
	@ResponseBody
	public int deleteChFromEventByChId(int chId,int evId) {
		return evService.deleteChFromEventByChId(chId,evId);
	}
	/**
	 * 根据事件id查询出场人物
	 * 
	 * @param evId
	 * @return
	 */
	@RequestMapping(value = "getCharacterListByEvId.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Character> getCharacterListByEvId(int evId) {
		return evService.getCharacterListByEvId(evId);
	}

	/**
	 * 更新事件信息
	 * 
	 * @param ev
	 * @return
	 */
	@RequestMapping(value = "updateEvent.do", method = RequestMethod.POST)
	@ResponseBody
	public int updateEvent(int evId, String evName, String intro, String origin, String result) {
		Event ev = new Event();
		ev.setId(evId);
		ev.setEvName(evName);
		ev.setIntro(intro);
		ev.setOrigin(origin);
		ev.setResult(result);
		int res = evService.updateEvent(ev);
		if (res <= 0)
			return Status.FAILED;
		return Status.SUCCESS;
	}

	/**
	 * 插入新事件
	 * 
	 * @param ev
	 * @return
	 */
	@RequestMapping(value = "insertEvent.do", method = RequestMethod.POST)
	@ResponseBody
	public int insertEvent(int bookId, String evName, String intro, String origin, String result) {
		Event ev = new Event();
		ev.setBookId(bookId);
		ev.setEvName(evName);
		ev.setIntro(intro);
		ev.setOrigin(origin);
		ev.setResult(result);
		int res = evService.insertEvent(ev);
		if (res <= 0)
			return Status.FAILED;
		return Status.SUCCESS;
	}

	/**
	 * 根据事件id删除事件
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteEventByEvId.do", method = RequestMethod.POST)
	@ResponseBody
	public int deleteEventByEvId(int evId) {
		logger.debug("根据事件id删除事件：" + evId);
		int result = evService.deleteEventByEvId(evId);
		if (result <= 0) {
			logger.debug("删除失败！");
			return Status.FAILED;
		}
		logger.debug("删除成功！");
		return Status.SUCCESS;
	}
}
