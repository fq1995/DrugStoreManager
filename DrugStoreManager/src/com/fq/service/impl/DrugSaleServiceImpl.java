package com.fq.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.DrugSaleDAO;
import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugSalesBean;
import com.fq.po.DrugUnitBean;
import com.fq.po.MemberBean;
import com.fq.po.UserBean;
import com.fq.service.DrugSaleService;
import com.fq.util.PageModel;
@Service("drugSaleService")
public class DrugSaleServiceImpl implements DrugSaleService {
	
	@Autowired
	private DrugSaleDAO drugSaleDao;
	
	@Override
	public DrugSalesBean selectSaleByName(String name) {
		return drugSaleDao.selectSaleByName(name);
	}

	@Override
	public DrugSalesBean selectSaleByDrugcode(Integer code) {
		return drugSaleDao.selectSaleByDrugcode(code);
	}

	@Override
	public void addSale(DrugBean drugBean, DrugSalesBean bean, String time) throws Exception {
		drugSaleDao.addSale(drugBean, bean, time);
		
	}


	@Override
	public PageModel<DrugSalesBean> splitSale(Integer currPage, Integer pageSize, String keyword) {
		return drugSaleDao.splitSale(currPage, pageSize, keyword);
	}

	@Override
	public List<DrugSalesBean> showAllSale(String ids) {
		return drugSaleDao.showAllSale(ids);
	}

	@Override
	public void deleteAllSale(List<DrugSalesBean> list) {
		drugSaleDao.deleteAllSale(list);
		
	}

	@Override
	public void updateSale(DrugSalesBean bean, String time) {
		drugSaleDao.updateSale(bean,time);
		
	}


	@Override
	public DrugSalesBean selectById(String id) {
		return drugSaleDao.selectById(id);
	}

	@Override
	public List<DrugCategoryBean> selectCategory() {
		return drugSaleDao.selectCategory();
	}

	@Override
	public List<DrugUnitBean> selectUnit() {
		return drugSaleDao.selectUnit();
	}

	@Override
	public List<DosageformBean> selectForm() {
		return drugSaleDao.selectForm();
	}

	@Override
	public List<DrugBean> selectDrug() {
		return drugSaleDao.selectDrug();
	}

	@Override
	public List<MemberBean> selectMember() {
		return drugSaleDao.selectMember();
	}

	@Override
	public DrugSalesBean selectSaleByDrugId(String id) {
		return drugSaleDao.selectById(id);
	}

	@Override
	public List<UserBean> selectUser() {
		return drugSaleDao.selectUser();
	}

	@Override
	public void addSale(UserBean userBean, DrugBean drugBean, DrugSalesBean bean, String time) throws Exception {
		drugSaleDao.addSale(userBean, drugBean, bean, time);
		
	}

	@Override
	public List<DrugSalesBean> show(Date date) {
		return drugSaleDao.show(date);
	}

	
}
