package com.fq.action;

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
/**
 * 
 * @author fu
 * 用户管理
 */
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
	private String mes;
	private String keyword;
	private Integer userCode;
	private String newpass;
	private String nickname;
	private String username;
	@Autowired
	private UserService userService;
	private UserBean userBean = new UserBean();
	/**
	 * 用户登录
	 * @return
	 */
	public String login(){
		UserBean user = userService.loginOrNot(userBean.getEmail(), userBean.getPassword());
		if(user!=null){
			session.put("user", user);
			session.put("username",user.getUsername());
			session.put("nickname", user.getNickname());
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
			userCode = userService.selectCode().get(0);
			userService.register(userCode,userBean,time);
			return "error";
		}else{
			request.put("message","邮箱已被使用！");
			return "defult";
		}
		
	}
	/**
	 * 修改密码
	 */
	public void editpass(){
		 newpass = ServletActionContext.getRequest().getParameter("newpass");
		 userBean = (UserBean) ServletActionContext.getRequest().getSession().getAttribute("user");
		 userService.editpass(userBean, newpass);
	}
	/**
	 * 用户分页
	 * @return
	 */
	public String showUser() {
		if(null == keyword){
			keyword="";
		}
		if(null != keyword){
			request.put("keyword", keyword);
		}
		if(currPage == null) {
			currPage = 1;
		}
		PageModel<UserBean>  page = userService.splitUser(currPage,ConstantUtils.PAGESIZE,keyword);
		request.put("page", page);

		return "showUser";
	}
	/**
	 * 跳转新增
	 */
	public String doaddUser(){
		userCode = userService.selectCode().get(0);
		request.put("userCode", userCode);
		return "doadd";
	}
	/**
	 * 新增用户
	 * @return
	 * @throws Exception 
	 */
	public String addUser(){
		if(null == selectUserByName() && null == selectUserByUsercode()){
			userCode = userService.selectCode().get(0);
			try {
				userService.addUser(userCode,userBean,time);
			} catch (Exception e) {
				System.out.println("时间转换错误");
				e.printStackTrace();
			}
			return "show";
		}
		request.put("message","用户名已被使用！");
		request.put("message2","用户编号已被使用！");
		return "addUser";
		
	}
	/**
	 * 删除用户
	 */
	public String delUser(){
		List<UserBean> listUser = userService.showAllUser(ids);
		userService.deleteAllUser(listUser);
		return "show";
	/*	for(UserBean user : listUser){
			if(user.getUsername().equals(session.get("username"))){
				request.put("warn","没有权限删除!");
			}else{
				userService.deleteAllUser(listUser);
			}
		}*/
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
		if(null == selectUserByNameAndUserId()){
			userService.updateUser(userBean,time);
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
	 * 根据用户邮箱查询
	 */
	public UserBean selectUserByEmail(){
		UserBean bean = userService.selectUserByEmail(userBean.getEmail());
		return bean;
	}  
	/**
	 * 根据用户名和id查重
	 * @return
	 */
	public UserBean selectUserByNameAndUserId(){
		UserBean bean =userService.selectUserByNameAndUserId(userBean.getUsername(),userBean.getUserId());
		return bean;
	}
	/**
	 * 根据用户编号查重
	 * @return
	 */
	public UserBean selectUserByUsercode(){
		UserBean bean =userService.selectUserByUsercode(userBean.getUserCode());
		return bean;
	}
	/**
	 * 
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
	 * ajax校验昵称是否可用
	 */
	public String validateNickName() {
		nickname = ServletActionContext.getRequest().getParameter("nickname");
		UserBean bean =userService.selectUserByNickName(nickname);
		if(null == bean) {
			mess = "用户名可用";
		} else if(null != bean){
			mess = "用户名不可用";
		}
		return "ajax_verifyName";
	}
	
	/**
	 * ajax校验用户名是否可用
	 */
	public String validateName() {
		username = ServletActionContext.getRequest().getParameter("username");
		UserBean bean =userService.selectUserByName(username);
		if(null == bean) {
			mess = "用户名可用";
		} else if(null != bean){
			mess = "用户名不可用";
		}
		return "ajax_verifyName";
	}
	
	/**
	 * ajax校验邮箱是否可用
	 */
	public String validateEmail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String email = (String)request.getParameter("email");
		UserBean bean =userService.selectUserByEmail(email);
		if(null == bean) {
			mes = "邮箱名可用";
		} else if(null != bean){
			mes = "邮箱名不可用";
		}
		return "ajax_verifyEmail";
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
	public Integer getUserCode() {
		return userCode;
	}
	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getNewpass() {
		return newpass;
	}
	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}
	
	
}
