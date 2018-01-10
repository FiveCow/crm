package com.itheima.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.Customer;
import com.itheima.domain.LinkMan;
import com.itheima.domain.PageBean;
import com.itheima.service.CustomerService;
import com.itheima.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	private LinkMan linkMan = new LinkMan();

	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	
	private LinkManService linkManService;
	
	private CustomerService customerService;
	
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}



	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	private Integer currentPage = 1;
	public void setCurrentPage(Integer currentPage) {
		if(currentPage==null)
			currentPage = 1;
		else
			this.currentPage = currentPage;
	}
	
	private Integer pageSize = 3;
	public void setPageSize(Integer pageSize) {
		if(pageSize==null)
			pageSize = 3;
		else
			this.pageSize = pageSize;
	}



	public String findByPage(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		if(linkMan.getLkm_name()!=null&&!"".equals(linkMan.getLkm_name())){
			detachedCriteria.add(Restrictions.like("lkm_name", linkMan.getLkm_name(),MatchMode.ANYWHERE));
		}
		if(linkMan.getLkm_gender()!=null && !"".equals(linkMan.getLkm_gender())){
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		PageBean<LinkMan> pb =linkManService.findByPage(detachedCriteria,currentPage,pageSize);
		
		ActionContext.getContext().getValueStack().push(pb);
		
		return "findByPageSuccess";
	}

	//跳转添加联系人
	public String saveUI(){
		List<Customer> list = customerService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "findAllSuccess";
	}
	
	//保存联系人
	public String save(){
		linkManService.save(linkMan);
		return "saveSuccess";
	}
	
	//根据id查询指定的联系人
	public String edit(){
		linkMan = linkManService.findById(linkMan.getLkm_id());
		List<Customer> list = customerService.findAll();
		
		ActionContext.getContext().getValueStack().push(linkMan);
		ActionContext.getContext().getValueStack().set("list", list);
		return "editSuccess";
	}
	
	//修改联系人
	public String update(){
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	
	//删除联系人
	public String delete(){
		linkMan = linkManService.findById(linkMan.getLkm_id());
		linkManService.delete(linkMan);
		return "deleteSuccess";
	}
}
