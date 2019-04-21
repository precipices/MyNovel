package com.wk.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 小说表
id int(11) NOT NULL AUTO_INCREMENT,		-- id主键
user_id int(11) NOT NULL,				-- 用户id
book_name varchar(128) NOT NULL,		-- 书名
intro varchar(255),						-- 简介
type varchar(16),						-- 小说类型
tags varchar(256),						-- 小说标签
created datetime DEFAULT NULL,			-- 创建时间
updated datetime DEFAULT NULL,			-- 更新时间
PRIMARY KEY(id)
 * @author pre
 *
 */
public class Book {
	private int id;
	private int userId;
	private String bookName;
	private String intro;
	private String type;
	private String tags;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date created;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date updated;
	private List<Chapter> chapterList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public List<Chapter> getChapterList() {
		return chapterList;
	}
	public void setChapterList(List<Chapter> chapterList) {
		this.chapterList = chapterList;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", userId=" + userId + ", bookName=" + bookName + ", intro=" + intro + ", type="
				+ type + ", tags=" + tags + ", created=" + created + ", updated=" + updated + ", chapterList="
				+ chapterList + "]";
	}
	
}
