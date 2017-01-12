package com.fq.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.DrugUnitBean;
import com.fq.service.DrugUnitService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 
 * @author fu
 * 药品单位管理
 */
@Controller("drugUnitAction")
@Scope("prototype")
public class DrugUnitAction extends BaseAction implements ModelDriven<DrugUnitBean>, RequestAware {
	// 药品单位管理
	private Map<String, Object> session;
	private Map<String, Object> request;
	private boolean flag;
	private Integer currPage;

	private String ids;
	private String id;
	private String time;
	private String mess;
	private String keyword;
	private String name;

	@Autowired
	private DrugUnitService drugUnitService;
	private DrugUnitBean drugUnitBean = new DrugUnitBean();

	/**
	 * 药品单位分页
	 * 
	 * @return
	 */
	public String showUnit() {
		if (null == keyword) {
			keyword = "";
		}
		if (null != keyword) {
			request.put("keyword", keyword);
		}

		if (currPage == null) {
			currPage = 1;
		}
		PageModel<DrugUnitBean> page = drugUnitService.splitDrugUnit(currPage, ConstantUtils.PAGESIZE, keyword);
		request.put("page", page);
		return "showUnit";
	}

	/**
	 * 跳转新增
	 */
	public String doaddUnit() {

		return "doadd";
	}

	/**
	 * 新增药品单位
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUnit() {

		if (null == selectDrugUnitByName()) {
			drugUnitService.addDrugUnit(drugUnitBean);
			return "show";
		}
		request.put("message", "药品单位名已被使用！");
		return "doadd";

	}

	/**
	 * 删除药品单位
	 */
	public String delUnit() {
		List<DrugUnitBean> listDrug = drugUnitService.showAllDrugUnit(ids);
		drugUnitService.deleteAllDrugUnit(listDrug);
		return "show";
	}

	/**
	 * 编辑药品单位
	 */
	public String editUnit() {
		DrugUnitBean drugUnitBean1 = new DrugUnitBean();
		if (null != id) {
			System.out.println(id);
			drugUnitBean1 = drugUnitService.selectById(id);
		}
		if (null != drugUnitBean1) {
			request.put("unit", drugUnitBean1);
		}
		return "edit";
	}

	/**
	 * 修改药品单位
	 */
	public String updateUnit() {

		if (null == selectDrugUnitByName()) {
			drugUnitService.updateDrugUnit(drugUnitBean);
			return "show";
		}
		request.put("message", "药品单位名已被使用！");
		return editUnit();
	}

	/**
	 * 根据药品单位名查重
	 * 
	 * @return
	 */
	public DrugUnitBean selectDrugUnitByName() {
		DrugUnitBean bean = drugUnitService.selectDrugUnitByName(drugUnitBean.getUnitname());
		return bean;
	}

	/**
	 * 根据药品单位名和id查重
	 * 
	 * @return
	 */
	public DrugUnitBean selectDrugUnitByNameAndDrugUnitId() {
		DrugUnitBean bean = drugUnitService.selectDrugUnitByNameAndDrugUnitId(drugUnitBean.getUnitname(),
				drugUnitBean.getUnitnameId());
		return bean;
	}

	/**
	 * ajax校验药品单位名是否可用
	 */
	public String validateName() {
		if(null != name){
			DrugUnitBean bean = drugUnitService.selectDrugUnitByName(name);
			if (null == bean) {
				mess = "药品单位名可用";
			} else if (null != bean) {
				mess = "药品单位名不可用";
			}
			return "ajax_verifyName";
		}
		return "doadd";
		
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public DrugUnitBean getModel() {
		return drugUnitBean;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
