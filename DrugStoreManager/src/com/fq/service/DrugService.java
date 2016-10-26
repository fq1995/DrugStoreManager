package com.fq.service;

import java.util.Date;
import java.util.List;

import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
import com.fq.util.PageModel;

public interface DrugService {
	
	DrugBean selectDrugByName(String drugname);
	
	DrugBean selectDrugByDrugcode(Integer drugcode);
	
	DrugBean selectDrugByNameAndDrugId(String drugname,String drugid);
	
	void addDrug(DrugBean DrugBean,String time) throws Exception;
	
	void addDrug(DrugBean DrugBean);

	PageModel<DrugBean> splitDrug(Integer currPage, Integer pageSize, String keyword);
	
	PageModel<DrugBean> splitDrug(Integer currPage, Integer pageSize,String drugName,String dosageform,String drugUnit,String drugCategory,String manufacturer,Date modifyTime,String modifier);
	//批量查询
	List<DrugBean> showAllDrug(String ids);
	//批量删除对象
	void deleteAllDrug(List<DrugBean> drugList);
	//修改
	void updateDrug(DrugBean DrugBean,String time);
	
	void updateDrug(DrugBean DrugBean);
	//根据ID查询
	DrugBean selectById(String id);
	//查询类别
	List<DrugCategoryBean> selectCategory();
	//查询单位
	List<DrugUnitBean> selectUnit();
	//查询剂型
	List<DosageformBean> selectForm();
}
