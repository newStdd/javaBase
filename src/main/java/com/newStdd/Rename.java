package com.newStdd;

import java.io.File;
import java.text.ParseException;

import com.newStdd.util.DateUtil;
import com.newStdd.util.FileUtil;

public class Rename {
	public static void main(String[] args) throws ParseException {
		String directory= "d:\\新建文件夹\\";
		File fileDirectory= new File(directory);
		File[] files= fileDirectory.listFiles();
		for (File file: files) {
			String newFileName=DateUtil.getDateStringZh(file.lastModified());
			file.renameTo(new File(directory+ newFileName+ "."+ FileUtil.getFileExtension(file)));
		}
	}
}