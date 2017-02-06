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
import com.fq.util.UUIDBuild;
@Repository("userDAO")
public class UserDAOImpl extends BaseDAO<UserBean> implements UserDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void register(UserBean userBean) {
		userBean.setUserId(UUIDBuild.getUUID());
		userBean.setStatus(1);
		hibernateTemplate.save(userBean);
	}

	@Override
	public UserBean loginOrNot(String username, String password) {
		String hql ="from UserBean where email=? and password=? and status=1";
		List<UserBean> UserList = (List<UserBean>) hibernateTemplate.find(hql, new Object[]{username,password});
		return UserList==null||UserList.size()<=0?null:UserList.get(0);
	}

	@Override
	public UserBean selectUserByName(String username) {
		if(null!=username || "".equals(username)){
			String hql ="from UserBean where username=? and status=1";
			List<UserBean> Userlist = (List<UserBean>) hibernateTemplate.find(hql, username);
			return Userlist==null||Userlist.size()<=0?null:Userlist.get(0);			
		}else{
			return null;
		}
		
	}

	@Override
	public PageModel<UserBean> splitUser(Integer currPage, Integer pageSize,String keyword) {
		String hql_count = "select count(*) from UserBean where username like :keyword";
		String hql = "from UserBean where username like :keyword order by userCode desc";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}

	@Override
	public List<UserBean> showAllUser(String ids) {
			String[] arr = ids.split(",");
			StringBuilder sb = new StringBuilder();
			String hql ="from UserBean where userId in(";
			sb.append(hql);
			for(int i = 0;i<arr.length;i++){
				if(i==arr.length-1){
					sb.append("'").append(arr[i]).append("')");
				}else{
					sb.append("'").append(arr[i]).append("'").append(",");
				}
			}
			List<UserBean> userList = (List<UserBean>) getHibernateTemplate().find(sb.toString());
			if(userList != null && userList.size() > 0) {
				return userList;
			}
		return null;
	}
	//批量删除的dao层
	@Override
	public void deleteAllUser(List<UserBean> userList) {
		getHibernateTemplate().deleteAll(userList);
	}

	@Override
	public void updateUser(UserBean userBean,String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			System.out.println("时间转换错误");
			e.printStackTrace();
		}
		userBean.setAddtime(date);
		getHibernateTemplate().update(userBean);
		
	}

	@Override
	public UserBean selectById(String id) {
		UserBean userBean = getHibernateTemplate().get(UserBean.class, id);
		return null==userBean?null:userBean;
	}

	@Override
	public void addUser(Integer code,UserBean userBean, String time) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(time);
		userBean.setAddtime(date);
		userBean.setUserId(UUIDBuild.getUUID());
		userBean.setUserCode(++code);
		hibernateTemplate.save(userBean);
		
	}

	@Override
	public UserBean selectUserByUsercode(Integer usercode) {
		String hql ="from UserBean where userCode=?";
		List<UserBean> Userlist = (List<UserBean>) hibernateTemplate.find(hql, usercode);
		return Userlist==null||Userlist.size()<=0?null:Userlist.get(0);
	}

	@Override
	public UserBean selectUserByNameAndUserId(String username, String userid) {
		String hql ="from UserBean where username =? and userId !=? and status=1";
		List<UserBean> Userlist = (List<UserBean>) hibernateTemplate.find(hql, username,userid);
		return Userlist==null||Userlist.size()<=0?null:Userlist.get(0);
	}

	@Override
	public List<UserBean> show() {
		String hql ="from UserBean";
		List<UserBean> list = (List<UserBean>) hibernateTemplate.find(hql);
		return list==null||list.size()<=0?null:list;
	}

	@Override
	public List<Integer> selectCode() {
		String hql ="select max(userCode) from UserBean";
		List<Integer> list =  (List<Integer>) hibernateTemplate.find(hql);
		return list==null||list.size()<=0?null:list;
	}

	@Override
	public UserBean selectUserByEmail(String email) {
		String hql ="from UserBean where email =?";
		List<UserBean> list =  (List<UserBean>) hibernateTemplate.find(hql,email);
		return list==null||list.size()<=0?null:list.get(0);
	}

	@Override
	public void editpass(UserBean userBean, String newpass) {
		userBean.setPassword(newpass);
		hibernateTemplate.update(userBean);
	}

	@Override
	public UserBean selectUserByNickName(String nickname) {
		if(null!=nickname || "".equals(nickname)){
			String hql ="from UserBean where nickname=? and status=1";
			List<UserBean> Userlist = (List<UserBean>) hibernateTemplate.find(hql, nickname);
			return Userlist==null||Userlist.size()<=0?null:Userlist.get(0);			
		}else{
			return null;
		}
	}

}
