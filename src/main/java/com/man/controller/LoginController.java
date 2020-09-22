package com.man.controller;

import com.man.common.RestResult;
import com.man.common.anno.IgnoreLogin;
import com.man.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制
 */
@RequestMapping("sys")
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping("login")
	@IgnoreLogin
	public RestResult login(String username,String password){
		return RestResult.ok().put(RestResult.DATA,loginService.login(username,password));
	}
}
