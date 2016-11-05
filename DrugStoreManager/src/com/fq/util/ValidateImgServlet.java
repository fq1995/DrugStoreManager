package com.fq.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateImgServlet extends HttpServlet {

	protected void service(HttpServletRequest reqeust, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置响应的类型格式为图片格式  
        response.setContentType("image/jpeg");  
        //禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
          
        HttpSession session = reqeust.getSession();  
          
        ValidateCode vCode = new ValidateCode(120,40,5,100);  
        session.setAttribute("code", vCode.getCode()); 
        vCode.write(response.getOutputStream());  
	}
	
}
