package com.p2p.webapp.common.init;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import com.p2p.webapp.system.menu.entity.MenuInfo;
import com.p2p.webapp.system.menu.service.MenuService;
import com.p2p.webapp.system.parammgr.entity.MsgInfo;
import com.p2p.webapp.system.parammgr.entity.SystemParam;
import com.p2p.webapp.system.parammgr.service.ParamMgrService;

public class AppInitFactoryImpl implements AppInitFactory{

	private ApplicationContext ac;
	public static Logger logger = LoggerFactory.getLogger(AppInitFactoryImpl.class);
	public AppInitFactoryImpl(ApplicationContext ac1){
		this.ac = ac1;
	}
	
	@Override
	public String load() {
		// TODO Auto-generated method stub
		logger.debug("开始加载系统参数表信息");
		//获取service
		try{
			ParamMgrService ps = (ParamMgrService)ac.getBean("paramMgrService");
			MenuService ms = (MenuService)ac.getBean("menuService");
			//系统参数信息
			List<SystemParam> list = ps.querySystemParamInfo();
			//系统信息码
			List<MsgInfo> msglist = ps.queryMsgInfo();
			//系统菜单数据
			List<MenuInfo> menulist = ms.queryMenuInfo();
			//系统菜单数据
			List<MenuInfo> pubmenulist = ms.queryPubMenuInfo();
			
			AppContext acc = new AppContext();
			acc.setSysteminfolist(list);
			acc.setMsginfolist(msglist);
			acc.setMenulist(menulist);
			acc.setPubmenulist(pubmenulist);
		}catch(Exception e){
			e.printStackTrace();
		}
		

		logger.debug("加载系统参数表信息成功");
		return null;
	}

}
