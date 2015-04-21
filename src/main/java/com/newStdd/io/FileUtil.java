package com.newStdd.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
	

/**
 * 文件工具
 * */
public class FileUtil {
	/**
	 * 如果路径最后不带路径分隔符，补上路径分隔符
	 * @param PSPath 路径
	 * */
	public static String appendSeparator(String PSPath) {
		String SNewPath = PSPath;
		String SLastChar = PSPath.substring(PSPath.length() - 1);
		if (!SLastChar.equals(File.separator)) {
			SNewPath += File.separator;
		}		
		return SNewPath;
	}
	
	/**
	 * 复制文件
	 * @param PFileSource 源文件
	 * @param PSDestinationPathAndFileName 目标路径及文件名
	 * @param BOverwriteExist 是否覆盖
	 * @exception FileFoundException 源文件不存在时抛出
	 * @exception RootNotFoundException 参数PSDestinationPathAndFileName中根目录不存在时抛出
	 * @exception IOException 读写文件操作错误时抛出
	 * */	
	
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
	 * 获取文件扩展名
	 * @param PFile 文件
	 * @return null：PFile为null时；file无后缀名时
	 * */	
	 public static String getFileExtension(File PFile) {
		 String fileExtension= null;
		 if (PFile != null) {
			 String fileName= PFile.getName();
			 int dot = fileName.lastIndexOf('.'); 
			 if ((dot >-1) && (dot < (fileName.length() - 1))) { 
				 fileExtension= fileName.substring(dot + 1); 
			 } 
		 }
     return fileExtension; 		 
	}
	 
		/**
		 * 去除扩展名，只获取件名
		 * @param PFile 文件
		 * @return null：PFile为null时
		 * */		 
   public static String getFileNameNoExtension(File PFile) { 
  	 String fileNameNoExtension= null;
  	 if (PFile != null) {
  		 String fileName= PFile.getName();
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
   
		/**
		 * 获取包含路径加文件名字符串中的路径
		 * @param PSPathAndFileName 路径加文件名
		 * */	   
 	public static String getPath(String PSPathAndFileName) {
 		File file = new File(PSPathAndFileName);
 		//入参是路径时直接返回入参
 		if (file.isDirectory()) {
 			return PSPathAndFileName;
 		} else {
 			//入参包括文件名时返回路径名
 			return PSPathAndFileName.substring(0, PSPathAndFileName.lastIndexOf(File.separator));
 		}
	}
	   
	
	/**
	 * 建立给定目录中不存在的目录，如给出\a\b\c，系统中存在\a，则新建目录b、c
	 * @param PSPath 指定的目录
	 * @exception RootNotFoundException 指定目录中根目录不存在时抛出
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
}
