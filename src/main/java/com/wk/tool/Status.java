package com.wk.tool;

import org.springframework.stereotype.Component;

@Component
public class Status {

	/**
	 * 未知错误
	 */
	public final static int FAILED = -1;
	/**
	 * 请求成功
	 */
	public final static int SUCCESS = 1;
	/**
	 * 用户名或密码不正确
	 */
	public final static int PASS_USER_ERR = 2;

	/**
	 * 用户名或密码为空
	 */
	public final static int PASS_USER_NULL = 3;
	/**
	 * 用户名不规范
	 */
	public final static int USERNAME_NOT_STANDARD = 4;

	/**
	 * 密码复杂度不够
	 */
	public final static int PASS_NOT_ENOUGH = 5;
	/**
	 * 用户名已被注册
	 */
	public final static int USERNAME_EXISTS = 6;

	/**
	 * 书不存在
	 */
	public final static int BOOK_NOT_EXISTS = 7;
	/**
	 * 小说删除失败，可能是id不存在
	 */
	public final static int DEL_BOOK_FALSE = 8;
}
