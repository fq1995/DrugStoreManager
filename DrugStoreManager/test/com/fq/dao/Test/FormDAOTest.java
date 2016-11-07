package com.fq.dao.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fq.dao.FormDAO;
import com.fq.po.DosageformBean;
import com.fq.service.FormService;

import junit.framework.TestCase;

public class FormDAOTest extends TestCase {
	private ApplicationContext applicationContext;
	
	@Autowired
	private FormDAO formDAO;
	@Autowired
	private FormService formService;
	
	@Before
	protected void setUp() throws Exception {
		//获取spring的容器
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
			"spring_bean.xml",
			"spring.xml"
		});
		formDAO = (FormDAO) applicationContext.getBean("formDAO");
		formService = (FormService) applicationContext.getBean("formService");
		DosageformBean formBean = new  DosageformBean();
	}
	
	
	public void testSelectFormByName() {
		formDAO.selectFormByName("");
	}

	public void testAddForm() {
		DosageformBean formBean = new  DosageformBean();
		formDAO.addForm(formBean);
	}

	public void testSplitForm() {
		formDAO.splitForm(1, 5, "");
	}

	public void testShowAllForm() {
		formDAO.showAllForm("");
	}

	public void testDeleteAllForm() {
		List<DosageformBean> formList = new ArrayList<DosageformBean>();
		formDAO.deleteAllForm(formList);
	}

	public void testUpdateForm() {
		DosageformBean formBean = new  DosageformBean();
		formDAO.updateForm(formBean);
	}

	public void testSelectById() {
		formDAO.selectById("");
	}

	public void testSelectAll() {
		formDAO.selectAll();
	}

	public void testSelectFormByNameAndFormId() {
		formDAO.selectFormByNameAndFormId("", "");
	}

}
