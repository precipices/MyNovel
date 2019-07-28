package com.wk.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wk.entity.ChapterContent;

public interface ContentMapper {
	/**
	 * 根据内容id查询章节内容
	 * 
	 * @param id
	 * @return
	 */
	@Select(value= {"SELECT content" + 
			"		FROM content WHERE id = #{id}"})
	String queryContentByContentId(int contentId);

	/**
	 * 根据内容id和内容修改章节内容
	 * 
	 * @param contentId
	 * @param content
	 * @return
	 */
	@Update(value= {"update content set content=#{content} where" + 
			"		id=#{contentId};"})
	int updateContentByContentId(@Param("contentId") int contentId, @Param("content") String content);

	/**
	 * 插入章节内容，返回章节内容id
	 * 
	 * @param content
	 * @return
	 */
	@Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
	@Insert(value= {"INSERT INTO content (content) values(#{content});"})
	int insertContent(ChapterContent content);
	
	
}
