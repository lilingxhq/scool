package com.man.service;

import com.man.common.PageUtils;
import com.man.common.form.ExamQuery;
import com.man.entity.Exam;

public interface ExamService {

	void save(Exam exam);

	PageUtils selectPage(ExamQuery param);
}
