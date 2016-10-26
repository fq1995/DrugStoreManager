package com.fq.service;

import java.util.List;

import com.fq.po.SupplierBean;
import com.fq.util.PageModel;

public interface SupplierService {
	//根据供货商名查询
	SupplierBean selectSupByName(String supname);
	//根据供货商名和id查询
	SupplierBean selectSupByNameAndSupId(String supname,String supid);
	//根据供货商编码查询
	SupplierBean selectSupBySupcode(String supcode);
	//分页
	PageModel<SupplierBean> splitSup(Integer currPage, Integer pageSize, String keyword);
	//添加供货商
	void addSup(SupplierBean supBean);
	//批量查询
	List<SupplierBean> showAllSup(String ids);
	//批量删除
	void deleteAllSup(List<SupplierBean> supList);
	//修改供货商
	void updateSup(SupplierBean supBean);
	//根据id查询
	SupplierBean selectById(String id);
}
