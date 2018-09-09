package com.ant.webPage.controller;

import com.ant.webPage.model.Msg;
import com.ant.webPage.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录相关（管理员）
 *
 * @author Billing
 * @date 2018/8/16 17:16
 */
@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	Msg msg;

	@Autowired private UserService userService;

	/**
	 * 登录
	 */
	@ResponseBody
	@PostMapping("/sys/login")
	public Msg login(String telphone, String password) {
		msg = userService.login(telphone, password);
		return msg;
	}

	/**
	 * 退出
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		//ShiroUtils.logout();
		return "redirect:login.html";
	}

}
