package com.wk.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
	private int id;
	private String userName;
	private String password;
	private String name;
	private Integer sex;
	private Date birthday;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date created;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date updated;
	private List<Book> bookList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
	
	public Book getBookById(int bookId) {
		for(int i=0;i<this.bookList.size();i++) {
			Book book=bookList.get(i);
			if(book.getId()==bookId)
				return book;
		}
		return null;
	}

	@Override
	public String toString() {
		String bookListStr="";
		if(bookList!=null) {
			for(int i=0;i<bookList.size();i++) {
				bookListStr+=bookList.get(i)+"\n";
			}
		}
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", name=" + name + ", sex="
				+ sex + ", birthday=" + birthday + ", created=" + created + ", updated=" + updated + ", bookList=\n"
				+ bookListStr + "]";
	}

}
