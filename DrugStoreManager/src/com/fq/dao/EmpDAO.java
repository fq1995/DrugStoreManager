package com.fq.dao;

import java.util.List;

import com.fq.po.EmployeeBean;
import com.fq.util.PageModel;

public interface EmpDAO {
	
		//根据员工名查询
		EmployeeBean selectEmpByName(String empName);
		//根据员工名和id查询
		EmployeeBean selectEmpByNameAndEmpId(String empName,String empId);
		//根据员工编码查询
		EmployeeBean selectEmpByEmpcode(String empCode);
		//分页
		PageModel<EmployeeBean> splitEmp(Integer currPage, Integer pageSize, String keyword);
		//添加员工
		void addEmp(EmployeeBean empBean,String time) throws Exception;
		//添加员工
		void addEmp(EmployeeBean empBean);
		//批量查询
		List<EmployeeBean> showAllEmp(String ids);
		//批量删除
		void deleteAllEmp(List<EmployeeBean> empList);
		//修改员工
		void updateEmp(EmployeeBean empBean,String time);
		//修改员工
		void updateEmp(EmployeeBean empBean);
		//根据id查询
		EmployeeBean selectById(String id);
}