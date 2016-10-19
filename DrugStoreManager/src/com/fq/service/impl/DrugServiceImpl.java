package com.fq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.DrugDAO;
import com.fq.po.DrugBean;

import com.fq.service.DrugService;

import com.fq.util.PageModel;
@Service("drugService")
public class DrugServiceImpl implements DrugService {

	@Autowired
	private DrugDAO drugDAO;

	@Override
	public DrugBean selectDrugByName(String drugname) {
		return drugDAO.selectDrugByName(drugname);
	}

	@Override
	public DrugBean selectDrugByDrugcode(Integer drugcode) {
		return drugDAO.selectDrugByDrugcode(drugcode);
	}

	@Override
	public DrugBean selectDrugByNameAndDrugId(String drugname, String drugid) {
		return drugDAO.selectDrugByNameAndDrugId(drugname, drugid);
	}

	@Override
	public void addDrug(DrugBean DrugBean, String time) throws Exception {
		drugDAO.addDrug(DrugBean, time);
	}

	@Override
	public void addDrug(DrugBean DrugBean) {
		drugDAO.addDrug(DrugBean);
	}

	@Override
	public PageModel<DrugBean> splitDrug(Integer currPage, Integer pageSize, String keyword) {
		return drugDAO.splitDrug(currPage, pageSize, keyword);
	}

	@Override
	public List<DrugBean> showAllDrug(String ids) {
		return drugDAO.showAllDrug(ids);
	}

	@Override
	public void deleteAllDrug(List<DrugBean> drugList) {
		drugDAO.deleteAllDrug(drugList);
	}

	@Override
	public void updateDrug(DrugBean DrugBean, String time) {
		drugDAO.updateDrug(DrugBean, time);
	}

	@Override
	public DrugBean selectById(String id) {
		return drugDAO.selectById(id);
	}

	@Override
	public void updateDrug(DrugBean DrugBean) {
		drugDAO.updateDrug(DrugBean);
		
	}
	
	

}
