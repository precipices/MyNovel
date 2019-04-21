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

import com.wk.dao.BookMapper;
import com.wk.dao.ChapterMapper;
import com.wk.entity.Book;
import com.wk.entity.Chapter;
import com.wk.entity.User;
import com.wk.service.SessionService;
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

	@RequestMapping(value = "getChapterList.do", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<Chapter> getChapterList2(int bookId) {
		ArrayList<Chapter> chapterList = (ArrayList<Chapter>) chapterMapper.queryChapterListByBookId(bookId);
		logger.debug(chapterList);
		return chapterList;
	}


	@RequestMapping(value = "getContent.do", method = RequestMethod.POST)
	@ResponseBody
	public String getContent(int contentId) {
		return chapterMapper.queryContentByContentId(contentId);
	}
}
