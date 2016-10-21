package com.fq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.FormDAO;
import com.fq.po.DosageformBean;
import com.fq.po.RoleBean;
import com.fq.po.UserBean;
import com.fq.util.BaseDAO;
import com.fq.util.PageModel;
import com.fq.util.UUIDBuild;
@Repository("formDAO")
public class FormDAOImpl extends BaseDAO<DosageformBean> implements FormDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public DosageformBean selectFormByName(String formname) {
		String hql = "from DosageformBean where dosageform=?";
		List<DosageformBean> formList = (List<DosageformBean>) hibernateTemplate.find(hql, formname);
		return formList==null||formList.size()<=0?null:formList.get(0);
	}

	@Override
	public void addForm(DosageformBean formBean) {
		formBean.setDosageformId(UUIDBuild.getUUID());
		 hibernateTemplate.save(formBean);
	}

	@Override
	public PageModel<DosageformBean> splitForm(Integer currPage, Integer pageSize,String keyword) {
		String hql_count = "select count(*) from DosageformBean where dosageform like :keyword";
		String hql = "from DosageformBean where dosageform like :keyword order by dosageformId desc";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}

	@Override
	public List<DosageformBean> showAllForm(String ids) {
		String[] arr = ids.split(",");
		StringBuilder sb = new StringBuilder();
		String hql ="from DosageformBean where dosageformId in(";
		sb.append(hql);
		for(int i = 0;i<arr.length;i++){
			if(i==arr.length-1){
				sb.append("'").append(arr[i]).append("')");
			}else{
				sb.append("'").append(arr[i]).append("'").append(",");
			}
		}
		List<DosageformBean> formList = (List<DosageformBean>) getHibernateTemplate().find(sb.toString());
		if(formList != null && formList.size() > 0) {
			return formList;
		}
		return null;
		
	}

	@Override
	public void deleteAllForm(List<DosageformBean> formList) {
		getHibernateTemplate().deleteAll(formList);

	}

	@Override
	public void updateForm(DosageformBean formBean) {
		getHibernateTemplate().update(formBean);

	}

	@Override
	public DosageformBean selectById(String id) {
		DosageformBean formBean = getHibernateTemplate().get(DosageformBean.class, id);
		return null==formBean?null:formBean;
	}

	@Override
	public DosageformBean selectAll() {
		String hql = "from DosageformBean";
		List<DosageformBean> formList = (List<DosageformBean>) hibernateTemplate.find(hql);
		return formList==null||formList.size()<=0?null:formList.get(0);
	}

	@Override
	public DosageformBean selectFormByNameAndFormId(String formname, String formid) {
		String hql = "from DosageformBean where dosageformId =? and dosageform !=?";
		List<DosageformBean> formList = (List<DosageformBean>) hibernateTemplate.find(hql,formid,formname);
		return formList==null||formList.size()<=0?null:formList.get(0);
	}

}
