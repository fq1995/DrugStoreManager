package com.fq.dao;

import java.util.Date;
import java.util.List;

import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
import com.fq.util.PageModel;

public interface DrugDAO {
	
DrugBean selectDrugByName(String drugname);
	
	
	List<DrugBean> show();
	
	DrugBean selectDrugByDrugcode(Integer drugcode);
	
	DrugBean selectDrugByNameAndDrugId(String drugname,String drugid);
	
	void addDrug(Integer drugCode, DrugBean drugBean,String time) ;
	
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
	//根据id查询类别
	DrugCategoryBean selectCategoryById(String id);
	//查询单位
	List<DrugUnitBean> selectUnit();
	//根据id查询单位
	DrugUnitBean selectUnitById(String id);
	//查询剂型
	List<DosageformBean> selectForm();
	//根据id查找剂型
	DosageformBean selectFormById(String id);
	//查询药品编号
	Integer select();
	
	
	
	
	
}
