package org.dllwh.common.util;

import java.io.IOException;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 文件上传工具类
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年12月22日 下午11:51:14
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
public class FileUploadUtils {

	/** 默认上传的地址 */
	@Value("")
	private static String defaultBaseDir = "";
	/** 默认图片文件类型jpg */
	public static final String IMAGE_JPG_EXTENSION = ".jpg";

	/**
	 * @方法描述:以默认配置进行文件上传
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static final String upload(MultipartFile file) throws IOException {
		return "";
	}

	/**
	 * @方法描述:根据文件路径上传
	 * @param baseDir 相对应用的基目录
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static final String upload(String baseDir, MultipartFile file) throws IOException {
		return "";
	}

	/**
	 * @方法描述:TODO(这里用一句话描述这个方法的作用)
	 * @param baseDir
	 * @param file
	 * @param extension
	 * @return
	 * @throws FileSizeLimitExceededException
	 * @throws IOException
	 * @throws FileNameLengthLimitExceededException
	 */
	public static final String upload(String baseDir, MultipartFile file, String extension)
			throws IOException, FileUploadException {
		return "";
	}
}
