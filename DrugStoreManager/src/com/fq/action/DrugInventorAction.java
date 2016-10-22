package com.fq.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
import com.fq.po.InventoriesBean;
import com.fq.service.DrugInventorService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("drugInventorAction")
@Scope("prototype")
public class DrugInventorAction extends BaseAction implements ModelDriven<InventoriesBean>,RequestAware{
	//药品管理
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
	private DrugInventorService inventorService;
	private InventoriesBean inventoriesBean = new InventoriesBean();
	private DrugBean drugBean = inventoriesBean.getDrugBean();
	
	/**
	 * 药品分页
	 * @return
	 */
	public String showInventor() {
		if(null == keyword){
			keyword="";
		}
		if(null != keyword){
			request.put("keyword", keyword);
		}
		
		if(currPage == null) {
			currPage = 1;
		}
		PageModel<InventoriesBean>  page = inventorService.splitInventor(currPage, ConstantUtils.PAGESIZE, keyword);
		request.put("page", page);
		return "showInventor";
	}
	/**
	 * 跳转新增
	 */
	public String doaddInventor(){
		List<DrugCategoryBean>  drugCategoryList = inventorService.selectCategory();
		List<DrugUnitBean> drugUnitList = inventorService.selectUnit();
		List<DosageformBean> dosageformList = inventorService.selectForm();
		
		request.put("drugCategoryList",drugCategoryList);
		request.put("drugUnitList",drugUnitList);
		request.put("dosageformList",dosageformList);
		return "doadd";
	}
	/**
	 * 新增药品
	 * @return
	 * @throws Exception 
	 */
	public String addInventor(){
		List<DrugCategoryBean>  drugCategoryList = inventorService.selectCategory();
		List<DrugUnitBean> drugUnitList = inventorService.selectUnit();
		List<DosageformBean> dosageformList = inventorService.selectForm();
		
		request.put("drugCategoryList",drugCategoryList);
		request.put("drugUnitList",drugUnitList);
		request.put("dosageformList",dosageformList);
		if(null == selectInventorByName() && null == selectInventorByDrugcode()){
			
			try {
				inventorService.addInventor(drugBean,inventoriesBean,time);
			} catch (Exception e) {
				System.out.println("时间转换错误");
				e.printStackTrace();
			}
			return "show";
		}
		request.put("message","药品名已被使用！");
		request.put("message2","药品编号已被使用！");
		return "addInventor";
		
	}
	/**
	 * 删除药品
	 */
	public String delInventor(){
		List<InventoriesBean> list = inventorService.showAllInventor(ids);
		inventorService.deleteAllInventor(list);
		return "show";
	}
	
	/**
	 * 编辑药品
	 */
	public String editInventor(){
		InventoriesBean bean1 = inventorService.selectById(id);
		List<DrugCategoryBean>  drugCategoryList = inventorService.selectCategory();
		List<DrugUnitBean> drugUnitList = inventorService.selectUnit();
		List<DosageformBean> dosageformList = inventorService.selectForm();
		
		request.put("drugCategoryList",drugCategoryList);
		request.put("drugUnitList",drugUnitList);
		request.put("dosageformList",dosageformList);
		if(null != bean1){
			request.put("inventor",bean1);
		}
		return "edit";
	}
		
	/**
	 * 修改药品 
	 */
	public String updateInventor(){
		List<DrugCategoryBean>  drugCategoryList = inventorService.selectCategory();
		List<DrugUnitBean> drugUnitList = inventorService.selectUnit();
		List<DosageformBean> dosageformList = inventorService.selectForm();
		
		request.put("drugCategoryList",drugCategoryList);
		request.put("drugUnitList",drugUnitList);
		request.put("dosageformList",dosageformList);
		if(null == selectInventorByDrugcode()){
			inventorService.updateInventor(inventoriesBean,time);
			return "show";
		}
		request.put("message","药品名已被使用！");
		return editInventor();
	}
	/**
	 * 根据药品名查重
	 * @return
	 */
	public InventoriesBean selectInventorByName(){
		InventoriesBean bean =inventorService.selectInventorByName(inventoriesBean.getDrugBean().getDrugName());
		return bean;
	}
	
	/**
	 * 根据药品编号查重
	 * @return
	 */
	public InventoriesBean selectInventorByDrugcode(){
		InventoriesBean bean =inventorService.selectInventorByDrugcode(inventoriesBean.getDrugBean().getDrugCode());
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
	 * ajax校验药品名是否可用
	 */
	public String validateName() {
		InventoriesBean bean =inventorService.selectInventorByName(inventoriesBean.getDrugBean().getDrugName());
		if(null == bean) {
			mess = "药品名可用";
		} else if(null != bean){
			mess = "药品名不可用";
		}
		return "ajax_verifyName";
	}
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	@Override
	public InventoriesBean getModel() {
		return inventoriesBean;
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
