package com.man.controller;


import com.man.common.RestResult;
import com.man.common.anno.IgnoreLogin;
import com.man.common.form.ExamQuery;
import com.man.entity.Exam;
import com.man.service.ExamService;
import com.man.util.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("exam")
@RestController
public class ExamController {
	public static final String EXAM_FILE_PATH = "exam";

	@Autowired
	private ExamService examService;

	@PostMapping("list")
	public RestResult selectPage(ExamQuery param){
		return RestResult.ok().put(RestResult.DATA,examService.selectPage(param));
	}

	@PostMapping("save")
	public RestResult save(Exam exam){
		examService.save(exam);
		return RestResult.ok();
	}

	@RequestMapping("uploadFile")
	@IgnoreLogin
	public RestResult uploadWork(MultipartFile file){
		String s = UploadFileUtils.uploadFile(EXAM_FILE_PATH, file);
		return RestResult.ok().put(RestResult.DATA,s);
	}

	@RequestMapping("download")
	@IgnoreLogin
	public void downLoad(String file , HttpServletResponse response)throws Exception{
		UploadFileUtils.downloadFile(response,EXAM_FILE_PATH,file);
	}
}
