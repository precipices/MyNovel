package fun.wk.random;

import fun.wk.entity.World;

/**
 * 大世界类World随机生成算法
 */
public class RandomWorld {
	public World getARandomWorld() {
		World w=new World();
		w.setName("");
		w.setWorldType("");
		return new World();
	}
	//根据世界类型生成一个随机的世界名
	private String getAWorldName(String worldType) {
		return "";
	}
	//生成一个随机世界类型
	private String getAWorldType() {
		return "";
	}
}
