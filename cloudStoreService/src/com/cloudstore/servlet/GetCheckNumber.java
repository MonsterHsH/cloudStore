package com.cloudstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aliyuncs.exceptions.ClientException;
import com.cloudstore.tools.javaBean.CreateCheckNumber;
import com.cloudstore.tools.javaBean.SendMessage;
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
		String phoneNumber= request.getParameter("phoneNumber");
		//向该手机号发送验证码
		if(phoneNumber!=null){
			try {
				String checkNumber=CreateCheckNumber.getCheckNumber();
				System.out.println("checkNumber:"+checkNumber);
				SendMessage.sendMessage(phoneNumber,checkNumber);
				response.getWriter().print(checkNumber);     //短信发送成功返回
			} catch (ClientException e) {
				response.getWriter().print("0");     //短信发送失败
			}
//			response.getWriter().print(checkNumber);
			
		}
	}

}
