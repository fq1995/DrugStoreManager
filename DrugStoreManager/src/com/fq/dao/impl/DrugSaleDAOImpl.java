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

import com.fq.dao.DrugSaleDAO;
import com.fq.po.DrugBean;
import com.fq.po.DrugSalesBean;
import com.fq.po.MemberBean;
import com.fq.po.UserBean;
import com.fq.util.BaseDAO;
import com.fq.util.PageModel;
import com.fq.util.UUIDBuild;
@Repository("drugSaleDAO")
public class DrugSaleDAOImpl extends BaseDAO<DrugSalesBean> implements DrugSaleDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Resource
	SessionFactory sessionFactory;
	
	@Override
	public DrugSalesBean selectSaleByName(String name) {
		String hql ="from DrugSalesBean where drugBean.drugName=? and drugBean.status=1";
		List<DrugSalesBean> list = (List<DrugSalesBean>) hibernateTemplate.find(hql, name);
		return list==null||list.size()<=0?null:list.get(0);
	}

	@Override
	public DrugSalesBean selectSaleByDrugcode(Integer code) {
		String hql = "from DrugSalesBean where drugBean.drugCode=?";
		List<DrugSalesBean> list = (List<DrugSalesBean>) hibernateTemplate.find(hql,code);
		return list==null||list.size()<=0?null:list.get(0);
	}

	
	@Override
	public void addSale(Integer saleCode, UserBean userBean, DrugBean drugBean, DrugSalesBean bean, String time) throws Exception {
//		drugBean = bean.getDrugBean();
//		userBean = bean.getUserBean();
		MemberBean mbean = bean.getMemberBean();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(time);
		bean.setSalesDate(date);
		bean.setSalesCode((++saleCode).toString());
		bean.setSalesId(UUIDBuild.getUUID());
		Session session=sessionFactory.getCurrentSession();
		session.clear();
		session.merge(bean);
	}
	

	@Override
	public PageModel<DrugSalesBean> splitSale(Integer currPage, Integer pageSize, String keyword) {
		String hql_count = "select count(*) from DrugSalesBean where drugBean.drugName like :keyword";
		String hql = "from DrugSalesBean where drugBean.drugName like :keyword ";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}

	@Override
	public List<DrugSalesBean> showAllSale(String ids) {
		String[] arr = ids.split(",");
		StringBuilder sb = new StringBuilder();
		String hql ="from DrugSalesBean where salesId in(";
		sb.append(hql);
		for(int i = 0;i<arr.length;i++){
			if(i==arr.length-1){
				sb.append("'").append(arr[i]).append("')");
			}else{
				sb.append("'").append(arr[i]).append("'").append(",");
			}
		}
		List<DrugSalesBean> list = (List<DrugSalesBean>) hibernateTemplate.find(sb.toString());
		return list==null||list.size()<=0?null:list;
	}

	@Override
	public void deleteAllSale(List<DrugSalesBean> list) {
		getHibernateTemplate().deleteAll(list);
		
	}

	@Override
	public void updateSale(DrugSalesBean bean, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			System.out.println("时间转换错误");
			e.printStackTrace();
		}
		bean.setSalesDate(date);
		
		Session session=sessionFactory.getCurrentSession();
		session.clear();
		DrugSalesBean dsBean = (DrugSalesBean) session.get(DrugSalesBean.class, bean.getSalesId());
		dsBean = bean; 
		dsBean.setDrugBean(bean.getDrugBean());
		getHibernateTemplate().saveOrUpdate(dsBean);
		session.flush();
		/*
		dsBean.setMemberBean(bean.getMemberBean()); 
		dsBean.setMemberprice(bean.getMemberprice());
		dsBean.setSalepeice(bean.getSalepeice());
		dsBean.setUserBean(bean.getUserBean());*/
		/*getHibernateTemplate().merge(dsBean);*/
		
	}
	
	
	@Override
	public DrugSalesBean selectById(String id) {
		DrugSalesBean bean = getHibernateTemplate().get(DrugSalesBean.class, id); 
		return null==bean?null:bean;
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
	public DrugBean selectSaleByDrugId(String id) {
		String hql = "from DrugBean where drugId =?";
		List<DrugBean> list = (List<DrugBean>) hibernateTemplate.find(hql,id);
		return list==null||list.size()<=0?null:list.get(0);
	}

	@Override
	public List<UserBean> selectUser() {
		String hql = "from UserBean";
		List<UserBean> list = (List<UserBean>) hibernateTemplate.find(hql);
		return list==null||list.size()<=0?null:list;
	}

	@Override
	public List<DrugSalesBean> show(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String str = sdf.format(date);
		String hql = "from DrugSalesBean where date_format(salesDate,'%Y-%m') = '"+str+"'";
		List<DrugSalesBean> list = (List<DrugSalesBean>) hibernateTemplate.find(hql);
		return list==null||list.size()<=0?null:list;
	}

	@Override
	public MemberBean selectSaleByTel(String suppliertel) {
		String hql ="from MemberBean where suppliertel =?";
		List<MemberBean> list = (List<MemberBean>) hibernateTemplate.find(hql,suppliertel);
		return list==null||list.size()<=0?null:list.get(0);
	}

	@Override
	public Integer selectCode() {
		String hql = "select max(salesCode) from DrugSalesBean";
		List<String> list = (List<String>) hibernateTemplate.find(hql);
		Integer saleCode = Integer.valueOf(list.get(0));
		return saleCode;
	}

	@Override
	public List<DrugSalesBean> stats() {
		String hql = "from DrugSalesBean order by salesVolume desc";
		List<DrugSalesBean> list = (List<DrugSalesBean>) hibernateTemplate.find(hql);
		return list;
	}

	@Override
	public List<DrugBean> comboGrid() {
		String hql = "from DrugBean order by drugName";
		List<DrugBean> list = (List<DrugBean>) hibernateTemplate.find(hql);
		return list;
	}

	
	

}
