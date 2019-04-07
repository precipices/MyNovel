package com.wk.mynovel;

import org.apache.log4j.Logger;

import com.wk.creater.FantasyPlaceCreater;

public class test {
	private static Logger logger = Logger.getLogger(test.class);

	public static void main(String[] args) {
//		Random r=new Random();
//		Math.random();
//		System.out.println(new Random().nextInt(2));
		FantasyPlaceCreater c=new FantasyPlaceCreater();
		String str="\n";
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++)
				str+=c.getRandomPlaceName()+"\t";
			str+="\n";
		}
		logger.debug(str);
	}

}
