package com.wk.action;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wk.dao.UserMapper;
import com.wk.entity.User;

@Controller
public class LoginAndRegisterController {
	private static Logger logger=Logger.getLogger(LoginAndRegisterController.class);
	@Autowired
	private UserMapper userMapper;

	/**
	 * 用户名密码检测
	 * 
	 * @param username
	 * @param password
	 * @return 1：登录成功，0：用户名或密码不正确
	 */
	@RequestMapping(value = "/checkuser.do", method = RequestMethod.POST)
	@ResponseBody
	public int login(String username, String password,HttpSession session) {
		User user=userMapper.checkUser(username, password);
		System.out.println(user);
		logger.debug(user);
		if (user!=null) {
			user=userMapper.queryUserWithBookById(user.getId());
			session.setAttribute("user", user);
			return 1;
		}
		else
			return 0;
	}

	/**
	 * 注册
	 * 
	 * @param username
	 * @param password
	 * @return 1：登录成功 0：用户名或密码为空 2：用户名不规范 3：密码复杂度不够 4：用户名已被注册
	 */
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	@ResponseBody
	public int register(String username, String password) {
		// 用户名或密码为空
		if (username == null || password == null || "".equals(username) || "".equals(password)) {
			return 0;
		}

		// 用户名不规范（待修改）
		if (username.length() < 0) {
			return 2;
		}
		// 密码复杂度不够（待修改）
		if (password.length() < 0) {
			return 3;
		}

		// 用户名已被注册（待修改）
		if (userMapper.queryUserByUserName(username)!=null) {
			return 4;
		}
		
		//操作数据库
		User user=new User();
		user.setUserName(username);
		user.setPassword(password);
		userMapper.insertUser(user);
		logger.debug(user);
		if(user.getId()!=0) {

			// 注册成功
			return 1;
		}else {
			//注册失败
			return 5;
		}
		/**
		 * 一些操作。。。
		 */
	}
}
