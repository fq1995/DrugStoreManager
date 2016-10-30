package com.fq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.DrugInventorDAO;
import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
import com.fq.po.InventoriesBean;
import com.fq.service.DrugInventorService;
import com.fq.util.PageModel;
@Service("drugInventorService")
public class DrugInventorServiceImpl implements DrugInventorService {
	
	@Autowired
	private DrugInventorDAO drugInventorDao;
	
	@Override
	public InventoriesBean selectInventorByName(String name) {
		return drugInventorDao.selectInventorByName(name);
	}

	@Override
	public InventoriesBean selectInventorByDrugcode(Integer code) {
		return drugInventorDao.selectInventorByDrugcode(code);
	}

	@Override
	public void addInventor(DrugBean drugBean,InventoriesBean bean, String time){
		drugInventorDao.addInventor(drugBean,bean,time);
	}

	

	@Override
	public PageModel<InventoriesBean> splitInventor(Integer currPage, Integer pageSize, String keyword) {
		return drugInventorDao.splitInventor(currPage, pageSize, keyword);
	}

	@Override
	public List<InventoriesBean> showAllInventor(String ids) {
		return drugInventorDao.showAllInventor(ids);
	}

	@Override
	public void deleteAllInventor(List<InventoriesBean> list) {
		drugInventorDao.deleteAllInventor(list);

	}


	@Override
	public void updateInventor(InventoriesBean bean) {
		drugInventorDao.updateInventor(bean);
	}

	@Override
	public InventoriesBean selectById(String id) {
		return drugInventorDao.selectById(id);
	}

	@Override
	public List<DrugCategoryBean> selectCategory() {
		return drugInventorDao.selectCategory();
	}

	@Override
	public List<DrugUnitBean> selectUnit() {
		return drugInventorDao.selectUnit();
	}

	@Override
	public List<DosageformBean> selectForm() {
		return drugInventorDao.selectForm();
	}

	@Override
	public PageModel<InventoriesBean> splitWarn(Integer currPage, Integer pagesize, String keyword) {
		return drugInventorDao.splitWarn(currPage, pagesize, keyword);
	}

}
