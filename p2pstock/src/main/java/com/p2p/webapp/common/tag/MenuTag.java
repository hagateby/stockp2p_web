package com.p2p.webapp.common.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.init.AppContext;
import com.p2p.webapp.system.menu.entity.MenuInfo;
import com.p2p.webapp.system.menu.service.MenuService;
import com.p2p.webapp.system.menu.service.impl.MenuServiceImpl;

public class MenuTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String menuorder;
	
	
	
	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException{
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest(); 
		MenuService menuService = new MenuServiceImpl();
		String user_id = (String)request.getSession().getAttribute(Constant.getSession_userid());
		StringBuilder sb = new StringBuilder();
		String webroot = Constant.webContontRoot;
		String menunow = (String)request.getSession().getAttribute("menusec");
		List<MenuInfo>  mymenulist = (List<MenuInfo>)request.getSession().getAttribute(Constant.getSession_mymenulist());
		//一级菜单
		if("1".equals(menuorder)){
			List<MenuInfo> pubMenuList;
			//判断用户是否登录
			if(user_id == null ||"".equals(user_id)){//未登录
				pubMenuList = AppContext.getPubmenulist();
			}else{
				pubMenuList = menuService.queryMenuInfoByOrder(mymenulist, "1");
			}
			if(pubMenuList != null){
				sb.append("<ul class='ui-nav'>");
				for(MenuInfo mi:pubMenuList){
					sb.append("<li class='ui-nav-item ui-nav-item-x'id='li_" + mi.getMenu_code() +  "'>");
					sb.append("<a class='ui-nav-item-link rrd-dimgray'id='a_" + mi.getMenu_code()+ "' href='" + webroot + mi.getMenu_action()+ "?menucode="+ mi.getMenu_code() +"'>");
					sb.append("<span id=span_"+mi.getMenu_code()+"'>" +mi.getMenu_name() + "</span>");
					sb.append("</a>");
					sb.append("</li>");
				}
				sb.append("</ul>");
			}
		}else if("2".equals(menuorder)){//树菜单
			MenuInfo pmi = menuService.queryMenuInfoByCode(menunow.split("\\.")[0]);
			sb.append("<ul class='ui-side-list nav nav-list bs-docs-sidenav affix'>");
			sb.append(" <li class='ui-side-item active'>");
			sb.append("<a class='ui-side-item-link' href='#'><i class='fn-left ui-icon-mid index'></i>"+pmi.getMenu_name()+"</a>");
			sb.append("</li>");
			//获取2级子菜单
			List<MenuInfo> towmenulist = menuService.querySubMenuByCode(mymenulist,pmi.getMenu_code());
			for(MenuInfo mi:towmenulist){
				sb.append("<li class='ui-side-item'>");
				sb.append("<a class='ui-side-item-link' id='btn_"+mi.getMenu_code()+"' onclick='menuclick("+'"'+"ul_" + mi.getMenu_code() +'"'+ ")'><i class='"+mi.getMenu_iconclass()+"'></i>"+mi.getMenu_name()+"</a>");
				sb.append("<ul class='ui-side-sub-list' id='ul_"+mi.getMenu_code()+"'>");
				//获取下一级菜单
				List<MenuInfo> threemenulist = menuService.querySubMenuByCode(mymenulist,mi.getMenu_code());
				for(MenuInfo misub:threemenulist){
					sb.append("<li class='ui-side-sub-item '>");
					if(misub.getMenu_action().indexOf("javascript")>=0){
						sb.append("<a class='ui-side-sub-item-link' id='sub_" + misub.getMenu_code() + "' href='"+ misub.getMenu_action() +"'>" + misub.getMenu_name() + "</a>");
					}else{
						sb.append("<a class='ui-side-sub-item-link' id='sub_" + misub.getMenu_code() + "' href='" + webroot + misub.getMenu_action() + "?menucode="+ misub.getMenu_code() +"'>" + misub.getMenu_name() + "</a>");
					}
										
					sb.append("</li>");
				}
				sb.append("</ul>");
				sb.append("</li>");
			}
			sb.append("</ul>");
		}
    	try {
			this.pageContext.getOut().println(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return 0;
	}
	
	
	
	
	
	
	public String getMenuorder() {
		return menuorder;
	}
	public void setMenuorder(String menuorder) {
		this.menuorder = menuorder;
	}
}
