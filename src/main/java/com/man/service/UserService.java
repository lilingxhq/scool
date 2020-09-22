package com.man.service;

import com.man.common.PageUtils;
import com.man.common.form.UserQuery;
import com.man.entity.User;

import java.util.List;


public interface UserService {

	List<User> selectAll();

	void insertUser(User user);

	PageUtils selectPage(UserQuery param);

	void updateUser(User user);

	void deleteUser(Integer userId);

	void updatePassword(String oldPassword, String newPassword, String quePassword);
}
