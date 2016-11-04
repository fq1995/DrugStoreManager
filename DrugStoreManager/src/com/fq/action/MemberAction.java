package com.fq.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.MemberBean;
import com.fq.service.MemberService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("memberAction")
@Scope("prototype")
public class MemberAction extends BaseAction implements ModelDriven<MemberBean>, RequestAware {
	// 会员管理
	private Map<String, Object> session;
	private Map<String, Object> request;
	private boolean flag;
	private Integer currPage;

	private String ids;
	private String id;
	private String time;
	private String mess;
	private String keyword;
	private Integer memCode;

	@Autowired
	private MemberService memberService;
	private MemberBean memberBean = new MemberBean();

	/**
	 * 会员分页
	 * 
	 * @return
	 */
	public String showMember() {
		if (null == keyword) {
			keyword = "";
		}
		if (null != keyword) {
			request.put("keyword", keyword);
		}

		if (currPage == null) {
			currPage = 1;
		}
		PageModel<MemberBean> page = memberService.splitMember(currPage, ConstantUtils.PAGESIZE, keyword);
		request.put("page", page);

		return "showMember";
	}

	/**
	 * 跳转添加界面
	 */
	public String doaddMember() {
		memCode = memberService.selectCode();
		request.put("memCode", memCode);
		return "doadd";
	}

	/**
	 * 新增会员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addMember() {
		if (null == selectMemberByName() && null == selectMemberByMembercode()) {
			memCode = memberService.selectCode();
		
			memberService.addMember(memCode,memberBean);
			return "show";
		}

		request.put("message2", "会员编号已被使用！");
		return "addMember";

	}

	/**
	 * 删除会员
	 */
	public String delMember() {
		List<MemberBean> list = memberService.showAllMember(ids);
		memberService.deleteAllMember(list);
		return "show";
	}

	/**
	 * 编辑会员
	 */
	public String editMember() {
		MemberBean MemberBean1 = memberService.selectById(id);
		if (null != MemberBean1) {
			request.put("member", MemberBean1);
		}
		return "edit";
	}

	/**
	 * 修改会员
	 */
	public String updateMember() {
		if (null == selectMemberByNameAndMemberId()) {
			memberService.updateMember(memberBean);
			return "show";
		}
		request.put("message", "会员名已被使用！");
		return editMember();
	}

	/**
	 * 根据会员名查重
	 * 
	 * @return
	 */
	public MemberBean selectMemberByName() {
		MemberBean bean = memberService.selectMemberByName(memberBean.getMemberName());
		return bean;
	}

	/**
	 * 根据会员名和id查重
	 * 
	 * @return
	 */
	public MemberBean selectMemberByNameAndMemberId() {
		MemberBean bean = memberService.selectMemberByNameAndMemberId(memberBean.getMemberName(),
				memberBean.getMemberId());
		return bean;
	}

	/**
	 * 根据会员编号查重
	 * 
	 * @return
	 */
	public MemberBean selectMemberByMembercode() {
		MemberBean bean = memberService.selectMemberByMembercode(memberBean.getMemberCode());
		return bean;
	}

	/**
	 * ajax校验会员名是否可用
	 */
	public String validateName() {
		MemberBean bean = memberService.selectMemberByName(memberBean.getMemberName());
		if (null == bean) {
			mess = "会员名可用";
		} else if (null != bean) {
			mess = "会员名不可用";
		}
		return "ajax_verifyName";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public MemberBean getModel() {
		return memberBean;
	}

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getMemCode() {
		return memCode;
	}

	public void setMemCode(Integer memCode) {
		this.memCode = memCode;
	}

}
