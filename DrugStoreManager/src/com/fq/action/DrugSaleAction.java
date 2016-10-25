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
import com.fq.po.DrugSalesBean;
import com.fq.po.DrugUnitBean;
import com.fq.po.MemberBean;
import com.fq.po.UserBean;
import com.fq.service.DrugSaleService;
import com.fq.service.DrugService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("drugSaleAction")
@Scope("prototype")
public class DrugSaleAction extends BaseAction implements ModelDriven<DrugSalesBean>,RequestAware{
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
	private String drugId;
	
	
	@Autowired
	private DrugSaleService drugSaleService;
	@Autowired
	private DrugService drugService; 
	private DrugSalesBean salesBean = new DrugSalesBean();
	private DrugBean drugBean = salesBean.getDrugBean();
	
	/**
	 * 药品分页
	 * @return
	 */
	public String showSale() {
		if(null == keyword){
			keyword="";
		}
		if(null != keyword){
			request.put("keyword", keyword);
		}
		
		if(currPage == null) {
			currPage = 1;
		}
 		PageModel<DrugSalesBean>  page = drugSaleService.splitSale(currPage, ConstantUtils.PAGESIZE, keyword);
		request.put("page", page);
		return "showSale";
	}
	/**
	 * 跳转新增
	 */
	public String doaddSale(){
		List<MemberBean> memberList = drugSaleService.selectMember();
		List<UserBean> userList = drugSaleService.selectUser();
		List<DrugBean> drugList = drugSaleService.selectDrug();
		List<DrugCategoryBean>  drugCategoryList = drugSaleService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugSaleService.selectUnit();
		List<DosageformBean> dosageformList = drugSaleService.selectForm();
		
		request.put("userList", userList);
		request.put("memberList", memberList);
		request.put("drugList", drugList);
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
	public String addSale(){
		List<UserBean> userList = drugSaleService.selectUser();
		List<MemberBean> memberList = drugSaleService.selectMember();
		List<DrugBean> drugList = drugSaleService.selectDrug();
		List<DrugCategoryBean>  drugCategoryList = drugSaleService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugSaleService.selectUnit();
		List<DosageformBean> dosageformList = drugSaleService.selectForm();
		
		request.put("userList", userList);
		request.put("memberList", memberList);
		request.put("drugList", drugList);
		request.put("drugCategoryList",drugCategoryList);
		request.put("drugUnitList",drugUnitList);
		request.put("dosageformList",dosageformList);
		
			
		try {
			drugSaleService.addSale(drugBean,salesBean,time);
		} catch (Exception e) {
			System.out.println("时间转换错误");
			e.printStackTrace();
		}
		return "addSale";
		
	}
	/**
	 * 删除药品
	 */
	public String delInventor(){
		List<DrugSalesBean> list = drugSaleService.showAllSale(ids);
		drugSaleService.deleteAllSale(list);
		return "show";
	}
	
	/**
	 * 编辑药品
	 */
	public String editSale(){
		DrugSalesBean bean1 = drugSaleService.selectById(id);
		List<UserBean> userList = drugSaleService.selectUser();
		List<MemberBean> memberList = drugSaleService.selectMember();
		List<DrugBean> drugList = drugSaleService.selectDrug();
		List<DrugCategoryBean>  drugCategoryList = drugSaleService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugSaleService.selectUnit();
		List<DosageformBean> dosageformList = drugSaleService.selectForm();
		
		request.put("userList", userList);
		request.put("memberList", memberList);
		request.put("drugList", drugList);
		request.put("drugCategoryList",drugCategoryList);
		request.put("drugUnitList",drugUnitList);
		request.put("dosageformList",dosageformList);
		if(null != bean1){
			request.put("sale",bean1);
		}
		return "edit";
	}
		
	/**
	 * 修改药品 
	 */
	public String updateSale(){
		List<UserBean> userList = drugSaleService.selectUser();
		List<MemberBean> memberList = drugSaleService.selectMember();
		List<DrugBean> drugList = drugSaleService.selectDrug();
		List<DrugCategoryBean>  drugCategoryList = drugSaleService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugSaleService.selectUnit();
		List<DosageformBean> dosageformList = drugSaleService.selectForm();
		
		request.put("userList", userList);
		request.put("memberList", memberList);
		request.put("drugList", drugList);
		request.put("drugCategoryList",drugCategoryList);
		request.put("drugUnitList",drugUnitList);
		request.put("dosageformList",dosageformList);
		if(null == selectSaleByDrugcode()){
			drugSaleService.updateSale(salesBean,time);
			return "show";
		}
		request.put("message","药品名已被使用！");
		return editSale();
	}
	
	/*public void selectDrugByName(){
		System.out.println(drugId);
		HttpServletRequest request = ServletActionContext.getRequest();
		String drugId = (String)request.getParameter("drugId");
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("code");
		DrugSalesBean bean = drugSaleService.selectSaleByDrugId(drugId);
		Double salePrice = bean.getSalepeice();
		Double memberPrice = bean.getMemberprice();
		request.setAttribute("salePrice", salePrice);
		request.setAttribute("memberPrice", memberPrice);
		
	}*/
	/**
	 * 根据药品名查重
	 * @return
	 */
	public DrugSalesBean selectSaleByName(){
		DrugSalesBean bean =drugSaleService.selectSaleByName(salesBean.getDrugBean().getDrugName());
		return bean;
	}
	
	/**
	 * 根据药品编号查重
	 * @return
	 */
	public DrugSalesBean selectSaleByDrugcode(){
		DrugSalesBean bean =drugSaleService.selectSaleByDrugcode(salesBean.getDrugBean().getDrugCode());
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
		DrugSalesBean bean =drugSaleService.selectSaleByName(salesBean.getDrugBean().getDrugName());
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
	public DrugSalesBean getModel() {
		return salesBean;
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
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
}
