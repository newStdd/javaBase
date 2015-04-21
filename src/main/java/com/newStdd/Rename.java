package com.newStdd;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import com.newStdd.io.FileFoundException;
import com.newStdd.io.FileUtil;
import com.newStdd.io.RootNotFoundException;
import com.newStdd.util.DateUtil;

public class Rename {
	public static void main(String[] args) throws ParseException {
		//检测输入
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要处理的目录路径：");
		//要处理的目录字符串名字
		String SProcessPath = scanner.nextLine();
		//要处理的目录文件对象
		File File_processDirectory = new File(SProcessPath);	
		//要处理的目录中的文件列表
		File[] AFfile_processDirectory = File_processDirectory.listFiles();
		System.out.println("请输入输出的文件路径：");
		String SOutPath = scanner.nextLine();
		//如果路径最后不带路径分隔符，补上路径分隔符，不然文件生成路径不对
		SOutPath = FileUtil.appendSeparator(SOutPath);
		for (File file: AFfile_processDirectory) {
			//文件修改日期
			long lDate = file.lastModified();
			while (true) {
				//转换文件修改日期为中文
				String SNewPathAndName = SOutPath + DateUtil.getDateStringZh(lDate) + "." + FileUtil.getFileExtension(file);
				try {
					FileUtil.copy(file, SNewPathAndName, false);
					break;
				} catch (FileFoundException fileFoundException) {
					//如果有重名的文件，则加1秒重命名
					lDate+= 1000;
				}	catch (RootNotFoundException rootNotFoundException) {
					System.out.println("根目录不存在！");
					break;
				} catch (IOException iOException) {
					System.out.println("读写文件错误，请联系作者！");
					break;
				}
			}
		}
		scanner.close();
		System.out.println("处理完毕！");
	}
}