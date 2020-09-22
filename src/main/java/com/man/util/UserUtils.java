package com.man.util;


import com.alibaba.fastjson.JSON;
import com.man.common.constant.AuthorizationConstants;
import com.man.entity.AdminUser;
import net.minidev.json.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {

	private UserUtils(){
		throw new AssertionError();
	}

	public static AdminUser getUser(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String token = request.getHeader(AuthorizationConstants.AUTHORIZATION);

		JSONObject jo = Jwt.getJo(token);
		if (jo == null){
			return null;
		}
		JSONObject user =(JSONObject) jo.get(Jwt.subjectKey);
		String string = JSON.toJSONString(user);
		AdminUser adminUser = JSON.parseObject(string, AdminUser.class);
		return adminUser;
	}
}
