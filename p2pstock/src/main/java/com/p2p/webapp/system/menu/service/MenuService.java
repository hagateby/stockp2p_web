package com.p2p.webapp.system.menu.service;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.system.menu.entity.MenuInfo;

public interface MenuService {
	//��ȡ�˵�����
	public List<MenuInfo> queryMenuInfo();
	//��ȡ������ҳ�˵�����
	public List<MenuInfo> queryPubMenuInfo();
	//��ȡĳһ��ȵ����в˵�
	public List<MenuInfo> queryMenuInfoByOrder(List<MenuInfo> menulist,String order);
	//��ȡĳһ�˵�����һ���Ӳ˵�
	public List<MenuInfo> querySubMenuByCode(List<MenuInfo> menulist,String code);
	//��ȡĳ�˵�����
	public MenuInfo queryMenuInfoByCode(String code);
	//��ȡ�˵��� menu1.menu2.menu3
	public Map<String,Object> queryMenuTree(String code);
}
