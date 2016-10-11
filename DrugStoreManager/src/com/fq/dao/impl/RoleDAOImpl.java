package com.fq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.RoleDAO;
import com.fq.po.RoleBean;
import com.fq.util.BaseDAO;
import com.fq.util.PageModel;
@Repository("roleDAO")
public class RoleDAOImpl extends BaseDAO<RoleBean> implements RoleDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;


	@Override
	public RoleBean selectRoleByName(String rolename) {
		String hql ="from RoleBean where rolename=?";
		List<RoleBean> Rolelist = (List<RoleBean>) hibernateTemplate.find(hql, rolename);
		return Rolelist==null||Rolelist.size()<=0?null:Rolelist.get(0);
	}

	@Override
	public PageModel<RoleBean> splitRole(Integer currPage, Integer pageSize,String keyword) {
		String hql_count = "select count(*) from RoleBean where rolename like :keyword";
		String hql = "from RoleBean where rolename like :keyword order by roleid desc";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}

	@Override
	public void addRole(RoleBean roleBean){
		 hibernateTemplate.save(roleBean);
	}

	@Override
	public List<RoleBean> showAllRole(String ids) {
		String[] arr = ids.split(",");
		if(arr!=null){
			Integer[] arrId = new Integer[arr.length];
			for (Integer i = 0; i < arr.length; i++) {
				arrId[i] = Integer.parseInt(arr[i]);
			}
			String hql ="from RoleBean where roleid in(";
			StringBuilder sb = new StringBuilder(hql);
			for(Integer i = 0; i < arrId.length; i++) {
				if(i != arrId.length - 1){
					sb.append(arrId[i] + ",");
				}else{
					sb.append(arrId[i] + ")");
				}
			}
			List<RoleBean> roleList = (List<RoleBean>) getHibernateTemplate().find(sb.toString());
			if(roleList != null && roleList.size() > 0) {
				return roleList;
			}
		}
		return null;
	}
	//批量删除的dao层
	@Override
	public void deleteAllRole(List<RoleBean> roleList) {
		getHibernateTemplate().deleteAll(roleList);
	}

	@Override
	public void updateRole(RoleBean roleBean) {
		getHibernateTemplate().update(roleBean);
		
	}

	@Override
	public RoleBean selectById(String id) {
		RoleBean roleBean = getHibernateTemplate().get(RoleBean.class, Integer.valueOf(id));
		return null==roleBean?null:roleBean;
	}

	@Override
	public RoleBean selectAll() {
		String hql= "from RoleBean";
		List<RoleBean> Rolelist = (List<RoleBean>) hibernateTemplate.find(hql);
		return Rolelist==null||Rolelist.size()<=0?null:Rolelist.get(0);
	}




}
