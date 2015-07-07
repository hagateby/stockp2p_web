package com.p2p.webapp.common.base;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.page.Page;

public class BaseAction extends ActionSupport implements SessionAware,
ServletRequestAware, ServletResponseAware{
	protected Map session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected String webContontRoot = "/p2pstock";
	//分页参数
	protected int pageNo = 1;
	protected String pageUrl = "";
	protected String pageflag = "0";
	//错误码
	protected String msgCode;
	public Page getPageInstance(Page page){
		page = Page.newBuilder(pageNo,pageUrl);
		//分页使用保存条件
		if("1".equals(pageflag)){
			System.out.println("ppppp="+request.getSession().getAttribute(Constant.getSession_secparam()));
			page.setParamMap((HashMap)request.getSession().getAttribute(Constant.getSession_secparam()));
			page.setPageflag(pageflag);
		}
		return page;
	}
	/*
	 * 保存分页session
	 */
	public void paramSave(Page page){
		request.getSession().setAttribute(Constant.getSession_secparam(), page.getParamMap());
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void setSession(Map session) {
		this.session = session;
	}
	public void setServletRequest(HttpServletRequest httpRequest) {
		this.request = httpRequest;
	}

	public void setServletResponse(HttpServletResponse httpResponse) {
		this.response = httpResponse;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		if(pageUrl == null || "".equals(pageUrl)){
			this.pageUrl = (String)request.getAttribute("pageUrl");
		}else{
			this.pageUrl = pageUrl;
		}
		
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getPageflag() {
		return pageflag;
	}
	public void setPageflag(String pageflag) {
		this.pageflag = pageflag;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	public String getWebContontRoot() {
		return webContontRoot;
	}
	public void setWebContontRoot(String webContontRoot) {
		this.webContontRoot = webContontRoot;
	}
	
}
