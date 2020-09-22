package com.man.service;

import com.man.common.PageUtils;
import com.man.common.form.DiscussionQuery;
import com.man.entity.Discussion;

public interface DiscussionService {

	PageUtils selectPage(DiscussionQuery query);

	void save(Discussion discussion);

	void delete(Integer id);

}
