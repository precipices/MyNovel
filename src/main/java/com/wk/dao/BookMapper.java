package com.wk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wk.entity.Book;

/**
 * 小说表数据dao
 * 
 * @author pre
 *
 */
public interface BookMapper {
	/**
	 * 根据用户ID查询所有书
	 * 
	 * @param i
	 * @return
	 */
	@Select(value = { "SELECT * FROM book where user_id=#{userId}" })
	List<Book> queryBookByUserId(int userId);

	/**
	 * 根据小说id查询小说信息
	 * 
	 * @param bookId
	 * @return
	 */
	@Select(value = { "select * from book where id = #{bookId}" })
	Book queryBookByBookId(int bookId);

	/**
	 * 根据小说id删除小说
	 * 
	 * @param bookId
	 * @return
	 */
	@Update(value = { "delete from book where id = #{bookId}" })
	int deleteBook(int bookId);

	/**
	 * 新增一本小说
	 * 
	 * @param book
	 * @return
	 */
	@Insert(value = {
			"INSERT INTO book (user_id,book_name,intro, type, tags, created,updated) values(#{userId},#{bookName},#{intro},#{type},#{tags},NOW(),NOW());" })
	// option注解标签useGeneratedKeys=true表示使用数据库自动增长的主键，keyColumn用于指定数据库table中的主键，keyProperty用于指定传入对象的成员变量。
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	int insertBook(Book book);

	/**
	 * 更新小说
	 * 
	 * @param book
	 * @return
	 */
	@Update(value= {"<script>update book" + 
			"		<trim prefix=\"set\" suffixOverrides=\",\">" + 
			"			<if test=\"userId!=null and userId!=0\">user_id=#{userId},</if>" + 
			"			<if test=\"bookName!=null and bookName!=''\">book_name=#{bookName},</if>" + 
			"			<if test=\"intro!=null and intro!=''\">intro=#{intro},</if>" + 
			"			<if test=\"type!=null and type!=''\">type=#{type},</if>" + 
			"			<if test=\"tags!=null and tags!=''\">tags=#{tags},</if>" + 
			"			updated=NOW()," + 
			"		</trim>" + 
			"		WHERE" + 
			"		(id=#{id});</script>"})
	int updateBook(Book book);
}
