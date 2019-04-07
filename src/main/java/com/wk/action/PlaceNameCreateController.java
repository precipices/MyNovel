package com.wk.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wk.createradapter.FantasyPlaceCreaterAdapter;
/**
 * 处理人名生成器相关请求
 *
 */
@Controller
public class PlaceNameCreateController {
	private static Logger logger = Logger.getLogger(PlaceNameCreateController.class);

	/**
	 * 
	 * @param createType	生成类型
	 * @param createNum		生成数量
	 * @return
	 */
	@RequestMapping("/placenamecreate.do")
	@ResponseBody
	public String namecreate(int createType,int createNum) {
		if(createType==0) {
			List<String> names=new FantasyPlaceCreaterAdapter().createPlaceNames(createNum);
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
