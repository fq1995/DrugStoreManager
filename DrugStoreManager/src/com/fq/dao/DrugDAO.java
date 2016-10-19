package com.fq.dao;

import java.util.List;

import com.fq.po.DrugBean;
import com.fq.util.PageModel;

public interface DrugDAO {
	
	DrugBean selectDrugByName(String drugname);
	
	DrugBean selectDrugByDrugcode(Integer drugcode);
	
	DrugBean selectDrugByNameAndDrugId(String drugname,String drugid);
	
	void addDrug(DrugBean DrugBean,String time) throws Exception;
	
	void addDrug(DrugBean DrugBean);

	PageModel<DrugBean> splitDrug(Integer currPage, Integer pageSize, String keyword);
	//批量查询
	List<DrugBean> showAllDrug(String ids);
	//批量删除对象
	void deleteAllDrug(List<DrugBean> drugList);
	//修改
	void updateDrug(DrugBean DrugBean,String time);
	
	void updateDrug(DrugBean DrugBean);
	//根据ID查询
	DrugBean selectById(String id);
}
