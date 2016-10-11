package com.fq.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.RoleBean;
import com.fq.service.RoleService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction implements ModelDriven<RoleBean>,RequestAware{
	private Map<String, Object> request;
	private Integer currPage;
	  
	private String ids;
	private String id;
	private String mess;
	private String keyword;
	
	
	@Autowired
	private RoleService roleService;
	private RoleBean roleBean = new RoleBean();

	/**
	 * 分页
	 * @return
	 */
	public String showRole() {
		if(null == keyword){
			keyword="";
		}
		if(currPage == null) {
			currPage = 1;
		}
		PageModel<RoleBean>  page = roleService.splitRole(currPage, ConstantUtils.PAGESIZE,keyword);
		request.put("page", page);
		RoleBean role= roleService.selectAll();
		request.put("role", role);
		return "showRole";
	}
	/**
	 * 新增角色
	 * @return
	 * @throws Exception 
	 */
	public String addRole(){
		if(null == selectRoleByName()){
			roleService.addRole(roleBean);
			return "show";
		}
		request.put("message","角色名已被使用！");
		return "addRole";
		
	}
	/**
	 * 删除角色
	 */
	public String delRole(){
		List<RoleBean> rolelist = roleService.showAllRole(ids);
		roleService.deleteAllRole(rolelist);
		return "show";
	}
	/**
	 * 编辑角色
	 */
	public String editRole(){
		RoleBean roleBean1 = roleService.selectById(id);
		if(null!=roleBean1){
			request.put("role",roleBean1);
		}
		return "edit";
	}
		
	/**
	 * 修改角色 
	 */
	public String updateRole(){
		if(null == selectRoleByName()){
			roleService.updateRole(roleBean);
			return "show";
		}
		request.put("message","角色名已被使用！");
		return editRole();
	}
	/**
	 * 根据角色名查重
	 * @return
	 */
	public RoleBean selectRoleByName(){
		RoleBean bean =roleService.selectRoleByName(roleBean.getRolename());
		return bean;
	}
	
	/**
	 * ajax校验角色名是否可用
	 */
	public String validateName() {
		RoleBean bean =roleService.selectRoleByName(roleBean.getRolename());
		if(null == bean) {
			mess = "角色名可用";
		} else if(null != bean){
			mess = "角色名不可用";
		}
		return "ajax_verifyName";
	}
	
	
	@Override
	public void setSession(Map<String, Object> session) {
	}

	@Override
	public RoleBean getModel() {
		return roleBean;
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
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request= request;
	}
	
}
