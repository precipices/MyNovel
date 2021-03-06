package com.wk.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wk.createradapter.NomalNameCreaterAdapter;
/**
 * 处理人名生成器相关请求
 *
 */
@Controller
public class NameCreateAction {
	private static Logger logger = Logger.getLogger(NameCreateAction.class);

	/**
	 * 
	 * @param createType	生成类型
	 * @param createNum		生成数量
	 * @param xingNum		单双姓	0全部1单姓2双姓
	 * @param mingNum		单双名	0全部1单名2双名
	 * @param sex			性别		0全部1男2女
	 * @return
	 */
	@RequestMapping("/namecreate.do")
	@ResponseBody
	public String namecreate(int createType,int createNum,int xingNum,int mingNum,int sex) {
		if(createType==0) {
			List<String> names=new NomalNameCreaterAdapter().createNames(createNum, xingNum, mingNum);
			String results=JSONObject.toJSONString(names);
			logger.debug(results);
			return results;
		}else if(createType==1) {
			
		}

		// 记录debug级别的信息
		// logger.debug("This is debug message.");
		// // 记录info级别的信息
		// logger.info("This is info message.");
		// // 记录error级别的信息
		// logger.error("This is error message.");

		return "failed";
	}
}
