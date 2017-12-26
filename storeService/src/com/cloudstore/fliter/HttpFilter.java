package com.cloudstore.fliter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *  
 * @author panda
 *
 */
public abstract class HttpFilter implements Filter
{
	
	private FilterConfig config=null; 
	
	public void destroy() {}
	
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response=(HttpServletResponse) arg1;
		doFilter(request,response,arg2);
		
	}
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain);
	
	public void init(FilterConfig arg0) throws ServletException {
		this.config=arg0;
		init();
	}
	/**
	 * 
	 */
	public void init()
	{
		
	}
	//
	public FilterConfig getConfig() {
		return config;
	}

}
