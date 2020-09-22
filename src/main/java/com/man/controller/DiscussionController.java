package com.man.controller;

import com.man.common.RestResult;
import com.man.common.form.DiscussionQuery;
import com.man.entity.Discussion;
import com.man.entity.RevertDiscussion;
import com.man.service.DiscussionService;
import com.man.service.RevertDiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("discussion")
public class DiscussionController {

	@Autowired
	private DiscussionService discussionService;
	@Autowired
	private RevertDiscussionService revertDiscussionService;

	@PostMapping("list")
	public RestResult selectPage(DiscussionQuery param){
		return RestResult.ok().put(RestResult.DATA,discussionService.selectPage(param));
	}

	@PostMapping("save")
	public RestResult save(Discussion discussion){
		discussionService.save(discussion);
		return RestResult.ok();
	}

	@PostMapping("revertList")
	public RestResult selectAllRevert(Integer curId){
		return RestResult.ok().put(RestResult.DATA,revertDiscussionService.selectAllByChild(curId));
	}


	@PostMapping("saveRevert")
	public RestResult saveRevert(RevertDiscussion revertDiscussion){
		revertDiscussionService.saveRevertDiscussion(revertDiscussion);
		return RestResult.ok();

	}


	@PostMapping("delete")
	public RestResult delete(Integer id){
		discussionService.delete(id);
		return RestResult.ok();
	}
}
