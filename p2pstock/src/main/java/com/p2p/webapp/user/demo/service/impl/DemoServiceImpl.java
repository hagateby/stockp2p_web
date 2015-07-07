package com.p2p.webapp.user.demo.service.impl;

import com.p2p.webapp.user.demo.dao.DemoDao;
import com.p2p.webapp.user.demo.entity.Demo;
import com.p2p.webapp.user.demo.service.DemoService;

public class DemoServiceImpl implements DemoService{
	
	private DemoDao demodao;
	
	@Override
	public String testDemoService() {
		String bb="";
		try{
			// TODO Auto-generated method stub
			Demo demo = demodao.getTest("11");
			bb = demo.getTest_bb();
			System.out.println("查询成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return bb;
	}
	
	@Override
	public String updateService(){
		
		demodao.update1("11","22");
		System.out.println("更新1成功");
		demodao.update2("33");
		System.out.println("更新成功");
		return "";
	}

	public DemoDao getDemodao() {
		return demodao;
	}

	public void setDemodao(DemoDao demodao) {
		this.demodao = demodao;
	}


}
