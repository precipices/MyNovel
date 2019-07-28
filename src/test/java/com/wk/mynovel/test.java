package com.wk.mynovel;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.wk.tool.TxtReadTool;

public class test {
	private static Logger logger = Logger.getLogger(test.class);

	@Test
	public static void test() {
		StringBuffer sb = TxtReadTool
				.getSB("E:\\CodingPlace\\eclipse_nomal\\mynovel\\target\\classes\\upload\\我有一座恐怖屋.txt");
		System.out.println(sb);
	}

}
