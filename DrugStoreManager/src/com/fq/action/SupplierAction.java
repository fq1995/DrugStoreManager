package com.fq.action;

import java.util.List;
import java.util.Map;

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
	//供货商管理
	private Map<String ,Object> session;
	private Map<String, Object> request;
	private boolean flag;
	private Integer currPage;
	 
	private String ids;
	private String id;
	private String time;
	private String mess;
	private String keyword;
	private Integer code;
	
	@Autowired
	private SupplierService supService;
	private SupplierBean supBean = new SupplierBean();
	
	/**
	 * 供货商分页
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
		code = supService.select();
		request.put("code", code);
		return "doadd";
	}
	/**
	 * 新增供货商
	 * @return
	 * @throws Exception 
	 */
	public String addSupplier(){
		if(null == selectSupplierByName() && null == selectSupplierBySuppliercode()){
			code = supService.select();
			supService.addSup(code,supBean);
			return "show";
		}
		request.put("message","供货商名已被使用！");
		request.put("message2","供货商编号已被使用！");
		return "addSupplier";
		
	}
	/**
	 * 删除供货商
	 */
	public String delSupplier(){
		List<SupplierBean> listSupplier = supService.showAllSup(ids);
		supService.deleteAllSup(listSupplier);
		return "show";
	}
	/**
	 * 编辑供货商
	 */
	public String editSupplier(){
		SupplierBean supBean1 = supService.selectById(id);
		if(null!=supBean1){
			request.put("supplier",supBean1);
		}
		return "edit";
	}
		
	/**
	 * 修改供货商 
	 */
	public String updateSupplier(){
		if(null == selectSupplierByNameAndSupplierId()){
			supService.updateSup(supBean);
			return "show";
		}
		request.put("message","供货商名已被使用！");
		return editSupplier();
	}
	/**
	 * 根据供货商名查重
	 * @return
	 */
	public SupplierBean selectSupplierByName(){
		SupplierBean bean =supService.selectSupByName(supBean.getSupplier());
		return bean;
	}
	/**
	 * 根据供货商名和id查重
	 * @return
	 */
	public SupplierBean selectSupplierByNameAndSupplierId(){
		SupplierBean bean =supService.selectSupByNameAndSupId(supBean.getSupplier(),supBean.getSupplierId());
		return bean;
	}
	/**
	 * 根据供货商编号查重
	 * @return
	 */
	public SupplierBean selectSupplierBySuppliercode(){
		SupplierBean bean =supService.selectSupBySupcode(supBean.getSupplierCode());
		return bean;
	}
	/**
	 * ajax校验供货商名是否可用
	 */
	public String validateName() {
		SupplierBean bean =supService.selectSupByName(supBean.getSupplier());
		if(null == bean) {
			mess = "供货商名可用";
		} else if(null != bean){
			mess = "供货商名不可用";
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
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
}
