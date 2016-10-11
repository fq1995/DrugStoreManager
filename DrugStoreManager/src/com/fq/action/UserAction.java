package com.fq.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.UserBean;
import com.fq.service.UserService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction implements ModelDriven<UserBean>,RequestAware{
	//用户管理
	private Map<String ,Object> session;
	private Map<String, Object> request;
	private boolean flag;
	private Integer currPage;
	 
	private String ids;
	private String id;
	private String time;
	private String mess;
	private String keyword;
	
	
	@Autowired
	private UserService userService;
	private UserBean userBean = new UserBean();
	/**
	 * 用户登录
	 * @return
	 */
	public String login(){
		UserBean user = userService.loginOrNot(userBean.getUsername(), userBean.getPassword());
		if(user!=null){
			session.put("user", user);
			return "ok";
		}else{
			request.put("tishi","用户名或密码错误");
			return "error";
		}
	}
	/**
	 * 用户注册
	 * @return
	 */
	public String registUser(){
		if(null == selectUserByName()){
			userService.register(userBean);
			return "error";
		}else{
			request.put("message","用户名已被使用！");
			return "defult";
		}
		
	}
	/**
	 * 用户分页
	 * @return
	 */
	public String showUser() {
		/*HttpServletRequest request1 = ServletActionContext.getRequest();
		keyword = request1.getParameter("keyword");*/
		System.out.println(keyword+"=================================================");
		if(null == keyword){
			keyword="";
		}
		if(currPage == null) {
			currPage = 1;
		}
		PageModel<UserBean>  page = userService.splitUser(currPage,ConstantUtils.PAGESIZE,keyword);
		request.put("page", page);

		return "showUser";
	}
	/**
	 * 新增用户
	 * @return
	 * @throws Exception 
	 */
	public String addUser() throws Exception{
		if(null == selectUserByName()){
			userService.addUser(userBean,time);
			return "show";
		}
		request.put("message","用户名已被使用！");
		return "addUser";
		
	}
	/**
	 *  1	1001	user_showUser.action
		2	1002	user_updateUser.acti
		3	1003	user_editUser.action
		4	1004	user_delUser.action
		1	1	超级管理员	1001
		2	2	管理员	1002
	 * 删除用户
	 */
	public String delUser(){
		List<UserBean> listUser = userService.showAllUser(ids);
		userService.deleteAllUser(listUser);
		return "show";
	}
	/**
	 * 编辑用户
	 */
	public String editUser(){
		UserBean userBean1 = userService.selectById(id);
		if(null!=userBean1){
			request.put("user",userBean1);
		}
		return "edit";
	}
		
	/**
	 * 修改用户 
	 */
	public String updateUser(){
		if(null == selectUserByName()){
			userService.updateUser(userBean);
			return "show";
		}
		request.put("message","用户名已被使用！");
		return editUser();
	}
	/**
	 * 根据用户名查重
	 * @return
	 */
	public UserBean selectUserByName(){
		UserBean bean =userService.selectUserByName(userBean.getUsername());
		return bean;
	}
	/**
	 * ajax校验验证码是否正确
	 */
	public String validateVerifyCode() {
		 HttpServletRequest request = ServletActionContext.getRequest();
		String verifyCode = (String)request.getParameter("yanzheng");
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("code");
		if(verifyCode.equalsIgnoreCase(code)) {
			flag = true;
		} else if(!verifyCode.equalsIgnoreCase(code)){
			flag = false;
		}
		return "ajax_verifyCode";
	}
	/**
	 * ajax校验用户名是否可用
	 */
	public String validateName() {
		UserBean bean =userService.selectUserByName(userBean.getUsername());
		if(null == bean) {
			mess = "用户名可用";
		} else if(null != bean){
			mess = "用户名不可用";
		}
		return "ajax_verifyName";
	}
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	@Override
	public UserBean getModel() {
		return userBean;
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
	
}
