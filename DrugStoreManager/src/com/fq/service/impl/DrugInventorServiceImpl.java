package com.fq.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fq.dao.DrugInventorDAO;
import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugBuy;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
import com.fq.util.InvenStats;
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
	public void addInventor(Integer code,Integer drugCode,DrugBean drugBean,InventoriesBean bean, String time){
		drugInventorDao.addInventor(code,drugCode,drugBean,bean,time);
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

	@Override
	public List<InventoriesBean> selectByDate(Date date) {
		return drugInventorDao.selectByDate(date);
	}

	@Override
	public Integer select() {
		return drugInventorDao.select();
	}

	@Override
	public List<DrugBuy> addPurchase(List<InventoriesBean> list) {
		return drugInventorDao.addPurchase(list);
		
	}

	@Override
	public String stats() {
		String json = null;
		List<InventoriesBean> list = drugInventorDao.stats();
		List<InvenStats> list2 = new ArrayList<>();
		if(list.size() >= 20){
			for(int i = 0; i<20;i++){
				list2.add(new InvenStats(list.get(i).getDrugBean().getDrugName(),list.get(i).getStocknumber(),list.get(i).getStocklimit()));
			}
		}
		if(list.size() < 20){
			for(int i = 0; i<list.size();i++){
				list2.add(new InvenStats(list.get(i).getDrugBean().getDrugName(),list.get(i).getStocknumber(),list.get(i).getStocklimit()));
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
	public void updateInventor(InventoriesBean bean, String time) {
		drugInventorDao.updateInventor(bean,time);
		
	}

	@Override
	public Double getSaleByName(String drugName) {
		return drugInventorDao.getSaleByName(drugName);
	}

	

}
