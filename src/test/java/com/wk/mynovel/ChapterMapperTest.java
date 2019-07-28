package com.wk.mynovel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.wk.dao.ChapterMapper;
import com.wk.entity.Chapter;
import com.wk.service.CrossDaoService;

@RunWith(SpringRunner.class)
@SpringBootTest
// @MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChapterMapperTest {
	@Autowired
	private ChapterMapper chapterMapper;
	@Autowired
	private CrossDaoService crossMapper;

	@Test
	public void queryBookByBookId() {
		System.out.println(chapterMapper.queryChapterListByBookId(1));
	}

	@Test
	public void queryChapterByChapterId() {
		System.out.println(chapterMapper.queryChapterByChapterId(1));
	}

	@Test
	@Rollback(false)
	public void insertChapter() {
		Chapter chapter = new Chapter();
		chapter.setBookId(1);
		chapter.setContentId(3);
		chapter.setTitle("测试章节3");
		chapter.setWords(0);
		System.out.println(chapterMapper.insertChapter(chapter));
	}

	@Test
	@Rollback(false)
	public void insertChapterWithContent() {
		Chapter chapter = new Chapter();
		chapter.setBookId(1);
		chapter.setTitle("测试章节9");
		chapter.setWords(0);
		System.out.println(crossMapper.insertChapterWithContent(chapter, "测试章节9内容"));
	}

	@Test
	@Rollback(false)
	public void deleteChapter() {
		System.out.println(chapterMapper.deleteContentByChapterId(1));
		System.out.println(chapterMapper.deleteChapterByChapterId(1));
	}
}
