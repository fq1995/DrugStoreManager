package com.fq.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.fq.util.UUIDBuild;
@Repository("drugSaleDAO")
public class DrugSaleDAOImpl extends BaseDAO<DrugSalesBean> implements DrugSaleDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
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
	public void addSale(String tel, DrugSalesBean bean) throws Exception {
		DrugBean drug = hibernateTemplate.get(DrugBean.class, bean.getDrugBean().getDrugId());
		drug.setSalepeice(bean.getDrugBean().getSalepeice());
		BigDecimal   b   =   new   BigDecimal(drug.getSalepeice()*ConstantUtils.discount); 
		Double   f1   =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();  
		drug.setMemberprice(f1); 
		drug.setStocknumber(drug.getStocknumber()-bean.getSalesVolume());
		if(null == drug.getNewName() || "".equals(drug.getNewName())){
			drug.setNewName("zanwu.png");
		}
		UserBean user = hibernateTemplate.get(UserBean.class, bean.getUserBean().getUserId());
		bean.setDrugBean(drug);
		bean.setUserBean(user);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String time = sdf.format(date);
		date = sdf.parse(time);
		bean.setSalesDate(date);
		bean.setSalesId(UUIDBuild.getUUID());
		bean.setSalepeice(bean.getDrugBean().getSalepeice());
		bean.setMemberprice(f1);
		updateMember(tel,bean); 
		hibernateTemplate.merge(bean);
	}
	

	@Override
	public PageModel<DrugSalesBean> splitSale(Integer currPage, Integer pageSize, String keyword) {
		String hql_count = "select count(*) from DrugSalesBean where drugBean.drugName like :keyword";
		String hql = "from DrugSalesBean where drugBean.drugName like :keyword order by salesDate desc";
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
	public void updateSale(DrugSalesBean bean2, String time) {
		DrugSalesBean bean = hibernateTemplate.get(DrugSalesBean.class, bean2.getSalesId());
		DrugBean drugbean = hibernateTemplate.get(DrugBean.class, bean2.getDrugBean().getDrugId());
		UserBean userBean = hibernateTemplate.get(UserBean.class, bean2.getUserBean().getUserId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			System.out.println("时间转换错误");
			e.printStackTrace();
		}
		drugbean.setSalepeice(bean2.getDrugBean().getSalepeice());
		BigDecimal   b   =   new   BigDecimal(drugbean.getSalepeice()*ConstantUtils.discount); 
		Double   f1   =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();  
		drugbean.setMemberprice(f1);
		drugbean.setStocknumber(drugbean.getStocknumber()-bean.getSalesVolume());
		if(null == drugbean.getNewName() || "".equals(drugbean.getNewName())){
			drugbean.setNewName("zanwu.png");
		}
		bean.setDrugBean(drugbean);
		bean.setSalesDate(date);
		bean.setMemberprice(f1);
		bean.setSalepeice(bean2.getDrugBean().getSalepeice());
		bean.setSalesVolume(bean2.getSalesVolume());
		bean.setTotalamount(bean2.getTotalamount());
		bean.setUserBean(userBean);
		getHibernateTemplate().merge(bean);
		
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
		String hql = "from DrugSalesBean GROUP BY drugBean.drugId order by salesVolume desc";
		List<DrugSalesBean> list = (List<DrugSalesBean>) hibernateTemplate.find(hql);
		return list;
	}

	@Override
	public List<DrugBean> comboGrid() {
		String hql = "from DrugBean group by drugName order by drugName";
		List<DrugBean> list = (List<DrugBean>) hibernateTemplate.find(hql);
		return list;
	}

	@Override
	public void updateMember(String tel,DrugSalesBean bean) {
		//设置会员积分为成交价格取整
				if(null != tel && !"".equals(tel) && null != bean){
					MemberBean member = selectSaleByTel(tel); 
					Double d = Math.floor(bean.getDrugBean().getSalepeice()) ;
					if(null!=member){
						member.setIntegration(member.getIntegration()+Integer.parseInt(new java.text.DecimalFormat("0").format(d))*(bean.getSalesVolume()));
						hibernateTemplate.update(member);
					}
				}
	}

	
	

}
