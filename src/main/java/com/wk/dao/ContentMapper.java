package com.wk.dao;

import org.apache.ibatis.annotations.Param;

import com.wk.entity.ChapterContent;

public interface ContentMapper {
	/**
	 * 根据内容id查询章节内容
	 * 
	 * @param id
	 * @return
	 */
	String queryContentByContentId(int contentId);

	/**
	 * 根据内容id和内容修改章节内容
	 * 
	 * @param contentId
	 * @param content
	 * @return
	 */
	int updateContentByContentId(@Param("contentId") int contentId, @Param("content") String content);

	/**
	 * 插入章节内容，返回章节内容id
	 * 
	 * @param content
	 * @return
	 */
	int insertContent(ChapterContent content);
}
