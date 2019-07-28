package com.wk.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.wk.entity.Using;

public interface UsingMapper {
	/**
	 * 为用户新增一张正在使用表 
	 * @param userId
	 * @return
	 */
	@Insert(value= {"INSERT INTO `using`" + 
			"		(user_id) VALUES (#{userId});"})
	public int insertUsing(int userId);
	/**
	 * 查询用户的正在使用表
	 * @param userId
	 * @return
	 */
	@Select(value= {"Select * from `using` where user_id = #{userId};"})
	public Using queryUsingByUserId(int userId);
}
