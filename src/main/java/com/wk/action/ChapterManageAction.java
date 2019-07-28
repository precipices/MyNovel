package com.wk.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wk.dao.ChapterMapper;
import com.wk.entity.Book;
import com.wk.entity.Chapter;
import com.wk.entity.User;
import com.wk.service.CrossDaoService;
import com.wk.tool.Status;

@Controller
public class ChapterManageAction {
	private static Logger logger = Logger.getLogger(ChapterManageAction.class);

	@Autowired
	private ChapterMapper chapterMapper;

	/**
	 * 根据bookid查找章节列表数据，并放入session的user的book的章节列表中
	 * 
	 * @param bookId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "getChapterList2.do", method = RequestMethod.POST)
	@ResponseBody
	public int getChapterList(int bookId, HttpSession session) {
		List<Chapter> chapterList = chapterMapper.queryChapterListByBookId(bookId);
		logger.debug(chapterList);
		User user = (User) session.getAttribute("user");
		Book book = user.getBookById(bookId);
		session.setAttribute("bookId", bookId);
		if (book != null) {
			book.setChapterList(chapterList);
			return Status.SUCCESS;
		} else {
			return Status.BOOK_NOT_EXISTS;
		}
	}

	/**
	 * 获取章节列表
	 * 
	 * @param bookId
	 * @return
	 */
	@RequestMapping(value = "getChapterList.do", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<Chapter> getChapterList2(int bookId) {
		ArrayList<Chapter> chapterList = (ArrayList<Chapter>) chapterMapper.queryChapterListByBookId(bookId);
		logger.debug(chapterList);
		return chapterList;
	}

	@Autowired
	CrossDaoService crossDao;

	/**
	 * 新增章节
	 * 
	 * @param bookId
	 * @param title
	 * @param words
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "createChapter.do", method = RequestMethod.POST)
	@ResponseBody
	public int createChapter(int bookId, String title, int words, String content) {

		Chapter chapter = new Chapter();
		chapter.setBookId(bookId);
		chapter.setTitle(title);
		chapter.setWords(words);
		int result = crossDao.insertChapterWithContent(chapter, content);
		if (result > 0)
			return Status.SUCCESS;
		else
			return Status.FAILED;
	}

	/**
	 * 根据章节id删除章节内容和章节信息
	 * 
	 * @param chapterId
	 * @return
	 */
	@RequestMapping(value = "deleteChapter.do", method = RequestMethod.POST)
	@ResponseBody
	public int deleteChapter(int chapterId) {
		//删除章节内容
		int result=-1;
		result = chapterMapper.deleteContentByChapterId(chapterId);
		if(result<=0)
			return Status.FAILED;
		//删除章节信息
		result = chapterMapper.deleteChapterByChapterId(chapterId);
		if (result > 0)
			return Status.SUCCESS;
		else
			return Status.FAILED;
	}

}
