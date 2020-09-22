package com.man.dao;

import com.man.common.form.ExamQuery;
import com.man.entity.Exam;

import java.util.List;

public interface ExamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);

	Integer selectCount(ExamQuery param);

    List<Exam> selectPage(ExamQuery param);
}