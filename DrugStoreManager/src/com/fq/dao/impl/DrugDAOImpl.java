package com.fq.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.DrugDAO;
import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
import com.fq.util.BaseDAO;
import com.fq.util.PageModel;
import com.fq.util.UUIDBuild;
@Repository("drugDAO")
public class DrugDAOImpl extends BaseDAO<DrugBean> implements DrugDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public DrugBean selectDrugByName(String drugname) {
		String hql ="from DrugBean where drugName=? and status=1";
		List<DrugBean> drugList = (List<DrugBean>) hibernateTemplate.find(hql, drugname);
		return drugList==null||drugList.size()<=0?null:drugList.get(0);
	}

	@Override
	public DrugBean selectDrugByDrugcode(Integer drugCode) {
		String hql = "from DrugBean where drugCode=?";
		List<DrugBean> drugList = (List<DrugBean>) hibernateTemplate.find(hql, drugCode);
		return drugList==null||drugList.size()<=0?null:drugList.get(0);
	}

	@Override
	public DrugBean selectDrugByNameAndDrugId(String drugname, String drugid) {
		String hql = "from DrugBean where drugName =? and drugId !=? and status=1";
		List<DrugBean> drugList = (List<DrugBean>) hibernateTemplate.find(hql, drugname,drugid);
		return drugList==null||drugList.size()<=0?null:drugList.get(0);
	}

	@Override
	public void addDrug(DrugBean drugBean, String time) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(time);
		drugBean.setModifyTime(date);
		drugBean.setDrugId(UUIDBuild.getUUID());
		drugBean.setStatus("1");
		hibernateTemplate.save(drugBean);

	}

	@Override
	public void addDrug(DrugBean drugBean) {
		hibernateTemplate.save(drugBean);

	}

	@Override
	public PageModel<DrugBean> splitDrug(Integer currPage, Integer pageSize, String keyword) {
		String hql_count = "select count(*) from DrugBean where drugName like :keyword";
		String hql = "from DrugBean where drugName like :keyword order by modifyTime desc";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}

	@Override
	public List<DrugBean> showAllDrug(String ids) {
		String[] arr = ids.split(",");
		StringBuilder sb = new StringBuilder();
		String hql ="from DrugBean where drugId in(";
		sb.append(hql);
		for(int i = 0;i<arr.length;i++){
			if(i==arr.length-1){
				sb.append("'").append(arr[i]).append("')");
			}else{
				sb.append("'").append(arr[i]).append("'").append(",");
			}
		}
		List<DrugBean> drugList = (List<DrugBean>) hibernateTemplate.find(sb.toString());
		return drugList==null||drugList.size()<=0?null:drugList;
	}

	@Override
	public void deleteAllDrug(List<DrugBean> drugList) {
		getHibernateTemplate().deleteAll(drugList);

	}

	@Override
	public void updateDrug(DrugBean drugBean, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			System.out.println("时间转换错误");
			e.printStackTrace();
		}
		drugBean.setModifyTime(date);
		drugBean.setStatus("1");
		getHibernateTemplate().update(drugBean);
	}

	@Override
	public DrugBean selectById(String id) {
		DrugBean drugBean = getHibernateTemplate().get(DrugBean.class, id); 
		return null==drugBean?null:drugBean;
	}

	@Override
	public void updateDrug(DrugBean drugBean) {
		getHibernateTemplate().update(drugBean);
		
	}

	@Override
	public List<DrugCategoryBean> selectCategory() {
		String hql ="from DrugCategoryBean";
		List<DrugCategoryBean> drugCategoryList = (List<DrugCategoryBean>) hibernateTemplate.find(hql);
		return drugCategoryList==null||drugCategoryList.size()<=0?null:drugCategoryList;
	}

	@Override
	public List<DrugUnitBean> selectUnit() {
		String hql = "from DrugUnitBean";
		List<DrugUnitBean> drugUnitList = (List<DrugUnitBean>) hibernateTemplate.find(hql);
		return drugUnitList==null||drugUnitList.size()<=0?null:drugUnitList;
	}

	@Override
	public List<DosageformBean> selectForm() {
		String hql = "from DosageformBean";
		List<DosageformBean> dosageformList = (List<DosageformBean>) hibernateTemplate.find(hql);
		return dosageformList==null||dosageformList.size()<=0?null:dosageformList;
	}

	
}
