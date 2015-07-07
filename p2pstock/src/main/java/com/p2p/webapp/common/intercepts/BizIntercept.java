package com.p2p.webapp.common.intercepts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.p2p.webapp.common.constant.Constant;

/**
 * 所有业务处理公用拦截器
 * 处理用户登录状态等校验
 * @author songcs
 *
 */
public class BizIntercept extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(BizIntercept.class);


	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		logger.debug("进入拦截器BizIntercept");
		
		
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		String requestURI=request.getRequestURI();
		//分页使用
		request.setAttribute("pageUrl", requestURI);
		//不进行登录校验的UAL命名空间
		String filterUrl = Constant.getBizinterceptfilter();
		logger.debug("requestURI==="+requestURI);
		if(checkFilter(requestURI,filterUrl)){
			//执行登录状态校验
			String userid = (String)request.getSession().getAttribute("userid");
			if(userid == null || "".equals(userid)){
				logger.debug("未登录或登录超时请重新登录");
				request.setAttribute("errmsg", "未登录或登录超时请重新登录");
				return "timeout";
			}
		}
		String result = invocation.invoke();
		logger.debug("结束拦截器BizIntercept");
		return result;
	}
	/*
	 * 验证是否需要进行登录校验 true需要 false 不需要
	 */
	private boolean checkFilter(String uri,String filter){
		String[] filterarray = filter.split(",");
		String[] uriarray = uri.split("\\/");
		int uaral = uriarray.length;
		//首页过滤
		if(uriarray[uaral -1].equals("index.action")){
			return false;
		}
		//登录、注册、demo过滤
		for(int i = 0;i< filterarray.length;i++){
			if(filterarray[i].equals(uriarray[uaral -2])){
				return false;
			}
		}
		
		return true;
	}

}
