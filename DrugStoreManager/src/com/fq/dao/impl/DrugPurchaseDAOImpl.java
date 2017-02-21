package com.fq.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.DrugPurchaseDAO;
import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugPurchaseBean;
import com.fq.po.DrugUnitBean;
import com.fq.po.MemberBean;
import com.fq.po.SupplierBean;
import com.fq.po.UserBean;
import com.fq.util.BaseDAO;
import com.fq.util.PageModel;
import com.fq.util.UUIDBuild;
@Repository("drugPurchaseDAO")
public class DrugPurchaseDAOImpl extends BaseDAO<DrugPurchaseBean> implements DrugPurchaseDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Resource
	SessionFactory sessionFactory;
	
	
	@Override
	public DrugPurchaseBean selectPseByName(String name) {
		String hql ="from DrugPurchaseBean where drugBean.drugName=? and drugBean.status=1";
		List<DrugPurchaseBean> list = (List<DrugPurchaseBean>) hibernateTemplate.find(hql, name);
		return list==null||list.size()<=0?null:list.get(0);
	}

	@Override
	public DrugPurchaseBean selectPseByDrugcode(Integer code) {
		String hql = "from DrugPurchaseBean where drugBean.drugCode=?";
		List<DrugPurchaseBean> list = (List<DrugPurchaseBean>) hibernateTemplate.find(hql,code);
		return list==null||list.size()<=0?null:list.get(0);
	}

	@Override
	public DrugPurchaseBean selectPseByDrugId(String id) {
		String hql = "from DrugPurchaseBean where drugBean.drugId=?";
		List<DrugPurchaseBean> list = (List<DrugPurchaseBean>) hibernateTemplate.find(hql,id);
		return list==null||list.size()<=0?null:list.get(0);
	}

	@Override
	public void addPse(Integer drugCode, Integer pseCode, SupplierBean supBean,DosageformBean dfBean, DrugCategoryBean dcBean, DrugUnitBean duBean, DrugBean drugBean,
			DrugPurchaseBean drugPseBean, String time) {
		drugBean = drugPseBean.getDrugBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		drugPseBean.setPurchaseCode((++pseCode).toString());
		drugPseBean.setPurchasedate(date);
		Session session=sessionFactory.getCurrentSession();
		session.clear();
		session.load(dfBean, dfBean.getDosageformId());
		session.load(dcBean, dcBean.getCategoryId());
		session.load(duBean, duBean.getUnitnameId());
		session.load(supBean, supBean.getSupplierId());
		drugBean.setDrugId(UUIDBuild.getUUID());
		drugBean.setModifyTime(date);
		drugBean.setStatus("1");
		drugBean.setDrugCode(++drugCode);
		drugPseBean.setPurchaseId(UUIDBuild.getUUID());
		hibernateTemplate.merge(drugPseBean);
	}

	@Override
	public PageModel<DrugPurchaseBean> splitPse(Integer currPage, Integer pageSize, String keyword) {
		String hql_count = "select count(*) from DrugPurchaseBean where drugBean.drugName like :keyword  order by purchaseCode desc";
		String hql = "from DrugPurchaseBean where drugBean.drugName like :keyword  order by purchaseCode desc";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}

	@Override
	public List<DrugPurchaseBean> showAllPse(String ids) {
		String[] arr = ids.split(",");
		StringBuilder sb = new StringBuilder();
		String hql ="from DrugPurchaseBean where purchaseId in(";
		sb.append(hql);
		for(int i = 0;i<arr.length;i++){
			if(i==arr.length-1){
				sb.append("'").append(arr[i]).append("')");
			}else{
				sb.append("'").append(arr[i]).append("'").append(",");
			}
		}
		List<DrugPurchaseBean> list = (List<DrugPurchaseBean>) hibernateTemplate.find(sb.toString());
		return list==null||list.size()<=0?null:list;
	}

	@Override
	public void deleteAllPse(List<DrugPurchaseBean> list) {
		getHibernateTemplate().deleteAll(list);

	}

	@Override
	public void updatePse(DrugPurchaseBean bean) {
		getHibernateTemplate().merge(bean);

	}

	@Override
	public DrugPurchaseBean selectById(String id) {
		DrugPurchaseBean bean = getHibernateTemplate().get(DrugPurchaseBean.class, id); 
		return null==bean?null:bean;
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
	public List<DrugBean> selectDrug() {
		String hql = "from DrugBean";
		List<DrugBean> drugList = (List<DrugBean>) hibernateTemplate.find(hql);
		return drugList==null||drugList.size()<=0?null:drugList;
	}

	@Override
	public List<MemberBean> selectMember() {
		String hql = "from MemberBean";
		List<MemberBean> memberList = (List<MemberBean>) hibernateTemplate.find(hql);
		return memberList==null||memberList.size()<=0?null:memberList;
	}

	@Override
	public List<UserBean> selectUser() {
		String hql = "from UserBean";
		List<UserBean> list = (List<UserBean>) hibernateTemplate.find(hql);
		return list==null||list.size()<=0?null:list;
	}

	@Override
	public List<SupplierBean> selectSupplier() {
		String hql = "from SupplierBean";
		List<SupplierBean> list = (List<SupplierBean>) hibernateTemplate.find(hql);
		return list==null||list.size()<=0?null:list;
	}

	@Override
	public PageModel<DrugPurchaseBean> splitDateWarn(Integer currPage, Integer pagesize, String keyword) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		String hql_count = "select count(*) from DrugPurchaseBean where drugBean.drugName like :keyword and validityDate < '"+str+"'";
		String hql = "from DrugPurchaseBean where drugBean.drugName like :keyword and datediff(validityDate,str_to_date('"+str+"','%Y-%m-%d')) between 0 and 90 ";
		return super.split(hql, hql_count, currPage, pagesize,keyword);
	}

	@Override
	public List<DrugPurchaseBean> show(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String str = sdf.format(date);
		String hql ="from DrugPurchaseBean where date_format(purchasedate,'%Y-%m') = '"+str+"'";
		List<DrugPurchaseBean> list = (List<DrugPurchaseBean>) hibernateTemplate.find(hql);
		return list==null||list.size()<=0?null:list;
	}

	@Override
	public Integer selectCode() {
		String hql = "select max(purchaseCode) from DrugPurchaseBean";
		List<String> list = (List<String>) hibernateTemplate.find(hql);
		Integer pseCode = Integer.valueOf(list.get(0));
		return pseCode; 
	}

	@Override
	public PageModel<DrugPurchaseBean> splitOverDate(Integer currPage, Integer pagesize, String keyword) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		String hql_count = "select count(*) from DrugPurchaseBean where drugBean.drugName like :keyword and validityDate < '"+str+"'";
		String hql = "from DrugPurchaseBean where drugBean.drugName like :keyword and validityDate < '"+str+"'";
		return super.split(hql, hql_count, currPage, pagesize,keyword);
	}

	@Override
	public List<DrugPurchaseBean> stats() {
		String hql ="from DrugPurchaseBean order by amount desc";
		List<DrugPurchaseBean> list = (List<DrugPurchaseBean>) hibernateTemplate.find(hql);
		return list;
	}


}
