package com.fq.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fq.dao.DrugPurchaseDAO;
import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugPurchaseBean;
import com.fq.po.DrugUnitBean;
import com.fq.po.MemberBean;
import com.fq.util.PurchaseStats;
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
	public void updatePse(DrugPurchaseBean bean, String time) {
		drugPseDao.updatePse(bean,time);
		
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
	public List<SupplierBean> selectSupplier() {
		return drugPseDao.selectSupplier();
	}

	@Override
	public PageModel<DrugPurchaseBean> splitDateWarn(Integer currPage, Integer pagesize, String keyword) {
		return drugPseDao.splitDateWarn(currPage, pagesize, keyword);
	}

	@Override
	public List<DrugPurchaseBean> show(Date date) {
		return drugPseDao.show(date);
	}

	@Override
	public Integer selectCode() {
		return drugPseDao.selectCode();
	}

	@Override
	public PageModel<DrugPurchaseBean> splitOverDate(Integer currPage, Integer pagesize, String keyword) {
		return drugPseDao.splitOverDate(currPage, pagesize, keyword);
	}

	@Override
	public String stats() {
		String json = null;
		List<DrugPurchaseBean> list = drugPseDao.stats();
		List<PurchaseStats> list2 = new ArrayList<>();
		if(list.size()>=10){
			for(int i = 0; i<10;i++){
				list2.add(new PurchaseStats(list.get(i).getAmount(),list.get(i).getDrugBean().getDrugName()));
			}
		}
		if(list.size()<10){
			for(int i = 0; i<list.size();i++){
				list2.add(new PurchaseStats(list.get(i).getAmount(),list.get(i).getDrugBean().getDrugName()));
			}
		}
		ObjectMapper mapper = new ObjectMapper();  
		try {
			json = mapper.writeValueAsString(list2);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}  
		return json;
	}

	@Override
	public void addPse(Integer drugCode, Integer pseCode, DrugPurchaseBean drugPseBean, String time) {
		drugPseDao.addPse(drugCode, pseCode, drugPseBean, time);
		
	}
	
	
}
