package com.cloudstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.cloudstore.tools.javaBean.Tools;

/**
 * Servlet implementation class GetCheckNumber
 */
@WebServlet(name = "getCheckNumber", urlPatterns = { "/getCheckNumber" })
public class GetCheckNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetCheckNumber() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取手机号
		String phoneNumber=request.getParameter("phoneNumber");
		//生成验证码
		String checkNumber=Tools.getCheckNumber();
		//将验证码存入session
		HttpSession session=request.getSession();
		session.setAttribute("checkNumber", checkNumber);
	}

}
