package com.gzdata.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ResponseUtil
 *
 * @author wcy 王晨阳
 * @version 1.0
 * 2020-05-20 11:07
 **/
public class ResponseUtil {

    /**
     *
     *输出错误信息
     *@author 王晨阳
     *@version 1.0
     *2020-05-20 11:07
     **/
    public static void notScript(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<script language='javascript'>alert('"+msg+"');window.history.back();</script>");
        out.close();
    }


}
