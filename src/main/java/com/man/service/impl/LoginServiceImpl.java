package com.man.service.impl;

import com.man.common.constant.AuthorizationConstants;
import com.man.dao.MenuMapper;
import com.man.dao.UserMapper;
import com.man.entity.AdminUser;
import com.man.entity.Menu;
import com.man.entity.User;
import com.man.exception.SchoolException;
import com.man.service.LoginService;
import com.man.util.Jwt;
import com.man.util.MD5Utils;
import com.man.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public Map<String,Object> login(String username, String password) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			throw new SchoolException("用户名或密码不能为空");
		}
		User user = userMapper.selectByUsername(username);
		if (user == null){
			throw new SchoolException("用户不存在");
		}
		password = MD5Utils.md5Encry(password);
		if (!user.getPassword().equals(password)){
			throw new SchoolException("密码错误");
		}
		Map<String, Object> payload = new HashMap<>(1);
		AdminUser adminUser = new AdminUser();
		BeanUtils.copyProperties(user,adminUser);
		payload.put(Jwt.subjectKey,adminUser);
		payload.put(Jwt.tokenKey, user.getId());
		payload.put(Jwt.roleKey,user.getRoleid());
		payload.put(Jwt.tokenExtKey, Jwt.extTime());
		String token = Jwt.createToken(payload);
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put(AuthorizationConstants.AUTHORIZATION,token);
		return resultMap;
	}

	@Override
	public List<Menu> selectMyMenuByChildren(){
		List<Menu> menus = this.selectMyMenu();
		Map<Integer,Menu> firstMenuMap = new HashMap<>();
		for (Menu menu:menus) {
			if (menu.getParentid() == null){
				firstMenuMap.put(menu.getMenuid(),menu);
			}
			if (menu.getParentid() != null){
				Menu  firstMenu = firstMenuMap.get(menu.getParentid());
				if (firstMenu == null){
					firstMenu = menuMapper.selectByPrimaryKey(menu.getParentid());
					firstMenuMap.put(firstMenu.getMenuid(),firstMenu);
				}
				if (firstMenu != null){
					List<Menu> secondMenus = firstMenu.getChildren();
					if (secondMenus == null){
						secondMenus = new ArrayList<>();
						firstMenu.setChildren(secondMenus);
					}
					secondMenus.add(menu);
				}
			}
		}
		return new ArrayList<>(firstMenuMap.values());
	}

	/**
	 * 查询我的菜单
	 * @return
	 */
    @Override
    public List<Menu> selectMyMenu() {
		return selectCurUserMenu();
    }

    private List<Menu> selectCurUserMenu(){
		AdminUser user = UserUtils.getUser();
		if (user == null ){
			throw new SchoolException("无法获取当前用户");
		}
		return menuMapper.selectByUserid(user.getId());
	}

	/**
	 * 查询我的所有权限
	 * @return
	 */
	@Override
	public Set<String> selectMyPermission() {
		List<Menu> menus = selectCurUserMenu();
		Set<String> permission = new HashSet<>();
		if (menus !=null && !menus.isEmpty()){
			menus.forEach(menu -> permission.add(menu.getPermission()));
		}
		return permission;
	}
}
