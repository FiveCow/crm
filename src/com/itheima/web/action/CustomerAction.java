package com.itheima.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;
import com.itheima.service.CustomerService;
import com.itheima.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	
	private Customer customer = new Customer();

	@Override
	public Customer getModel() {
		return customer;
	}
	
	private CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//跳转页面
	public String saveUI(){
		return "saveUI";
	}
	
	private String uploadFileName; //文件名
	private String uploadContentType;//文件类型
	private File upload;//上传文件
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	//保存客户操作
	public String save() throws IOException{
		//定义一个文件的保存路径
		String path = "d:/upload";
		//使用唯一文件名
		String uuidFileName = UploadUtils.getUUIDFileName(uploadFileName);
		//创建二级目录
		String realpath = UploadUtils.getPath(uuidFileName); //   /2/3
		
		path = path+realpath;
		//即代表文件，又代表目录
		File storeDir = new File(path);
		if(!storeDir.exists()){
			storeDir.mkdirs();
		}
		//创建一个文件对象
		File file = new File(path+"/"+uuidFileName);
		//保存文件 
		FileUtils.copyFile(upload, file);
		
		customer.setCust_image(path+"/"+uuidFileName); //保存图片路径到客户对象中
		
		
		customerService.save(customer);
		return NONE;
	}
	
	
	//设置当前页码
	private Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		if(currentPage==null){
			currentPage = 1;
		}else{
			this.currentPage = currentPage;
		}
	}
	//设置每页显示条数
	private Integer pageSize = 3;
	public void setPageSize(Integer pageSize) {
		if(pageSize==null){
			this.pageSize = 3;
		}else{
			this.pageSize = pageSize;
		}
	}

	//分页查询
	public String findByPage(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		if(!"".equals(customer.getCust_name())&&customer.getCust_name()!=null){
			detachedCriteria.add(Restrictions.like("cust_name", customer.getCust_name(), MatchMode.ANYWHERE));
		}
		//给客户来源添加条件
		if(customer.getBaseDictSource()!=null&&!"".equals(customer.getBaseDictSource().getDict_id())){
			detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
		}
		//给客户来源添加条件
		if(customer.getBaseDictLevel()!=null&&!"".equals(customer.getBaseDictLevel().getDict_id())){
			detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
		}
		
		//给客户来源添加条件
		if(customer.getBaseDictIndustry()!=null&&!"".equals(customer.getBaseDictIndustry().getDict_id())){
			detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
		}
		
		
		//调用业务
		PageBean<Customer> pb = customerService.findByPage(detachedCriteria,currentPage,pageSize);
		ActionContext.getContext().getValueStack().push(pb);//把pb对象保存到值栈对象中
		return "findByPageSuccess";
	}
	
	//根据cust_id删除客户
	public String delete(){
		customer = customerService.findById(customer.getCust_id());
		String path = customer.getCust_image();
		if(path!=null){
			//根据文件路径，创建一个目录对象
			File file = new File(path);
			if(file.exists()){
				file.delete();
			}
		}
		customerService.delete(customer);
		return "deleteSuccess";
	}
	
	//根据id查询客户对象，做数据回显
	public String edit(){
		customer = customerService.findById(customer.getCust_id());
		ActionContext.getContext().getValueStack().push(customer);
		return "editSuccess";
	}
	
	public String update() throws IOException{
		if(upload!=null){
			String cust_image = customer.getCust_image();
			//删除原文件
			if(!"".equals(cust_image)&&cust_image!=null){
				//根据文件路径，创建一个目录对象
				File file = new File(cust_image);
				if(file.exists()){
					file.delete();
				}
			}
			//上传新文件
			//定义一个文件的保存路径
			String path = "d:/upload";
			//使用唯一文件名
			String uuidFileName = UploadUtils.getUUIDFileName(uploadFileName);
			//创建二级目录
			String realpath = UploadUtils.getPath(uuidFileName); //   /2/3
			
			path = path+realpath;
			//即代表文件，又代表目录
			File storeDir = new File(path);
			if(!storeDir.exists()){
				storeDir.mkdirs();
			}
			//创建一个文件对象
			File file = new File(path+"/"+uuidFileName);
			//保存文件 
			FileUtils.copyFile(upload, file);
			
			customer.setCust_image(path+"/"+uuidFileName); //保存图片路径到客户对象中
		}
		customerService.update(customer);
		return "updateSuccess";
	}
	
	
	//查询所有客户
	public String findAll() throws IOException{
		List<Customer> list = customerService.findAll();
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"linkMans","baseDictSource","baseDictIndustry","baseDictLevel"});
		
		String json = JSONArray.fromObject(list,jsonConfig).toString();
		System.out.println(json); // [{"cust_id":1,"cust_name":"百度","baseDictSource":{"dict_id":"1","dict_name":"小广告"}},{"cust_id":2,"cust_name":"新浪"}]
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(json);
		return NONE;
	}
}
