package com.fq.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public void addMember(Integer code,MemberBean memberBean) {
		memberDao.addMember(code,memberBean);

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

	@Override
	public Integer selectCode() {
		return memberDao.selectCode();
	}

	@Override
	public String stats() {
		List<MemberBean> list = memberDao.stats();
		String json = null;
		List<MemberBean> list2 = new ArrayList<>();
		if(list.size()>=10){
			for(int i = 0;i<10;i++){
				list2.add(new MemberBean(list.get(i).getMemberName(), list.get(i).getMemberLevel(), list.get(i).getIntegration()));
			}
		}
		if(list.size()<10){
			for(int i = 0;i<list.size();i++){
				list2.add(new MemberBean(list.get(i).getMemberName(), list.get(i).getMemberLevel(), list.get(i).getIntegration()));
			}
		}
		ObjectMapper mapper = new ObjectMapper();  
		try {
			json = mapper.writeValueAsString(list2);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}  
		return json;
	}

}
