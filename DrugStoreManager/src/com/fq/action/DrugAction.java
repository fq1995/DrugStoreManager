package com.fq.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.DrugBean;
import com.fq.service.DrugService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("drugAction")
@Scope("prototype")
public class DrugAction extends BaseAction implements ModelDriven<DrugBean>,RequestAware{
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
	private DrugService drugService;
	private DrugBean drugBean = new DrugBean();
	
	/**
	 * 药品分页
	 * @return
	 */
	public String showDrug() {
		if(null == keyword){
			keyword="";
		}
		if(currPage == null) {
			currPage = 1;
		}
		PageModel<DrugBean>  page = drugService.splitDrug(currPage, ConstantUtils.PAGESIZE, keyword);
		request.put("page", page);

		return "showDrug";
	}
	/**
	 * 跳转新增
	 */
	public String doaddDrug(){
		return "doadd";
	}
	/**
	 * 新增药品
	 * @return
	 * @throws Exception 
	 */
	public String addDrug(){
		if(null == selectDrugByName() && null == selectDrugByDrugcode()){
			try {
				drugService.addDrug(drugBean,time);
			} catch (Exception e) {
				System.out.println("时间转换错误");
				e.printStackTrace();
			}
			return "show";
		}
		request.put("message","药品名已被使用！");
		request.put("message2","药品编号已被使用！");
		return "addDrug";
		
	}
	/**
	 * 删除药品
	 */
	public String delDrug(){
		List<DrugBean> listDrug = drugService.showAllDrug(ids);
		drugService.deleteAllDrug(listDrug);
		return "show";
	}
	/**
	 * 编辑药品
	 */
	public String editDrug(){
		DrugBean drugBean1 = drugService.selectById(id);
		if(null!=drugBean1){
			request.put("drug",drugBean1);
		}
		return "edit";
	}
		
	/**
	 * 修改药品 
	 */
	public String updateDrug(){
		if(null == selectDrugByNameAndDrugId()){
			drugService.updateDrug(drugBean,time);
			return "show";
		}
		request.put("message","药品名已被使用！");
		return editDrug();
	}
	/**
	 * 根据药品名查重
	 * @return
	 */
	public DrugBean selectDrugByName(){
		DrugBean bean =drugService.selectDrugByName(drugBean.getDrugName());
		return bean;
	}
	/**
	 * 根据药品名和id查重
	 * @return
	 */
	public DrugBean selectDrugByNameAndDrugId(){
		DrugBean bean =drugService.selectDrugByNameAndDrugId(drugBean.getDrugName(),drugBean.getDrugId());
		return bean;
	}
	/**
	 * 根据药品编号查重
	 * @return
	 */
	public DrugBean selectDrugByDrugcode(){
		DrugBean bean =drugService.selectDrugByDrugcode(drugBean.getDrugCode());
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
		DrugBean bean =drugService.selectDrugByName(drugBean.getDrugName());
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
	public DrugBean getModel() {
		return drugBean;
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
