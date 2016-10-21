package com.fq.service;

import java.util.List;

import com.fq.po.DrugUnitBean;
import com.fq.util.PageModel;

public interface DrugUnitService {
	//根据名称查询
	DrugUnitBean selectDrugUnitByName(String drugname);
	//根据id和名称查询
	DrugUnitBean selectDrugUnitByNameAndDrugUnitId(String drugUnitname,String drugUnitId);
	//添加
	void addDrugUnit(DrugUnitBean drugUnitBean);
	//分页
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
