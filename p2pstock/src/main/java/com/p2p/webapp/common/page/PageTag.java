package com.p2p.webapp.common.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 分页标签
 * @author songcs
 *
 */
public class PageTag extends TagSupport{
	   /** 
     *  
     */  
    private static final long serialVersionUID = 1L;  

    private Page page;
    private String formid;
    private String subtype;
      
    @Override  
    public int doStartTag() throws JspException {  
    	int current =  page.getPageNo(); 
    	int begin = 1;
    	int end = page.getTotalPage();
    	HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest(); 
    	request.setAttribute("current", current);  
    	request.setAttribute("begin", begin);  
    	request.setAttribute("end", end);  
    	request.setAttribute("pList", page.getPageNoDisp());
    	String pList = page.getPageNoDisp();
    	//无数据不输出
    	if(page.getTotalRecord() == 0){
    		return 0;
    	}
    	StringBuilder sb = new StringBuilder(); 
    	sb.append("<div class='div-page'> ");
    	if (current!=1 && end!=0){
    		sb.append("<a href='javascript:pageClick(1");
			sb.append(",\"");
			sb.append(formid);
			sb.append("\",\"");
			sb.append(subtype);
			sb.append("\")' class='div-a'>首页</a>");
    		sb.append("<a href='javascript:pageClick(");
    		sb.append(current-1);
			sb.append(",\"");
			sb.append(formid);
			sb.append("\",\"");
			sb.append(subtype);
    		sb.append("\")'class='div-a'>前页</a> ");
    	}else{
    		sb.append("<a class='div-a'>首页</a>");
    		sb.append("<a class='div-a'>前页</a>");
    	}
    	String[] parray = pList.split("\\|");
    	for(int i=0;i<parray.length;i++){
    		String tmp = parray[i];
    		if("0".equals(tmp)){
    			sb.append("<label style='font-size: 10px; width: 20px; text-align: center;'>...</label> ");
    		}else if(!String.valueOf(current).equals(tmp)){

    			sb.append("<a href='javascript:pageClick(");
    			sb.append(tmp);
    			sb.append(",\"");
    			sb.append(formid);
    			sb.append("\",\"");
    			sb.append(subtype);
    			sb.append("\")' class='div-a'>");
    			sb.append(tmp);
    			sb.append("</a> ");
    		}else{
    			sb.append("<a class='div-page-current'>");
    			sb.append(tmp);
    			sb.append("</a>");
    		}
    	}
    	if (current<end && end!=0){
    		sb.append("<a class='div-a' href='javascript:pageClick(");
    		sb.append(current+1);
			sb.append(",\"");
			sb.append(formid);
			sb.append("\",\"");
			sb.append(subtype);
    		sb.append("\")'>后页</a>");
    		
    		sb.append("<a class='div-a' href='javascript:pageClick(");
    		sb.append(end);
			sb.append(",\"");
			sb.append(formid);
			sb.append("\",\"");
			sb.append(subtype);
    		sb.append("\")'>末页</a>");
    	}else{
    		sb.append("<a class='div-a'>后页</a>");
    		sb.append("<a class='div-a'>末页</a>");
    	}
    	sb.append("<a class='div-a'>总条数：");
    	sb.append(page.getTotalRecord());
    	sb.append("</a>");
    	sb.append("</div>");
    	//页面隐藏分页属性
    	
    	sb.append("<input id='paraJson' type = 'hidden' value='");
    	sb.append(page.getParaJson());
    	sb.append("'/>");
    	sb.append("<input id='paraListJson' type = 'hidden' value='");
    	sb.append(page.getParaListJson());
    	sb.append("'/>");
    	sb.append("<input id='pageSize' type = 'hidden' value='");
    	sb.append(page.getPageSize());
    	sb.append("'/>");
    	sb.append("<input id='searchUrl' type = 'hidden' value='");
    	sb.append(page.getSearchUrl());
    	sb.append("'/>");
    	sb.append("<input id='pageNo' name='pageNo' type = 'hidden'/>");
    	sb.append("<input id='pageflag' name='pageflag' type = 'hidden' value='0'/>");
    	try {
			this.pageContext.getOut().println(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return 0;  
    }


	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}


	public String getFormid() {
		return formid;
	}


	public void setFormid(String formid) {
		this.formid = formid;
	}


	public String getSubtype() {
		return subtype;
	}


	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

  
  


}
