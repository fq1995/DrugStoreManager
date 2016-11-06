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
import com.fq.util.ConstantUtils;
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
	public void addDrug(Integer drugCode, DrugBean drugBean, String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		drugBean.setDrugCode(++drugCode);
		drugBean.setModifyTime(date);
		drugBean.setDrugId(UUIDBuild.getUUID());
		drugBean.setStatus("1");
		if(null != drugBean.getSalepeice()){
			drugBean.setMemberprice(drugBean.getSalepeice()*ConstantUtils.discount);
		}
		hibernateTemplate.save(drugBean);

	}

	@Override
	public PageModel<DrugBean> splitDrug(Integer currPage, Integer pageSize, String keyword) {
		String hql_count = "select count(*) from DrugBean where drugName like :keyword";
		String hql = "from DrugBean where drugName like :keyword order by drugCode desc";
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
		if(null != drugBean.getSalepeice()){
			
			drugBean.setMemberprice(drugBean.getSalepeice()*ConstantUtils.discount);
		}
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

	@Override
	public PageModel<DrugBean> splitDrug(Integer currPage, Integer pageSize, String drugName, String dosageformId,
			String unitnameId, String categoryId, String manufacturer, Date modifyTime, String modifier) {
		
		String hql_count = "select count(*) from DrugBean d inner join d.drugUnitBean du inner join d.dosageformBean df inner join d.drugCategoryBean dc where d.drugUnitBean.unitnameId = du.unitnameId and d.dosageformBean.dosageformId = df.dosageformId and d.drugCategoryBean.categoryId = dc.categoryId ";
		String hql = "select d from DrugBean d inner join d.drugUnitBean du inner join d.dosageformBean df inner join d.drugCategoryBean dc where d.drugUnitBean.unitnameId = du.unitnameId and d.dosageformBean.dosageformId = df.dosageformId and d.drugCategoryBean.categoryId = dc.categoryId ";
		StringBuffer sb1 = new StringBuffer(hql_count);
		StringBuffer sb = new StringBuffer(hql);
		if(null != drugName && !"".equals(drugName)){
			sb1.append(" and d.drugName  like '%"+drugName+"%'");
			sb.append(" and d.drugName  like '%"+drugName+"%'");
//			sb.append(" and d.drugName  like '"+drugName+"'");
		}
		if(null != dosageformId && !"".equals(dosageformId)){
			sb1.append(" and df.dosageformId   ='"+dosageformId+"'");
			sb.append(" and df.dosageformId   ='"+dosageformId+"'");
		}
		if(null != unitnameId && !"".equals(unitnameId)){
			sb1.append(" and du.unitnameId  ='"+unitnameId+"'");
			sb.append(" and du.unitnameId  ='"+unitnameId+"'");
		}
		if(null != categoryId && !"".equals(categoryId)){
			sb1.append(" and dc.categoryId   ='"+categoryId+"'");
			sb.append(" and dc.categoryId  ='"+categoryId+"'");
		}
		if(null != manufacturer && !"".equals(manufacturer)){
			sb1.append(" and d.manufacturer   ='"+manufacturer+"'");
			sb.append(" and d.manufacturer   ='"+manufacturer+"'");
		}
		if(null != modifyTime && !"".equals(modifyTime)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String str = sdf.format(modifyTime);
			sb1.append(" and d.modifyTime  ='"+str+"'");
			sb.append(" and d.modifyTime   ='"+str+"'");
		}
		if(null != modifier && !"".equals(modifier)){
			sb1.append(" and d.modifier   ='"+modifier+"'");
			sb.append(" and d.modifier  ='"+modifier+"'");
		}
		System.out.println(sb1.toString());
		System.out.println(sb.toString());
		return super.split(sb.toString(), sb1.toString(), currPage, pageSize);
	}

	@Override
	public DrugCategoryBean selectCategoryById(String id) {
		return getHibernateTemplate().get(DrugCategoryBean.class, id);
		 
	}

	@Override
	public DrugUnitBean selectUnitById(String id) {
		return getHibernateTemplate().get(DrugUnitBean.class, id);
	}

	@Override
	public DosageformBean selectFormById(String id) {
		return getHibernateTemplate().get(DosageformBean.class, id);
	}

	@Override
	public List<DrugBean> show() {
		String hql ="from DrugBean";
		List<DrugBean> list = (List<DrugBean>) hibernateTemplate.find(hql);
		return list==null||list.size()<=0?null:list;
	}

	@Override
	public Integer select() {
		String hql = "select max(drugCode) from DrugBean";
		List<Integer> list = (List<Integer>) hibernateTemplate.find(hql);
		Integer drugCode = list.get(0);
		return drugCode;
	}

}
