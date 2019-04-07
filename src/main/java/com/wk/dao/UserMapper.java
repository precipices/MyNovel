package com.wk.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wk.entity.User;

/**
 * 持久层接口： UserMapper.java (定义操作数据库的方法) 这里切记要用@Mapper注解标识
 */
@Mapper
public interface UserMapper {

	/**
	 * 检查用户名密码是否正确
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public User checkUser(@Param("userName") String userName, @Param("password") String password);

	/**
	 * 根据userName查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public User queryUserByUserName(@Param("userName") String userName);

	/**
	 * 新增一个用户
	 * 
	 * @param user
	 * @return
	 */
	public void insertUser(User user);

	/**
	 * 根据id查询用户及其名下所有书的信息
	 * 
	 * @param id
	 * @return
	 */
	public User queryUserWithBookById(@Param("id") int id);

}