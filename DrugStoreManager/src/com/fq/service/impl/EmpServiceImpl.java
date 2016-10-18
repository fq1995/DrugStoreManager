package com.fq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.EmpDAO;
import com.fq.po.EmployeeBean;
import com.fq.service.EmpService;
import com.fq.util.PageModel;
@Service("empService")
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private EmpDAO empdao;
	
	@Override
	public EmployeeBean selectEmpByName(String empName) {
		return empdao.selectEmpByName(empName);
	}

	@Override
	public EmployeeBean selectEmpByNameAndEmpId(String empName, String empId) {
		return empdao.selectEmpByNameAndEmpId(empName, empId);
	}


	@Override
	public PageModel<EmployeeBean> splitEmp(Integer currPage, Integer pageSize, String keyword) {
		return empdao.splitEmp(currPage, pageSize, keyword);
	}

	@Override
	public void addEmp(EmployeeBean empBean, String time) throws Exception {
		empdao.addEmp(empBean,time);

	}

	@Override
	public void addEmp(EmployeeBean empBean) {
		empdao.addEmp(empBean);

	}

	@Override
	public List<EmployeeBean> showAllEmp(String ids) {
		return empdao.showAllEmp(ids);
	}

	@Override
	public void deleteAllEmp(List<EmployeeBean> empList) {
		empdao.deleteAllEmp(empList);

	}

	@Override
	public void updateEmp(EmployeeBean empBean, String time) {
		empdao.updateEmp(empBean, time);

	}
	
	@Override
	public void updateEmp(EmployeeBean empBean) {
		empdao.updateEmp(empBean);

	}
	
	@Override
	public EmployeeBean selectById(String id) {
		return empdao.selectById(id);
	}

	@Override
	public EmployeeBean selectEmpByEmpcode(String empCode) {
		return empdao.selectEmpByEmpcode(empCode);
	}

}
