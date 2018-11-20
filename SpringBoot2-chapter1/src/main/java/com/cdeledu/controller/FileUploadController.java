package com.cdeledu.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 文件上传
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2018年10月31日 上午9:38:10
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Controller
@Slf4j
public class FileUploadController {
	@RequestMapping("/uploadPage")
	public String uploadPage() {
		return "uploadPage";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest req, @RequestParam("file") MultipartFile file,
			Model model) {
		try {
			// 判断上传文件是否为空
			if(file.isEmpty()){
				return "上传文件不能为空";
			}
			// 根据时间戳创建新的文件名
			String fileName = System.currentTimeMillis() + file.getOriginalFilename();
			log.info("服务器文件名称"+fileName);
			// 文件类型
			file.getContentType();
			// 文件大小
			file.getSize();
			String destFileName = "D:/up_files/springbootChapter/" + "uploaded" + File.separator + fileName;

			File destFile = new File(destFileName);
			if (!destFile.exists()) {
				destFile.getParentFile().mkdirs();
			}

			// 把上传的文件复制到指定位置
			// file.transferTo(destFile);

			model.addAttribute("fileName", fileName);
		} catch (Exception e) {
			return "上传失败," + e.getMessage();
		}

		return "showImg";
	}
}
