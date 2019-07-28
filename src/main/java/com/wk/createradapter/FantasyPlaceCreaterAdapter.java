package com.wk.createradapter;

import java.util.ArrayList;
import java.util.List;

import com.wk.creater.FantasyPlaceCreater;

/**
 * 玄幻地名生成器适配器，调用FantasyPlaceCreater.class中的方法，以实现复杂的地名生成功能。
 */
public class FantasyPlaceCreaterAdapter {
	public FantasyPlaceCreater fantasyPlaceCreater = null;

	public FantasyPlaceCreaterAdapter() {
		this.fantasyPlaceCreater = new FantasyPlaceCreater();
	}

	/**
	 * 生成玄幻地名
	 * 
	 * @param createNum
	 *            生成数量
	 * @return
	 */
	public List<String> createPlaceNames(int createNum) {
		List<String> results = new ArrayList<String>();
		for (int i = 0; i < createNum; i++) {
			results.add(this.fantasyPlaceCreater.getRandomPlaceName());
		}
		return results;
	}

}
