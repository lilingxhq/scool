package com.man.util;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

public class UploadFileUtils {

	private static final String FILE_PATH = "D:\\school_mana\\";

	public static String uploadFile(String paFileName, MultipartFile uploadFile){
		String realPath = FILE_PATH+paFileName;

		File file = new File(realPath);
		if(!file.exists()){
			file.mkdirs();
		}
		String path = file.getPath();
		//定义新的文件名
		String filename = uploadFile.getOriginalFilename();
		String newFileName = IDUtils.getUUID()+"_"+filename;
		String qName = path+"\\"+newFileName;
		File newFile = new File(qName);
		try {
			uploadFile.transferTo(newFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newFileName;
	}


	public static void downloadFile(HttpServletResponse response, String paFileName,String fileName) throws Exception {
		String filePath = FILE_PATH+paFileName+"\\"+fileName;
		File file = new File(filePath);
		if (!file.exists()) {
			response.sendError(404, "File not found!");
			return;
		}

		BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset(); // 非常重要
		boolean isOnLine = false;
		if (isOnLine) { // 在线打开方式
			URL u = new URL("file:///" + filePath);
			response.setContentType(u.openConnection().getContentType());
			response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
			// 文件名应该编码成UTF-8
		} else { // 纯下载方式
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
		}
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0){
			out.write(buf, 0, len);
		}
		br.close();
		out.close();

//		InputStream inputStream = new FileInputStream(file);
//
//		OutputStream out = response.getOutputStream();
//		byte[] bytes = new byte[1024];
//		while (inputStream.read(bytes) != -1){
//			out.write(bytes);
//		}
//		// 防止数据丢失。 不清空数据传输80%
//		out.flush();
//		out.close();
//		//响应的编码格式
//		response.setCharacterEncoding("UTF-8");
//		// 下载响应格式
//		response.setContentType("application/octet-stream;charset=UTF-8");
//		// 防止中文乱码，设置文本的编码格式，放止下载后文件名乱码。
//		fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
//		response.setHeader("Content-Disposition", "attachment; filename="+ fileName);

	}
}
