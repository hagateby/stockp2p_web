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
		logger.debug("��ʼ����ϵͳ��������Ϣ");
		//��ȡservice
		try{
			ParamMgrService ps = (ParamMgrService)ac.getBean("paramMgrService");
			MenuService ms = (MenuService)ac.getBean("menuService");
			//ϵͳ������Ϣ
			List<SystemParam> list = ps.querySystemParamInfo();
			//ϵͳ��Ϣ��
			List<MsgInfo> msglist = ps.queryMsgInfo();
			//ϵͳ�˵�����
			List<MenuInfo> menulist = ms.queryMenuInfo();
			//ϵͳ�˵�����
			List<MenuInfo> pubmenulist = ms.queryPubMenuInfo();
			
			AppContext acc = new AppContext();
			acc.setSysteminfolist(list);
			acc.setMsginfolist(msglist);
			acc.setMenulist(menulist);
			acc.setPubmenulist(pubmenulist);
		}catch(Exception e){
			e.printStackTrace();
		}
		

		logger.debug("����ϵͳ��������Ϣ�ɹ�");
		return null;
	}

}
