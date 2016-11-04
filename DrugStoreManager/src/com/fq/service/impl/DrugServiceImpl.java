package com.fq.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.DrugDAO;
import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
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
	public void addDrug(Integer drugCode, DrugBean drugBean, String time) throws Exception {
		drugDAO.addDrug(drugCode,drugBean, time);
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

	@Override
	public List<DrugCategoryBean> selectCategory() {
		return drugDAO.selectCategory();
	}

	@Override
	public List<DrugUnitBean> selectUnit() {
		return drugDAO.selectUnit();
	}

	@Override
	public List<DosageformBean> selectForm() {
		return drugDAO.selectForm();
	}

	@Override
	public PageModel<DrugBean> splitDrug(Integer currPage, Integer pageSize, String drugName, String dosageform,
			String drugUnit, String drugCategory, String manufacturer, Date modifyTime, String modifier) {
		return drugDAO.splitDrug(currPage, pageSize, drugName, dosageform, drugUnit, drugCategory, manufacturer, modifyTime, modifier);
	}

	@Override
	public DrugCategoryBean selectCategoryById(String id) {
		return drugDAO.selectCategoryById(id);
	}

	@Override
	public DrugUnitBean selectUnitById(String id) {
		return drugDAO.selectUnitById(id);
	}

	@Override
	public DosageformBean selectFormById(String id) {
		return drugDAO.selectFormById(id);
	}

	@Override
	public List<DrugBean> show() {
		return drugDAO.show();
	}

	@Override
	public Integer select() {
		return drugDAO.select();
	}
	
	

}
