package com.itheima.web.interceptor;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//获得用户对象
		User existUser = (User) ActionContext.getContext().getSession().get("existUser");
		if(existUser==null){
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("您没有权限，请先登录");
			return  actionSupport.LOGIN;
		}
		
		//放行
		return invocation.invoke();
	}

}
