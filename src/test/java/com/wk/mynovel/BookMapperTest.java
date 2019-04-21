package com.wk.mynovel;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.wk.action.LoginRegisterAction;
import com.wk.dao.BookMapper;
import com.wk.entity.Book;
@RunWith(SpringRunner.class)
//@SpringBootTest
@MybatisTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class BookMapperTest {
	private static Logger logger = Logger.getLogger(BookMapperTest.class);
	@Autowired
	private BookMapper bookMapper;
	@Test
	@Rollback(false)
	public void queryBookByUserId() {
		System.out.println(bookMapper.queryBookByUserId(1));
	}
	
	
	@Test
	public void queryBookByBookId() {
		System.out.println(bookMapper.queryBookByBookId(1));
	}
	@Test
	@Rollback(false)
	public void deleteBook() {
		logger.debug(bookMapper.deleteBook(4));
	}
	
	@Test
	@Rollback(false)
	public void insertBook() {
		Book book=new Book();
		book.setUserId(1);
		book.setBookName("书名test");
		book.setIntro("介绍test");
		book.setType("类型test");
		book.setTags("标签test");
		logger.debug(bookMapper.insertBook(book));
		logger.debug(book);
	}
	@Test
	@Rollback(false)
	public void updateBook() {
		Book book=new Book();
		book.setId(5);
		book.setBookName("书名test3");
		book.setIntro("介绍test3");
		book.setType("类型test3");
		book.setTags("标签test3");
		logger.debug(bookMapper.updateBook(book));
		logger.debug(book);
	}
}
