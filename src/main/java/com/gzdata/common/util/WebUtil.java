package com.gzdata.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

public class WebUtil {
	protected static final Logger logger = LoggerFactory.getLogger(WebUtil.class);

	/**
	 * 发送URL，并获得返回值 get
	 * */
	public static String sendGET(String url, String param) {
		String result = "";// 访问返回结果
		BufferedReader read = null;// 读取访问结果

		try {
			// 创建url 格式：http://123.56.92.138:8090/
			URL realurl = new URL(getBasePath() + url + "?" + param);
			// 打开连接
			URLConnection connection = realurl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection
					.setRequestProperty(
							"<a href='https://www.baidu.com/s?wd=user-agent&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1Y3nhfzmH7BPH9bPjRkn1F90ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6K1TL0qnfK1TL0z5HD0IgF_5y9YIZ0lQzqlpA-bmyt8mh7GuZR8mvqVQL7dugPYpyq8Q1D3nHTvP1DYn16krHT4P1T1rj6' target='_blank' class='baidu-highlight'>user-agent</a> ",
							"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立连接
			connection.connect();
			// 获取所有响应头字段
			// Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段，获取到cookies等
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			read = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String line;// 循环读取
			while ((line = read.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			logger.error("sendGet arsise exception:",e);
		} finally {
			if (read != null) {// 关闭流
				try {
					read.close();
				} catch (IOException e) {
					logger.error("sendGet arsise exception:",e);
				}
			}
		}

		return result;
	}

	/**
	 * 获取HttpServletRequest对象
	 * 
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		try {
			ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest req = sra.getRequest();
			return req;
		} catch (Exception e) {
			logger.error("getHttpServletRequest arsise exception:",e);
			return null;
		}
	}

	/**
	 * 获取HttpServletResponse对象
	 * 
	 * @return
	 */
	public static HttpServletResponse getHttpServletResponse() {
		try {

			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
			ServletWebRequest servletWebRequest = new ServletWebRequest(request);
			HttpServletResponse response = servletWebRequest.getResponse();

			return response;
		} catch (Exception e) {
			logger.error("getHttpServletResponse arsise exception:",e);
			return null;
		}

	}

	/**
	 * 获取HttpSession对象
	 * 
	 * @return
	 */
	public static HttpSession getHttpSession() {
		try {
			return getHttpServletRequest().getSession();
		} catch (Exception e) {
			logger.error("getHttpSession arsise exception:",e);
			return null;
		}
	}

	/**
	 * 获取ServletContext对象
	 * 
	 * @return
	 */
	public static ServletContext getServletContext() {
		try {
			return getHttpSession().getServletContext();
		} catch (Exception e) {
			logger.error("getServletContext arsise exception:",e);
			return null;
		}
	}

	/**
	 * 获取用户IP
	 * 
	 * @return
	 */
	public static String getUserIp() {
		HttpServletRequest request = getHttpServletRequest();
		String ipAddress = null;
		// ipAddress = this.getRequest().getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")
					|| ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}

		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) {
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 获取浏览器代理信息
	 * 
	 * @return
	 */
	public static String getUserAgent() {
		return getHttpServletRequest().getHeader("User-Agent");
	}

	/**
	 * 获取网站根路径
	 * 
	 * @return
	 */
	public static String getBasePath() {
		String path = getHttpServletRequest().getContextPath();
		String basePath = getHttpServletRequest().getScheme() + "://"
				+ getHttpServletRequest().getServerName() + ":"
				+ getHttpServletRequest().getServerPort() + path + "/";
		return basePath;

	}

	/**
	 * 
	 * 功能描述：本方法封装了往前台设置的header,contentType等信息
	 * 
	 * @param message
	 *            需要传给前台的数据
	 * @param type
	 *            指定传给前台的数据格式,如"html","json"等
	 * @param response
	 *            HttpServletResponse对象
	 * @throws IOException
	 * 
	 * @author 张小平
	 * 
	 * @since 2015年9月21日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void writeToWeb(String message, String type,
			HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/" + type + "; charset=utf-8");
		response.getWriter().write(message);
		response.getWriter().close();
	}

	/**
	 * 
	 * 功能描述：获得java全路径 URL
	 * 
	 * @return
	 * 
	 * @author 张小平
	 * 
	 * @since 2015年12月3日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@SuppressWarnings("rawtypes")
	public static String getRequestURL() {
		HttpServletRequest request = getHttpServletRequest();
		if (request == null) {
			return "";
		}
		String url = "http://";
		url = url + request.getServerName();
		url = url + request.getContextPath();
		url = url + request.getServletPath();

		Enumeration names = request.getParameterNames();
		int i = 0;
		if (!"".equals(request.getQueryString())
				|| request.getQueryString() != null) {
			url = url + "?" + request.getQueryString();
		}

		if (names != null) {
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				if (i == 0) {
					url = url + "?";
				} else {
					url = url + "&";
				}
				i++;

				String value = request.getParameter(name);
				if (value == null) {
					value = "";
				}

				url = url + name + "=" + value;
				// java.net.URLEncoder.encode(url, "ISO-8859");
			}
		}
		// String enUrl = java.net.URLEncoder.encode(url, "utf-8");

		return url;
	}
}
