package com.wk.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BookUploadAction {
	private static Logger logger=Logger.getLogger(BookUploadAction.class);
	/**
	 * 获取根目录
	 */
	public static String rootPath() {
		// 获取根目录
		File path = null;
		try {
			path = new File(ResourceUtils.getURL("classpath:").getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (!path.exists())
			path = new File("");
		System.out.println("path:" + path.getAbsolutePath());
		return path.getAbsolutePath();
	}
	/**
	 * 根目录所在绝对路径
	 */
	private static String rootPath=rootPath();
	/**
	 * 上传文件储存路径
	 */
	public static final String UPLOAD_PATH = rootPath + "\\upload\\";
	/**
	 * 下载文件储存路径
	 */
	public static final String DOWNLOAD_PATH = rootPath + "\\download\\";
	
	
	@RequestMapping(value="bookUpload.do",method=RequestMethod.POST)
	@ResponseBody
	public String bookUpload(@RequestParam("book_upload_file") MultipartFile file) {
		// 得到文件名
		String fileName = file.getOriginalFilename();
		fileName = fileName.substring(fileName.lastIndexOf('\\') + 1);
		// 设置文件路径
		String filePath = UPLOAD_PATH;
		if (!file.isEmpty()) {
			File targetFile = new File(filePath + fileName);
			try {
				// 如果父文件夹不存在则创建父文件夹
				if (targetFile.getParentFile() != null && !targetFile.getParentFile().exists()) {
					targetFile.getParentFile().mkdirs();
				}
				// 如果文件不存在则创建文件
				if (!targetFile.exists()) {
					targetFile.createNewFile();
				}
				// 将上传的文件内容复制到该文件中
				file.transferTo(targetFile);
				// FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				return "上传失败！";
			}
		}
		logger.debug("上传文件："+filePath + fileName);
		return filePath + fileName;
	}
}
