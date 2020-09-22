package com.man.service;

import com.man.common.PageUtils;
import com.man.common.form.LessonQuery;
import com.man.entity.Lesson;

import java.util.List;

public interface LessonService {

	PageUtils selectPage(LessonQuery param);

	List<Lesson> selectMyLesson();

	void saveLesson(Lesson lesson);

	void updateLesson(Lesson lesson);

	void deleteLesson(Integer lessonId);

	List<Lesson> selectAll();
}
