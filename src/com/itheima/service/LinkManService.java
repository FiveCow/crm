package com.itheima.service;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.LinkMan;
import com.itheima.domain.PageBean;

public interface LinkManService {

	PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

	void save(LinkMan linkMan);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);

}
