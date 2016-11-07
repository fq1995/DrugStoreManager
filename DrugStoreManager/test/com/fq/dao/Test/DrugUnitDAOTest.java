package com.fq.dao.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fq.dao.DrugUnitDAO;
import com.fq.po.DrugUnitBean;
import com.fq.service.DrugUnitService;

import junit.framework.TestCase;

public class DrugUnitDAOTest extends TestCase {
	private ApplicationContext applicationContext;
	@Autowired
	private DrugUnitDAO drugUnitDAO;
	@Autowired
	private DrugUnitService drugUnitService;
	
	@Before
	protected void setUp() throws Exception {
		//获取spring的容器
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
			"spring_bean.xml",
			"spring.xml"
		});
		drugUnitDAO = (DrugUnitDAO) applicationContext.getBean("drugUnitDAO");
		drugUnitService = (DrugUnitService) applicationContext.getBean("drugUnitService");
	}
	
	@Test
	public void testSelectDrugUnitByName() {
		drugUnitDAO.selectDrugUnitByName("支");
	}

	public void testSelectDrugUnitByNameAndDrugUnitId() {
		drugUnitDAO.selectDrugUnitByNameAndDrugUnitId("支", "192f6bdf72054caeb8e51b3d32efc2f0");
	}

	public void testAddDrugUnit() {
		DrugUnitBean drugUnitBean = new DrugUnitBean();
		drugUnitDAO.addDrugUnit(drugUnitBean);
	}

	public void testSplitDrugUnit() {
		drugUnitDAO.splitDrugUnit(1, 5, "");
	}

	public void testShowAllDrugUnit() {
		drugUnitDAO.showAllDrugUnit("");
	}

	public void testDeleteAllDrugUnit() {
		List<DrugUnitBean> drugUnitList = new ArrayList<DrugUnitBean>();
		drugUnitDAO.deleteAllDrugUnit(drugUnitList);
	}

	public void testUpdateDrugUnit() {
		DrugUnitBean drugUnitBean = new DrugUnitBean();
		drugUnitDAO.updateDrugUnit(drugUnitBean);
	}

	public void testSelectById() {
		drugUnitDAO.selectById("");
	}

}
