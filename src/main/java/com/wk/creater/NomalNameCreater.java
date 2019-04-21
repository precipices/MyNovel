package com.wk.creater;

import com.wk.tool.Name;

/**
 * 普通人名生成器
 */
public class NomalNameCreater {

	/**
	 * 随机得到一个单姓
	 */
	public String getRandomDanxing() {
		return Name.danxing[(int)(Math.random()*Name.danxing.length)];
	}

	/**
	 * 随机得到一个复姓
	 */
	public String getRandomShuangxing() {
		return Name.shuangxing[(int)(Math.random()*Name.shuangxing.length)];
	}
	/**
	 * 随机得到一个单字名
	 */
	public String getRandomDanming() {
		return Name.danming[(int) (Math.random()*Name.danming.length)];
	}
	
	/**
	 * 随机得到一个双字名
	 */
	public String getRandomShuangming() {
		return Name.shuangming[(int)(Math.random()*Name.shuangming.length)];
	}

	/**
	 * 随机得到一个姓
	 * @param proportion 单姓所占的概率
	 */
	public String getRandomXing(double probability) {
		if(Math.random()<probability) {
			return this.getRandomDanxing();
		}else
			return this.getRandomShuangxing();
	}
	/**
	 * 随机得到一个姓(等概率单双姓)
	 */
	public String getRandomXing() {
		double probability=(double)Name.danxing.length/(Name.danxing.length+Name.shuangxing.length);
		return this.getRandomXing(probability);
	}
	/**
	 * 随机得到一个名
	 * @param proportion 单名所占的概率
	 */
	public String getRandomMing(double probability) {
		if(Math.random()<probability) {
			return this.getRandomDanming();
		}else
			return this.getRandomShuangming();
	}
	/**
	 * 随机得到一个名(等概率单双名)
	 */
	public String getRandomMing() {
		double probability=(double)((double)Name.danming.length/(Name.danming.length+Name.shuangming.length));
		return this.getRandomMing(probability);
	}
}
