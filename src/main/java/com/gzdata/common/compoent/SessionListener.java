package com.gzdata.common.compoent;
/*package com.gzdata.common.compoent;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.gzdata.common.util.DateUtil;
*//**
 * session监听器
 * 
 *
 * @author 张兵帅
 *
 * @version 
 *
 * @since 2018年4月16日
 *//*
public class SessionListener implements HttpSessionListener {
	
	*//**
	 * session创建
	 *//*
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession ses = event.getSession();
		String id = ses.getId() + ses.getCreationTime();
		System.out.println("-----------create session "+DateUtil.getDate(DateUtil.DEFAULT_DATETIME_FORMAT)); // 创建会话时输出
	}

	*//**
	 * session销毁
	 *//*
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession ses = event.getSession();
		String id = ses.getId() + ses.getCreationTime();
		synchronized (this) {
			System.out.println("-----------destory session "+DateUtil.getDate(DateUtil.DEFAULT_DATETIME_FORMAT)); // 失效时输出
		}
	}
}*/