package com.cdeledu;

import java.io.File;
import java.util.Date;

public class ReplacementChain {
	static String path = "C:/Users/Administrator/Desktop/b";
	public static void main(String[] args) {
		String[] fileName = getFileName(path);
		for (int i = 0; i < fileName.length; i++) {
			String newName =  String.valueOf(new Date().getTime())+".jpg";// 修改新名字为从第四个字符之后的内容
			renameFile(path, fileName[i], newName);
		}
		System.out.println("OK");
	}
	
	// 得到文件名列表
	public static String[] getFileName(String path) {
		File file = new File(path);
		String[] fileName = file.list();
		return fileName;
	}
	
	// 重命名
	public static void renameFile(String path, String oldname, String newname) {
		if (!oldname.equals(newname)) {// 新的文件名和以前文件名不同时,才有必要进行重命名
			File oldfile = new File(path + "\\" + oldname);
			File newfile = new File(path + "\\" + newname);
			if (!oldfile.exists()) {
				return;// 重命名文件不存在
			}
			if (newfile.exists())// 若在该目录下已经有一个文件和新文件名相同，则不允许重命名
				System.out.println(newname + "已经存在！");
			else {
				oldfile.renameTo(newfile);
			}
		} else {
			System.out.println("新文件名和旧文件名相同...");
		}
	}
	
}
