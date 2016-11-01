package com.fq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.SupplierDAO;
import com.fq.po.SupplierBean;
import com.fq.po.UserBean;
import com.fq.util.BaseDAO;
import com.fq.util.PageModel;
import com.fq.util.UUIDBuild;
@Repository("supplierDAO")
public class SupplierDAOImpl extends BaseDAO<SupplierBean> implements SupplierDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public SupplierBean selectSupByName(String supname) {
		String hql ="from SupplierBean where supplier=? and status=1";
		List<SupplierBean> suplist = (List<SupplierBean>) hibernateTemplate.find(hql, supname);
		return suplist==null||suplist.size()<=0?null:suplist.get(0);
	}

	@Override
	public SupplierBean selectSupByNameAndSupId(String supname, String supid) {
		String hql ="from SupplierBean where supplier =? and supplierId !=? and status=1";
		List<SupplierBean> suplist = (List<SupplierBean>) hibernateTemplate.find(hql, supname,supid);
		return suplist==null||suplist.size()<=0?null:suplist.get(0);
	}

	@Override
	public SupplierBean selectSupBySupcode(String supcode) {
		String hql ="from SupplierBean where supplierCode=?";
		List<SupplierBean> suplist = (List<SupplierBean>) hibernateTemplate.find(hql, supcode);
		return suplist==null||suplist.size()<=0?null:suplist.get(0);
	}

	@Override
	public PageModel<SupplierBean> splitSup(Integer currPage, Integer pageSize, String keyword) {
		String hql_count = "select count(*) from SupplierBean where supplier like :keyword";
		String hql = "from SupplierBean where supplier like :keyword";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}

	@Override
	public void addSup(SupplierBean supBean) {
		supBean.setSupplierId(UUIDBuild.getUUID());
		hibernateTemplate.save(supBean);

	}

	@Override
	public List<SupplierBean> showAllSup(String ids) {
		String[] arr = ids.split(",");
		StringBuilder sb = new StringBuilder();
		String hql ="from SupplierBean where supplierId in(";
		sb.append(hql);
		for(int i = 0;i<arr.length;i++){
			if(i==arr.length-1){
				sb.append("'").append(arr[i]).append("')");
			}else{
				sb.append("'").append(arr[i]).append("'").append(",");
			}
		}
		List<SupplierBean> supList = (List<SupplierBean>) getHibernateTemplate().find(sb.toString());
		if(supList != null && supList.size() > 0) {
			return supList;
		}
	return null;
	}

	@Override
	public void deleteAllSup(List<SupplierBean> supList) {
		getHibernateTemplate().deleteAll(supList);

	}

	@Override
	public void updateSup(SupplierBean supBean) {
		getHibernateTemplate().update(supBean);

	}

	@Override
	public SupplierBean selectById(String id) {
		SupplierBean supBean = getHibernateTemplate().get(SupplierBean.class, id);
		return null==supBean?null:supBean;
	}

	@Override
	public List<SupplierBean> show() {
		String hql ="from SupplierBean";
		List<SupplierBean> list = (List<SupplierBean>) hibernateTemplate.find(hql);
		return list==null||list.size()<=0?null:list;
	}

}
