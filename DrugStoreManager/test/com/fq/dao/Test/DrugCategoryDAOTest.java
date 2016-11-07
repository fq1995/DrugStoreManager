package com.fq.dao.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fq.dao.DrugCategoryDAO;
import com.fq.po.DrugCategoryBean;
import com.fq.service.DrugCategoryService;

import junit.framework.TestCase;

public class DrugCategoryDAOTest extends TestCase {
	
private ApplicationContext applicationContext;
	
	@Autowired
	private DrugCategoryDAO drugCategoryDAO;
	@Autowired
	private DrugCategoryService drugCategoryService;
	
	@Before
	protected void setUp() throws Exception {
		//获取spring的容器
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
			"spring_bean.xml",
			"spring.xml"
		});
		drugCategoryDAO = (DrugCategoryDAO) applicationContext.getBean("drugCategoryDAO");
		drugCategoryService = (DrugCategoryService) applicationContext.getBean("drugCategoryService");
	}
	
	@Test
	public void testSelectDrugCategoryByName() {
		drugCategoryDAO.selectDrugCategoryByName("软膏");
	}

	public void testSelectDrugCategoryByNameAndId() {
		drugCategoryDAO.selectDrugCategoryByNameAndId("软膏", "1b1999eeea93485fb939109516e738c2");
	}

	public void testAddDrugCategory() {
		DrugCategoryBean categoryBean = new DrugCategoryBean();
		drugCategoryService.addDrugCategory(categoryBean);
	}

	public void testSplitDrugCategory() {
		drugCategoryDAO.splitDrugCategory(1, 5, "");
	}

	public void testShowAllDrugCategory() {
		drugCategoryDAO.showAllDrugCategory("387c8362d40b427984e22626349514b3");
	}

	public void testDeleteAllDrugCategory() {
		List<DrugCategoryBean> categoryList = new ArrayList<DrugCategoryBean>();
		drugCategoryService.deleteAllDrugCategory(categoryList);
	}

	public void testUpdateDrugCategory() {
		DrugCategoryBean categoryBean = new DrugCategoryBean();
		categoryBean.setCategoryId("387c8362d40b427984e22626349514b3");
		drugCategoryService.updateDrugCategory(categoryBean);
	}

	public void testSelectById() {
		drugCategoryDAO.selectById("387c8362d40b427984e22626349514b3");
	}

}
