package com.fq.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.DosageformBean;
import com.fq.service.FormService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("dosageformAction")
@Scope("prototype")
public class DosageformAction extends BaseAction implements ModelDriven<DosageformBean>,RequestAware{
	private Map<String, Object> request;
	private Integer currPage;
	  
	private String ids;
	private String id;
	private String mess;
	private String keyword;
	
	@Autowired
	private FormService formService;
	private DosageformBean formBean = new DosageformBean();

	/**
	 * 分页
	 * @return
	 */
	public String showForm() {
		if(null == keyword){
			keyword="";
		}
		if(null != keyword){
			request.put("keyword", keyword);
		}
		if(currPage == null) {
			currPage = 1;
		}
		PageModel<DosageformBean>  page = formService.splitForm(currPage, ConstantUtils.PAGESIZE,keyword);
		request.put("page", page);
		return "showForm";
	}
	/**
	 * 跳转新增
	 */
	public String doaddForm(){
		return "doadd";
	}
	/**
	 * 新增药品剂型
	 * @return
	 *  
	 */
	public String addForm(){
		if(null == selectFormByName()){
			formService.addForm(formBean);
			return "show";
		}
		request.put("message","药品剂型名已被使用！");
		return "addForm";
		
	}
	/**
	 * 删除药品剂型
	 */
	public String delForm(){
		List<DosageformBean> Formlist = formService.showAllForm(ids);
		formService.deleteAllForm(Formlist);
		return "show";
	}
	/**
	 * 编辑药品剂型
	 */
	public String editForm(){
		DosageformBean FormBean1 = formService.selectById(id);
		if(null!=FormBean1){
			request.put("form",FormBean1);
		}
		return "edit";
	}
		
	/**
	 * 修改药品剂型 
	 */
	public String updateForm(){
		if(null == selectFormByName()){
			formService.updateForm(formBean);
			return "show";
		}
		request.put("message","药品剂型名已被使用！");
		return editForm();
	}
	/**
	 * 根据药品剂型名查重
	 * @return
	 */
	public DosageformBean selectFormByName(){
		DosageformBean bean =formService.selectFormByName(formBean.getDosageform());
		return bean;
	}
	/**
	 * 根据药品剂型名和药品剂型编号查重
	 * @return
	 */
	public DosageformBean selectFormByNameAndFormId(){
		DosageformBean bean =formService.selectFormByNameAndFormId(formBean.getDosageform(),formBean.getDosageformId());
		return bean;
	}
	
	
	/**
	 * ajax校验药品剂型名是否可用
	 */
	public String validateName() {
		DosageformBean bean =formService.selectFormByName(formBean.getDosageform());
		if(null == bean) {
			mess = "药品剂型名可用";
		} else if(null != bean){
			mess = "药品剂型名不可用";
		}
		return "ajax_verifyName";
	}
	
	
	@Override
	public void setSession(Map<String, Object> session) {
	}

	@Override
	public DosageformBean getModel() {
		return formBean;
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
