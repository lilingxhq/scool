package com.man.service.impl;

import com.man.common.PageUtils;
import com.man.common.form.NoticeQuery;
import com.man.dao.NoticeMapper;
import com.man.entity.Notice;
import com.man.entity.User;
import com.man.exception.SchoolException;
import com.man.service.NoticeService;
import com.man.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public PageUtils selectPage(NoticeQuery param) {
		if (param == null || param.getPage() == null || param.getPageSize() == null){
			throw new SchoolException("查询参数不能为空");
		}
		//查询数据总条数
		int count = noticeMapper.selectCount(param);
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
		List<Notice> notices = null;
		if (count > 0 ){
			notices = noticeMapper.selectPage(param);
		}
		return new PageUtils(notices,count,pageSize,page);
	}

	@Override
	public void saveNotice(Notice notice) {
		notice.setUser(UserUtils.getUser().getId());
		notice.setAdddate(new Date());
		notice.setEditdate(new Date());
		noticeMapper.insertSelective(notice);
	}
}
