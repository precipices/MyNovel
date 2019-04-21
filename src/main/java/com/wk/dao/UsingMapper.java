package com.wk.dao;

import com.wk.entity.Using;

public interface UsingMapper {
	public void insertUsing(int userId);
	
	public Using queryUsingByUserId(int userId);
}
