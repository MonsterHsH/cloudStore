package com.cloudstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data=request.getParameter("data");
		String msg="1";
		String phoneNumber=null;
		String checkNumber=null;
		String serverCheckNumber=null;
		JSONObject obj=JSONObject.parseObject(data);
		try{
			phoneNumber=obj.getString("phoneNumber");
		}catch (NullPointerException e) {
			msg="4";      //手机号码为空
		}
		try{
			checkNumber=obj.getString("checkNumber");
		}catch (NullPointerException e) {
			msg="5";     //验证码为空
		}
		try{
			serverCheckNumber=obj.getString("serverCheckNumber");
		}catch (NullPointerException e) {
			msg="6";     //未发送验证码
		}
		//从数据库查询该手机号是否被注册，若未注册msg="2"
		if(phoneNumber!=null){
			if(checkNumber.equals(serverCheckNumber)){
				msg="1";
			}else{
				msg="3";
			}
		}
		//若未被注册，检查checkNumber和serverCheckNumber的值是否相同，若相同，msg="1"
		//否则msg="3"	
		System.out.println("phoneNumber:"+phoneNumber+"\tcheckNumber:"+checkNumber+"\tserverCheckNumber:"+serverCheckNumber);
		response.getWriter().print(msg);
	

	}

}
