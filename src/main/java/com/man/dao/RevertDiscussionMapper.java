package com.man.dao;

import com.man.entity.RevertDiscussion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RevertDiscussionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RevertDiscussion record);

    int insertSelective(RevertDiscussion record);

    RevertDiscussion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RevertDiscussion record);

    int updateByPrimaryKey(RevertDiscussion record);

	List<RevertDiscussion> selectByParent(@Param("parentId") Integer parentId,@Param("curId") Integer curId);
}