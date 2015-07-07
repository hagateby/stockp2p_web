package com.p2p.webapp.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class FlowTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String flow_typ_invest = "1$,2$9$,5$,3$,4$";
	private final String flow_typ_investname = "投资,审核,开签,结算,利润分配";
	private final String flow_typ_product = "0$,3$,2$,4$,5$";
	private final String flow_typ_productname = "发行,开签,结算,利润分配中,利润分配";
	
	private String flowstep;
	private String flowtyp;
	
	public int doStartTag() throws JspException{
		StringBuilder sb = new StringBuilder();
		String flow = "";
		String flowname = "";
		String iconclass = "";
		if("typ_invest".equals(flowtyp)){
			flow = flow_typ_invest;
			flowname = flow_typ_investname;
			iconclass = "invest_";
		}
		if("typ_product".equals(flowtyp)){
			flow = flow_typ_product;
			flowname = flow_typ_productname;
			iconclass = "product_";
		}
		String flowaray[] = flow.split(",");
		String flownamearay[] = flowname.split(",");
		
		sb.append("<div class='" +iconclass +"stepInfo'>");
		//li
		sb.append("<ul>");
		int end = findIndex(flowaray,flowstep);
		for(int i=0;i<end;i++){
			sb.append("<li class='" +iconclass +"stepInfo_li_active'></li>");
		}
		for(int i=end;i<flowaray.length-1;i++){
			sb.append("<li class='" +iconclass +"stepInfo_li_unactive'></li>");
		}
		sb.append("</ul>");
		
		//name
		for(int i=0;i<=end;i++){
			sb.append("<div class='" +iconclass +"stepIco "+ iconclass + "stepIco"+(i+1)+"'>");
			sb.append(i+1);
			sb.append("<div class='" +iconclass +"stepText'>");
			sb.append(flownamearay[i]);
			sb.append("</div>");
			sb.append("</div>");
		}
		for(int i=end+1;i<flowaray.length;i++){
			sb.append("<div class='" +iconclass +"stepIco_un "+ iconclass + "stepIco"+(i+1)+"'>");
			sb.append(i+1);
			sb.append("<div class='" +iconclass +"stepText'>");
			sb.append(flownamearay[i]);
			sb.append("</div>");
			sb.append("</div>");
		}
		sb.append("<div style='height:30px'>");
		sb.append("</div>");
		sb.append("</div>");
		
		try {
			this.pageContext.getOut().println(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	private int findIndex(String aray[],String str){
		for(int i=0;i<aray.length;i++){
			if(aray[i].indexOf(str+"$") >=0){
				return i;
			}
		}
		return 0;
	}
	
	public String getFlowstep() {
		return flowstep;
	}

	public void setFlowstep(String flowstep) {
		this.flowstep = flowstep;
	}

	public String getFlowtyp() {
		return flowtyp;
	}

	public void setFlowtyp(String flowtyp) {
		this.flowtyp = flowtyp;
	}

}
