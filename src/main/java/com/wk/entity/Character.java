package com.wk.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

//id int(11) NOT NULL AUTO_INCREMENT,	-- id主键
//book_id int(11) NOT NULL,										-- 小说id
//ch_name varchar(31) NOT NULL,								-- 人物名
//ch_attr varchar(31) NOT NULL,								-- 人物属性（主角/配角/龙套）
//sex int(2),													-- 性别（1：男，0：女）
//belong varchar(31),									-- 所属势力
//intro varchar(2048),									-- 人物简介
//first_appear_id int(11),						-- 首次出场章节id
//appear_times int(11),								-- 出场次数
//created datetime DEFAULT NULL,				-- 创建时间
//updated datetime DEFAULT NULL,				-- 更新时间
public class Character {
	private int id;
	private int bookId;
	private String chName;
	private String chAttr;
	private int sex;
	private String belong;
	private String intro;
	private int firstAppearId;
	private String firstAppear;
	private int appearTimes;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date created;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updated;

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

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getChAttr() {
		return chAttr;
	}

	public void setChAttr(String chAttr) {
		this.chAttr = chAttr;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getFirstAppearId() {
		return firstAppearId;
	}

	public void setFirstAppearId(int firstAppearId) {
		this.firstAppearId = firstAppearId;
	}

	public String getFirstAppear() {
		return firstAppear;
	}

	public void setFirstAppear(String firstAppear) {
		this.firstAppear = firstAppear;
	}

	public int getAppearTimes() {
		return appearTimes;
	}

	public void setAppearTimes(int appearTimes) {
		this.appearTimes = appearTimes;
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

	@Override
	public String toString() {
		return "Character [id=" + id + ", bookId=" + bookId + ", chName=" + chName + ", chAttr=" + chAttr + ", sex="
				+ sex + ", belong=" + belong + ", intro=" + intro + ", firstAppearId=" + firstAppearId
				+ ", firstAppear=" + firstAppear + ", appearTimes=" + appearTimes + ", created=" + created
				+ ", updated=" + updated + "]";
	}

}
