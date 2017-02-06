package com.fq.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fq.dao.UserDAO;
import com.fq.po.UserBean;
import com.fq.service.UserService;
import com.fq.util.PageModel;
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public void register(UserBean userBean) {
		userBean.setAddtime(new Date());
		userDAO.register(userBean);
	}

	@Override
	public UserBean loginOrNot(String username, String password) {
		return userDAO.loginOrNot(username, password);
	}

	@Override
	public UserBean selectUserByName(String username) {
		return userDAO.selectUserByName(username);
	}
	
	
	@Override
	public PageModel<UserBean> splitUser(Integer currPage, Integer pageSize ,String keyword) {
		return userDAO.splitUser(currPage,pageSize,keyword);
	}

	@Override
	public void addUser(Integer code,UserBean userBean,String time) throws Exception {
		userDAO.addUser(code,userBean,time);
		
	}
	//批量查询
	@Override
	public List<UserBean> showAllUser(String ids) {
		return userDAO.showAllUser(ids);
	}

	@Override
	public void deleteAllUser(List<UserBean> userList) {
		userDAO.deleteAllUser(userList);
	}

	@Override
	public void updateUser(UserBean userBean,String time){
		userDAO.updateUser(userBean,time);
		
	}

	@Override
	public UserBean selectById(String id) {
		return userDAO.selectById(id);
	}


	@Override
	public UserBean selectUserByUsercode(Integer usercode) {
		return userDAO.selectUserByUsercode(usercode);
	}

	@Override
	public UserBean selectUserByNameAndUserId(String username, String userid) {
		return userDAO.selectUserByNameAndUserId(username, userid);
	}

	@Override
	public List<UserBean> show() {
		return userDAO.show();
	}

	@Override
	public List<Integer> selectCode() {
		return userDAO.selectCode();
	}

	@Override
	public UserBean selectUserByEmail(String email) {
		return userDAO.selectUserByEmail(email);
	}

	@Override
	public void editpass(UserBean userBean,String newpass) {
		userDAO.editpass(userBean,newpass);
		
	}

	@Override
	public UserBean selectUserByNickName(String nickname) {
		return userDAO.selectUserByNickName(nickname);
	}

}
