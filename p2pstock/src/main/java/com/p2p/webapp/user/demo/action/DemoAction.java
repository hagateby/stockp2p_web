package com.p2p.webapp.user.demo.action;

import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.user.demo.service.DemoService;
import com.p2p.webapp.user.demo.vo.DemoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DemoAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DemoService demoService;
	private DemoVO demoVo;
	private String testStr;
	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(DemoAction.class);
	
	public String testDemo() {
		System.out.println("Demo开始运行");
		logger.debug("Demo111开始运行");
		String aa = demoService.testDemoService();
		System.out.println("查询成功");
		demoService.updateService();
		System.out.println("更新成功");
		testStr = "STRUTS标签验证成功！";
		return "success";
	}
	public String testDemo2(){
		
		System.out.println(demoVo.getMessageStruts());
		return "success";
	}
	public DemoService getDemoService() {
		return demoService;
	}
	public void setDemoService(DemoService demoService) {
		this.demoService = demoService;
	}
	public String getTestStr() {
		return testStr;
	}
	public void setTestStr(String testStr) {
		this.testStr = testStr;
	}
	public DemoVO getDemoVo() {
		return demoVo;
	}
	public void setDemoVo(DemoVO demoVo) {
		this.demoVo = demoVo;
	}
	
}
