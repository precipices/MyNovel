package com.wk.dao;

import java.util.List;

import com.wk.entity.Chapter;
import com.wk.entity.Character;

public interface CharacterMapper {
	/**
	 * 根据人物id查询人物
	 * 
	 * @param chId
	 * @return
	 */
	Character getCharacterByChId(int chId);

	/**
	 * 根据小说id查询人物列表
	 * 
	 * @param bookId
	 * @return
	 */
	List<Character> getCharacterListByBookId(int bookId);

	/**
	 * 根据章节id查询出场人物
	 * 
	 * @param chapterId
	 * @return
	 */
	List<Character> getCharacterListByChapterId(int chapterId);

	/**
	 * 根据人物id查询出场章节
	 * 
	 * @param chId
	 * @return
	 */
	List<Chapter> getChapterListByChId(int chId);

	/**
	 * 更新人物信息
	 * 
	 * @param ch
	 * @return
	 */
	int updateCharacter(Character ch);

	/**
	 * 插入新人物
	 * 
	 * @param ch
	 * @return
	 */
	int insertCharacter(Character ch);

	/**
	 * 根据人物id删除人物
	 * 
	 * @param id
	 * @return
	 */
	int deleteCharacterByChId(int id);
}
