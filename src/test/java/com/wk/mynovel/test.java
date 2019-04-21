package com.wk.mynovel;

import org.apache.log4j.Logger;

import com.wk.creater.FantasyPlaceCreater;
import com.wk.tool.TxtReadTool;

public class test {
	private static Logger logger = Logger.getLogger(test.class);

	public static void main(String[] args) {
		StringBuffer sb=TxtReadTool.getSB("E:\\CodingPlace\\eclipse_nomal\\mynovel\\target\\classes\\upload\\我有一座恐怖屋.txt");
		System.out.println(sb);
	}

}
