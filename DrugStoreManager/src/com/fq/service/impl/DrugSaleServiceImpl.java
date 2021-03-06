package com.fq.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fq.dao.DrugSaleDAO;
import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugSalesBean;
import com.fq.po.DrugUnitBean;
import com.fq.po.MemberBean;
import com.fq.util.SaleStats;
import com.fq.po.UserBean;
import com.fq.service.DrugSaleService;
import com.fq.util.DrugCombogrid;
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
	public List<DrugBean> selectDrug() {
		return drugSaleDao.selectDrug();
	}

	@Override
	public List<MemberBean> selectMember() {
		return drugSaleDao.selectMember();
	}

	@Override
	public DrugBean selectSaleByDrugId(String id) {
		return drugSaleDao.selectSaleByDrugId(id);
	}

	@Override
	public List<UserBean> selectUser() {
		return drugSaleDao.selectUser();
	}

	@Override
	public void addSale(String tel, DrugSalesBean bean) throws Exception {
		drugSaleDao.addSale(tel,bean);
		
	}

	@Override
	public List<DrugSalesBean> show(Date date) {
		return drugSaleDao.show(date);
	}

	@Override
	public MemberBean selectSaleByTel(String suppliertel) {
		return drugSaleDao.selectSaleByTel(suppliertel);
	}

	@Override
	public Integer selectCode() {
		return drugSaleDao.selectCode();
	}

	@Override
	public String stats() {
		List<DrugSalesBean> list = drugSaleDao.stats();
		String json = null;
		List<SaleStats> list2 = new ArrayList<>();
		if(list.size()>=10){
			for(int i = 0; i<10; i++){
				list2.add(new SaleStats(list.get(i).getSalesVolume(),list.get(i).getDrugBean().getDrugName()));
			}
		}
		if(list.size()<10){
			for(int i = 0;i<list.size();i++){
				list2.add(new SaleStats(list.get(i).getSalesVolume(),list.get(i).getDrugBean().getDrugName()));
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
	public String comboGrid() {
		String json = null;
		List<DrugBean> list = drugSaleDao.comboGrid(); 
		List<DrugCombogrid> list2 = new ArrayList<>();
		for(int i = 0;i<list.size();i++){
			list2.add(new DrugCombogrid(list.get(i).getDrugId(),
										list.get(i).getDrugCode(), 
										list.get(i).getDrugName(), 
										list.get(i).getDosageformBean().getDosageform(), 
										list.get(i).getDrugUnitBean().getUnitname(), 
										list.get(i).getDrugCategoryBean().getCategory(), 
										list.get(i).getSalepeice(),
										list.get(i).getApprovalNumber(), 
										list.get(i).getManufacturer()));
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
	public void updateMember(String tel,DrugSalesBean bean) {
		drugSaleDao.updateMember(tel,bean);
		
	}

	
}
