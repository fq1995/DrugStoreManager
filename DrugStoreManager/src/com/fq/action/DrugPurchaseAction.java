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
import com.fq.po.DrugPurchaseBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
import com.fq.service.DrugPurchaseService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("drugPurchaseAction")
@Scope("prototype")
public class DrugPurchaseAction extends BaseAction implements ModelDriven<DrugPurchaseBean>,RequestAware{
	//药品进货管理
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
	private DrugPurchaseService drugPseService;
	private DrugPurchaseBean drugPseBean = new DrugPurchaseBean();
	private DrugBean drugBean = drugPseBean.getDrugBean();
	/**
	 * 药品进货分页
	 * @return
	 */
	public String showPurchase() {
		if(null == keyword){
			keyword="";
		}
		if(null != keyword){
			request.put("keyword", keyword);
		}
		
		if(currPage == null) {
			currPage = 1;
		}
		PageModel<DrugPurchaseBean>  page = drugPseService.splitPse(currPage, ConstantUtils.PAGESIZE, keyword);
		request.put("page", page);
		return "showPurchase";
	}
	/**
	 * 跳转新增
	 */
	public String doaddPurchase(){
		List<DrugCategoryBean>  drugCategoryList = drugPseService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugPseService.selectUnit();
		List<DosageformBean> dosageformList = drugPseService.selectForm();
		
		request.put("drugCategoryList",drugCategoryList);
		request.put("drugUnitList",drugUnitList);
		request.put("dosageformList",dosageformList);
		return "doadd";
	}
	/**
	 * 新增药品进货
	 * @return
	 * @throws Exception 
	 */
	/*public String addPurchase(){
		List<DrugCategoryBean>  drugCategoryList = drugPseService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugPseService.selectUnit();
		List<DosageformBean> dosageformList = drugPseService.selectForm();
		
		request.put("drugCategoryList",drugCategoryList);
		request.put("drugUnitList",drugUnitList);
		request.put("dosageformList",dosageformList);
		
		DosageformBean dfBean = drugBean.getDosageformBean();
		DrugCategoryBean dcBean = drugBean.getDrugCategoryBean();
		DrugUnitBean duBean = drugBean.getDrugUnitBean();	
		
		drugPseService.addPse(dfBean, dcBean, duBean, drugBean, drugPseBean, time);
		
		return "show";
		request.put("message","药品进货名已被使用！");
		request.put("message2","药品进货编号已被使用！");
		return "addPurchase";
		
	}*/
	/**
	 * 删除药品进货
	 */
	public String delPurchase(){
		List<DrugPurchaseBean> listDrug = drugPseService.showAllPse(ids);
		drugPseService.deleteAllPse(listDrug);
		return "show";
	}
	
	/**
	 * 编辑药品进货
	 */
	public String editPurchase(){
		DrugPurchaseBean drugPseBean = drugPseService.selectById(id);
		List<DrugCategoryBean>  drugCategoryList = drugPseService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugPseService.selectUnit();
		List<DosageformBean> dosageformList = drugPseService.selectForm();
		
		request.put("drugCategoryList",drugCategoryList);
		request.put("drugUnitList",drugUnitList);
		request.put("dosageformList",dosageformList);
		if(null!=drugPseBean){
			request.put("purchase",drugPseBean);
		}
		return "edit";
	}
		
	/**
	 * 修改药品进货 
	 */
	public String updatePurchase(){
		List<DrugCategoryBean>  drugCategoryList = drugPseService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugPseService.selectUnit();
		List<DosageformBean> dosageformList = drugPseService.selectForm();
		
		request.put("drugCategoryList",drugCategoryList);
		request.put("drugUnitList",drugUnitList);
		request.put("dosageformList",dosageformList);
		if(null == selectPurchaseByName()){
			drugPseService.updatePse(drugPseBean);
			return "show";
		}
		request.put("message","药品名已被使用！");
		return editPurchase();
	}
	/**
	 * 根据药品名查重
	 * @return
	 */
	public DrugPurchaseBean selectPurchaseByName(){
		DrugPurchaseBean bean =drugPseService.selectPseByName(drugPseBean.getDrugBean().getDrugName());
		return bean;
	}
	/**
	 * 根据药品编号查重
	 * @return
	 */
	public DrugPurchaseBean selectPurchaseByDrugcode(){
		DrugPurchaseBean bean =drugPseService.selectPseByDrugcode(drugPseBean.getDrugBean().getDrugCode());
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
		DrugPurchaseBean bean =drugPseService.selectPseByName(drugPseBean.getDrugBean().getDrugName());
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
	public DrugPurchaseBean getModel() {
		return drugPseBean;
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