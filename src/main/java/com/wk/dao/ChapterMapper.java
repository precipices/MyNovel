package com.wk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wk.entity.Chapter;

public interface ChapterMapper {

	/**
	 * 根据书id查询小说的所有章节信息
	 * 
	 * @param bookId
	 * @return
	 */
	@Select(value= {"SELECT * FROM chapter WHERE book_id = #{bookId};"})
	List<Chapter> queryChapterListByBookId(int bookId);

	/**
	 * 根据章节id查询章节信息
	 * 
	 * @param chapterId
	 * @return
	 */
	@Select(value= {"select * from chapter where id = #{chapterId};"})
	Chapter queryChapterByChapterId(int chapterId);

	/**
	 * 插入章节（不包含章节内容）
	 * 
	 * @param chapter
	 * @return
	 */
	@Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
	@Select(value= {"INSERT INTO chapter(title,book_id,content_id,words,created,updated) values(#{title},#{bookId},#{contentId},#{words},NOW(),NOW());"})
	int insertChapter(Chapter chapter);

	/**
	 * 修改更新时间为当前时间
	 * 
	 * @param chapterId
	 * @return
	 */
	@Update(value= {"update chapter set updated=NOW() where id=#{chapterId};"})
	int updateUpdatedTime(int chapterId);

	/**
	 * 根据章节id删除章节
	 * 
	 * @param chapterId
	 * @return
	 */
	@Update(value= {"delete from chapter where id = #{chapterId};"})
	int deleteChapterByChapterId(int chapterId);

	/**
	 * 根据章节id删除章节内容
	 * 
	 * @param chapterId
	 * @return
	 */
	@Update(value= {"delete from content where id in (select content_id from chapter where id = #{chapterId});"})
	int deleteContentByChapterId(int chapterId);

	/**
	 * 更新章节信息
	 * 
	 * @param chapter
	 * @return
	 */
	@Update(value= {"<script>update chapter" + 
			"		<trim prefix=\"set\" suffixOverrides=\",\">" + 
			"			<if test=\"title!=null and title!=''\">title=#{title},</if>" + 
			"			<if test=\"bookId!=null and bookId!=0\">book_id=#{bookId},</if>" + 
			"			<if test=\"contentId!=null and contentId!=0\">content_id=#{contentId},</if>" + 
			"			<if test=\"words!=null and words!=0\">words=#{words},</if>" + 
			"			updated=NOW()," + 
			"		</trim>" + 
			"		WHERE" + 
			"		(id=#{id});</script>"})
	int updateChapter(Chapter chapter);
}
