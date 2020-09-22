package com.man.controller;


import com.man.common.RestResult;
import com.man.common.form.NoticeQuery;
import com.man.entity.Notice;
import com.man.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("notice")
@RestController
public class NoticeController {

	@Autowired
	private NoticeService noticeService;


	@PostMapping("list")
	public RestResult selectList(NoticeQuery query){
		return RestResult.ok().put(RestResult.DATA,noticeService.selectPage(query));
	}


	@PostMapping("save")
	public RestResult save(Notice notice){
		noticeService.saveNotice(notice);
		return RestResult.ok();
	}

}
