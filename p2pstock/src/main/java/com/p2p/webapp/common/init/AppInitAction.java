package com.p2p.webapp.common.init;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



public class AppInitAction extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Logger logger = LoggerFactory.getLogger(AppInitAction.class);
	
	public AppInitAction() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
	}

	public void init() {
		try {
			ServletContext sc = getServletContext();
			
			ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(sc) ;
			AppInitFactory  af = new AppInitFactoryImpl(ac1);
			af.load();
		} catch (Exception e) {
			return;
		}
	}
	

}
