package com.fq.dao.Test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fq.po.DrugBean;
import com.fq.util.BaseDAO;

public class BaseDAOTest2 {

	private ApplicationContext applicationContext;
	private BaseDAO<DrugBean> baseDao = new BaseDAO<>();
	
	
	protected void setUp() throws Exception {
		//获取spring的容器
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
			"spring_bean.xml",
			"spring.xml"
		});
	}
	@Test
	public void testGetTotal() {
		String hql_count = "select count(*) from (select d from DrugBean d inner join d.drugUnitBean du inner join d.dosageformBean df inner join d.drugCategoryBean dc where d.drugUnitBean.unitnameId = du.unitnameId and d.dosageformBean.dosageformId = df.dosageformId and d.drugCategoryBean.categoryId = dc.categoryId) as total ";
		System.out.println(baseDao.getTotal(hql_count));
	}

}
