package com.p2p.webapp.system.menu.service;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.system.menu.entity.MenuInfo;

public interface MenuService {
	//获取菜单数据
	public List<MenuInfo> queryMenuInfo();
	//获取公共首页菜单数据
	public List<MenuInfo> queryPubMenuInfo();
	//获取某一深度的所有菜单
	public List<MenuInfo> queryMenuInfoByOrder(List<MenuInfo> menulist,String order);
	//获取某一菜单的下一级子菜单
	public List<MenuInfo> querySubMenuByCode(List<MenuInfo> menulist,String code);
	//获取某菜单数据
	public MenuInfo queryMenuInfoByCode(String code);
	//获取菜单树 menu1.menu2.menu3
	public Map<String,Object> queryMenuTree(String code);
}
