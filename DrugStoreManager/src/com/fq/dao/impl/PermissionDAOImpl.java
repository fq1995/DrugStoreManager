package com.fq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.PermissionDAO;
import com.fq.po.PermissionBean;
import com.fq.util.BaseDAO;
import com.fq.util.PageModel;
@Repository("permissionDAO")
public class PermissionDAOImpl extends BaseDAO<PermissionBean> implements PermissionDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	@Override
	public PermissionBean selectPerByName(String pername) {
		String hql ="from PermissionBean where pername=?";
		List<PermissionBean> perlist = (List<PermissionBean>) hibernateTemplate.find(hql, pername);
		return perlist==null||perlist.size()<=0?null:perlist.get(0);
	}

	@Override
	public void addPer(PermissionBean perBean) {
		hibernateTemplate.save(perBean);

	}

	@Override
	public PageModel<PermissionBean> splitPer(Integer currPage, Integer pageSize) {
		String hql_count = "select count(*) from PermissionBean ";
		String hql = "from PermissionBean order by perid desc";
		return super.split(hql, hql_count, currPage, pageSize);
	}

	@Override
	public List<PermissionBean> showAllPer(String ids) {
		String[] arr = ids.split(",");
		if(arr!=null){
			Integer[] arrId = new Integer[arr.length];
			for (Integer i = 0; i < arr.length; i++) {
				arrId[i] = Integer.parseInt(arr[i]);
			}
			String hql ="from PermissionBean where perid in(";
			StringBuilder sb = new StringBuilder(hql);
			for(Integer i = 0; i < arrId.length; i++) {
				if(i != arrId.length - 1){
					sb.append(arrId[i] + ",");
				}else{
					sb.append(arrId[i] + ")");
				}
			}
			List<PermissionBean> perList = (List<PermissionBean>) getHibernateTemplate().find(sb.toString());
			if(perList != null && perList.size() > 0) {
				return perList;
			}
		}
		return null;
	}

	@Override
	public void deleteAllPer(List<PermissionBean> perList) {
		getHibernateTemplate().deleteAll(perList);

	}

	@Override
	public void updatePer(PermissionBean perBean) {
		getHibernateTemplate().update(perBean);

	}

	@Override
	public PermissionBean selectById(String id) {
		PermissionBean perBean = getHibernateTemplate().get(PermissionBean.class, Integer.valueOf(id));
		return null==perBean?null:perBean;
	}

	@Override
	public PermissionBean selectAll() {
		String hql= "from PermissionBean";
		List<PermissionBean> perlist = (List<PermissionBean>) hibernateTemplate.find(hql);
		return perlist==null||perlist.size()<=0?null:perlist.get(0);
	}

	@Override
	public PermissionBean selectPerByNameAndPerId(String pername, Integer perid) {
		String hql ="from PermissionBean where pername=? and perid !=?";
		List<PermissionBean> perlist = (List<PermissionBean>) hibernateTemplate.find(hql, pername,perid);
		return perlist==null||perlist.size()<=0?null:perlist.get(0);
	}

}
