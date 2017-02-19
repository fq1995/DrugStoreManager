package test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fq.dao.DrugDAO;
import com.fq.po.DrugBean;

import junit.framework.TestCase;

public class DrugDAOTest extends TestCase{
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
	public void testShow() {
		fail("Not yet implemented");
	}

	@Test
	public void testSplitDrugIntegerIntegerString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSplitDrugIntegerIntegerStringStringStringStringStringDateString() {
		fail("Not yet implemented");
	}

	@Test
	public void testShowAllDrug() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAllDrug() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateDrugDrugBeanString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddDrug() {
		DrugBean drugBean = new DrugBean();
		drugBean.setDrugCode(1111);
		drugBean.setDrugId("b358ccc44d5c4b17bfc488b81ac8f622");
		drugBean.setDrugName("桑菊感冒片");
		drugBean.setModifier("张三");
		drugBean.setModifyTime(new Date());
		drugBean.setManufacturer("桂林中族中药股份有限公司");
		drugBean.setApprovalNumber("国药准字Z45020280");
		drugDAO.addDrug(((Integer)1111), drugBean, "2017-02-01");
	}
}
