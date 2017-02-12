package com.fq.service;

import java.util.List;

import com.fq.po.DrugBuy;
import com.fq.util.PageModel;

public interface DrugBuyService {

	List<DrugBuy> show();

	DrugBuy selectDrugByName(String drugname);

	DrugBuy selectDrugByDrugcode(Integer drugcode);

	DrugBuy selectDrugByNameAndDrugId(String drugname, String drugid);

	void addBuy(Integer drugCode, DrugBuy DrugBuy, String time) throws Exception;

	PageModel<DrugBuy> splitDrug(Integer currPage, Integer pageSize, String keyword);

	// 批量查询
	List<DrugBuy> showAllDrug(String ids);

	// 批量删除对象
	void deleteAllDrug(List<DrugBuy> drugList);

	// 修改
	void updateDrug(DrugBuy DrugBuy, String time);


	// 根据ID查询
	DrugBuy selectById(String id);

	

	// 查询药品编号
	Integer select();
}
