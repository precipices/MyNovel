package com.wk.creater;

import com.wk.staticdata.PlaceName;

public class FantasyPlaceCreater {
	/**
	 * 随机得到一个形容词1
	 */
	public String getRandomAdj1() {
		return PlaceName.adj1[(int)(Math.random()*PlaceName.adj1.length)];
	}

	/**
	 * 随机得到一个名词1
	 */
	public String getRandomNoun1() {
		return PlaceName.noun1[(int)(Math.random()*PlaceName.noun1.length)];
	}
	/**
	 * 随机得到一个名词的地名后缀
	 */
	public String getRandomNounE() {
		return PlaceName.nounE[(int) (Math.random()*PlaceName.nounE.length)];
	}
	
	/**
	 * 随机得到一个地名
	 */
	public String getRandomPlaceName() {
		String result="";
		result=this.getRandomAdj1()+this.getRandomNoun1()+this.getRandomNounE();
		return result;
	}



}
