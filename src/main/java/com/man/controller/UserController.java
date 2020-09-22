package com.man.controller;

import com.man.common.RestResult;
import com.man.common.anno.Permission;
import com.man.common.form.QueryParam;
import com.man.common.form.UserQuery;
import com.man.entity.User;
import com.man.service.UserService;
import com.man.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户信息
 * @author sz
 */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("selectAll")
	public List<User> selectAll(){
		return userService.selectAll();
	}

	@PostMapping("list")
	public RestResult selectList(UserQuery param){
		return RestResult.ok().put(RestResult.DATA,userService.selectPage(param));
	}

	@PostMapping("myInfo")
	public RestResult selectMyself(){
		return RestResult.ok().put(RestResult.DATA,UserUtils.getUser());
	}

	@RequestMapping("save")
	@Permission("system:user")
	public RestResult saveUser(User user){
		userService.insertUser(user);
		return RestResult.ok();
	}

	@PostMapping("update")
	@Permission("system:user")
	public RestResult updateUser(User user){
		userService.updateUser(user);
		return RestResult.ok();
	}

	@PostMapping("delete")
	@Permission("system:user")
	public RestResult deleteUser(Integer userId){
		userService.deleteUser(userId);
		return RestResult.ok();
	}

	@PostMapping("updatePassword")
	public RestResult updatePassword(String oldPassword,String newPassword,String quePassword){
		userService.updatePassword(oldPassword,newPassword,quePassword);
		return RestResult.ok();
	}
}
