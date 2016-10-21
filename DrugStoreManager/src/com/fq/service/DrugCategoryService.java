package com.fq.service;

import java.util.List;

import com.fq.po.DrugCategoryBean;
import com.fq.util.PageModel;

public interface DrugCategoryService {
	
		//根据名称查询
		DrugCategoryBean selectDrugCategoryByName(String name);
		//根据id和名称查询
		DrugCategoryBean selectDrugCategoryByNameAndId(String name,String Id);
		//添加
		void addDrugCategory(DrugCategoryBean CategoryBean);
		//分页
		PageModel<DrugCategoryBean> splitDrugCategory(Integer currPage, Integer pageSize, String keyword);
		//批量查询
		List<DrugCategoryBean> showAllDrugCategory(String ids);
		//批量删除对象
		void deleteAllDrugCategory(List<DrugCategoryBean> categoryList);
		//修改
		void updateDrugCategory(DrugCategoryBean CategoryBean);
		//根据ID查询
		DrugCategoryBean selectById(String id);
}
