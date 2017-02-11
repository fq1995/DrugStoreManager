package com.fq.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.DrugBuyDAO;
import com.fq.po.DrugBean;
import com.fq.po.DrugBuy;
import com.fq.util.BaseDAO;
import com.fq.util.PageModel;
import com.fq.util.UUIDBuild;
@Repository("drugBuyDAO")
public class DrugBuyDAOImpl extends BaseDAO<DrugBuy> implements DrugBuyDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<DrugBuy> show() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DrugBuy selectBuyBycode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DrugBuy selectDrugByNameAndDrugId(String drugname, String drugid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBuy(Integer code, DrugBuy buy, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DrugBean drug = buy.getDrugBean();
		drug.setDrugCode(++code);
		buy.setDrugBean(drug);
		buy.setModifyTime(date);
		buy.setDrugbuyId(UUIDBuild.getUUID());
		date = new Date();
		buy.setDrugbuyCode("1000"+sdf1.format(date)+(int)(1+Math.random()*100));
		
		hibernateTemplate.save(buy);
		
	}

	@Override
	public PageModel<DrugBuy> splitDrugBuy(Integer currPage, Integer pageSize, String keyword) {
		String hql_count = "select count(*) from DrugBuy where drugBean.drugName like :keyword";
		String hql = "from DrugBuy where drugBean.drugName like :keyword order by modifyTime desc";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}

	@Override
	public List<DrugBuy> showAllDrugBuy(String ids) {
		String[] arr = ids.split(",");
		StringBuilder sb = new StringBuilder();
		String hql ="from DrugBuy where drugbuyId in(";
		sb.append(hql);
		for(int i = 0;i<arr.length;i++){
			if(i==arr.length-1){
				sb.append("'").append(arr[i]).append("')");
			}else{
				sb.append("'").append(arr[i]).append("'").append(",");
			}
		}
		List<DrugBuy> drugList = (List<DrugBuy>) hibernateTemplate.find(sb.toString());
		return drugList==null||drugList.size()<=0?null:drugList;
	}

	@Override
	public void deleteAllDrugBuy(List<DrugBuy> drugList) {
		getHibernateTemplate().deleteAll(drugList);
		
	}

	@Override
	public void updateDrugBuy(DrugBuy buy, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			System.out.println("时间转换错误");
			e.printStackTrace();
		}
		buy.setModifyTime(date);
		getHibernateTemplate().update(buy);
	}

	@Override
	public DrugBuy selectById(String id) {
		DrugBuy buy = hibernateTemplate.get(DrugBuy.class, id);
		return buy==null?null:buy;
	}

	@Override
	public Integer select() {
		String hql = "select max(drugCode) from DrugBean";
		List<Integer> list = (List<Integer>) hibernateTemplate.find(hql);
		Integer drugCode = list.get(0);
		return drugCode;
	}

}
