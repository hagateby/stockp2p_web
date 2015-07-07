package com.p2p.webapp.system.menu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.p2p.webapp.common.init.AppContext;
import com.p2p.webapp.system.menu.dao.MenuDao;
import com.p2p.webapp.system.menu.entity.MenuInfo;
import com.p2p.webapp.system.menu.service.MenuService;

public class MenuServiceImpl implements MenuService{

	private MenuDao menuDao;
	//��ȡ�˵�����
	public List<MenuInfo> queryMenuInfo(){
		Map<String,Object> paramap = new HashMap<String,Object>();
		List<MenuInfo> menulist = menuDao.getMenuInfo(paramap);
		return menulist;
	}
	//��ȡ������ҳ�˵�����
	public List<MenuInfo> queryPubMenuInfo(){
		Map<String,Object> paramap = new HashMap<String,Object>();
		paramap.put("menu_flag", "1");
		paramap.put("menu_prvgflag", "0");
		List<MenuInfo> menulist = menuDao.getMenuInfo(paramap);
		return menulist;
	}
	//��ȡĳ�˵�����
	public MenuInfo queryMenuInfoByCode(String code){
		List<MenuInfo> menulist = AppContext.getMenulist();
		for(MenuInfo mi:menulist){
			if(code.equals(mi.getMenu_code())){
				return mi;
			}
		}
		return null;
	}
	//��ȡ�˵��� menu1.menu2.menu3
	public Map<String,Object> queryMenuTree(String code){
		Map<String,Object> rmap = new HashMap<String,Object>();
		MenuInfo mi = queryMenuInfoByCode(code);
		String treecode = "";
		String treename = "";
		String name = mi.getMenu_name();
		if(!"".equals(mi.getMenu_parentcode())){
			treecode = mi.getMenu_parentcode() + "." + code;
			MenuInfo mp = queryMenuInfoByCode(mi.getMenu_parentcode());
			treename = mp.getMenu_name() + ">" + name;
		}else{
			treecode = code;
			treename = name;
		}
		
		int morder = Integer.parseInt(mi.getMenu_order());
		String scode = mi.getMenu_parentcode();
		for(int i=morder-1;i>1;i--){
			MenuInfo misub = queryMenuInfoByCode(scode);
			treecode = misub.getMenu_parentcode() + "." + treecode;
			MenuInfo mp = queryMenuInfoByCode(misub.getMenu_parentcode());
			treename = mp.getMenu_name() + ">" + treename;
			scode = misub.getMenu_parentcode();
		}
		rmap.put("treecode", treecode);
		rmap.put("treename", treename);
		return rmap;
	}
	//��ȡĳһ��ȵ����в˵�
	public List<MenuInfo> queryMenuInfoByOrder(List<MenuInfo> menulist,String order){
		List<MenuInfo> rlist = new ArrayList<MenuInfo>();
		for(MenuInfo mi:menulist){
			if(order.equals(mi.getMenu_order())){
				rlist.add(mi);
			}
		}
		return rlist;
	}

	//��ȡĳһ�˵�����һ���Ӳ˵�
	public List<MenuInfo> querySubMenuByCode(List<MenuInfo> menulist,String code){
		List<MenuInfo> rlist = new ArrayList<MenuInfo>();
		for(MenuInfo mi:menulist){
			if(code.equals(mi.getMenu_parentcode())){
				rlist.add(mi);
			}
		}
		return rlist;
	}
	
	
	
	
	public MenuDao getMenuDao() {
		return menuDao;
	}
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
	

}