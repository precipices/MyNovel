package com.wk.dao;

import java.util.List;

import com.wk.entity.Book;
public interface BookMapper {
	/**
	 * 根据用户ID查询所有书
	 * @param i
	 * @return
	 */
	List<Book> queryBookByUserId(int i);
}
