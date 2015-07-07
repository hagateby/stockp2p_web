package com.p2p.webapp.common.init;

import java.util.List;

import com.p2p.webapp.system.menu.entity.MenuInfo;
import com.p2p.webapp.system.parammgr.entity.MsgInfo;
import com.p2p.webapp.system.parammgr.entity.SystemParam;

public class AppContext {
	
	private static List<SystemParam> systeminfolist;
	private static List<MsgInfo> msginfolist;
	private static List<MenuInfo> menulist;
	private static List<MenuInfo> pubmenulist;
	public static List<SystemParam> getSysteminfolist() {
		return systeminfolist;
	}

	public void setSysteminfolist(List<SystemParam> systeminfolist) {
		AppContext.systeminfolist = systeminfolist;
	}

	public static List<MsgInfo> getMsginfolist() {
		return msginfolist;
	}

	public void setMsginfolist(List<MsgInfo> msginfolist) {
		AppContext.msginfolist = msginfolist;
	}

	public static List<MenuInfo> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<MenuInfo> menulist) {
		AppContext.menulist = menulist;
	}

	public static List<MenuInfo> getPubmenulist() {
		return pubmenulist;
	}

	public void setPubmenulist(List<MenuInfo> pubmenulist) {
		AppContext.pubmenulist = pubmenulist;
	}
}
