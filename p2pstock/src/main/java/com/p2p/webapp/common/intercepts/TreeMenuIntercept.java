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
 * ����ҵ������������
 * �˵���������
 * @author songcs
 *
 */
public class TreeMenuIntercept extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//д��־����
	public static Logger logger = LoggerFactory.getLogger(TreeMenuIntercept.class);


	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		logger.debug("����������BizIntercept");
		MenuService menuService = new MenuServiceImpl();
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		//��ȡ��ǰmenucode
		String menucode = request.getParameter("menucode");
		String menucodes = (String)request.getSession().getAttribute("menusec");
		if(menucode == null && menucodes == null){
			menucode = "MYACOUNT";
		}
		//��ȡ��ǰmenucode�ĸ��˵�
		String menutree = "";
		String menutreename = "";
		if(menucode != null && !"".equals(menucode)){
			Map<String,Object> map = menuService.queryMenuTree(menucode);
			menutree = (String)map.get("treecode");
			menutreename = (String)map.get("treename");
			logger.debug("��ǰ�˵���:"+menutree);
			logger.debug("��ǰ�˵���:"+menutreename);
		}
		String menusec = menutree;
		String menusecname = menutreename;
		if(menusec != null && !"".equals(menusec)){
			request.getSession().setAttribute("menusec", menusec);
			request.getSession().setAttribute("menusecname", menusecname);
		}
		logger.debug("��ѡ�в˵�:"+menusec);
		logger.debug("����������BizIntercept");
		return invocation.invoke();
	}
}
