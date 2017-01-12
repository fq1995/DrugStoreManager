package com.fq.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.fq.dao.MemberDAO;
import com.fq.po.MemberBean;
import com.fq.util.BaseDAO;
import com.fq.util.PageModel;
import com.fq.util.UUIDBuild;
@Repository("memberDAO")
public class MemberDAOImpl extends BaseDAO<MemberBean> implements MemberDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public MemberBean selectMemberByName(String memberName) {
		String hql = "from MemberBean where memberName =?";
		List<MemberBean> list = (List<MemberBean>) hibernateTemplate.find(hql,memberName);
		return list==null||list.size()<=0?null:list.get(0);
	}

	@Override
	public MemberBean selectMemberByNameAndMemberId(String memberName, String memberId) {
		String hql ="from MemberBean where memberName=? and memberId !=?";
		List<MemberBean> list = (List<MemberBean>) hibernateTemplate.find(hql, memberName,memberId);
		return list==null||list.size()<=0?null:list.get(0);
	}

	@Override
	public MemberBean selectMemberByMembercode(String memberCode) {
		String hql ="from MemberBean where memberCode=?";
		List<MemberBean> list = (List<MemberBean>) hibernateTemplate.find(hql, memberCode);
		return list==null||list.size()<=0?null:list.get(0);
	}

	@Override
	public PageModel<MemberBean> splitMember(Integer currPage, Integer pageSize, String keyword) {
		String hql_count = "select count(*) from MemberBean where memberName like :keyword";
		String hql = "from MemberBean where memberName like :keyword ";
		return super.split(hql, hql_count, currPage, pageSize,keyword);
	}


	@Override
	public void addMember(Integer code,MemberBean memberBean) {
		memberBean.setMemberCode((++code).toString());
		memberBean.setMemberId(UUIDBuild.getUUID());
		hibernateTemplate.save(memberBean);
	}

	@Override
	public List<MemberBean> showAllMember(String ids) {
		String[] arr = ids.split(",");
		StringBuilder sb = new StringBuilder();
		String hql ="from MemberBean where memberId in(";
		sb.append(hql);
		for(int i = 0;i<arr.length;i++){
			if(i==arr.length-1){
				sb.append("'").append(arr[i]).append("')");
			}else{
				sb.append("'").append(arr[i]).append("'").append(",");
			}
		}
		List<MemberBean> empList = (List<MemberBean>) getHibernateTemplate().find(sb.toString());
		if(empList != null && empList.size() > 0) {
			return empList;
		}
		return null;
	}

	@Override
	public void deleteAllMember(List<MemberBean> memberList) {
		getHibernateTemplate().deleteAll(memberList);

	}

	@Override
	public void updateMember(MemberBean memberBean) {
		getHibernateTemplate().update(memberBean);

	}

	@Override
	public MemberBean selectById(String id) {
		MemberBean bean = getHibernateTemplate().get(MemberBean.class, id);
		return null==bean?null:bean;
	}

	@Override
	public List<MemberBean> show() {
		String hql ="from MemberBean";
		List<MemberBean> list = (List<MemberBean>) hibernateTemplate.find(hql);
		return list==null||list.size()<=0?null:list;
	}

	@Override
	public Integer selectCode() {
		String hql = "select max(memberCode) from MemberBean";
		List<String> list = (List<String>) hibernateTemplate.find(hql);
		Integer code = Integer.valueOf(list.get(0));
		return code;
	}


}
