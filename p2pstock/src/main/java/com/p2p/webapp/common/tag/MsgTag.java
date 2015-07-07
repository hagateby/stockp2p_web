package com.p2p.webapp.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.p2p.webapp.system.parammgr.entity.MsgInfo;
import com.p2p.webapp.system.parammgr.service.ParamMgrService;
import com.p2p.webapp.system.parammgr.service.impl.ParamMgrServiceImpl;

/**
 * 根据msgcode获取msg信息标签
 * @author songcs
 *
 */
public class MsgTag extends TagSupport{
	/** 
     *  
     */  
    private static final long serialVersionUID = 1L;  

    private String msgcode;
      
    @Override  
    public int doStartTag() throws JspException {  
    	ParamMgrService ps = new ParamMgrServiceImpl();
    	MsgInfo msginfo = ps.queryMsgInfoByCode(msgcode);
    	if(msginfo == null){
    		return 0;
    	}
    	StringBuffer sb = new StringBuffer();
    	sb.append(msginfo.getMsg_comment());
    	try {
			this.pageContext.getOut().println(sb.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return 0;  
    }

	public String getMsgcode() {
		return msgcode;
	}

	public void setMsgcode(String msgcode) {
		this.msgcode = msgcode;
	}
}
