package com.fq.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.DrugInventorDAO;
import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugBuy;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
import com.fq.po.InventoriesBean;
import com.fq.util.BaseDAO;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.fq.util.UUIDBuild;
@Repository("drugInventorDAO")
public class DrugInventorDAOImpl extends BaseDAO<InventoriesBean> implements DrugInventorDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	 
	@Override
	public InventoriesBean selectInventorByName(String name) {
		String hql ="from InventoriesBean where drugBean.drugName=? and drugBean.status=1";
		List<InventoriesBean> inventorList = (List<InventoriesBean>) hibernateTemplate.find(hql, name);
		return inventorList==null||inventorList.size()<=0?null:inventorList.get(0);
	}

	@Override
	public InventoriesBean selectInventorByDrugcode(Integer code) {
		String hql = "from InventoriesBean where drugBean.drugCode=?";
		List<InventoriesBean> list = (List<InventoriesBean>) hibernateTemplate.find(hql,code);
		return list==null||list.size()<=0?null:list.get(0);
	}

	
	@Override
	public void addInventor(Integer code,Integer drugCode,DrugBean drugBean,InventoriesBean Bean, String time){
		drugBean = Bean.getDrugBean();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date =null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		drugBean.setDrugId(UUIDBuild.getUUID());
		drugBean.setDrugCode(++drugCode);
		drugBean.setStatus("1");
		DrugBean drugBean2 = exit(drugBean);
		if(null!=drugBean2){
			drugBean = getHibernateTemplate().get(DrugBean.class, drugBean2.getDrugId());
		}
		drugBean.setStocknumber(Bean.getStocklimit());
		drugBean.setSalepeice(Bean.getDrugBean().getSalepeice());
		drugBean.setModifyTime(date);
		Bean.setStockCode((++code).toString());
		Bean.setDate(date);
		Bean.setStockId(UUIDBuild.getUUID());
		Bean.setDrugBean(drugBean);
		hibernateTemplate.merge(drugBean);
		hibernateTemplate.merge(Bean);
	}


	@Override
	public PageModel<InventoriesBean> splitInventor(Integer currPage, Integer pageSize, String keyword) {
		String hql_count = "select count(*) from InventoriesBean where drugBean.drugName like :keyword " ;
		String hql = "from InventoriesBean where drugBean.drugName like :keyword order by date desc";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}

	@Override
	public List<InventoriesBean> showAllInventor(String ids) {
		String[] arr = ids.split(",");
		StringBuilder sb = new StringBuilder();
		String hql ="from InventoriesBean where stockId in(";
		sb.append(hql);
		for(int i = 0;i<arr.length;i++){
			if(i==arr.length-1){
				sb.append("'").append(arr[i]).append("')");
			}else{
				sb.append("'").append(arr[i]).append("'").append(",");
			}
		}
		List<InventoriesBean> list = (List<InventoriesBean>) hibernateTemplate.find(sb.toString());
		return list==null||list.size()<=0?null:list;
	}

	@Override
	public void deleteAllInventor(List<InventoriesBean> list) {
		getHibernateTemplate().deleteAll(list);

	}

	@Override
	public InventoriesBean selectById(String id) {
		InventoriesBean bean = getHibernateTemplate().get(InventoriesBean.class, id); 
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
	public PageModel<InventoriesBean> splitWarn(Integer currPage, Integer pagesize, String keyword) {
		String hql_count = "select count(*) from InventoriesBean where drugBean.drugName like :keyword and drugBean.stocknumber <= stocklimit ";
		String hql = "from InventoriesBean where drugBean.drugName like :keyword and drugBean.stocknumber <= stocklimit ";
		return super.split(hql, hql_count, currPage, pagesize,keyword);
	}

	@Override
	public List<InventoriesBean> selectByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String str = sdf.format(date);
		
		String hql ="from InventoriesBean where date_format(date,'%Y-%m') = '"+str+"'";
//		String hql ="from InventoriesBean where date between '"+str+"-01"+"' and '"+str+"-31"+"'";
		List<InventoriesBean> List = (List<InventoriesBean>) hibernateTemplate.find(hql);
		return List==null||List.size()<=0?null:List;
	}

	@Override
	public Integer select() {
		String hql = "select max(stockCode) from InventoriesBean";
		List<String> list = (List<String>) hibernateTemplate.find(hql);
		Integer code = Integer.valueOf(list.get(0));
		return code;
	}

	@Override
	public List<DrugBuy> addPurchase(List<InventoriesBean> list) {
		List<DrugBuy> druglist = new ArrayList<DrugBuy>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		StringBuilder sb = new StringBuilder();
		sb.append("1000");
		sb.append(sdf1.format(date));
		DrugBuy drugbuy = new DrugBuy();
		for(InventoriesBean bean :list){
			sb.append(list.size());
			drugbuy.setDrugBean(bean.getDrugBean());
			drugbuy.setDrugbuyId(UUIDBuild.getUUID());
			try {
				drugbuy.setModifyTime(sdf.parse(sdf.format(date)));
				drugbuy.setDrugbuyCode(sb.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			druglist.add(drugbuy);
			getHibernateTemplate().save(drugbuy);
		}
		
		return druglist;
	}

	@Override
	public List<InventoriesBean> stats() {
		String hql = "from InventoriesBean i order by i.stocknumber";
		List<InventoriesBean> list = (List<InventoriesBean>) hibernateTemplate.find(hql);
		return list;
	}

	@Override
	public void updateInventor(InventoriesBean Bean, String time) {
		InventoriesBean bean = hibernateTemplate.get(InventoriesBean.class, Bean.getStockId());
		DrugBean drugBean = hibernateTemplate.get(DrugBean.class, Bean.getDrugBean().getDrugId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date =null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		drugBean.setSalepeice(Bean.getDrugBean().getSalepeice());
		BigDecimal   b   =   new   BigDecimal(drugBean.getSalepeice()*ConstantUtils.discount); 
		Double   f1   =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();  
		drugBean.setMemberprice(f1); 
		drugBean.setStatus("1");
		drugBean.setModifyTime(date);
		drugBean.setStocknumber(Bean.getDrugBean().getStocknumber());
		drugBean.setApprovalNumber(Bean.getDrugBean().getApprovalNumber());
		drugBean.setDosageformBean(Bean.getDrugBean().getDosageformBean());
		drugBean.setDrugCategoryBean(Bean.getDrugBean().getDrugCategoryBean());
		drugBean.setDrugName(Bean.getDrugBean().getDrugName());
		drugBean.setDrugUnitBean(Bean.getDrugBean().getDrugUnitBean());
		drugBean.setManufacturer(Bean.getDrugBean().getManufacturer());
		drugBean.setModifier(Bean.getDrugBean().getModifier());
		
		bean.setDate(date);
		bean.setStocklimit(Bean.getStocklimit());
		bean.setStocknumber(drugBean.getStocknumber());
		bean.setDrugBean(drugBean); 
		
		getHibernateTemplate().merge(drugBean);
		getHibernateTemplate().merge(bean);
	}
	
	public DrugBean exit(DrugBean drugBean){
		String hql ="from DrugBean as d where d.drugName =? and d.manufacturer =? and d.approvalNumber =? "
				+ "and d.drugUnitBean =? and d.dosageformBean =? and  d.drugCategoryBean =? ";
		List<DrugBean> drugBeanList = (List<DrugBean>) hibernateTemplate.find(hql,drugBean.getDrugName(),drugBean.getManufacturer(),
				drugBean.getApprovalNumber(),drugBean.getDrugUnitBean(),drugBean.getDosageformBean(),
				drugBean.getDrugCategoryBean());
		return drugBeanList==null||drugBeanList.size()<=0?null:drugBeanList.get(0);
	}

	@Override
	public Double getSaleByName(String drugName) {
		DrugBean drug = new DrugBean();
		List<DrugBean> drugList = null;
		String hql ="from DrugBean where drugName=? and status=1";
		if(null!=drugName && !"".equals(drugName)){
			drugList = (List<DrugBean>) hibernateTemplate.find(hql, drugName);
		}
		if(drugList==null||drugList.size()<=0){
			return  null;
		}else{
			drug = drugList.get(0);
		}
		return drug.getSalepeice();
	}
	

}
