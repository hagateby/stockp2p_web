package com.p2p.webapp.common.intercepts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.p2p.webapp.common.constant.Constant;

/**
 * ����ҵ������������
 * �����û���¼״̬��У��
 * @author songcs
 *
 */
public class BizIntercept extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//д��־����
	public static Logger logger = LoggerFactory.getLogger(BizIntercept.class);


	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		logger.debug("����������BizIntercept");
		
		
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		String requestURI=request.getRequestURI();
		//��ҳʹ��
		request.setAttribute("pageUrl", requestURI);
		//�����е�¼У���UAL�����ռ�
		String filterUrl = Constant.getBizinterceptfilter();
		logger.debug("requestURI==="+requestURI);
		if(checkFilter(requestURI,filterUrl)){
			//ִ�е�¼״̬У��
			String userid = (String)request.getSession().getAttribute("userid");
			if(userid == null || "".equals(userid)){
				logger.debug("δ��¼���¼��ʱ�����µ�¼");
				request.setAttribute("errmsg", "δ��¼���¼��ʱ�����µ�¼");
				return "timeout";
			}
		}
		String result = invocation.invoke();
		logger.debug("����������BizIntercept");
		return result;
	}
	/*
	 * ��֤�Ƿ���Ҫ���е�¼У�� true��Ҫ false ����Ҫ
	 */
	private boolean checkFilter(String uri,String filter){
		String[] filterarray = filter.split(",");
		String[] uriarray = uri.split("\\/");
		int uaral = uriarray.length;
		//��ҳ����
		if(uriarray[uaral -1].equals("index.action")){
			return false;
		}
		//��¼��ע�ᡢdemo����
		for(int i = 0;i< filterarray.length;i++){
			if(filterarray[i].equals(uriarray[uaral -2])){
				return false;
			}
		}
		
		return true;
	}

}
