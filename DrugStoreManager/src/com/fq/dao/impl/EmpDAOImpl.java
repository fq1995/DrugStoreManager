package com.fq.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.EmpDAO;
import com.fq.po.DrugBean;
import com.fq.po.EmployeeBean;
import com.fq.util.BaseDAO;
import com.fq.util.PageModel;
import com.fq.util.UUIDBuild;
@Repository("empDAO")
public class EmpDAOImpl extends BaseDAO<EmployeeBean> implements EmpDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public EmployeeBean selectEmpByName(String empName) {
		String hql = "from EmployeeBean where empName =?";
		List<EmployeeBean> emplist = (List<EmployeeBean>) hibernateTemplate.find(hql,empName);
		return emplist==null||emplist.size()<=0?null:emplist.get(0);
	}

	@Override
	public EmployeeBean selectEmpByNameAndEmpId(String empName, String empId) {
		String hql ="from EmployeeBean where empName=? and empId !=?";
		List<EmployeeBean> list = (List<EmployeeBean>) hibernateTemplate.find(hql, empName,empId);
		return list==null||list.size()<=0?null:list.get(0);
	}

	@Override
	public EmployeeBean selectEmpByEmpcode(String empCode) {
		String hql ="from EmployeeBean where empCode=?";
		List<EmployeeBean> list = (List<EmployeeBean>) hibernateTemplate.find(hql, empCode);
		return list==null||list.size()<=0?null:list.get(0);
	}

	@Override
	public PageModel<EmployeeBean> splitEmp(Integer currPage, Integer pageSize, String keyword) {
		String hql_count = "select count(*) from EmployeeBean where empName like :keyword";
		String hql = "from EmployeeBean where empName like :keyword order by empCode desc";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}


	@Override
	public void addEmp(Integer empCode,EmployeeBean empBean) {
		empBean.setEmpCode((++empCode).toString());
		empBean.setEmpId(UUIDBuild.getUUID());
		hibernateTemplate.save(empBean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> showAllEmp(String ids) {
		String[] arr = ids.split(",");
		StringBuilder sb = new StringBuilder();
		String hql ="from EmployeeBean where empId in(";
		sb.append(hql);
		for(int i = 0;i<arr.length;i++){
			if(i==arr.length-1){
				sb.append("'").append(arr[i]).append("')");
			}else{
				sb.append("'").append(arr[i]).append("'").append(",");
			}
		}
		List<EmployeeBean> empList = (List<EmployeeBean>) getHibernateTemplate().find(sb.toString());
		if(empList != null && empList.size() > 0) {
			return empList;
		}
		return null;
	}

	@Override
	public void deleteAllEmp(List<EmployeeBean> empList) {
		getHibernateTemplate().deleteAll(empList);

	}


	@Override
	public EmployeeBean selectById(String id) {
		EmployeeBean empbean = getHibernateTemplate().get(EmployeeBean.class, id);
		return null==empbean?null:empbean;
	}

	@Override
	public void updateEmp(EmployeeBean empBean) {
		getHibernateTemplate().update(empBean);
	}

	@Override
	public List<EmployeeBean> show() {
		String hql ="from EmployeeBean";
		List<EmployeeBean> list = (List<EmployeeBean>) hibernateTemplate.find(hql);
		return list==null||list.size()<=0?null:list;
	}

	@Override
	public Integer selectCode() {
		String hql = "select max(empCode) from EmployeeBean";
		List<String> list = (List<String>) hibernateTemplate.find(hql);
		Integer empCode =Integer.valueOf(list.get(0));
		return empCode;
	}

}
