package com.fq.service;

import java.util.List;

import com.fq.po.MemberBean;
import com.fq.util.PageModel;

public interface MemberService {

	// 根据会员名查询
	MemberBean selectMemberByName(String memberName);

	// 根据会员名和id查询
	MemberBean selectMemberByNameAndMemberId(String memberName, String memberId);

	// 根据会员编码查询
	MemberBean selectMemberByMembercode(String memberCode);

	// 分页
	PageModel<MemberBean> splitMember(Integer currPage, Integer pageSize, String keyword);

	// 添加会员
	void addMember(Integer code, MemberBean memberBean);

	// 批量查询
	List<MemberBean> showAllMember(String ids);

	// 批量删除
	void deleteAllMember(List<MemberBean> memberList);

	// 修改会员
	void updateMember(MemberBean memberBean);

	// 根据id查询
	MemberBean selectById(String id);

	// 查询全部
	List<MemberBean> show();

	// 查询编号
	Integer selectCode();
}
