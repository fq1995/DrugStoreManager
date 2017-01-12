package com.fq.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.PermissionBean;
import com.fq.service.PermissionService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 
 * @author fu
 * 权限管理
 */
@Controller("permissionAction")
@Scope("prototype")
public class PermissionAction extends BaseAction implements ModelDriven<PermissionBean>,RequestAware{
	private Map<String, Object> request;
	private Integer currPage;
	  
	private String ids;
	private String id;
	private String mess;
	
	
	@Autowired
	private PermissionService perService;
	private PermissionBean perBean = new PermissionBean();

	/**
	 * 分页
	 * @return
	 */
	public String showPer() {
		if(currPage == null) {
			currPage = 1;
		}
		PageModel<PermissionBean>  page = perService.splitPer(currPage, ConstantUtils.PAGESIZE);
		request.put("page", page);
		return "showPer";
	}
	/**
	 * 跳转新增
	 */
	public String doaddPer(){
		return "doaddPer";
	}
	/**
	 * 新增权限
	 * @return
	 * @throws Exception 
	 */
	public String addPer(){
		if(null == selectPerByName()){
			perService.addPer(perBean);
			return "show";
		}
		request.put("message","权限名已被使用！");
		request.put("message2","权限编号已被使用！");
		return "addPer";
		
	}
	/**
	 * 删除权限
	 */
	public String delPer(){
		List<PermissionBean> perlist = perService.showAllPer(ids);
		perService.deleteAllPer(perlist);
		return "show";
	}
	/**
	 * 编辑权限
	 */
	public String editPer(){
		PermissionBean perBean1 = perService.selectById(id);
		if(null!=perBean1){
			request.put("per",perBean1);
		}
		return "edit";
	}
		
	/**
	 * 修改权限 
	 */
	public String updatePer(){
		if(null == selectPerByNameAndPerId()){
			perService.updatePer(perBean);
			return "show";
		}
		request.put("message","权限名已被使用！");
		return editPer();
	}
	/**
	 * 根据权限名查重
	 * @return
	 */
	public PermissionBean selectPerByName(){
		PermissionBean bean =perService.selectPerByName(perBean.getPername());
		return bean;
	}
	/**
	 * 根据权限名和权限编号查重
	 * @return
	 */
	public PermissionBean selectPerByNameAndPerId(){
		PermissionBean bean =perService.selectPerByNameAndPerId(perBean.getPername(),perBean.getPerid());
		return bean;
	}
	
	/**
	 * ajax校验权限名是否可用
	 */
	public String validateName() {
		PermissionBean bean =perService.selectPerByName(perBean.getPername());
		if(null == bean) {
			mess = "权限名可用";
		} else if(null != bean){
			mess = "权限名不可用";
		}
		return "ajax_verifyName";
	}
	
	
	@Override
	public void setSession(Map<String, Object> session) {
	}

	@Override
	public PermissionBean getModel() {
		return perBean;
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
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request= request;
	}
	
}
