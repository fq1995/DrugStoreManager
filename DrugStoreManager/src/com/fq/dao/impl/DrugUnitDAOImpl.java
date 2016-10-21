package com.fq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.DrugUnitDAO;
import com.fq.po.DrugUnitBean;
import com.fq.util.BaseDAO;
import com.fq.util.PageModel;
import com.fq.util.UUIDBuild;
@Repository("drugUnitDAO")
public class DrugUnitDAOImpl extends BaseDAO<DrugUnitBean> implements DrugUnitDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public DrugUnitBean selectDrugUnitByName(String unitname) {
		String hql ="from DrugUnitBean where unitname=?";
		List<DrugUnitBean> drugUnitList = (List<DrugUnitBean>) hibernateTemplate.find(hql, unitname);
		return drugUnitList==null||drugUnitList.size()<=0?null:drugUnitList.get(0);
	}

	@Override
	public DrugUnitBean selectDrugUnitByNameAndDrugUnitId(String drugUnitname, String drugUnitId) {
		String hql = "from DrugUnitBean where unitname =? and unitnameId !=?";
		List<DrugUnitBean> drugUnitList = (List<DrugUnitBean>) hibernateTemplate.find(hql, drugUnitname,drugUnitId);
		return drugUnitList==null||drugUnitList.size()<=0?null:drugUnitList.get(0);
	}

	@Override
	public void addDrugUnit(DrugUnitBean drugUnitBean) {
		drugUnitBean.setUnitnameId(UUIDBuild.getUUID());
		hibernateTemplate.save(drugUnitBean);
	}

	@Override
	public PageModel<DrugUnitBean> splitDrugUnit(Integer currPage, Integer pageSize, String keyword) {
		String hql_count = "select count(*) from DrugUnitBean where unitname like :keyword";
		String hql = "from DrugUnitBean where unitname like :keyword ";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}

	@Override
	public List<DrugUnitBean> showAllDrugUnit(String ids) {
		String[] arr = ids.split(",");
		StringBuilder sb = new StringBuilder();
		String hql ="from DrugUnitBean where unitnameId in(";
		sb.append(hql);
		for(int i = 0;i<arr.length;i++){
			if(i==arr.length-1){
				sb.append("'").append(arr[i]).append("')");
			}else{
				sb.append("'").append(arr[i]).append("'").append(",");
			}
		}
		List<DrugUnitBean> drugUnitList = (List<DrugUnitBean>) hibernateTemplate.find(sb.toString());
		return drugUnitList==null||drugUnitList.size()<=0?null:drugUnitList;
	}

	@Override
	public void deleteAllDrugUnit(List<DrugUnitBean> drugUnitList) {
		getHibernateTemplate().deleteAll(drugUnitList);

	}


	@Override
	public void updateDrugUnit(DrugUnitBean drugUnitBean) {
		getHibernateTemplate().update(drugUnitBean);

	}

	@Override
	public DrugUnitBean selectById(String id) {
		DrugUnitBean drugUnitBean = getHibernateTemplate().get(DrugUnitBean.class, id); 
		return null==drugUnitBean?null:drugUnitBean;
	}

}
