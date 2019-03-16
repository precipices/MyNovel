package fun.wk.creater;

public class FantasyNameCreater {
	//298个单姓
	private static String[] danxing = {};
	//81个复姓
	private static String[] shuangxing = {};
	
	//普通名字
	private static String[] ming={};
	

	/**
	 * 随机得到一个姓
	 */
	public String getRandomXing() {
		String answer = "";
		int num = danxing.length + shuangxing.length;
		int randInt = (int) (Math.random() * num);
		if (randInt < danxing.length)
			answer = danxing[randInt];
		else
			answer = shuangxing[randInt - danxing.length];
		return answer;
	}

	/**
	 * 随机得到一个单姓
	 */
	public String getRandomDanxing() {
		return danxing[(int)(Math.random()*danxing.length)];
	}

	/**
	 * 随机得到一个复姓
	 */
	public String getRandomShuangxing() {
		return shuangxing[(int)(Math.random()*shuangxing.length)];
	}
	/**
	 * 随机得到一个普通名字
	 */
	public String getRandomMing() {
		return ming[(int)(Math.random()*ming.length)];
	}
	/**
	 * 随机得到一个普通全名
	 */
	public String getRandomXingming() {
		return this.getRandomXing()+this.getRandomMing();
	}
}
