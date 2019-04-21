package com.wk.mynovel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.wk.dao.BookMapper;
import com.wk.dao.ChapterMapper;
@RunWith(SpringRunner.class)
//@SpringBootTest
@MybatisTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class ChapterMapperTest {
	@Autowired
	private ChapterMapper chapterMapper;

	@Test
	@Rollback(false)
	public void queryContentByContentId() {
		System.out.println(chapterMapper.queryContentByContentId(1));
	}

	@Test
	public void queryBookByBookId() {
		System.out.println(chapterMapper.queryChapterListByBookId(1));
	}

	@Test
	public void queryChapterByChapterId() {
		System.out.println(chapterMapper.queryChapterByChapterId(1));
	}}
