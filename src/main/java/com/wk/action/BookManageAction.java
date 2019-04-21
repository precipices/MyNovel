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
public class BookManageAction {
	private static Logger logger = Logger.getLogger(BookManageAction.class);

	@Autowired
	private BookMapper bookMapper;

	@Autowired
	private SessionService sessionService;

	@RequestMapping(value="getBookList.do",method=RequestMethod.POST)
	@ResponseBody
	public List<Book> getBookList() {
		return sessionService.getBookListFromUser();
	}
	
	@RequestMapping(value = "deleteBook.do", method = RequestMethod.POST)
	@ResponseBody
	public int deleteBook(int bookId, HttpSession session) {
		// 删除id为bookId的书
		int result = bookMapper.deleteBook(bookId);

		if (result >= 1) {
			if (sessionService.removeBookById(bookId))
				return 1;
			else
				return Status.DEL_BOOK_FALSE;
		} else
			return Status.DEL_BOOK_FALSE;
	}

	@RequestMapping(value = "createBook.do", method = RequestMethod.POST)
	@ResponseBody
	public int createBook(String bookName, String bookType, String bookTags, String bookIntro, HttpSession session) {
		// 获得用户id
		User user = (User) session.getAttribute("user");
		if (user == null)
			return Status.FAILED;
		int userId = user.getId();
		// 添加新增书籍内容
		Book book = new Book();
		book.setUserId(userId);
		book.setBookName(bookName);
		book.setType(bookType);
		book.setTags(bookTags);
		book.setIntro(bookIntro);
		int result = bookMapper.insertBook(book);
		logger.debug("插入sql返回值："+result);
		logger.debug("添加新增书籍内容:" + book);
		if (result == 1) {
			// 更新用户的bookList,前面已判断用户不为空，这里不用判断，肯定能更新成功
			sessionService.refreshBookListForUser();
			return book.getId();
		} else
			return Status.FAILED;
	}
	
	@RequestMapping(value = "updateBook.do", method = RequestMethod.POST)
	@ResponseBody
	public int updateBook(int bookId,String bookName, String bookType, String bookTags, String bookIntro, HttpSession session) {
		// 获得用户id
		User user = (User) session.getAttribute("user");
		if (user == null)
			return Status.FAILED;
		int userId = user.getId();
		// 更新小说信息
		Book book = new Book();
		book.setId(bookId);
//		book.setUserId(userId);
		book.setBookName(bookName);
		book.setType(bookType);
		book.setTags(bookTags);
		book.setIntro(bookIntro);
		int result = bookMapper.updateBook(book);
		logger.debug(result);
		logger.debug("更新小说信息:" + book);
		if (result == 1) {
			//更新session中的书
			sessionService.refreshBook(bookId);
			return book.getId();
		} else
			return Status.FAILED;
	}
	@RequestMapping(value="getBookById.do",method=RequestMethod.POST)
	@ResponseBody
	public Book getBookById(int bookId) {
		Book book=bookMapper.queryBookByBookId(bookId);
		return book;
	}
}
