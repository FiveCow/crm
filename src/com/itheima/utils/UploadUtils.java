package com.itheima.utils;

import java.util.UUID;

/**
 * 文件上传的工具类:
 * @author jt
 *
 */
public class UploadUtils {

	/**
	 * 获得唯一文件名
	 * @param fileName
	 * @return
	 */
	public static String getUUIDFileName(String fileName){
		// aaa.txt  --- xxsekluowerjlsdjr.txt 
		int idx = fileName.lastIndexOf(".");
		// 获得文件的扩展名
		String extention = fileName.substring(idx);
		// 生成唯一的文件名：
		return UUID.randomUUID().toString().replace("-", "")+extention;
	}
	
	/**
	 * 获得目录分离的路径:
	 * @param args
	 */
	public static String getPath(String uuidFileName){
		// 通过唯一文件名 获得 hashCode值.
		int code1 = uuidFileName.hashCode();
		// 让hashCode值&0xf:得到一级目录
		int d1 = code1 & 0xf;
		// 让hashCode 右移 4位 得到一个新的值
		int code2 = code1 >>> 4;
		// 让新的值 & 0xf; :得到二级目录.
		int d2 = code2 & 0xf;
		return "/"+d1+"/"+d2;
	}
	
	
	public static void main(String[] args) {
		System.out.println("ea7e8c4eb01f4b7796fc8a27f944d845.txt".hashCode());
	}
}
