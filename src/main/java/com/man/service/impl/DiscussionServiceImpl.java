package com.man.service.impl;

import com.man.common.PageUtils;
import com.man.common.form.DiscussionQuery;
import com.man.dao.DiscussionMapper;
import com.man.entity.Discussion;
import com.man.entity.Exam;
import com.man.exception.SchoolException;
import com.man.service.DiscussionService;
import com.man.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DiscussionServiceImpl implements DiscussionService {

	@Autowired
	private DiscussionMapper discussionMapper;

	@Override
	public PageUtils selectPage(DiscussionQuery param) {
		if (param == null || param.getPage() == null || param.getPageSize() == null){
			throw new SchoolException("查询参数不能为空");
		}
		//查询数据总条数
		int count = discussionMapper.selectCount(param);
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
		List<Discussion> discussions = null;
		if (count > 0 ){
			discussions = discussionMapper.selectPage(param);
		}
		return new PageUtils(discussions,count,pageSize,page);
	}

	@Override
	public void save(Discussion discussion) {
		discussion.setUserid(UserUtils.getUser().getId());
		discussion.setAdddate(new Date());
		discussion.setEditdate(new Date());
		discussionMapper.insertSelective(discussion);
	}

	/**
	 * 用户只允许删除自己发布的帖子
	 * @param id
	 */
	@Override
	public void delete(Integer id) {
		Discussion discussion = discussionMapper.selectByPrimaryKey(id);
		if (discussion != null ){
			if (!UserUtils.getUser().getId().equals(discussion.getUserid())){
				throw new SchoolException("只允许删除自己的帖子");
			}else{
				discussionMapper.deleteByPrimaryKey(id);
			}
		}
	}
}
