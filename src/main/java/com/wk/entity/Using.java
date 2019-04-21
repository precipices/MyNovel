package com.wk.entity;
/**
 * 正在使用表
 * @author pre
 *
 */
public class Using {
	private int userId;
	private int bookId;
	private int chapterId;
	private int contentId;
	/**
	 * book是为了拿到正在使用的章节列表
	 */
	private Book book;
	/**
	 * chapter与content是对应的
	 */
	private Chapter chapter;
	private String content;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getChapterId() {
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Using [userId=" + userId + ", bookId=" + bookId + ", chapterId=" + chapterId + ", contentId="
				+ contentId + ", book=" + book + ", chapter=" + chapter + ", content=" + content + "]";
	}

}
