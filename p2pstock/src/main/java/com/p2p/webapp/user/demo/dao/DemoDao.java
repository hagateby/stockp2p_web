package com.p2p.webapp.user.demo.dao;

import org.apache.ibatis.annotations.Param;

import com.p2p.webapp.user.demo.entity.Demo;


	
	public interface DemoDao{
		
		public Demo getTest(String test_aa);
		public Demo update1(@Param(value="test_aa") String test_aa,@Param(value="test_bb") String test_bb);
		public Demo update2(String test_aa);
	}
