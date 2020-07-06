package com.gzdata.common.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;


public class ResultUtil {

	private static String characterEncoding = "UTF-8";

	private static String contentType = "text/html";

	/**
	 * 初始化工具
	 * 
	 * @param characterEncoding
	 *            编码
	 * @param contentType
	 */
	public static void init(String characterEncoding, String contentType) {
		ResultUtil.characterEncoding = characterEncoding;
		ResultUtil.contentType = contentType;
	}

	/**
	 * 将对象以JSON字符串的形式写入Response
	 * 
	 * @param result
	 *            结果对象
	 * @param response
	 * @throws IOException
	 */
	public static void write(Object result, HttpServletResponse response)
			throws IOException {
		response.setCharacterEncoding(characterEncoding);
		response.setContentType(contentType);
		response.getWriter().write(jsonToString(result));
	}

	/**
	 * 返回执行JSONP回调函数，传入结果对象
	 * 
	 * @param callback
	 *            回调函数名
	 * @param result
	 *            结果对象
	 * @param response
	 * @throws IOException
	 */
	public static void write(String callback, Object result,
			HttpServletResponse response) throws IOException {
		response.setCharacterEncoding(characterEncoding);
		response.setContentType(contentType);
		response.getWriter().write(callback + "(" + jsonToString(result) + ")");
	}

	/**
	 * 对象转成JSON字符串
	 * 
	 * @param obj
	 * @return
	 */
	private static String jsonToString(Object obj) {
		return JSON.toJSONString(obj);
	}

}
