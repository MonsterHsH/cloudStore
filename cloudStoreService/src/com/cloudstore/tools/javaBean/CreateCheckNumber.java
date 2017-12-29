package com.cloudstore.tools.javaBean;

/**
 * 项目中需要用到的工具类
 * @author 82871
 *
 */
public class CreateCheckNumber {
	
	/**
	 * 获取6位数的验证码
	 * @return
	 */
	public static String getCheckNumber(){
		String checkNumber="";
		Integer number=(int)((Math.random()*9+1)*100000);
		checkNumber=number.toString();
		return checkNumber;
	}
}
