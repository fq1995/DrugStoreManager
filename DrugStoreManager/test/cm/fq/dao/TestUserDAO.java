package cm.fq.dao;

import java.util.Date;

import org.junit.Test;

import com.fq.po.UserBean;
import com.opensymphony.xwork2.interceptor.annotations.Before;

public class TestUserDAO {
	
	public void init(){
		UserBean userBean = new UserBean();
		userBean.setUserId("4871b2c9fb5f42699e91e196385dd4e5");
		userBean.setUserCode(1100);
		userBean.setStatus(1);
		userBean.setUsername("测试");
		userBean.setPassword("123456");
		userBean.setAddtime(new Date());
	}
	
	@Test
	public void registerTest(UserBean userBean){
		userBean = new UserBean();
		userBean.setUsername("测试");
		userBean.setPassword("123456");
	};
}
