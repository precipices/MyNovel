package com.wk.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wk.dao.ChapterMapper;
import com.wk.dao.ContentMapper;
import com.wk.entity.Chapter;
import com.wk.tool.Status;

@Controller
public class ContentManageAction {
	private static Logger logger = Logger.getLogger(ContentManageAction.class);

	@Autowired
	private ContentMapper contentMapper;
	@Autowired
	private ChapterMapper chapterMapper;

	/**
	 * 获取章节内容
	 * @param contentId
	 * @return
	 */
	@RequestMapping(value = "getContent.do", method = RequestMethod.POST)
	@ResponseBody
	public String getContent(int contentId) {
		String content = contentMapper.queryContentByContentId(contentId);
		logger.debug("获取章节内容：" + content);
		return content;
	}

	/**
	 * 更新章节内容
	 * @param chapterId
	 * @param contentId
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "updateContent.do", method = RequestMethod.POST)
	@ResponseBody
	public int updateContent(int chapterId,int contentId,String title, int words, String content) {
		chapterMapper.updateUpdatedTime(chapterId);
		int result = contentMapper.updateContentByContentId(contentId, content);
		if(result<=0)
			return Status.FAILED;
		Chapter chapter = new Chapter();
		chapter.setId(chapterId);
		chapter.setTitle(title);
		chapter.setWords(words);
		result=chapterMapper.updateChapter(chapter);
		logger.debug("修改章节内容结果：" + result);
		if (result == 1)
			return Status.SUCCESS;
		else
			return Status.FAILED;
	}
}
