package cm.fq.dao;

import org.junit.Test;

import com.fq.dao.RoleDAO;
import com.fq.dao.impl.RoleDAOImpl;

public class TestRoleDAO {
	
	@Test
	public void testSelectRoleByRoleCode(){
		RoleDAO dao = new RoleDAOImpl();
		dao.selectRoleByRoleCode(1);
	}
}
