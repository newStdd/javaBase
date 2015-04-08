package com.newStdd;

import java.io.File;

import com.newStdd.util.FileUtil;

public class Test {
	public static void main(String[] args) {
//		File a = new File("c:");
//		if (a.exists()) {
//			System.out.println("hehe");
//		}
//		System.out.println(FileUtil.copy(a, "c:\\b\\c.txt", false));
//		System.out.println(FileUtil.makeDirectories("d:\\b\\a\\b"));
		String a="d:\\b\\a\\b\\a.txt";
		System.out.println(a.substring(0, a.lastIndexOf(File.separator)));
		;
//		System.out.println((("c:"+File.separator+"b"+File.separator+"a"+File.separator+"b\\").split(File.separator+File.separator)).length);
	}
}
