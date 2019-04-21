package com.wk.tool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class TxtReadTool {

	/**
	 * 读取文本文件为StringBuffer
	 * @author Administrator
	 * 日期:2015年7月19日
	 * 时间:下午8:59:55
	 * @param filePath	文件的路径
	 * @return	读取文件的内容
	 */
	public static StringBuffer getSB(String filePath) {
		StringBuffer sb = new StringBuffer();
		Reader reader = null;
		BufferedReader br = null;
		try {
			reader = new FileReader(filePath);
			br = new BufferedReader(reader);
			String data = null;
			while ((data = br.readLine()) != null) {
				sb.append(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sb;
	}

}
