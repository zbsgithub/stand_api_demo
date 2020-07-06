package com.gzdata.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 将图片转换为Base64<br>
 * 将base64编码字符串解码成img图片
 * 
 * @创建时间 2015-06-01 15:50
 *
 */
@SuppressWarnings({ "unused", "restriction" })
@Component
public class Base64Util {
	private static final Logger logger=LoggerFactory.getLogger(Base64Util.class);

//	public static void main(String[] args) {
//		String imgFile = "C:\\Users\\zbs\\Desktop\\test_pic\\test_pic_01.jpg";// 待处理的图片
//		
//		String strImg = GetImageStr(imgFile);
////		System.out.println(strImg);
//		
//		String imgFilePath = "D:\\new-1232-1234.jpg";// 新生成的图片
//		GenerateImage(strImg,imgFilePath);
//	}

	/**
	 * 
	 * 功能描述：图片转化成base64码
	 *@param imgFile 原始图片绝对路径
	 *
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2018年12月20日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String GetImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
//		String imgFile = "C:\\Users\\zbs\\Desktop\\test_pic\\test_pic_01.jpg";// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			logger.error("read img bytes arise exception:",e);
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	/**
	 * 
	 * 功能描述：base64码转化成新的图片
	 *
	 * @param imgStr 旧的图片base码
	 * @param imgNewAbsPath 新生成的图片绝对路径
	 * @return
	 * 
	 * @author 张兵帅
	 *
	 * @since 2018年12月20日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static boolean GenerateImage(String imgStr,String imgNewAbsPath) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
//			String imgFilePath = "D:\\new-1232.jpg";// 新生成的图片
			OutputStream out = new FileOutputStream(imgNewAbsPath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			logger.error("img decode arise exception:",e);
			return false;
		}
	}
}