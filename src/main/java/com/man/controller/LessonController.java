package com.man.controller;

import com.man.common.RestResult;
import com.man.common.anno.Permission;
import com.man.common.form.LessonQuery;
import com.man.entity.Lesson;
import com.man.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lesson")
public class LessonController {


	@Autowired
	private LessonService lessonService;


	@PostMapping("list")
	public RestResult selectList(LessonQuery param){
		return RestResult.ok().put(RestResult.DATA,lessonService.selectPage(param));
	}


	@RequestMapping("all")
	public RestResult selectAll(){
		return RestResult.ok().put(RestResult.DATA,lessonService.selectAll());
	}

	@PostMapping("save")
	@Permission("lesson")
	public RestResult save(Lesson lesson){
		lessonService.saveLesson(lesson);
		return RestResult.ok();
	}

	@PostMapping("update")
	@Permission("lesson")
	public RestResult update(Lesson lesson){
		lessonService.updateLesson(lesson);
		return RestResult.ok();
	}

	@PostMapping("delete")
	@Permission("lesson")
	public RestResult delete(Integer lessonId){
		lessonService.deleteLesson(lessonId);
		return RestResult.ok();
	}
}
