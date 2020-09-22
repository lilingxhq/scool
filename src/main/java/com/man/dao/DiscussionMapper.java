package com.man.dao;

import com.man.common.form.DiscussionQuery;
import com.man.entity.Discussion;

import java.util.List;

public interface DiscussionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Discussion record);

    int insertSelective(Discussion record);

    Discussion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Discussion record);

    int updateByPrimaryKey(Discussion record);

	Integer selectCount(DiscussionQuery param);

    List<Discussion> selectPage(DiscussionQuery param);
}