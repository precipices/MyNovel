package com.wk.createradapter;

import java.util.ArrayList;
import java.util.List;

import com.wk.creater.NomalNameCreater;

/**
 * 普通人名生成器适配器
 */
public class NomalNameCreaterAdapter {
	private NomalNameCreater nomalNameCreater = null;

	public NomalNameCreaterAdapter() {
		this.nomalNameCreater = new NomalNameCreater();
	}

	/**
	 * 随机生成指定数量的姓名
	 * @param createNum 	生成数量
	 * @param xingNum		单双姓	0全部1单姓2双姓
	 * @param mingNum		单双名	0全部1单名2双名
	 */
	public List<String> createNames(int createNum, int xingNum, int mingNum) {
		List<String> results = new ArrayList<String>();
		for (int i = 0; i < createNum; i++) {
			// 生成姓
			String xing = "";
			if (xingNum == 0)
				xing = this.nomalNameCreater.getRandomXing();
			else if (xingNum == 1)
				xing = this.nomalNameCreater.getRandomDanxing();
			else if (xingNum == 2)
				xing = this.nomalNameCreater.getRandomShuangxing();
			// 生成名
			String ming = "";
			if (mingNum == 0)
				ming = this.nomalNameCreater.getRandomMing();
			else if (mingNum == 1)
				ming = this.nomalNameCreater.getRandomDanming();
			else if (mingNum == 2)
				ming = this.nomalNameCreater.getRandomShuangming();
			// 组合
			results.add(xing + ming);
		}
		return results;
	}
}
