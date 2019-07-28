package com.wk.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
	@Select(value= {"SELECT * FROM user WHERE" + 
			"		user_name" + 
			"		= #{userName} and password = #{password}"})
	public User checkUser(@Param("userName") String userName, @Param("password") String password);

	/**
	 * 根据userName查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	@Select(value= {"select *" + 
			"		from user" + 
			"		where user_name=#{userName}"})
	public User queryUserByUserName(@Param("userName") String userName);

	/**
	 * 新增一个用户
	 * 
	 * @param user
	 * @return
	 */
	@Insert(value= {"INSERT INTO `user`" + 
			"		( user_name, password, name, sex, birthday, created, updated) VALUES (" + 
			"		#{userName}, #{password}, #{name}, #{sex}," + 
			"		#{birthday}, NOW(), NOW());"})
	@Options(useGeneratedKeys=true, keyColumn="id", keyProperty="id")
	public int insertUser(User user);

	/**
	 * 根据id查询用户及其名下所有小说的信息;
	 * 该方法bug:当用户名下没有书时，小说列表会包含一本没有book_id的书
	 * @param id
	 * @return
	 */
	@Deprecated
	public User queryUserWithBookById(@Param("id") int id);

}