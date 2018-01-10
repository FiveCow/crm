package com.itheima.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.SaleVisitDao;
import com.itheima.domain.PageBean;
import com.itheima.domain.SaleVisit;
import com.itheima.service.SaleVisitService;
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {

		@Resource(name="saleVisitDao")
		private SaleVisitDao saleVisitDao;

		@Override
		public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currentPage,Integer pageSize) {
			
			int count = saleVisitDao.count(detachedCriteria);
			int totalPage = (int) Math.ceil(count*1.0/pageSize);
			List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria, currentPage, pageSize);
			
			PageBean<SaleVisit> pb = new PageBean<>();
			pb.setCount(count);
			pb.setCurrentPage(currentPage);
			pb.setList(list);
			pb.setPageSize(pageSize);
			pb.setTotalPage(totalPage);
			
			return pb;
		}

		@Override
		public void save(SaleVisit saleVisit) {
			saleVisitDao.save(saleVisit);
		}
}
