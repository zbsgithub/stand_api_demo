package com.gzdata.common.util;

import java.io.IOException;







import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 使用okhttp发送请求
 * @author xiaoping
 *
 */
public class OkHttpUtil {
	
	private static final Logger logger=LoggerFactory.getLogger(OkHttpUtil.class);

	private static OkHttpClient okHttpClient = new OkHttpClient();
	
	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

	public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

	public static final MediaType MEDIA_TYPE_ENCODE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

	/**
	 *  使用okHttp发送请求
	 *  GET请求
	 * @param url
	 * @return
	 */
	public static String okHttpReqGet(String url,String headerToken) {
		Request okRequest = null;
		Response okResponse = null;
		String result = null;
		try {
			if(StringUtils.isNotBlank(headerToken)){
				okRequest = new Request.Builder().header("token",headerToken).header("Content-Type","application/json").url(url).build();
			}else{
				okRequest = new Request.Builder().url(url).build();
			}
			okResponse = okHttpClient.newCall(okRequest).execute();
			result = okResponse.body().string();
		}
		catch (IOException e) {
			logger.error("okhttp get req arise exception:",e);
		}finally{
			if(okResponse.body() != null){
				okResponse.body().close();//关闭
			}
		}
		return result;
	}
	
	public static String okHttpReqGet(String url,String headerToken,String contentType) {
		Request okRequest = null;
		Response okResponse = null;
		String result = null;
		try {
			if(StringUtils.isNotBlank(headerToken)){
				okRequest = new Request.Builder().header("token",headerToken).header("Content-Type","application/x-www-form-urlencoded").url(url).build();
			}else{
				okRequest = new Request.Builder().url(url).build();
			}
			okResponse = okHttpClient.newCall(okRequest).execute();
			result = okResponse.body().string();
		}
		catch (IOException e) {
			logger.error("okhttp get req arise exception:",e);
		}finally{
			if(okResponse.body() != null){
				okResponse.body().close();//关闭
			}
		}
		return result;
	}
	
	/**
	 *  使用okHttp发送请求
	 *  POST请求
	 * @param url
	 * @return
	 */
	public static String okHttpReqPost(String url,String param,String token) {
		Request okRequest = null;
		Response okResponse = null;
		String result = null;
		try {
			if(StringUtils.isBlank(token)){
				okRequest = new Request.Builder().url(url).post(RequestBody.create(MEDIA_TYPE_MARKDOWN, param)).build();
			}else{
				okRequest = new Request.Builder().header("token", token).url(url).post(RequestBody.create(MEDIA_TYPE_JSON, param)).build();
			}

			okResponse = okHttpClient.newCall(okRequest).execute();
			result = okResponse.body().string();
		}
		catch (IOException e) {
			logger.error("okhttp get req arise exception:",e);
		}finally{
			if(okResponse.body() != null){
				okResponse.body().close();//关闭
			}
		}
		return result;
	}
}

