package com.wk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wk.dao.ChapterMapper;
import com.wk.dao.ContentMapper;
import com.wk.entity.Chapter;
import com.wk.entity.ChapterContent;

/**
 * 跨表操作数据，用于处理通过sql语句不便处理的跨表操作，也使操作便于修改和控制。
 */
@Component
public class CrossDaoService {
	@Autowired
	ChapterMapper chapterMapper;
	@Autowired
	ContentMapper contentMapper;

	/**
	 * 插入章节（包含章节内容）
	 * 
	 * @param chapter
	 * @param content
	 * @return
	 */
	public int insertChapterWithContent(Chapter chapter, String content) {
		ChapterContent chapterContent=new ChapterContent();
		chapterContent.setContent(content);
		int result=contentMapper.insertContent(chapterContent);
		if(result<1)
			return 0;
		chapter.setContentId(chapterContent.getId());
		result=chapterMapper.insertChapter(chapter);
		if(result<1)
			return 0;
		return 1;
	}
	/**
	 * 更新章节（包含章节内容）
	 * @param chapter
	 * @param content
	 * @return
	 */
	public int updateChapterWithContent(Chapter chapter,String content,int contentId) {

		int result = contentMapper.updateContentByContentId(contentId, content);
		
		return 1;
	}
}
