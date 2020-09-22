package com.man.dao;

import com.man.common.form.NoticeQuery;
import com.man.entity.Notice;
import com.man.entity.User;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    Integer selectCount(NoticeQuery param);

    List<Notice> selectPage(NoticeQuery param);
}