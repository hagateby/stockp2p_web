package com.p2p.webapp.user.jsontest.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.user.jsontest.bean.ServiceMsgHead;
import com.p2p.webapp.user.jsontest.bean.ServicePara;
import com.p2p.webapp.user.jsontest.bean.User;

public class JsonTestAction extends BaseAction{

	public void test() throws IOException{
		System.out.println("dasddsa");
		 String tt= (String)request.getParameter("para");
		 System.out.println("收到报文："+tt);
		 Map<String,Class<?>> m  = new HashMap<String,Class<?>>();
		 ServiceMsgHead serviceMsgHead  = (ServiceMsgHead)JSONObject.toBean( JSONObject.fromObject(tt), ServiceMsgHead.class, m );
		 String servicePara = serviceMsgHead.getServicePara();
		 User user = new User();
		 user.setAddress("永安里");
		 user.setBindingMobile("13422343243");
		 user.setBindingState("das");
		 user.setBirthday("1988-09-09");
		 user.setCid("11");
		 user.setEmail("22@qq.com");
		 user.setLoginDate("2015-06-19");
		 user.setLoginType("A");
		 user.setMobile("1352324432");
		 user.setNickName("哈哈");
		 user.setPassword("123456");
		 user.setPolicyCount("2");
		 user.setProvince("山东");
		 user.setResultCode("0");
		 user.setResultMsg("登录成功");
		 user.setSessionId("312312");
		 user.setSex("0");
		 user.setTelePhone("754321321");
		 user.setUserName("dada");
		 user.setZipCode("100000");
		 
		 String rr = JSONObject.fromObject(user).toString();
		 
		 System.out.println("返回报文："+rr);
		 
		 response.setContentType("text/html;charset=utf-8"); 
		 PrintWriter out = response.getWriter();  
		 out.println(rr);  
		 out.flush();  
		 out.close();
	}
}
