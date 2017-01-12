package com.fq.dao;

import java.util.List;

import com.fq.po.DrugUnitBean;
import com.fq.util.PageModel;

public interface DrugUnitDAO {
	
	DrugUnitBean selectDrugUnitByName(String drugname);
	
	DrugUnitBean selectDrugUnitByNameAndDrugUnitId(String drugUnitname,String drugUnitId);
	
	void addDrugUnit(DrugUnitBean drugUnitBean);

	PageModel<DrugUnitBean> splitDrugUnit(Integer currPage, Integer pageSize, String keyword);
	//批量查询
	List<DrugUnitBean> showAllDrugUnit(String ids);
	//批量删除对象
	void deleteAllDrugUnit(List<DrugUnitBean> drugUnitList);
	//修改
	void updateDrugUnit(DrugUnitBean drugUnitBean);
	//根据ID查询
	DrugUnitBean selectById(String id);
}
