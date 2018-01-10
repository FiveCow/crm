package com.itheima.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.PageBean;
import com.itheima.domain.SaleVisit;
import com.itheima.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

	private SaleVisit saleVisit = new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}

	@Resource(name="saleVisitService")
	private SaleVisitService saleVisitService;
	
	
	private Integer currentPage = 1;
	private Integer pageSize = 3;
	
	public void setCurrentPage(Integer currentPage) {
		if(currentPage==null){
			this.currentPage = 1;
		}else{
			this.currentPage = currentPage;
		}
	}

	public void setPageSize(Integer pageSize) {
		if(pageSize==null){
			this.pageSize = 3;
		}else{
			this.pageSize = pageSize;
		}
	}

	private Date visit_end_time;

	public Date getVisit_end_time() {
		return visit_end_time;
	}
	public void setVisit_end_time(Date visit_end_time) {
		this.visit_end_time = visit_end_time;
	}

	//分页查询客户拜访
	public String findByPage(){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		if(saleVisit.getVisit_time()!=null){
			detachedCriteria.add(Restrictions.ge("visit_time", saleVisit.getVisit_time()));
		}
		if(visit_end_time!=null){
			detachedCriteria.add(Restrictions.le("visit_time", visit_end_time));
		}
		
		PageBean<SaleVisit> pb = saleVisitService.findByPage(detachedCriteria,currentPage,pageSize);
		ActionContext.getContext().getValueStack().push(pb);
		return "findByPageSuccess";
	}
	
	//新增客户拜访页面跳转
	public String saveUI(){
		return "saveUI";
	}
	
	//添加客户拜访信息
	public String save(){
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
	
}
