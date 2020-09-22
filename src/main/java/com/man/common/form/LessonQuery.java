package com.man.common.form;


public class LessonQuery extends QueryParam{
	private Integer lessonTeacher;


	public Integer getLessonTeacher() {
		return lessonTeacher;
	}

	public void setLessonTeacher(Integer lessonTeacher) {
		this.lessonTeacher = lessonTeacher;
	}

	@Override
	public void setPage(Integer page){
		this.page=page;
	}
	@Override
	public void setPageSize(Integer pageSize){
		this.pageSize=pageSize;
	}
}
