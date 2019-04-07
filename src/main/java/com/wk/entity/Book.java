package com.wk.entity;

import java.util.Date;

public class Book {
	private int id;
	private int userId;
	private String bookName;
	private Date created;
	private Date updated;
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
		return "Book [id=" + id + ", userId=" + userId + ", bookName=" + bookName + ", created=" + created
				+ ", updated=" + updated + "]";
	}
	
}
