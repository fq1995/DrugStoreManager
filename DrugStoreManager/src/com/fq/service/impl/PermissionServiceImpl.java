package com.fq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.PermissionDAO;
import com.fq.po.PermissionBean;
import com.fq.service.PermissionService;
import com.fq.util.PageModel;
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionDAO perDAO;
	
	@Override
	public PermissionBean selectPerByName(String pername) {
		return perDAO.selectPerByName(pername);
	}

	@Override
	public void addPer(PermissionBean perBean) {
		perDAO.addPer(perBean);
		
	}

	@Override
	public PageModel<PermissionBean> splitPer(Integer currPage, Integer pageSize) {
		return perDAO.splitPer(currPage, pageSize);
	}

	@Override
	public List<PermissionBean> showAllPer(String ids) {
		return perDAO.showAllPer(ids);
	}

	@Override
	public void deleteAllPer(List<PermissionBean> perList) {
		perDAO.deleteAllPer(perList);
		
	}

	@Override
	public void updatePer(PermissionBean perBean) {
		perDAO.updatePer(perBean);
		
	}

	@Override
	public PermissionBean selectById(String id) {
		return perDAO.selectById(id);
	}

	@Override
	public PermissionBean selectAll() {
		return perDAO.selectAll();
	}

}
