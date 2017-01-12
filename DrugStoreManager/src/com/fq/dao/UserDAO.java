package com.fq.dao;

import java.util.List;

import com.fq.po.UserBean;
import com.fq.util.PageModel;

public interface UserDAO {

	// 注册
	void register(UserBean userBean);

	// 登录
	UserBean loginOrNot(String username, String password);

	// 根据用户名查询
	UserBean selectUserByName(String username);

	// 根据用户名和id查询
	UserBean selectUserByNameAndUserId(String username, String userid);

	// 根据用户编码查询
	UserBean selectUserByUsercode(Integer usercode);

	// 分页
	PageModel<UserBean> splitUser(Integer currPage, Integer pageSize, String keyword);

	// 添加用户
	void addUser(Integer code, UserBean userBean, String time) throws Exception;

	// 批量查询
	List<UserBean> showAllUser(String ids);

	// 批量删除
	void deleteAllUser(List<UserBean> userList);

	// 修改用户
	void updateUser(UserBean userBean, String time);

	// 根据id查询
	UserBean selectById(String id);

	// 查询用户
	List<UserBean> show();

	// 查询用户编码
	List<Integer> selectCode();

	// 查询邮箱
	UserBean selectUserByEmail(String email);
}
