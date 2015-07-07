package com.p2p.webapp.common.intercepts;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 捕获所有抛出的运行时异常，进行公共处理，阻止将运行时异常显示页面
 * @author cssong
 *
 */
public class ErrIntercept extends MethodFilterInterceptor{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(ErrIntercept.class);
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		logger.debug("进入拦截器ErrIntercept");
		String result = "";
		try{
			result =  invocation.invoke();
			System.out.println("结束拦截器ErrIntercept");
			return result;
		}catch(Exception e){
			//定义页面显示异常信息
			String errmsg = "尊敬的用户您好，系统出现异常，请与管理员联系。";
			HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
			request.setAttribute("errmsg", errmsg);
			StringWriter out = new StringWriter();
			PrintWriter pw = new PrintWriter(out);
			e.printStackTrace(pw);
			logger.error(out.toString());
			logger.debug("结束拦截器ErrIntercept->error");
			return "error";
		}
	}
}
