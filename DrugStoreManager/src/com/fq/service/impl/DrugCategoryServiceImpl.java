package com.fq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.DrugCategoryDAO;
import com.fq.po.DrugCategoryBean;
import com.fq.service.DrugCategoryService;
import com.fq.util.PageModel;
@Service("drugCategoryService")
public class DrugCategoryServiceImpl implements DrugCategoryService {
	
	@Autowired
	private DrugCategoryDAO drugCategoryDao;
	
	@Override
	public DrugCategoryBean selectDrugCategoryByName(String name) {
		return drugCategoryDao.selectDrugCategoryByName(name);
	}

	@Override
	public DrugCategoryBean selectDrugCategoryByNameAndId(String name, String Id) {
		return drugCategoryDao.selectDrugCategoryByNameAndId(name, Id);
	}

	@Override
	public void addDrugCategory(DrugCategoryBean CategoryBean) {
		drugCategoryDao.addDrugCategory(CategoryBean);

	}

	@Override
	public PageModel<DrugCategoryBean> splitDrugCategory(Integer currPage, Integer pageSize, String keyword) {
		return drugCategoryDao.splitDrugCategory(currPage, pageSize, keyword);
	}

	@Override
	public List<DrugCategoryBean> showAllDrugCategory(String ids) {
		return drugCategoryDao.showAllDrugCategory(ids);
	}

	@Override
	public void deleteAllDrugCategory(List<DrugCategoryBean> categoryList) {
		drugCategoryDao.deleteAllDrugCategory(categoryList);

	}

	@Override
	public void updateDrugCategory(DrugCategoryBean CategoryBean) {
		drugCategoryDao.updateDrugCategory(CategoryBean);

	}

	@Override
	public DrugCategoryBean selectById(String id) {
		return drugCategoryDao.selectById(id);
	}

}
