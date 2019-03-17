package fun.wk.createradapter;

import java.util.ArrayList;
import java.util.List;

import fun.wk.creater.FantasyPlaceCreater;

public class FantasyPlaceCreaterAdapter {
	public FantasyPlaceCreater fantasyPlaceCreater=null;
	public FantasyPlaceCreaterAdapter() {
		this.fantasyPlaceCreater=new FantasyPlaceCreater();
	}
	
	/**
	 * 生成玄幻地名
	 * @param createNum	生成数量
	 * @return
	 */
	public List<String> createPlaceNames(int createNum) {
		List<String> results = new ArrayList<String>();
		for(int i=0;i<createNum;i++) {
			results.add(this.fantasyPlaceCreater.getRandomPlaceName());
		}
		return results;
	}



}
