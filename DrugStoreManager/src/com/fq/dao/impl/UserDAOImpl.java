package com.fq.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.UserDAO;
import com.fq.po.UserBean;
import com.fq.util.BaseDAO;
import com.fq.util.PageModel;
@Repository("userDAO")
public class UserDAOImpl extends BaseDAO<UserBean> implements UserDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void register(UserBean userBean) {
		hibernateTemplate.save(userBean);
	}

	@Override
	public UserBean loginOrNot(String username, String password) {
		String hql ="from UserBean where username=? and password=? and status=1";
		List<UserBean> UserList = (List<UserBean>) hibernateTemplate.find(hql, new Object[]{username,password});
		return UserList==null||UserList.size()<=0?null:UserList.get(0);
	}

	@Override
	public UserBean selectUserByName(String username) {
		String hql ="from UserBean where username=? and status=1";
		List<UserBean> Userlist = (List<UserBean>) hibernateTemplate.find(hql, username);
		return Userlist==null||Userlist.size()<=0?null:Userlist.get(0);
	}

	@Override
	public PageModel<UserBean> splitUser(Integer currPage, Integer pageSize,String keyword) {
		String hql_count = "select count(*) from UserBean where username like :keyword";
		String hql = "from UserBean where username like :keyword order by userid desc";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}

	@Override
	public void addUser(UserBean userBean){
		 hibernateTemplate.save(userBean);
	}

	@Override
	public List<UserBean> showAllUser(String ids) {
		String[] arr = ids.split(",");
		if(arr!=null){
			Integer[] arrId = new Integer[arr.length];
			for (Integer i = 0; i < arr.length; i++) {
				arrId[i] = Integer.parseInt(arr[i]);
			}
			String hql ="from UserBean where userid in(";
			StringBuilder sb = new StringBuilder(hql);
			for(Integer i = 0; i < arrId.length; i++) {
				if(i != arrId.length - 1){
					sb.append(arrId[i] + ",");
				}else{
					sb.append(arrId[i] + ")");
				}
			}
			List<UserBean> userList = (List<UserBean>) getHibernateTemplate().find(sb.toString());
			if(userList != null && userList.size() > 0) {
				return userList;
			}
		}
		return null;
	}
	//批量删除的dao层
	@Override
	public void deleteAllUser(List<UserBean> userList) {
		getHibernateTemplate().deleteAll(userList);
	}

	@Override
	public void updateUser(UserBean userBean) {
		getHibernateTemplate().update(userBean);
		
	}

	@Override
	public UserBean selectById(String id) {
		UserBean userBean = getHibernateTemplate().get(UserBean.class, Integer.valueOf(id));
		return null==userBean?null:userBean;
	}

	@Override
	public void addUser(UserBean userBean, String time) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(time);
		userBean.setAddtime(date);
		hibernateTemplate.save(userBean);
		
	}

}
