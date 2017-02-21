package test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fq.po.DrugBean;
import com.fq.service.DrugService;
@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml") 
public class DrugDAOTest extends AbstractJUnit4SpringContextTests{
/*	private ApplicationContext applicationContext;*/
/*	
	@Autowired
	private DrugDAO drugDAO;*/

	
	@Resource
	private DrugService drugService;
	
	
	protected void setUp() throws Exception {
	/*	//获取spring的容器
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
			"spring_bean.xml",
			"spring.xml"
		});
		drugDAO = (DrugDAO) applicationContext.getBean("drugDAO");
		drugService = (DrugService)applicationContext.getBean("drugService");*/
	}
	@Test
	public void testShow() {
	}

	@Test
	public void testSplitDrugIntegerIntegerString() {
	}

	@Test
	public void testSplitDrugIntegerIntegerStringStringStringStringStringDateString() {
	}

	@Test
	public void testShowAllDrug() {
	}

	@Test
	public void testDeleteAllDrug() {
	}

	@Test
	public void testUpdateDrugDrugBeanString() {
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
		try {
			drugService.addDrug(((Integer)1111), drugBean, "2017-02-01");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
