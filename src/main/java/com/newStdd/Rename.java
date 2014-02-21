package com.newStdd;

import java.io.File;
import java.text.ParseException;

import com.newStdd.util.DateUtil;

public class Rename {
	public static void main(String[] args) throws ParseException {
		File directory= new File("d:\\dcim");
		File[] files= directory.listFiles();
		for (File file: files) {
			System.out.println(file.getName()+ ": "+ DateUtil.getDateString(file.lastModified(), DateUtil.dateFormatYyyy_MM_dd_HH_mm_ss));
//			file.renameTo(new File("junk.dat"));
		}
	}
}