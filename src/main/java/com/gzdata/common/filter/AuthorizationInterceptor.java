package com.gzdata.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gzdata.common.exception.GenericException;
import com.gzdata.common.util.RedisUtil;

/**
 * 权限（token）验证
 * @author wujiaxing
 * @date 2019-06-30
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
	@Autowired
	private HttpSession session;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 从请求头获取token
        String token = request.getHeader("x-auth-token");
        if(request.getRequestURI().contains("/login") || request.getRequestURI().contains("/anon") || request.getRequestURI().contains("/swagger-ui.html") ||
        		request.getRequestURI().contains("/webjars") || request.getRequestURI().contains("/v2") || request.getRequestURI().contains("/swagger-resources")
        		|| request.getRequestURI().contains("/static") || request.getRequestURI().contains("/upload")){
        	logger.info("------------直接跳过------------请求地址："+request.getRequestURI());
        	return true;
        }

        // 如果请求头没有token,则从请求参数中取
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("x-auth-token");
        }
        // 如果还是没有token,则抛异常
        if (StringUtils.isBlank(token)) {
        	logger.info(request.getRequestURI());
//           throw new RuntimeException("token不能为空，请确认token是否存在");
           throw new GenericException("token不能为空，请确认token是否存在");

        }

        // 查询token信息
//        boolean  flag = redisUtil.hHasKey(Constants.HASHNAME, token);
//        // 如果token信息是否为null或是否过期，则抛异常
//        if (!flag) {
//            throw new RuntimeException("token不存在");
//        }
//
//        // 否则，存入request作用域,后续根据userId，获取用户信息
////        request.getSession().setAttribute("token", token);
//
//        Object userId = String.valueOf(redisUtil.hget(Constants.HASHNAME, token)).split(",")[2];
        session.setAttribute("token", token);


        return true;
    }
}
