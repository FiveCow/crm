package com.itheima.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.BaseDict;
import com.itheima.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {
	
	private BaseDict baseDict = new BaseDict();

	@Override
	public BaseDict getModel() {
		return baseDict;
	}
	
	private BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}

	public String findById() throws IOException{
		List<BaseDict> list = baseDictService.findById(baseDict.getDict_type_code());
		//[{"id":1,"type_code":"001","item_name":"教育培训 "},{"id":2,"type_code":"002","item_name":"网络营销"}]
		String json = JSONArray.fromObject(list).toString();
		System.out.println(json);
		//JSONObject.fromObject(object); //{"id":1,"type_code":"001","item_name":"教育培训 "}
		
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);//响应数据到客户端浏览器 
		return NONE;
	}
	
}
