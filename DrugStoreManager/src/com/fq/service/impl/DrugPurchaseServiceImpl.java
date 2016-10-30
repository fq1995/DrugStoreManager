package com.fq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.DrugPurchaseDAO;
import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugPurchaseBean;
import com.fq.po.DrugUnitBean;
import com.fq.po.MemberBean;
import com.fq.po.SupplierBean;
import com.fq.po.UserBean;
import com.fq.service.DrugPurchaseService;
import com.fq.util.PageModel;
@Service("drugPurchaseService")
public class DrugPurchaseServiceImpl implements DrugPurchaseService {

	@Autowired
	private DrugPurchaseDAO drugPseDao;

	@Override
	public DrugPurchaseBean selectPseByName(String name) {
		return drugPseDao.selectPseByName(name);
	}

	@Override
	public DrugPurchaseBean selectPseByDrugcode(Integer code) {
		return drugPseDao.selectPseByDrugcode(code);
	}

	@Override
	public DrugPurchaseBean selectPseByDrugId(String id) {
		return drugPseDao.selectPseByDrugId(id);
	}

	@Override
	public PageModel<DrugPurchaseBean> splitPse(Integer currPage, Integer pageSize, String keyword) {
		return drugPseDao.splitPse(currPage, pageSize, keyword);
	}

	@Override
	public List<DrugPurchaseBean> showAllPse(String ids) {
		return drugPseDao.showAllPse(ids);
	}

	@Override
	public void deleteAllPse(List<DrugPurchaseBean> list) {
		drugPseDao.deleteAllPse(list);
		
	}


	@Override
	public void updatePse(DrugPurchaseBean bean) {
		drugPseDao.updatePse(bean);
		
	}

	@Override
	public DrugPurchaseBean selectById(String id) {
		return drugPseDao.selectById(id);
	}

	@Override
	public List<DrugCategoryBean> selectCategory() {
		return drugPseDao.selectCategory();
	}

	@Override
	public List<DrugUnitBean> selectUnit() {
		return drugPseDao.selectUnit();
	}

	@Override
	public List<DosageformBean> selectForm() {
		return drugPseDao.selectForm();
	}

	@Override
	public List<DrugBean> selectDrug() {
		return drugPseDao.selectDrug();
	}

	@Override
	public List<MemberBean> selectMember() {
		return drugPseDao.selectMember();
	}

	@Override
	public List<UserBean> selectUser() {
		return drugPseDao.selectUser();
	}

	@Override
	public void addPse(SupplierBean supBean,DosageformBean dfBean, DrugCategoryBean dcBean, DrugUnitBean duBean, DrugBean drugBean,
			DrugPurchaseBean drugPseBean, String time) {
		drugPseDao.addPse(supBean, dfBean, dcBean, duBean, drugBean, drugPseBean, time);
		
		
	}

	@Override
	public List<SupplierBean> selectSupplier() {
		return drugPseDao.selectSupplier();
	}

	@Override
	public PageModel<DrugPurchaseBean> splitDateWarn(Integer currPage, Integer pagesize, String keyword) {
		return drugPseDao.splitDateWarn(currPage, pagesize, keyword);
	}
	
	
}
