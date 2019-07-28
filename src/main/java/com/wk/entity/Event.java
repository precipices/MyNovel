package com.wk.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

//id int(11) not null auto_increment,		-- 事件id
//book_id int(11) not null,							-- 小说id
//ev_name varchar(31) not null,					-- 事件名
//intro varchar(2048),										-- 事件介绍
//origin varchar(2048),									-- 事件起因
//result varchar(2048),									-- 事件结果
//created datetime DEFAULT NULL,				-- 创建时间
//updated datetime DEFAULT NULL,				-- 更新时间
public class Event {
	private int id;
	private int bookId;
	private String evName;
	private String intro;
	private String origin;
	private String result;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date created;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updated;
	
	private List<Character> chList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getEvName() {
		return evName;
	}

	public void setEvName(String evName) {
		this.evName = evName;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public List<Character> getChList() {
		return chList;
	}

	public void setChList(List<Character> chList) {
		this.chList = chList;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", bookId=" + bookId + ", evName=" + evName + ", intro=" + intro + ", origin="
				+ origin + ", result=" + result + ", created=" + created + ", updated=" + updated + ", chList=" + chList
				+ "]";
	}


}
