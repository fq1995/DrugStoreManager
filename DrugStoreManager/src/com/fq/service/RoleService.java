package com.fq.service;

import java.util.List;

import com.fq.po.RoleBean;
import com.fq.util.PageModel;

public interface RoleService {

	// 根据名称查询
	RoleBean selectRoleByName(String rolename);

	// 根据编号查询
	RoleBean selectRoleByRoleCode(Integer rolecode);

	// 添加
	void addRole(RoleBean roleBean);

	// 分页查询
	PageModel<RoleBean> splitRole(Integer currPage, Integer pageSize);

	// 批量查询
	List<RoleBean> showAllRole(String ids);

	// 批量删除对象
	void deleteAllRole(List<RoleBean> roleList);

	// 修改
	void updateRole(RoleBean roleBean);

	// 根据ID查询
	RoleBean selectById(String id);

	// 查询全部
	RoleBean selectAll();

	// 根据角色名和角色编号查询
	RoleBean selectRoleByNameAndRoleId(String rolename, Integer roleid);

}
