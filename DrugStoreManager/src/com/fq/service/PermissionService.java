package com.fq.service;

import java.util.List;

import com.fq.po.PermissionBean;
import com.fq.util.PageModel;

public interface PermissionService {
		//根据名称查询
		PermissionBean selectPerByName(String pername);
		//添加
		void addPer(PermissionBean perBean);
		//分页查询
		PageModel<PermissionBean> splitPer(Integer currPage, Integer pageSize);
		//批量查询
		List<PermissionBean> showAllPer(String ids);
		//批量删除对象
		void deleteAllPer(List<PermissionBean> perList);
		//修改
		void updatePer(PermissionBean perBean);
		//根据ID查询
		PermissionBean selectById(String id);
		//查询全部
		PermissionBean selectAll();
}
