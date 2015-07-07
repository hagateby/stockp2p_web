package com.p2p.webapp.common.base;

public class RedirectAction {

	public String forerror(){
		
		System.out.println("===============");
		return "rdterror";
	}

	public String fortimeout(){
		
		System.out.println("---------------");
		return "rdttimeout";
	}
	
}
