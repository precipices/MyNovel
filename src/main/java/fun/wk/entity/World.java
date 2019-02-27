package fun.wk.entity;

public class World {
	private String name;		//大世界名称
	private String worldType;	//世界类型（修真，都市等）
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWorldType() {
		return worldType;
	}
	public void setWorldType(String worldType) {
		this.worldType = worldType;
	}
}
