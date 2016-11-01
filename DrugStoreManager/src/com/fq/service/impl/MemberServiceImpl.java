package com.fq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.MemberDAO;
import com.fq.po.MemberBean;
import com.fq.service.MemberService;
import com.fq.util.PageModel;
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDao;
	
	@Override
	public MemberBean selectMemberByName(String memberName) {
		return memberDao.selectMemberByName(memberName);
	}

	@Override
	public MemberBean selectMemberByNameAndMemberId(String memberName, String memberId) {
		return memberDao.selectMemberByNameAndMemberId(memberName, memberId);
	}

	@Override
	public MemberBean selectMemberByMembercode(String memberCode) {
		return memberDao.selectMemberByMembercode(memberCode);
	}

	@Override
	public PageModel<MemberBean> splitMember(Integer currPage, Integer pageSize, String keyword) {
		return memberDao.splitMember(currPage, pageSize, keyword);
	}

	@Override
	public void addMember(MemberBean memberBean, String time) throws Exception {
		

	}

	@Override
	public void addMember(MemberBean memberBean) {
		memberDao.addMember(memberBean);

	}

	@Override
	public List<MemberBean> showAllMember(String ids) {
		return memberDao.showAllMember(ids);
	}

	@Override
	public void deleteAllMember(List<MemberBean> memberList) {
		memberDao.deleteAllMember(memberList);

	}

	@Override
	public void updateMember(MemberBean memberBean, String time) {

	}

	@Override
	public void updateMember(MemberBean memberBean) {
		memberDao.updateMember(memberBean);

	}

	@Override
	public MemberBean selectById(String id) {
		return memberDao.selectById(id);
	}

	@Override
	public List<MemberBean> show() {
		return memberDao.show();
	}

}
