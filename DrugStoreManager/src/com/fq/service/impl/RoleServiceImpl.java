package com.fq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.RoleDAO;
import com.fq.po.RoleBean;
import com.fq.service.RoleService;
import com.fq.util.PageModel;
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDAO roleDAO;


	@Override
	public RoleBean selectRoleByName(String rolename) {
		return roleDAO.selectRoleByName(rolename);
	}

	@Override
	public void addRole(RoleBean roleBean) {
		roleDAO.addRole(roleBean);
		
	}

	@Override
	public PageModel<RoleBean> splitRole(Integer currPage, Integer pageSize) {
		return roleDAO.splitRole(currPage, pageSize);
	}

	@Override
	public List<RoleBean> showAllRole(String ids) {
		return roleDAO.showAllRole(ids);
	}

	@Override
	public void deleteAllRole(List<RoleBean> roleList) {
		roleDAO.deleteAllRole(roleList);
		
	}

	@Override
	public void updateRole(RoleBean roleBean) {
		roleDAO.updateRole(roleBean);
		
	}

	@Override
	public RoleBean selectById(String id) {
		return roleDAO.selectById(id);
	}

	@Override
	public RoleBean selectAll() {
		return roleDAO.selectAll();
	}

	@Override
	public RoleBean selectRoleByRoleCode(Integer rolecode) {
		return roleDAO.selectRoleByRoleCode(rolecode);
	}


	
}
