package com.man.common.form;

public class NoticeQuery extends QueryParam {
	private Integer lessonId;

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
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
