package com.gzdata.config.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.DelegatingSession;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultHeaderSessionManager extends DefaultSessionManager implements WebSessionManager {

    private static final Logger LOGGER  = LoggerFactory.getLogger(DefaultHeaderSessionManager.class);

    private final String X_AUTH_TOKEN = "x-auth-token";

    @Override
    protected void onStart(Session session, SessionContext context) {
        //super.onStart(session, context);
        if (!WebUtils.isHttp(context)) {
            LOGGER.debug("SessionContext argument is not HTTP compatible or does not have an HTTP request/response pair. No session ID cookie will be set.");
        } else {
            HttpServletRequest request = WebUtils.getHttpRequest(context);
            HttpServletResponse response = WebUtils.getHttpResponse(context);
            Serializable sessionId = session.getId();
//            this.storeSessionId(sessionId, request, response);
            request.removeAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_IS_NEW, Boolean.TRUE);
        }
        
    }

    //获取sessionid
    @Override
    public Serializable getSessionId(SessionKey key) {
        Serializable id = super.getSessionId(key);
        if (id == null && WebUtils.isWeb(key)) {
            ServletRequest request = WebUtils.getRequest(key);
            ServletResponse response = WebUtils.getResponse(key);
            id = getSessionId(request, response);
        }
        return id;
    }

    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        return this.getReferencedSessionId(request, response);
    }


    /**
     * 
     * 功能描述：请求头中获取 sessionId
     * 		     并把sessionId 放入 response 中
     *
     * @param request
     * @param response
     * @return
     * 
     * @author 张兵帅
     *
     * @since 2020年6月23日
     *
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     */
    private String getSessionIdHeaderValue(ServletRequest request, ServletResponse response) {
        if (!(request instanceof HttpServletRequest)) {
            LOGGER.debug("Current request is not an HttpServletRequest - cannot get session ID cookie.  Returning null.");
            return null;
        }
        else {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            // 在request 中 读取 x-auth-token 信息  作为 sessionId
            String sessionId = httpRequest.getHeader(this.X_AUTH_TOKEN);

            // 每次读取之后 都把当前的 sessionId 放入 response 中
            /*HttpServletResponse httpResponse = (HttpServletResponse) response;
            
            if (StringUtils.isNotEmpty(sessionId)) {
                httpResponse.setHeader(this.X_AUTH_TOKEN, sessionId);
            }*/
            
            LOGGER.info("Current session ID is {}", sessionId);
            
            return sessionId;
        }
    }

    //获取sessionid
    private Serializable getReferencedSessionId(ServletRequest request, ServletResponse response) {
        String id = this.getSessionIdHeaderValue(request, response);

        //DefaultWebSessionManager 中代码 直接copy过来
        if (id != null) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        }
        //不会把sessionid放在URL后
        request.setAttribute(ShiroHttpServletRequest.SESSION_ID_URL_REWRITING_ENABLED, Boolean.FALSE);
        return id;
    }

    // 移除sessionid 并设置为 deleteMe 标识
    private void removeSessionIdHeader(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(this.X_AUTH_TOKEN, "deleteMe");
    }

    /**
     * 把sessionId 放入 response header 中
     * onStart时调用
     * 没有sessionid时 会产生sessionid 并放入 response header中
     */
    private void storeSessionId(Serializable currentId, HttpServletRequest ignored, HttpServletResponse response) {
        if (currentId == null) {
            String msg = "sessionId cannot be null when persisting for subsequent requests.";
            throw new IllegalArgumentException(msg);
        } else {
            String idString = currentId.toString();

            response.setHeader(this.X_AUTH_TOKEN, idString);

            LOGGER.info("Set session ID header for session with id {}", idString);
        }
    }

    // 创建session
    @Override
    protected Session createExposedSession(Session session, SessionContext context) {
    	
        if (!WebUtils.isWeb(context)) {
            return super.createExposedSession(session, context);
        } else {
            ServletRequest request = WebUtils.getRequest(context);
            ServletResponse response = WebUtils.getResponse(context);
            SessionKey key = new WebSessionKey(session.getId(), request, response);
            return new DelegatingSession(this, key);
        }
    }

    @Override
    protected Session createExposedSession(Session session, SessionKey key) {
        if (!WebUtils.isWeb(key)) {
            return super.createExposedSession(session, key);
        } else {
            ServletRequest request = WebUtils.getRequest(key);
            ServletResponse response = WebUtils.getResponse(key);
            SessionKey sessionKey = new WebSessionKey(session.getId(), request, response);
            return new DelegatingSession(this, sessionKey);
        }
    }

    @Override
    protected void onExpiration(Session s, ExpiredSessionException ese, SessionKey key) {
        super.onExpiration(s, ese, key);
        this.onInvalidation(key);
    }

    @Override
    protected void onInvalidation(Session session, InvalidSessionException ise, SessionKey key) {
        super.onInvalidation(session, ise, key);
        this.onInvalidation(key);
    }

    private void onInvalidation(SessionKey key) {
    	
        ServletRequest request = WebUtils.getRequest(key);
        if (request != null) {
            request.removeAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID);
        }

        if (WebUtils.isHttp(key)) {
        	LOGGER.debug("Referenced session was invalid.  Removing session ID header.");
            this.removeSessionIdHeader(WebUtils.getHttpRequest(key), WebUtils.getHttpResponse(key));
        } else {
            LOGGER.debug("SessionKey argument is not HTTP compatible or does not have an HTTP request/response pair. Session ID cookie will not be removed due to invalidated session.");
        }
        

    }

    @Override
    protected void onStop(Session session, SessionKey key) {
        super.onStop(session, key);
        if (WebUtils.isHttp(key)) {
            HttpServletRequest request = WebUtils.getHttpRequest(key);
            HttpServletResponse response = WebUtils.getHttpResponse(key);
            LOGGER.debug("Session has been stopped (subject logout or explicit stop).  Removing session ID cookie.");
            this.removeSessionIdHeader(request, response);
        } else {
            LOGGER.debug("SessionKey argument is not HTTP compatible or does not have an HTTP request/response pair. Session ID cookie will not be removed due to stopped session.");
        }
    }

    @Override
    public boolean isServletContainerSessions() {
        return false;
    }

}
