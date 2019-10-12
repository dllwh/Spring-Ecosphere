package org.dllwh;

import java.io.File;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReplacementChain {
	static String path = "/Users/dllwh/Desktop/DevVideo/IDEA视频教程";

	public static void main(String[] args) {
		String[] fileName = getFileName(path);
		for (int i = 0; i < fileName.length; i++) {
			String newName = "";
			renameFile(path, fileName[i], newName);
		}
	}

	// 得到文件名列表
	public static String[] getFileName(String path) {
		File file = new File(path);
		String[] fileName = file.list();
		return fileName;
	}

	// 重命名
	public static void renameFile(String path, String oldName, String newName) {
		if (!oldName.equals(newName)) {// 新的文件名和以前文件名不同时,才有必要进行重命名
			File oldfile = new File(path + File.separator + oldName);
			File newfile = new File(path + File.separator + newName);
			if (!oldfile.exists()) {
				log.info(oldName + " 文件不存在！");
				return;// 重命名文件不存在
			}
			if (newfile.exists())// 若在该目录下已经有一个文件和新文件名相同，则不允许重命名
				log.info(newName + "已经存在！");
			else {
				oldfile.renameTo(newfile);
			}
		} else {
			log.info("新文件名和旧文件名相同...");
		}
	}
}
