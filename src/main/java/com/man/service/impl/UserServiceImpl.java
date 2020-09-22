package com.man.service.impl;

import com.man.common.PageUtils;
import com.man.common.constant.AuthorizationConstants;
import com.man.common.form.UserQuery;
import com.man.dao.UserMapper;
import com.man.entity.User;
import com.man.exception.SchoolException;
import com.man.service.UserService;
import com.man.util.IDUtils;
import com.man.util.MD5Utils;
import com.man.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户相关service
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 查询所有用户信息
	 * @return
	 */
	@Override
	public List<User> selectAll() {
		return userMapper.selectAll();
	}

	@Override
	@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
	public PageUtils selectPage(UserQuery param) {
		if (param == null || param.getPage() == null || param.getPageSize() == null){
			throw new SchoolException("查询参数不能为空");
		}
		//查询数据总条数
		int count = userMapper.selectCount(param);
		//查询到当前页
		int page = 0;
		int totalPage = 1;
		int pageSize = 0;
		//总页数
		if (param.getPage() != null && param.getPageSize() != null){
			page = param.getPage();
			pageSize = param.getPageSize();
			totalPage =(int)Math.ceil((double)count / param.getPageSize());
			param.setPage((page-1)*param.getPageSize());
		}
		List<User> users = null;
		if (count > 0 ){
			users = userMapper.selectPage(param);
		}
		return new PageUtils(users,count,pageSize,page);
	}

	/**
	 * 插入用户
	 * @param user
	 */
	@Override
	public void insertUser(User user){
		// 用户名密码昵称不能为空
		if (user == null || StringUtils.isEmpty(user.getUsername())
				|| StringUtils.isEmpty(user.getPassword())
				|| StringUtils.isEmpty(user.getNickname())){
			throw new SchoolException("用户名或密码不能为空");
		}

		User oldUser = userMapper.selectByUsername(user.getUsername());
		if (oldUser != null){
			throw new SchoolException("用户名已经存在");
		}
		String salt = IDUtils.getUUID();
		user.setSalt(salt);
		// 进行md5加密
		String password = MD5Utils.md5Encry(user.getPassword());
		user.setPassword(password);
		user.setAdddate(new Date());
		user.setEditdate(new Date());
		userMapper.insert(user);
	}

	@Override
	public void updateUser(User user) {
		if (user == null){
			throw new SchoolException("未知的修改用户");
		}
		User oldUser = userMapper.selectByUsername(user.getUsername());
		if (oldUser == null){
			throw new SchoolException("未找到该用户");
		}
		if (StringUtils.isNotEmpty(user.getPassword())){
			oldUser.setPassword(MD5Utils.md5Encry(user.getPassword()));
		}
		oldUser.setRoleid(user.getRoleid());
		oldUser.setEditdate(new Date());
		userMapper.updateByPrimaryKey(oldUser);
	}

	@Override
	public void deleteUser(Integer userId){
		if (userId == null){
			return;
		}
		if (AuthorizationConstants.ADMIN_USER_ID.equals(userId)){
			throw new SchoolException("超级管理员用户不允许删除");
		}
		userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public void updatePassword(String oldPassword, String newPassword, String quePassword) {
		if (StringUtils.isEmpty(oldPassword)){
			throw new SchoolException("原密码不能为空");
		}
		if (StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(quePassword)){
			throw new SchoolException("新密码和确定密码不能为空");
		}
		if (!newPassword.equals(quePassword)){
			throw new SchoolException("新密码和确定密码不同");
		}
		Integer id = UserUtils.getUser().getId();
		User user = userMapper.selectByPrimaryKey(id);
		if(!user.getPassword().equals(MD5Utils.md5Encry(oldPassword))){
			throw new SchoolException("原密码错误");
		}
		user.setPassword(MD5Utils.md5Encry(newPassword));
		userMapper.updateByPrimaryKey(user);
	}
}
