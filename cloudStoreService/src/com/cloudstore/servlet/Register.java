package com.cloudstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Register
 */
@WebServlet(name = "register", urlPatterns = { "/register" })
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Register() {
        super();
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取手机号
		String phoneNumber=request.getParameter("phoneNumber");
		//查询数据库，查看该手机号是否被注册
		if(phoneNumber==null){
			//返回该账号已经存在信息
			response.getWriter().print("1");
		}
		//否则打开session
		else{
			HttpSession session=request.getSession();
			//获取验证码
			String checkNumber=(String)session.getAttribute("checkNumber");
			
			
			
			
		}
		
		
		
		
		
		
	
		
		
		
		
	}

}
