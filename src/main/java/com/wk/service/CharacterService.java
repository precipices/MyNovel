package com.wk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wk.dao.ChapterMapper;
import com.wk.dao.CharacterMapper;
import com.wk.entity.Chapter;
import com.wk.entity.Character;

@Service
public class CharacterService {
	@Autowired
	CharacterMapper characterMapper;
	@Autowired
	ChapterMapper chapterMapper;

	private boolean setFirstAppear(Character ch) {
		int appearId = ch.getFirstAppearId();
		if (appearId > 0) {
			Chapter chp = chapterMapper.queryChapterByChapterId(appearId);
			if (chp != null) {
				ch.setFirstAppear(chp.getTitle());
			} else {
				ch.setFirstAppear("未设置");
				return false;
			}
		} else {
			ch.setFirstAppear("未设置");
			return false;
		}
		return true;
	}

	/**
	 * 根据人物id查询人物(同时将首次出场章节名放入pojo中)(可优化)
	 * 
	 * @param chId
	 * @return
	 */
	public Character getCharacterByChId(int chId) {
		if (chId <= 0)
			return null;
		Character ch = characterMapper.getCharacterByChId(chId);
		if (ch == null)
			return null;
		this.setFirstAppear(ch);
		return ch;
	}

	/**
	 * 根据小说id查询人物列表(同时将首次出场章节名放入pojo中)(可优化)
	 * 
	 * @param bookId
	 * @return
	 */
	public List<Character> getCharacterListByBookId(int bookId) {
		if (bookId <= 0)
			return null;
		List<Character> chs = characterMapper.getCharacterListByBookId(bookId);
		if (chs == null || chs.size() == 0)
			return null;
		for (Character ch : chs) {
			this.setFirstAppear(ch);
		}
		return chs;
	}

	/**
	 * 根据章节id查询出场人物
	 * 
	 * @param chapterId
	 * @return
	 */
	public List<Character> getCharacterListByChapterId(int chapterId) {
		if (chapterId <= 0)
			return null;
		List<Character> chs = characterMapper.getCharacterListByChapterId(chapterId);
		if (chs == null || chs.size() == 0)
			return null;
		for (Character ch : chs) {
			this.setFirstAppear(ch);
		}
		return chs;
	}

	/**
	 * 根据人物id查询出场章节
	 * 
	 * @param chId
	 * @return
	 */
	public List<Chapter> getChapterListByChId(int chId) {
		if (chId <= 0)
			return null;
		List<Chapter> chps = characterMapper.getChapterListByChId(chId);
		if (chps == null || chps.size() == 0)
			return null;
		return chps;
	}

	/**
	 * 更新人物信息
	 * 
	 * @param ch
	 * @return
	 */
	public int updateCharacter(Character ch) {
		if (ch == null)
			return -1;
		return characterMapper.updateCharacter(ch);
	}

	/**
	 * 插入新人物
	 * 
	 * @param ch
	 * @return
	 */
	public int insertCharacter(Character ch) {
		if (ch == null)
			return -1;
		return characterMapper.insertCharacter(ch);
	}

	/**
	 * 根据人物id删除人物
	 * 
	 * @param id
	 * @return
	 */
	public int deleteCharacterByChId(int id) {
		if (id <= 0)
			return -1;
		return characterMapper.deleteCharacterByChId(id);
	}
}
