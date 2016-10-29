package com.fq.dao.Test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fq.dao.DrugDAO;
import com.fq.po.DrugBean;
import com.fq.util.PageModel;

import junit.framework.TestCase;

public class DrugDAOTest2 extends TestCase {

	private ApplicationContext applicationContext;
	
	@Autowired
	private DrugDAO drugDAO;
	
	protected void setUp() throws Exception {
		//获取spring的容器
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
			"spring_bean.xml",
			"spring.xml"
		});
		drugDAO = (DrugDAO) applicationContext.getBean("drugDAO");
	}

	@Test
	public void testSelectDrugByDrugcode(){
		DrugBean drugList  =  drugDAO.selectDrugByDrugcode(1001);
		System.out.println(drugList);	
	}
	
	
	@Test
	public void testSplitDrug() {
		 DrugDAO drugDAO = (DrugDAO) applicationContext.getBean("drugDAO");
		 Date date =new Date();
		 PageModel<DrugBean> page = drugDAO.splitDrug(1, 5, "海狗肾宝丸", "", "", "", "", date, "");
		 System.out.println(page);
	}

}
