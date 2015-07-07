package com.p2p.webapp.common.intercepts;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.p2p.webapp.system.menu.service.MenuService;
import com.p2p.webapp.system.menu.service.impl.MenuServiceImpl;

/**
 * 所有业务处理公用拦截器
 * 菜单公共处理
 * @author songcs
 *
 */
public class TreeMenuIntercept extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(TreeMenuIntercept.class);


	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		logger.debug("进入拦截器BizIntercept");
		MenuService menuService = new MenuServiceImpl();
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		//获取当前menucode
		String menucode = request.getParameter("menucode");
		String menucodes = (String)request.getSession().getAttribute("menusec");
		if(menucode == null && menucodes == null){
			menucode = "MYACOUNT";
		}
		//获取当前menucode的父菜单
		String menutree = "";
		String menutreename = "";
		if(menucode != null && !"".equals(menucode)){
			Map<String,Object> map = menuService.queryMenuTree(menucode);
			menutree = (String)map.get("treecode");
			menutreename = (String)map.get("treename");
			logger.debug("当前菜单树:"+menutree);
			logger.debug("当前菜单树:"+menutreename);
		}
		String menusec = menutree;
		String menusecname = menutreename;
		if(menusec != null && !"".equals(menusec)){
			request.getSession().setAttribute("menusec", menusec);
			request.getSession().setAttribute("menusecname", menusecname);
		}
		logger.debug("已选中菜单:"+menusec);
		logger.debug("结束拦截器BizIntercept");
		return invocation.invoke();
	}
}
