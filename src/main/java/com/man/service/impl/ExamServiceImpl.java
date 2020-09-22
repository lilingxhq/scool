package com.man.service.impl;

import com.man.common.PageUtils;
import com.man.common.form.ExamQuery;
import com.man.dao.ExamMapper;
import com.man.entity.Exam;
import com.man.exception.SchoolException;
import com.man.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 考试
 */
@Service
@Transactional
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamMapper examMapper;

	@Override
	public void save(Exam exam) {
		examMapper.insertSelective(exam);
	}

	@Override
	public PageUtils selectPage(ExamQuery param) {
		if (param == null || param.getPage() == null || param.getPageSize() == null){
			throw new SchoolException("查询参数不能为空");
		}
		//查询数据总条数
		int count = examMapper.selectCount(param);
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
		List<Exam> exams = null;
		if (count > 0 ){
			exams = examMapper.selectPage(param);
		}
		return new PageUtils(exams,count,pageSize,page);
	}
}
