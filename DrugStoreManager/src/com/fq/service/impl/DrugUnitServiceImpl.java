package com.fq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.DrugUnitDAO;
import com.fq.po.DrugUnitBean;
import com.fq.service.DrugUnitService;
import com.fq.util.PageModel;
@Service("drugUnitService")
public class DrugUnitServiceImpl implements DrugUnitService {
	
	@Autowired
	private DrugUnitDAO drugUnitDao;
	
	@Override
	public DrugUnitBean selectDrugUnitByName(String unitname) {
		return drugUnitDao.selectDrugUnitByName(unitname);
	}

	@Override
	public DrugUnitBean selectDrugUnitByNameAndDrugUnitId(String drugUnitname, String drugUnitId) {
		return drugUnitDao.selectDrugUnitByNameAndDrugUnitId(drugUnitname, drugUnitId);
	}

	@Override
	public void addDrugUnit(DrugUnitBean drugUnitBean) {
		drugUnitDao.addDrugUnit(drugUnitBean);
	}

	@Override
	public PageModel<DrugUnitBean> splitDrugUnit(Integer currPage, Integer pageSize, String keyword) {
		return drugUnitDao.splitDrugUnit(currPage, pageSize, keyword);
	}

	@Override
	public List<DrugUnitBean> showAllDrugUnit(String ids) {
		return drugUnitDao.showAllDrugUnit(ids);
	}

	@Override
	public void deleteAllDrugUnit(List<DrugUnitBean> drugUnitList) {
		drugUnitDao.deleteAllDrugUnit(drugUnitList);

	}

	@Override
	public void updateDrugUnit(DrugUnitBean drugUnitBean) {
		drugUnitDao.updateDrugUnit(drugUnitBean);

	}

	@Override
	public DrugUnitBean selectById(String id) {
		return drugUnitDao.selectById(id);
	}

}
