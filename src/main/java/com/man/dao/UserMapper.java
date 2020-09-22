package com.man.dao;

import com.man.common.form.UserQuery;
import com.man.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAll();

    User selectByUsername(@Param("username") String username);

	Integer selectCount(UserQuery param);

    List<User> selectPage(UserQuery param);
}