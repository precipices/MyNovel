package com.wk.action;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping(value= {"/","/main.do","main","index","index.do"})
	public String index() {
		return "index";
	}
	@RequestMapping(value= {"login","login.do"})
	public String login() {
		return "login";
	}

	@RequestMapping(value= {"invalidate","invalidate.do"})
	public String invalidate(HttpSession session) {
		session.invalidate();
		return "login";
	}

	@RequestMapping(value= {"test"})
	public String test() {
		return "test";
	}
}
