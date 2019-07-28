package com.wk.action;

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
import com.wk.dao.UserMapper;
import com.wk.dao.UsingMapper;
import com.wk.entity.Book;
import com.wk.entity.Chapter;
import com.wk.entity.User;
import com.wk.entity.Using;
import com.wk.tool.Status;

@Controller
public class LoginRegisterAction {
	private static Logger logger = Logger.getLogger(LoginRegisterAction.class);
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private UsingMapper usingMapper;
	@Autowired
	private ChapterMapper chapterMapper;

	/**
	 * 用户名密码检测
	 * 
	 * @param username
	 * @param password
	 * @return 1：登录成功，0：用户名或密码不正确
	 */
	@RequestMapping(value = "/checkuser.do", method = RequestMethod.POST)
	@ResponseBody
	public int login(String username, String password, HttpSession session) {
		// 与数据库比对，检查用户名密码正确性
		User user = userMapper.checkUser(username, password);
		logger.debug("与数据库比对，检查用户名密码正确性：\n" + user);
		if (user != null) {
			// 用户名密码正确的情况下，读取数据库中的小说列表,并存入用户实体中
			List<Book> bookList=bookMapper.queryBookByUserId(user.getId());
			user.setBookList(bookList);
			logger.debug("用户名密码正确的情况下,读取数据库中的小说列表,并存入用户实体中：\n" + user);
			// 将user存入session中
			session.setAttribute("user", user);
			logger.debug("将user存入session中");
//			// 从数据库读取正在使用表,并根据表中的id读取相应信息储存到using实体中
//			Using using=usingMapper.queryUsingByUserId(user.getId());
//			Book usingBook=bookMapper.queryBookByBookId(using.getBookId());
//			List<Chapter> chapterList=chapterMapper.queryChapterListByBookId(using.getBookId());
//			usingBook.setChapterList(chapterList);
//			using.setBook(usingBook);
////			bookMapper.queryChaptersByBookId(bookId)
//			using.getChapterId();
			
			return Status.SUCCESS;
		} else
			return Status.PASS_USER_ERR;
	}

	/**
	 * 注册
	 * 
	 * @param username
	 * @param password
	 * @return Status
	 */
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	@ResponseBody
	public int register(String username, String password) {
		// 用户名或密码为空
		if (username == null || password == null || "".equals(username) || "".equals(password)) {
			logger.debug("用户名或密码为空");
			return Status.PASS_USER_NULL;
		}

		// 用户名不规范（待修改）
		if (username.length() < 0) {
			logger.debug("用户名不规范（待修改）");
			return Status.USERNAME_NOT_STANDARD;
		}
		// 密码复杂度不够（待修改）
		if (password.length() < 0) {
			logger.debug("密码复杂度不够（待修改）");
			return Status.PASS_NOT_ENOUGH;
		}

		// 用户名已被注册（待修改）
		if (userMapper.queryUserByUserName(username) != null) {
			logger.debug("用户名已被注册（待修改");
			return Status.USERNAME_EXISTS;
		}

		// 将用户信息存入数据库
		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		userMapper.insertUser(user);
		logger.debug("将用户信息存入数据库:"+user);
		// 为用户创建一张正在使用表
//		usingMapper.insertUsing(user.getId());
//		logger.debug("为用户创建一张正在使用表");

		// 注册成功
		if (user.getId() > 0) {
			logger.debug("注册成功");
			return Status.SUCCESS;
		} else {
			// 注册失败
			return Status.FAILED;
		}
		/**
		 * 一些操作。。。
		 */
	}
}
