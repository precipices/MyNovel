package com.wk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wk.entity.Chapter;
import com.wk.entity.Character;

public interface CharacterMapper {
	/**
	 * 根据人物id查询人物
	 * 
	 * @param chId
	 * @return
	 */
	@Select(value= {"select * from `character` where id=#{chId}"})
	Character getCharacterByChId(int chId);

	/**
	 * 根据小说id查询人物列表
	 * 
	 * @param bookId
	 * @return
	 */
	@Select(value= {"select *" + 
			"		from `character` where id in(select cht_id from `chp_cht` where" + 
			"		chp_id=#{chId});"})
	List<Character> getCharacterListByBookId(int bookId);

	/**
	 * 根据章节id查询出场人物
	 * 
	 * @param chapterId
	 * @return
	 */
	@Select(value= {"select * from" + 
			"		`chapter` where id in (select chp_id from chp_cht where" + 
			"		cht_id=#{chapterId}) ;"})
	List<Character> getCharacterListByChapterId(int chapterId);

	/**
	 * 根据人物id查询出场章节
	 * 
	 * @param chId
	 * @return
	 */
	@Select(value= {"select * from" + 
			"		`chapter` where id in (select chp_id from chp_cht where" + 
			"		cht_id=#{chapterId}) ;"})
	List<Chapter> getChapterListByChId(int chId);

	/**
	 * 更新人物信息
	 * 
	 * @param ch
	 * @return
	 */
	@Update(value="<script>update `character`" + 
			"		<trim prefix=\"set\" suffixOverrides=\",\">" + 
			"			<if test=\"bookId!=null and bookId!=0\">book_id=#{bookId},</if>" + 
			"			<if test=\"chName!=null and chName!=''\">ch_name=#{chName},</if>" + 
			"			<if test=\"chAttr!=null and chAttr!=''\">ch_attr=#{chAttr},</if>" + 
			"			<if test=\"sex!=null and sex!=0\">sex=#{sex},</if>" + 
			"			<if test=\"belong!=null and belong!=''\">belong=#{belong},</if>" + 
			"			<if test=\"intro!=null and intro!=''\">intro=#{intro},</if>" + 
			"			<if test=\"firstAppearId!=null and firstAppearId!=0\">first_appear_id=#{firstAppearId},</if>" + 
			"			<if test=\"appearTimes!=null and appearTimes!=0\">appear_times=#{appearTimes},</if>" + 
			"			updated=NOW()," + 
			"		</trim>" + 
			"		where id=#{id};</script>")
	int updateCharacter(Character ch);

	/**
	 * 插入新人物
	 * 
	 * @param ch
	 * @return
	 */
	@Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
	@Insert(value= {"INSERT INTO" + 
			"		`character`(book_id,ch_name,ch_attr,sex,belong,intro,first_appear_id,appear_times,created,updated)" + 
			"		values(#{bookId},#{chName},#{chAttr},#{sex},#{belong},#{intro},#{firstAppearId},#{appearTimes},NOW(),NOW());"})
	int insertCharacter(Character ch);

	/**
	 * 根据人物id删除人物
	 * 
	 * @param id
	 * @return
	 */
	@Delete(value="delete from `character` where id = #{chId}")
	int deleteCharacterByChId(int id);
}
