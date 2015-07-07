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

public class MenuTreeTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int doStartTag() throws JspException{
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest(); 
		List<MenuInfo>  mymenulist = (List<MenuInfo>)request.getAttribute(Constant.getRequest_menutree());
		//List<MenuInfo>  mymenulist = AppContext.getMenulist();
		StringBuilder sb = new StringBuilder();
		MenuService menuService = new MenuServiceImpl();
		//1级菜单
		List<MenuInfo> pubMenuList = menuService.queryMenuInfoByOrder(mymenulist, "1");
		//遍历1级菜单
		for(MenuInfo mi:pubMenuList){
			sb.append("<ul>");
			sb.append("<li>");
			if(Constant.getMenu_default().indexOf(mi.getMenu_code()+",") >= 0){
				sb.append("<input type='checkbox' checked='checked' disabled onclick='selectSubTreeAll(this);' value='" +mi.getMenu_code() + "'/>");
			}else{
				if(mi.getCheckflag() == null || "".equals(mi.getCheckflag())){
					sb.append("<input type='checkbox' onclick='selectSubTreeAll(this);' value='" +mi.getMenu_code() + "'/>");
				}else{
					sb.append("<input type='checkbox' checked='checked' onclick='selectSubTreeAll(this);' value='" +mi.getMenu_code() + "'/>");
				}
			}

			sb.append("<label class='tree_label'>"+mi.getMenu_name()+"</label>");
			sb.append("<ul class='tree_ul2'>");
			//下一级菜单
			List<MenuInfo> pubMenuListtwo = menuService.querySubMenuByCode(mymenulist, mi.getMenu_code());
			for(MenuInfo mitwo:pubMenuListtwo){
				sb.append("<li>");
				if(mitwo.getCheckflag() == null || "".equals(mitwo.getCheckflag())){
					sb.append("<input type='checkbox' onclick='selectSubMenu(this,2);selectSubTreeAll(this);' value='" + mitwo.getMenu_code() + "'/><label  class='tree_label'>"+mitwo.getMenu_name()+"</label>");
				}else{
					sb.append("<input type='checkbox' checked='checked' onclick='selectSubMenu(this,2);selectSubTreeAll(this);' value='" + mitwo.getMenu_code() + "'/><label  class='tree_label'>"+mitwo.getMenu_name()+"</label>");
				}
				sb.append("<ul class='tree_ul2'>");
				//下一级菜单
				List<MenuInfo> pubMenuListthree = menuService.querySubMenuByCode(mymenulist, mitwo.getMenu_code());
				for(MenuInfo mithree:pubMenuListthree){
					sb.append("<li>");
					if(mithree.getCheckflag() == null || "".equals(mithree.getCheckflag())){
						sb.append("<input type='checkbox' onclick='selectSubMenu(this,3);' value='"+mithree.getMenu_code()+"'/><label  class='tree_label'>"+mithree.getMenu_name()+"</label>");
					}else{
						sb.append("<input type='checkbox' checked='checked' onclick='selectSubMenu(this,3);' value='"+mithree.getMenu_code()+"'/><label  class='tree_label'>"+mithree.getMenu_name()+"</label>");
					}
					
					sb.append("</li>");
				}
				sb.append("</ul>");
				sb.append("</li>");
			}
			sb.append("</ul>");
			sb.append("</li>");
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
	

}
