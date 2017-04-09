package com.eko.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 包含方法:
 * 1.判断网络连接状态
 * 2.删除文件或文件夹
 * 3.获取当前时间
 */
public class Utils {

	private static Utils instance = null;

	private Utils() {
	}

	//带双重锁的单例模式
	public static synchronized Utils getInstance() {
		if (instance == null) {
			synchronized (Utils.class) {
				if (instance == null) {
					instance = new Utils();
				}
			}
		}
		return instance;
	}

	//判断计算机是否联网
	public boolean checkNetwork() {

		BufferedReader bufferedReader = null;
		try {
			Process process = Runtime.getRuntime().exec("ping www.baidu.com");
			bufferedReader = new BufferedReader(new InputStreamReader(
			process.getInputStream()));
			String connectionStr = null;
			while ((connectionStr = bufferedReader.readLine()) != null) {
				//来自 *.*.*.* 的回复: 字节=32 时间= TTL=
				if(connectionStr.contains("TTL")){
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}
	
	//删除文件或文件夹
	public boolean DeleteFolder(String sPath) {
		boolean flag = false;  
	    File file = new File(sPath);  
	    // 判断目录或文件是否存在  
	    if (!file.exists()) {  // 不存在返回 false  
	        return flag;  
	    } else {  
	        // 判断是否为文件  
	        if (file.isFile()) {  // 为文件时调用删除文件方法  
	            return deleteFile(sPath);  
	        } else {  // 为目录时调用删除目录方法  
	            return deleteDirectory(sPath);  
	        }  
	    }  
	} 
	
	public boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
	
	public boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
		boolean flag = false;
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}
	
	//获取当前时间
	public String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}
	
}
