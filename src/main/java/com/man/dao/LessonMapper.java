package com.man.dao;

import com.man.common.form.LessonQuery;
import com.man.entity.Lesson;

import java.util.List;

public interface LessonMapper {
    int deleteByPrimaryKey(Integer lessonId);

    int insert(Lesson record);

    int insertSelective(Lesson record);

    Lesson selectByPrimaryKey(Integer lessonId);

    int updateByPrimaryKeySelective(Lesson record);

    int updateByPrimaryKey(Lesson record);

    Integer selectCount(LessonQuery param);

    List<Lesson> selectPage(LessonQuery param);

    List<Lesson> selectAll();

}