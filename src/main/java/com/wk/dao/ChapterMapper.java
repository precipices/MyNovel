package com.wk.dao;

import java.util.List;

import com.wk.entity.Book;
import com.wk.entity.Chapter;

public interface ChapterMapper {
	/**
	 * 根据内容id查询章节内容
	 * 
	 * @param id
	 * @return
	 */
	String queryContentByContentId(int contentId);

	/**
	 * 根据书id查询小说的所有章节信息
	 * 
	 * @param bookId
	 * @return
	 */
	List<Chapter> queryChapterListByBookId(int bookId);

	/**
	 * 根据章节id查询章节信息
	 * @param chapterId
	 * @return
	 */
	Chapter queryChapterByChapterId(int chapterId);
}
