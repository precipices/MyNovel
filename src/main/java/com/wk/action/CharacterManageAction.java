package com.wk.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wk.entity.Chapter;
import com.wk.entity.Character;
import com.wk.service.CharacterService;
import com.wk.tool.Status;

@Controller
public class CharacterManageAction {
	private static Logger logger = Logger.getLogger(CharacterManageAction.class);

	@Autowired
	CharacterService chService;

	/**
	 * 根据人物id查询人物(同时将首次出场章节名放入pojo中)(可优化)
	 * 
	 * @param chId
	 * @return
	 */
	@RequestMapping(value = "getCharacterByChId.do", method = RequestMethod.POST)
	@ResponseBody
	public Character getCharacterByChId(int chId) {
		logger.debug("从前端接收到的人物id:" + chId);
		Character ch = chService.getCharacterByChId(chId);
		logger.debug("根据人物id查询人物:\n" + ch);
		return ch;
	}

	/**
	 * 根据小说id查询人物列表(同时将首次出场章节名放入pojo中)(可优化)
	 * 
	 * @param bookId
	 * @return
	 */
	@RequestMapping(value = "getCharacterListByBookId.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Character> getCharacterListByBookId(int bookId) {
		List<Character> chs = chService.getCharacterListByBookId(bookId);
		logger.debug("根据小说id查询人物列表：\n" + chs);
		return chs;
	}

	/**
	 * 根据章节id查询出场人物
	 * 
	 * @param chapterId
	 * @return
	 */
	@RequestMapping(value = "getCharacterListByChapterId.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Character> getCharacterListByChapterId(int chapterId) {
		return chService.getCharacterListByChapterId(chapterId);
	}

	/**
	 * 根据人物id查询出场章节
	 * 
	 * @param chId
	 * @return
	 */
	@RequestMapping(value = "getChapterListByChId.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Chapter> getChapterListByChId(int chId) {
		return chService.getChapterListByChId(chId);
	}

	/**
	 * 更新人物信息
	 * 
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "updateCharacter.do", method = RequestMethod.POST)
	@ResponseBody
	public int updateCharacter(int chId, int sex, String chName, String chAttr, String belong, String intro) {
		Character ch = new Character();
		ch.setId(chId);
		ch.setSex(sex);
		ch.setChName(chName);
		ch.setChAttr(chAttr);
		ch.setBelong(belong);
		ch.setIntro(intro);
		int result = chService.updateCharacter(ch);
		if (result <= 0)
			return Status.FAILED;
		return Status.SUCCESS;
	}

	/**
	 * 插入新人物
	 * 
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "insertCharacter.do", method = RequestMethod.POST)
	@ResponseBody
	public int insertCharacter(int bookId, int sex, String chName, String chAttr, String belong, String intro) {
		Character ch = new Character();
		ch.setBookId(bookId);
		ch.setSex(sex);
		ch.setChName(chName);
		ch.setChAttr(chAttr);
		ch.setBelong(belong);
		ch.setIntro(intro);
		int result = chService.insertCharacter(ch);
		if (result <= 0)
			return Status.FAILED;
		return Status.SUCCESS;
	}

	/**
	 * 根据人物id删除人物
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteCharacterByChId.do", method = RequestMethod.POST)
	@ResponseBody
	public int deleteCharacterByChId(int chId) {
		logger.debug("根据人物id删除人物：" + chId);
		int result = chService.deleteCharacterByChId(chId);
		if (result <= 0) {
			logger.debug("删除失败！");
			return Status.FAILED;
		}
		logger.debug("删除成功！");
		return Status.SUCCESS;
	}
}
