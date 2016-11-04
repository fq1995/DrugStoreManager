package com.fq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.SupplierDAO;
import com.fq.po.SupplierBean;
import com.fq.service.SupplierService;
import com.fq.util.PageModel;
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierDAO supplierDao;
	
	@Override
	public SupplierBean selectSupByName(String supname) {
		return supplierDao.selectSupByName(supname);
	}

	@Override
	public SupplierBean selectSupByNameAndSupId(String supname, String supid) {
		return supplierDao.selectSupByNameAndSupId(supname, supid);
	}

	@Override
	public SupplierBean selectSupBySupcode(String supcode) {
		return supplierDao.selectSupBySupcode(supcode);
	}

	@Override
	public PageModel<SupplierBean> splitSup(Integer currPage, Integer pageSize, String keyword) {
		return supplierDao.splitSup(currPage, pageSize, keyword);
	}

	@Override
	public void addSup(Integer code,SupplierBean supBean) {
		supplierDao.addSup(code,supBean);

	}

	@Override
	public List<SupplierBean> showAllSup(String ids) {
		return supplierDao.showAllSup(ids);
	}

	@Override
	public void deleteAllSup(List<SupplierBean> supList) {
		supplierDao.deleteAllSup(supList);

	}

	@Override
	public void updateSup(SupplierBean supBean) {
		supplierDao.updateSup(supBean);

	}

	@Override
	public SupplierBean selectById(String id) {
		return supplierDao.selectById(id);
	}

	@Override
	public List<SupplierBean> show() {
		return supplierDao.show();
	}

	@Override
	public Integer select() {
		return supplierDao.select();
	}

}
