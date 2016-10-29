package com.fq.dao.Test;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fq.dao.DrugDAO;
import com.fq.po.DrugBean;
import com.fq.util.BaseDAO;

public class DrugDAOTest extends BaseDAO<DrugBean>{
	private ApplicationContext applicationContext;
	
	@Autowired
	private DrugDAO drugDAO;
	
	protected void setUp() throws Exception {
		//获取spring的容器
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
			"spring_bean.xml",
			"spring.xml"
		});
	}

	@Test
	public void testSelectDrugByDrugcode(){
		DrugDAO drugDAO = (DrugDAO) applicationContext.getBean("drugDAO");
		drugDAO.selectDrugByDrugcode(1001);
		
	}
	
	
	@Test
	public void testSplitDrug() {
		 DrugDAO drugDAO = (DrugDAO) applicationContext.getBean("drugDAO");
		 Date date =new Date();
		 drugDAO.splitDrug(1, 5, "海狗肾宝丸", "", "", "", "", date, "");
	}

}
