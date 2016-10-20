package com.fq.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.EmployeeBean;
import com.fq.service.EmpService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("empAction")
@Scope("prototype")
public class EmpAction extends BaseAction implements ModelDriven<EmployeeBean>,RequestAware{
	//员工管理
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
	private EmpService empService;
	private EmployeeBean empBean = new EmployeeBean();
	
	/**
	 * 员工分页
	 * @return
	 */
	public String showEmp() {
		if(null == keyword){
			keyword="";
		}
		if(null != keyword){
			request.put("keyword", keyword);
		}
		
		if(currPage == null) {
			currPage = 1;
		}
		PageModel<EmployeeBean>  page = empService.splitEmp(currPage,ConstantUtils.PAGESIZE,keyword);
		request.put("page", page);

		return "showEmp";
	}
	/**
	 * 跳转添加界面
	 */
	public String doaddEmp(){
		return "doadd";
	}
	/**
	 * 新增员工
	 * @return
	 * @throws Exception 
	 */
	public String addEmp(){
		if(null == selectEmpByName() && null == selectEmpByEmpcode()){
			try {
				empService.addEmp(empBean);
			} catch (Exception e) {
				System.out.println("时间转换错误");
				e.printStackTrace();
			}
			return "show";
		}
		request.put("message","员工名已被使用！");
		request.put("message2","员工编号已被使用！");
		return "addEmp";
		
	}
	/**
	 * 删除员工
	 */
	public String delEmp(){
		List<EmployeeBean> listEmp = empService.showAllEmp(ids);
		empService.deleteAllEmp(listEmp);
		return "show";
	}
	/**
	 * 编辑员工
	 */
	public String editEmp(){
		EmployeeBean EmpBean1 = empService.selectById(id);
		if(null!=EmpBean1){
			request.put("emp",EmpBean1);
		}
		return "edit";
	}
		
	/**
	 * 修改员工 
	 */
	public String updateEmp(){
		if(null == selectEmpByNameAndEmpId()){
			empService.updateEmp(empBean);
			return "show";
		}
		request.put("message","员工名已被使用！");
		return editEmp();
	}
	/**
	 * 根据员工名查重
	 * @return
	 */
	public EmployeeBean selectEmpByName(){
		EmployeeBean bean =empService.selectEmpByName(empBean.getEmpName());
		return bean;
	}
	/**
	 * 根据员工名和id查重
	 * @return
	 */
	public EmployeeBean selectEmpByNameAndEmpId(){
		EmployeeBean bean =empService.selectEmpByNameAndEmpId(empBean.getEmpName(),empBean.getEmpId());
		return bean;
	}
	/**
	 * 根据员工编号查重
	 * @return
	 */
	public EmployeeBean selectEmpByEmpcode(){
		EmployeeBean bean =empService.selectEmpByEmpcode(empBean.getEmpCode());
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
	 * ajax校验员工名是否可用
	 */
	public String validateName() {
		EmployeeBean bean =empService.selectEmpByName(empBean.getEmpName());
		if(null == bean) {
			mess = "员工名可用";
		} else if(null != bean){
			mess = "员工名不可用";
		}
		return "ajax_verifyName";
	}
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	@Override
	public EmployeeBean getModel() {
		return empBean;
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
