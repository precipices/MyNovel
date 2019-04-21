package com.wk.dao;

import java.util.List;

import com.wk.entity.Book;
import com.wk.entity.Chapter;

public interface BookMapper {
	/**
	 * 根据用户ID查询所有书
	 * 
	 * @param i
	 * @return
	 */
	List<Book> queryBookByUserId(int userId);

	/**
	 * 根据小说id查询小说信息
	 * 
	 * @param bookId
	 * @return
	 */
	Book queryBookByBookId(int bookId);

	/**
	 * 根据小说id删除小说
	 * 
	 * @param bookId
	 * @return
	 */
	int deleteBook(int bookId);

	/**
	 * 新增一本小说
	 * 
	 * @param book
	 * @return
	 */
	int insertBook(Book book);

	/**
	 * 更新小说
	 * 
	 * @param book
	 * @return
	 */
	int updateBook(Book book);
}
