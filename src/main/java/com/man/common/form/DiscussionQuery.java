package com.man.common.form;

public class DiscussionQuery extends QueryParam{

	@Override
	public void setPage(Integer page){
		this.page=page;
	}
	@Override
	public void setPageSize(Integer pageSize){
		this.pageSize=pageSize;
	}
}
