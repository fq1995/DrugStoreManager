package com.fq.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.SupplierBean;
import com.fq.service.SupplierService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("supplierAction")
@Scope("prototype")
public class SupplierAction extends BaseAction implements ModelDriven<SupplierBean>,RequestAware{
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
	private SupplierService supService;
	private SupplierBean supBean = new SupplierBean();
	
	/**
	 * 用户分页
	 * @return
	 */
	public String showSupplier() {
		if(null == keyword){
			keyword="";
		}
		if(null != keyword){
			request.put("keyword", keyword);
		}
		if(currPage == null) {
			currPage = 1;
		}
		PageModel<SupplierBean>  page = supService.splitSup(currPage,ConstantUtils.PAGESIZE,keyword);
		request.put("page", page);

		return "showSupplier";
	}
	/**
	 * 跳转新增
	 */
	public String doaddSupplier(){
		return "doadd";
	}
	/**
	 * 新增用户
	 * @return
	 * @throws Exception 
	 */
	public String addSupplier(){
		if(null == selectSupplierByName() && null == selectSupplierBySuppliercode()){
			supService.addSup(supBean);
			return "show";
		}
		request.put("message","用户名已被使用！");
		request.put("message2","用户编号已被使用！");
		return "addSupplier";
		
	}
	/**
	 * 删除用户
	 */
	public String delSupplier(){
		List<SupplierBean> listSupplier = supService.showAllSup(ids);
		supService.deleteAllSup(listSupplier);
		return "show";
	}
	/**
	 * 编辑用户
	 */
	public String editSupplier(){
		SupplierBean supBean1 = supService.selectById(id);
		if(null!=supBean1){
			request.put("supplier",supBean1);
		}
		return "edit";
	}
		
	/**
	 * 修改用户 
	 */
	public String updateSupplier(){
		if(null == selectSupplierByNameAndSupplierId()){
			supService.updateSup(supBean);
			return "show";
		}
		request.put("message","用户名已被使用！");
		return editSupplier();
	}
	/**
	 * 根据用户名查重
	 * @return
	 */
	public SupplierBean selectSupplierByName(){
		SupplierBean bean =supService.selectSupByName(supBean.getSupplier());
		return bean;
	}
	/**
	 * 根据用户名和id查重
	 * @return
	 */
	public SupplierBean selectSupplierByNameAndSupplierId(){
		SupplierBean bean =supService.selectSupByNameAndSupId(supBean.getSupplier(),supBean.getSupplierId());
		return bean;
	}
	/**
	 * 根据用户编号查重
	 * @return
	 */
	public SupplierBean selectSupplierBySuppliercode(){
		SupplierBean bean =supService.selectSupBySupcode(supBean.getSupplierCode());
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
		SupplierBean bean =supService.selectSupByName(supBean.getSupplier());
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
	public SupplierBean getModel() {
		return supBean;
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
