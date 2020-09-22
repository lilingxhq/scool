package com.man.service;

import com.man.entity.Menu;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface LoginService {


	Map<String,Object> login(String username, String password);

	List<Menu> selectMyMenu();

	List<Menu> selectMyMenuByChildren();

	Set<String> selectMyPermission();

}
