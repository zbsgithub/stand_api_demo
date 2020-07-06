package com.gzdata.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.gzdata.common.exception.GenericException;
import com.gzdata.common.exception.RepeatedException;
 
/**
 * Created by Administrator on 2017/12/11.
 * 全局异常处理
 */
@Order(-1000)
public class GlobalExceptionResolver implements HandlerExceptionResolver {
 
	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);
	
    
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        if(ex instanceof GenericException){//自定义异常：token过期
        	map.put("code", 401);
        	map.put("msg", ex.getMessage());
        }else  if(ex instanceof MissingServletRequestParameterException){
        	map.put("code", 10003);
			map.put("msg", "请检查参数是否完整");
        }else if (ex instanceof HttpRequestMethodNotSupportedException) {
			map.put("code", 405);
			map.put("msg", "请检查调用方法是否正确");
		}else if (ex instanceof UnauthenticatedException) {
            map.put("code", 10001);
            map.put("msg", "token错误");
        } else if (ex instanceof UnauthorizedException) {
            map.put("code", 10002);
            map.put("msg", "用户无权限");
		} else if (ex instanceof RepeatedException) {
			map.put("code", 10005);
			map.put("msg", "该钉钉账户已被绑定，请更换其他钉钉账号重试");
        }else if(ex instanceof RuntimeException){
        	map.put("code", 500);
            map.put("msg", "程序运行时内部错误");
        } else {
            map.put("code", 10003);
            map.put("msg", "服务器内部错误");
        }
        
        logger.error("global exception try:",ex);
        
        view.setAttributesMap(map);
        mv.setView(view);
        return mv;
    }
}
