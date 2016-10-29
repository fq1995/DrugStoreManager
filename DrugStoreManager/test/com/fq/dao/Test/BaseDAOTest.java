package com.fq.dao.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.fq.po.DrugBean;
import com.fq.util.BaseDAO;

import junit.framework.TestCase;

public class BaseDAOTest extends TestCase {
	private ApplicationContext applicationContext;
	private BaseDAO<DrugBean> baseDao = new BaseDAO<>();
	
	
	protected void setUp() throws Exception {
		//获取spring的容器
		applicationContext = new ClassPathXmlApplicationContext(new String[] {
			"spring_bean.xml",
			"spring.xml"
		});
	}
	public void testGetTotal() {
		String hql_count = "select count(*) from (select d from DrugBean d inner join d.drugUnitBean du inner join d.dosageformBean df inner join d.drugCategoryBean dc where d.drugUnitBean.unitnameId = du.unitnameId and d.dosageformBean.dosageformId = df.dosageformId and d.drugCategoryBean.categoryId = dc.categoryId) as total ";
		System.out.println(baseDao.getTotal(hql_count));
	}

}
