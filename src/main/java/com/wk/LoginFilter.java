package com.wk;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.wk.entity.User;

@Component
@WebFilter(filterName = "loginFileter", urlPatterns = { "/*" })
public class LoginFilter implements Filter {
	private static Logger logger = Logger.getLogger(Filter.class);

	@Override
	public void destroy() {
//		logger.debug("destroy.......");

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
//		logger.debug("doFilter.......");
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		HttpSession session = req.getSession();
		// 获得用户请求的URI
		String path = req.getRequestURI();
		// 从session取得已经登录验证的凭证 我这里的demo用的是password来作为登录凭证
		User user= (User) session.getAttribute("user");
//		logger.debug("user:"+user);
//		logger.debug("path:"+path);
		if (path.contains("test")||path.contains("/js/")||path.contains("login")||path.contains("checkuser")||path.contains("register")) {// 注意：登录页面千万不能过滤 不然过滤器就。。。。。自行调试不要偷懒！这样记忆深刻
			arg2.doFilter(req, resp);
			return;
		} else {// 如果不是login.jsp进行过滤
			if (user == null) {
				// 跳转到登陆页面
				resp.sendRedirect("login");
			} else {
				// 已经登陆,继续此次请求
				arg2.doFilter(req, resp);
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
//		logger.debug("init.......");
	}

}
