package com.fq.dao.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fq.dao.DrugDAO;
import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
import com.fq.service.DrugService;
import com.fq.util.PageModel;

import junit.framework.TestCase;

public class DrugDAOTest extends TestCase {

	private ApplicationContext applicationContext;
	
	@Autowired
	private DrugDAO drugDAO;
	@Autowired
	private DrugService drugService;
	
	protected void setUp() throws Exception {
		//获取spring的容器
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
			"spring_bean.xml",
			"spring.xml"
		});
		drugDAO = (DrugDAO) applicationContext.getBean("drugDAO");
		drugService = (DrugService) applicationContext.getBean("drugService");
	}

	@Test
	public void testAddDrug() {
		DrugBean drugBean = new DrugBean();
		drugBean.setDrugCode(1063);
		drugBean.setDrugId("80e68628b28a440e9f90da0afdf41161");
		drugBean.setDrugName("盖克");
		drugBean.setDosageformBean(new DosageformBean("6910bddbefff4426aee4b1c7fa0461ae"));
		drugBean.setDrugCategoryBean(new DrugCategoryBean("24a4c5c3662a40d0b964f9490e277223"));
		drugBean.setDrugUnitBean(new DrugUnitBean("30fc216763bb41a88ecf2cc8ffe47b8d"));
		drugBean.setModifier("张三");
		drugBean.setManufacturer("四川蜀中制药有限公司");
		drugBean.setApprovalNumber("国药准字H51023460");
		drugBean.setSalepeice(21d);
		
		try {
			drugService.addDrug(((Integer)1063), drugBean, "2017-02-01");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*	@Test
	public void testDeleteAllDrug() {
		List<DrugBean> drugList = new ArrayList<DrugBean>();
		DrugBean drugBean = new DrugBean();
		drugBean.setDrugId("111111111111111111111111111111");
		drugBean.setDrugCode(1111);
		drugList.add(drugBean);
		drugDAO.deleteAllDrug(drugList);
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

	
	@Test
	public void testSelectDrugByName() {
		DrugBean drugBean = drugDAO.selectDrugByName("保济丸");
		System.out.println(drugBean);
		
	}

	@Test
	public void testShow() {
		drugDAO.show();
	}


	@Test
	public void testSelectDrugByNameAndDrugId() {
		drugDAO.selectDrugByNameAndDrugId("保济丸", "346cbdd663fc40649f0a464d8b9d57e0");
	}

	

	@Test
	public void testSplit() {
		drugDAO.splitDrug(1, 5, "片");
	}

	@Test
	public void testSplit2() {
		drugDAO.splitDrug(1, 5, "", null, null, null, "", null, "");
	}

	@Test
	public void testShowAllDrug() {
		drugDAO.showAllDrug("da116b026d5c4b8aa8d039f051bedda6");
	}

	

	@Test
	public void testUpdateDrug() {
		DrugBean drugBean = new DrugBean();
		drugBean.setDrugId("111111111111111111111111111111");
		drugBean.setDrugCode(1111);
		String time = "2017-03-13";
		drugDAO.updateDrug(drugBean,time);
	}

	@Test
	public void testUpdateDrug2() {
		DrugBean drugBean = new DrugBean();
		drugBean.setDrugId("111111111111111111111111111111");
		drugBean.setDrugCode(1111);
		drugDAO.updateDrug(drugBean, "2016-10-11");
	}

	@Test
	public void testSelectById() {
		drugDAO.selectById("7d21e4a9fd6a4179b9c73f536cbc885f");
	}

	@Test
	public void testSelectCategory() {
		drugDAO.selectCategory();
	}

	@Test
	public void testSelectCategoryById() {
		drugDAO.selectCategoryById("d3de060cc6d94820aeb665518cc2942c");
	}

	@Test
	public void testSelectUnit() {
		drugDAO.selectUnit();
	}

	@Test
	public void testSelectUnitById() {
		drugDAO.selectUnitById("fbe3a8e489e54231b16b0f56243c71f3");
	}

	@Test
	public void testSelectForm() {
		drugDAO.selectForm();
	}

	@Test
	public void testSelectFormById() {
		drugDAO.selectFormById("689e595cd4aa49be805541550b325f01");
	}

	@Test
	public void testSelect() {
		drugDAO.select();
	}
*/
}
