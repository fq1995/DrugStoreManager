package com.fq.service;

import java.util.List;

import com.fq.po.UserBean;
import com.fq.util.PageModel;

public interface UserService {
	void register(UserBean userBean);
	
	UserBean loginOrNot(String username,String password);
	
	UserBean selectUserByName(String username);

	PageModel<UserBean> splitUser(Integer currPage, Integer pageSize);

	void addUser(UserBean userBean);
	//批量查询
	List<UserBean> showAllUser(String ids);
	//批量删除
	void deleteAllUser(List<UserBean> userList);

	void updateUser(UserBean userBean);
	
	UserBean selectById(String id);
}
