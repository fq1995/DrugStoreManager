package com.fq.dao;

import java.util.List;

import com.fq.po.UserBean;
import com.fq.util.PageModel;

public interface UserDAO{
	
	void register(UserBean userBean);
	
	UserBean loginOrNot(String username,String password);
	
	UserBean selectUserByName(String username);
	
	void addUser(UserBean userBean,String time) throws Exception;
	void addUser(UserBean userBean);

	PageModel<UserBean> splitUser(Integer currPage, Integer pageSize);
	//批量查询
	List<UserBean> showAllUser(String ids);
	//批量删除对象
	void deleteAllUser(List<UserBean> userList);
	//修改
	void updateUser(UserBean userBean);
	//根据ID查询
	UserBean selectById(String id);
	
}
