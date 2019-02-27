package fun.wk.entity;
/**
 * 人物角色实体类
 */
public class Character {
	private long id;		//标识ID
	private String name;	//姓名
	private NDate date;		//日期（自定义历法）
	private String info; 	//人物介绍
	private World world;	//所属世界观
	
	private Place place;	//所属势力
	private SpeAttr speAttr;//特殊属性
	private NBook book;		//所属书籍
	private PlayingTime playingTime;//出场时间
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public NDate getDate() {
		return date;
	}
	public void setDate(NDate date) {
		this.date = date;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public SpeAttr getSpeAttr() {
		return speAttr;
	}
	public void setSpeAttr(SpeAttr speAttr) {
		this.speAttr = speAttr;
	}
	public NBook getBook() {
		return book;
	}
	public void setBook(NBook book) {
		this.book = book;
	}
}
