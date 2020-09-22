package com.man.service;

import com.man.common.PageUtils;
import com.man.common.form.NoticeQuery;
import com.man.entity.Notice;

public interface NoticeService {

	PageUtils selectPage(NoticeQuery param);

	void saveNotice(Notice notice);
}
