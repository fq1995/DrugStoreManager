package com.fq.action;

import java.util.Date;
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
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction implements ModelDriven<UserBean>,RequestAware{
	
	private Map<String ,Object> session;
	private Map<String, Object> request;
	private boolean flag;
	public Map<String, Object> getRequest() {
		return request;
	}
	private Integer currPage;
	 
	private Integer pageSize=5;
	private String ids;
	private String id;
	private String time;
	
	
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
			return "defult";
		}
		
	}
	/**
	 * 用户分页
	 * @return
	 */
	public String showUser() {
		if(currPage == null) {
			currPage = 1;
		}
		PageModel<UserBean>  page = userService.splitUser(currPage,pageSize);
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
		userService.updateUser(userBean);
		return "show";
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
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
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

}
