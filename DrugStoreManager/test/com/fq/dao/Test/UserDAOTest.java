package com.fq.dao.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.fq.dao.UserDAO;
import com.fq.po.UserBean;
import com.fq.service.UserService;

import junit.framework.TestCase;

public class UserDAOTest extends TestCase {
	
	private ApplicationContext applicationContext;
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserService userService; 
	
	@Before
	protected void setUp() throws Exception {
		//获取spring的容器
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
			"spring_bean.xml",
			"spring.xml"
		});
		userDAO = (UserDAO) applicationContext.getBean("userDAO");
		userService = (UserService) applicationContext.getBean("userService");
	}
/*
	@Test
	public void testRegister() {
		UserBean userBean = new UserBean();
		userBean.setUsername("测试");
		userBean.setPassword("123456");
		Integer userCode = 1111;
		String time = "2017-03-14 00:00:00";
		userService.register(userCode, userBean, time);
//		userDAO.register(userBean);
	}
	@Test
	public void testLoginOrNot() {
		userDAO.loginOrNot("111111@qq.com", "123456");
	}
	@Test
	public void testSelectUserByName() {
		userDAO.selectUserByName("张三");
	}
	@Test
	public void testSelectUserByNameAndUserId() {
		userDAO.selectUserByNameAndUserId("张三", "52d4b1f3daaa44fcaa5c8812ec63e42c");
	}
	@Test
	public void testSelectUserByUsercode() {
		userDAO.selectUserByUsercode(1001);
	}
	@Test
	public void testSplitUser() {
		userDAO.splitUser(1, 5, "");
	}*/
	@Test
	public void testAddUser() {
		UserBean userBean = new UserBean();
		userBean.setEmail("111222@163.com");
		userBean.setNickname("尼古拉斯");
		userBean.setPassword("111111");
		userBean.setUserCode(1111);
		userBean.setUsername("赵四");
		try {
			userService.addUser(1111, userBean, "2016-10-10");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*	@Test
	public void testShowAllUser() {
		userDAO.showAllUser("15c54ab46f7f464cace4544fe31bcf5d");
	}
	@Test
	public void testDeleteAllUser() {
		List<UserBean> userList = new ArrayList<UserBean>();
		userDAO.deleteAllUser(userList);
	}
	@Test
	public void testUpdateUser() {
		UserBean userBean = new UserBean();
		userDAO.updateUser(userBean, "2016-10-11");
	}
	@Test
	public void testSelectById() {
		userDAO.selectById("15c54ab46f7f464cace4544fe31bcf5d");
	}
	@Test
	public void testShow() {
		userDAO.show();
	}
	@Test
	public void testSelectCode() {
		userDAO.selectCode();
	}*/
}
