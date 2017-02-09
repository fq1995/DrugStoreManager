package com.fq.service;

import java.util.Date;
import java.util.List;

import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
import com.fq.po.InventoriesBean;
import com.fq.util.DrugBuy;
import com.fq.util.PageModel;

public interface DrugInventorService {

	InventoriesBean selectInventorByName(String name);

	InventoriesBean selectInventorByDrugcode(Integer code);

	void addInventor(Integer code, Integer drugCode, DrugBean drugBean, InventoriesBean bean, String time);

	PageModel<InventoriesBean> splitInventor(Integer currPage, Integer pageSize, String keyword);

	// 批量查询
	List<InventoriesBean> showAllInventor(String ids);

	// 批量删除对象
	void deleteAllInventor(List<InventoriesBean> list);

	// 修改
	void updateInventor(InventoriesBean bean);

	// 根据ID查询
	InventoriesBean selectById(String id);

	// 查询类别
	List<DrugCategoryBean> selectCategory();

	// 查询单位
	List<DrugUnitBean> selectUnit();

	// 查询剂型
	List<DosageformBean> selectForm();

	// 库存预警
	PageModel<InventoriesBean> splitWarn(Integer currPage, Integer pagesize, String keyword);

	// 根据时间查询
	List<InventoriesBean> selectByDate(Date date);

	// 查询编号
	Integer select();
	
	// 添加至采购清单
	List<DrugBuy> addPurchase(List<InventoriesBean> list);
	
	 
}
