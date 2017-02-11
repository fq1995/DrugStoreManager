package com.fq.dao;

import java.util.List;

import com.fq.po.DrugBuy;
import com.fq.util.PageModel;

public interface DrugBuyDAO {
	List<DrugBuy> show();
	
	DrugBuy selectBuyBycode(String code);
	
	DrugBuy selectDrugByNameAndDrugId(String drugname,String drugid);
	
	void addBuy(Integer code, DrugBuy buy,String time) ;
	
	PageModel<DrugBuy> splitDrugBuy(Integer currPage, Integer pageSize, String keyword);
	
	//批量查询
	List<DrugBuy> showAllDrugBuy(String ids);
	//批量删除对象
	void deleteAllDrugBuy(List<DrugBuy> drugList);
	//修改
	void updateDrugBuy(DrugBuy buy,String time);
	//根据ID查询
	DrugBuy selectById(String id);	
	
	Integer select();
	
}
