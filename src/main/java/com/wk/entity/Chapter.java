package com.wk.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 章节表
CREATE TABLE chapter(
id int(11) NOT NULL AUTO_INCREMENT,		-- id主键
title varchar(256) NOT NULL,			-- 章节标题
book_id int(11) NOT NULL,				-- 小说ID
content_id int(11) NOT NULL,			-- 章节内容ID
words int(11) DEFAULT 0,				-- 字数
created datetime DEFAULT NULL,			-- 创建时间
updated datetime DEFAULT NULL,			-- 更新时间
PRIMARY KEY(id)
 */
public class Chapter {
	private int id;
	private String title;
	private int bookId;
	private int contentId;
	private int words;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date created;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date updated;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getWords() {
		return words;
	}
	public void setWords(int words) {
		this.words = words;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Chapter [id=" + id + ", title=" + title + ", bookId=" + bookId + ", contentId=" + contentId + ", words="
				+ words + ", created=" + created + ", updated=" + updated + ", content=" + content + "]";
	}

}
