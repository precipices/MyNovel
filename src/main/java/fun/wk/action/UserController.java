package fun.wk.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import fun.wk.dao.UserMapper;
import fun.wk.entity.User;
import fun.wk.service.UserService;

/**
 * 在定义一个Rest接口时，我们通常会使用GET，POST，PUT，DELETE几种方式来完成我们所需要进行CRUD的一些操作，
 * 我们在这里罗列和教大家在实际开发中的使用，一些基本概念我们就不再赘述，例如使用POST的优缺点，可使用参数的大小限制等地：
 * 
 * GET：一般用于查询数据，不办函数据的更新以及插入操作。由于明文传输的关系，我们一般用来获取一些无关用户的信息。
 * 
 * POST：一般用于数据的插入操作，也是使用最多的传输方式，但是在H5调用时会有跨域的问题，一般使用JSONP来解决。
 * 
 * PUT：我们使用PUT方式来对数据进行更新操作。
 * 
 * DELETE：用于数据删除，注意在数据库内是逻辑删除（改变数据状态，用户不再查询得到，但还保留在数据库内）还是物理删除（真删了）。
 * 
 * @author Administrator
 *
 */
@Controller
public class UserController {
	 private static Logger logger = Logger.getLogger(UserController.class);
	    
	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;

	@RequestMapping("/getUserByName")
	@ResponseBody
	public String getUserByName(String username) {
		User u = userMapper.getUserByName(username);
		

        // 记录debug级别的信息  
//        logger.debug("This is debug message.");  
//        // 记录info级别的信息  
//        logger.info("This is info message.");  
//        // 记录error级别的信息  
//        logger.error("This is error message."); 
    
		
		return JSONObject.toJSONString(u);
	}

}