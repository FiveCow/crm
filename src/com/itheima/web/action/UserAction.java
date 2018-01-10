package com.itheima.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.ActionError;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	//注入业务类
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//用户注册 
	public String regist(){
		userService.regist(user);
		return "registSuccess";
	}
	
	//用户登录
	public String login(){
		User existUser = userService.login(user);
		if(existUser==null){
			addActionError("用户名或密码错误！");
			return LOGIN;
		}
		//保存用户对象
		ActionContext context = ActionContext.getContext();
		context.getSession().put("existUser", existUser);
		return "loginSuccess";
	}
	
	
	//查询所有业务员
	public String findAll() throws IOException{
		List<User> list = userService.findAll();
		String json = JSONArray.fromObject(list).toString();
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return NONE;
	}
}
