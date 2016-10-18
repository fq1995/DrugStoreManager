package com.fq.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.EmpDAO;
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
		List<EmployeeBean> perlist = (List<EmployeeBean>) hibernateTemplate.find(hql, empName,empId);
		return perlist==null||perlist.size()<=0?null:perlist.get(0);
	}

	@Override
	public EmployeeBean selectEmpByEmpcode(String empCode) {
		String hql ="from EmployeeBean where empCode=?";
		List<EmployeeBean> perlist = (List<EmployeeBean>) hibernateTemplate.find(hql, empCode);
		return perlist==null||perlist.size()<=0?null:perlist.get(0);
	}

	@Override
	public PageModel<EmployeeBean> splitEmp(Integer currPage, Integer pageSize, String keyword) {
		String hql_count = "select count(*) from EmployeeBean where empName like :keyword";
		String hql = "from EmployeeBean where empName like :keyword order by startdate desc";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}

	@Override
	public void addEmp(EmployeeBean empBean, String time) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(time);
		empBean.setStartdate(date);
		empBean.setEmpId(UUIDBuild.getUUID());
		hibernateTemplate.save(empBean);

	}

	@Override
	public void addEmp(EmployeeBean empBean) {
		empBean.setEmpId(UUIDBuild.getUUID());
		hibernateTemplate.save(empBean);
	}

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
	public void updateEmp(EmployeeBean empBean, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			System.out.println("时间转换错误");
			e.printStackTrace();
		}
		empBean.setStartdate(date);
		getHibernateTemplate().update(empBean);
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

}
