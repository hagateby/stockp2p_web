package com.p2p.webapp.common.intercepts;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * ���������׳�������ʱ�쳣�����й���������ֹ������ʱ�쳣��ʾҳ��
 * @author cssong
 *
 */
public class ErrIntercept extends MethodFilterInterceptor{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//д��־����
	public static Logger logger = LoggerFactory.getLogger(ErrIntercept.class);
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		logger.debug("����������ErrIntercept");
		String result = "";
		try{
			result =  invocation.invoke();
			System.out.println("����������ErrIntercept");
			return result;
		}catch(Exception e){
			//����ҳ����ʾ�쳣��Ϣ
			String errmsg = "�𾴵��û����ã�ϵͳ�����쳣���������Ա��ϵ��";
			HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
			request.setAttribute("errmsg", errmsg);
			StringWriter out = new StringWriter();
			PrintWriter pw = new PrintWriter(out);
			e.printStackTrace(pw);
			logger.error(out.toString());
			logger.debug("����������ErrIntercept->error");
			return "error";
		}
	}
}
