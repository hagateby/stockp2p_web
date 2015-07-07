package com.p2p.webapp.system.menu.dao;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.system.menu.entity.MenuInfo;

	
	public interface MenuDao{
		
		public List<MenuInfo> getMenuInfo(Map<String,Object> paramap);
		
	}
