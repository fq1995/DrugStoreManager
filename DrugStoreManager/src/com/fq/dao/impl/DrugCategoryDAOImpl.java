package com.fq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.DrugCategoryDAO;
import com.fq.po.DrugCategoryBean;
import com.fq.util.BaseDAO;
import com.fq.util.PageModel;
import com.fq.util.UUIDBuild;
@Repository("drugCategoryDAO")
public class DrugCategoryDAOImpl extends BaseDAO<DrugCategoryBean> implements DrugCategoryDAO{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public DrugCategoryBean selectDrugCategoryByName(String name) {
		String hql ="from DrugCategoryBean where category=?";
		List<DrugCategoryBean> categoryList = (List<DrugCategoryBean>) hibernateTemplate.find(hql, name);
		return categoryList==null||categoryList.size()<=0?null:categoryList.get(0);
	}

	@Override
	public DrugCategoryBean selectDrugCategoryByNameAndId(String name, String Id) {
		String hql = "from DrugCategoryBean where category =? and categoryId !=?";
		List<DrugCategoryBean> categoryList = (List<DrugCategoryBean>) hibernateTemplate.find(hql,name,Id);
		return categoryList==null||categoryList.size()<=0?null:categoryList.get(0);
	}

	@Override
	public void addDrugCategory(DrugCategoryBean categoryBean) {
		categoryBean.setCategoryId(UUIDBuild.getUUID());
		hibernateTemplate.save(categoryBean);
		
	}

	@Override
	public PageModel<DrugCategoryBean> splitDrugCategory(Integer currPage, Integer pageSize, String keyword) {
		String hql_count = "select count(*) from DrugCategoryBean where category like :keyword";
		String hql = "from DrugCategoryBean where category like :keyword ";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}

	@Override
	public List<DrugCategoryBean> showAllDrugCategory(String ids) {
		String[] arr = ids.split(",");
		StringBuilder sb = new StringBuilder();
		String hql ="from DrugCategoryBean where categoryId in(";
		sb.append(hql);
		for(int i = 0;i<arr.length;i++){
			if(i==arr.length-1){
				sb.append("'").append(arr[i]).append("')");
			}else{
				sb.append("'").append(arr[i]).append("'").append(",");
			}
		}
		List<DrugCategoryBean> categoryList = (List<DrugCategoryBean>) hibernateTemplate.find(sb.toString());
		return categoryList==null||categoryList.size()<=0?null:categoryList;
	}

	@Override
	public void deleteAllDrugCategory(List<DrugCategoryBean> categoryList) {
		getHibernateTemplate().deleteAll(categoryList);
		
	}

	@Override
	public void updateDrugCategory(DrugCategoryBean categoryBean) {
		getHibernateTemplate().update(categoryBean);
		
	}

	@Override
	public DrugCategoryBean selectById(String id) {
		DrugCategoryBean categoryBean = getHibernateTemplate().get(DrugCategoryBean.class, id); 
		return null==categoryBean?null:categoryBean;
	}
	

}
