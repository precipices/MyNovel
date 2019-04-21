package com.wk.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wk.action.LoginRegisterAction;
import com.wk.dao.BookMapper;
import com.wk.entity.Book;
import com.wk.entity.User;

@Service
public class SessionService {
	private static Logger logger = Logger.getLogger(SessionService.class);
	@Autowired
	HttpSession session;
	@Autowired
	BookMapper bookMapper;

	/**
	 * 更新session里的用户实体里的小说列表
	 * 
	 * @return
	 */
	public boolean refreshBookListForUser() {
		//从session中获取用户
		User user = (User) session.getAttribute("user");
		if (user == null)
			return false;
		// 用户名密码正确的情况下，读取数据库中的小说列表,并存入用户实体中
		List<Book> bookList=bookMapper.queryBookByUserId(user.getId());
		user.setBookList(bookList);
		logger.debug("读取数据库中的小说列表,并存入用户实体中：\n" + user);
		return true;
	}
	
	/**
	 * 将session中的某本书移除
	 * @param bookId
	 * @return
	 */
	public boolean removeBookById(int bookId) {
		//从session中获取用户
		User user = (User) session.getAttribute("user");
		if (user == null)
			return false;
		Book book=user.getBookById(bookId);
		if(book==null)
			return false;
		user.getBookList().remove(book);
		return true;
	}
	
	/**
	 * 直接从session中取出bookList
	 * @return
	 */
	public List<Book> getBookListFromUser() {
		//从session中获取用户
		User user = (User) session.getAttribute("user");
		if (user == null)
			return null;
		return user.getBookList();
	}
	
	public boolean refreshBook(int bookId) {
		//从session中获取用户
		User user = (User) session.getAttribute("user");
		if (user == null)
			return false;
		Book book=user.getBookById(bookId);
		Book newBook = bookMapper.queryBookByBookId(bookId);
//		book.setId(bookId);
		book.setUserId(newBook.getUserId());
		book.setBookName(newBook.getBookName());
		book.setIntro(newBook.getIntro());
		book.setType(newBook.getType());
		book.setTags(newBook.getTags());
		book.setCreated(newBook.getCreated());
		book.setUpdated(newBook.getUpdated());
		return true;
	}
}
