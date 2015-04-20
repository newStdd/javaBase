package com.newStdd.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.newStdd.io.FileFoundException;
import com.newStdd.io.RootNotFoundException;
	

/**
 * 文件工具
 * */
public class FileUtil {
	/**
	 * 如果路径最后不带路径分隔符，补上路径分隔符
	 * */
	public static String appendBackwardSlash(String PSPath) {
		String SNewPath = PSPath;
		String SLastChar = PSPath.substring(PSPath.length() - 1);
		if (!SLastChar.equals(File.separator)) {
			SNewPath += File.separator;
		}		
		return SNewPath;
	}
	
	public static String getPath(String PSPathAndFileName) {
		return PSPathAndFileName.substring(0, PSPathAndFileName.lastIndexOf(File.separator));
	}
	
	public static void copy(File PFileSource, String PSDestinationPathAndFileName, boolean BOverwriteExist) throws FileFoundException, RootNotFoundException, IOException {
		//不覆盖已有文件时判断文件是否存在
		if (!BOverwriteExist) {
			File FileTestExist = new File(PSDestinationPathAndFileName);
			if (FileTestExist.exists()) {
				throw new FileFoundException();
			}
		}
		
		//新建路径中不存在的路径
		makeDirectories(getPath(PSDestinationPathAndFileName));
		//每次读取的字节数
		int IByteRead = 0;
		//总共读取的字节数
		@SuppressWarnings("unused")
		int IByteSum = 0;			
		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;		
		try {
			inputStream = new FileInputStream(PFileSource);
			fileOutputStream = new FileOutputStream(PSDestinationPathAndFileName);					
			byte[] AByteBuffer = new byte[1444];
		//循环读取源文件内容写入
			while ((IByteRead = inputStream.read(AByteBuffer)) != -1) {
				IByteSum += IByteRead;
				fileOutputStream.write(AByteBuffer, 0, IByteRead);
			}
		} catch (IOException iOException) {
			throw new IOException();
		} finally {
			inputStream.close();
			fileOutputStream.close();
		}
	}
	
	/**
	 * 建立给定目录中不存在的目录，如给出\a\b\c，系统中存在\a，则新建目录b、c
	 * @param PSPath 文件
	 * @return 正常：file的扩展名</br>
	 * 				 null：file为null时；file无后缀名时
	 * */		
	public static void makeDirectories(String PSPath) throws RootNotFoundException {
		String[] ASSeparatorPath = PSPath.split(File.separator + File.separator);
		//如果根目录不存在返回错误
		File file = new File(ASSeparatorPath[0]);
		if (!file.exists()) {
			throw new RootNotFoundException();
		}		
		//循环过程中累加以得到当前路径
		String SCurrentPath = ASSeparatorPath[0];
		for (int i = 1; i < ASSeparatorPath.length; i++) {
			SCurrentPath += File.separator + ASSeparatorPath[i];
			file = new File(SCurrentPath);
			//当前路径不存在是新建目录及子目录
			if (!file.exists()) {
				file.mkdirs();
			}
		}
	}

	/**
	 * 获取文件扩展名
	 * @param file 文件
	 * @return 正常：file的扩展名</br>
	 * 				 null：file为null时；file无后缀名时
	 * */	
	 public static String getFileExtension(File file) {
		 String fileExtension= null;
		 if (file != null) {
			 String fileName= file.getName();
			 int dot = fileName.lastIndexOf('.'); 
			 if ((dot >-1) && (dot < (fileName.length() - 1))) { 
				 fileExtension= fileName.substring(dot + 1); 
			 } 
		 }
     return fileExtension; 		 
	}
	 
		/**
		 * 获取文件不带扩展名的文件名
		 * @param file 文件
		 * @return 正常：file的不带扩展名的文件名</br>
		 * 				 null：file为null时；file无后缀名时
		 * 				 与文件名相同值：file的文件名无扩展名时
		 * */		 
   public static String getFileNameNoExtension(File file) { 
  	 String fileNameNoExtension= null;
  	 if (file!= null) {
  		 String fileName= file.getName();
       if ((fileName != null) && (fileName.length() > 0)) { 
      	 int dot = fileName.lastIndexOf('.'); 
      	 if ((dot >-1) && (dot < (fileName.length()))) { 
      		 fileNameNoExtension= fileName.substring(0, dot); 
      	 } else {
      		 fileNameNoExtension= fileName;
      	 }
       }   		 
  	 }
     return fileNameNoExtension; 
	} 	 

   
}
