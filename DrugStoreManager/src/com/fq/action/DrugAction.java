package com.fq.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
import com.fq.service.DrugService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("drugAction")
@Scope("prototype")
public class DrugAction extends BaseAction implements ModelDriven<DrugBean>, RequestAware {
	// 药品管理
	private Map<String, Object> session;
	private Map<String, Object> request;
	private boolean flag;
	private Integer currPage;

	private String ids;
	private String id;
	private String time;
	private String mess;
	private String keyword;
	private Integer drugCode;
	private String name;
	@Autowired
	private DrugService drugService;
	private DrugBean drugBean = new DrugBean();

	/**
	 * 药品分页
	 * 
	 * @return
	 */
	public String showDrug() {
		if (null == keyword) {
			keyword = "";
		}
		if (null != keyword) {
			request.put("keyword", keyword);
		}

		if (currPage == null) {
			currPage = 1;
		}
		PageModel<DrugBean> page = drugService.splitDrug(currPage, ConstantUtils.PAGESIZE, keyword);
		request.put("page", page);
		return "showDrug";
	}

	/**
	 * 跳转到多条件查询
	 */
	public String doShowDrugByOptions() {
		List<DrugCategoryBean> drugCategoryList = drugService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugService.selectUnit();
		List<DosageformBean> dosageformList = drugService.selectForm();

		request.put("drugCategoryList", drugCategoryList);
		request.put("drugUnitList", drugUnitList);
		request.put("dosageformList", dosageformList);
		return "showDrugByOptions";
	}

	/**
	 * 多条件查询
	 * 
	 */
	public String showDrugByOptions() {
		List<DrugCategoryBean> drugCategoryList = drugService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugService.selectUnit();
		List<DosageformBean> dosageformList = drugService.selectForm();

		request.put("drugCategoryList", drugCategoryList);
		request.put("drugUnitList", drugUnitList);
		request.put("dosageformList", dosageformList);

		String drugName = (String) request.get("drugName");
		request.put("drugName", drugName);

		String modifier = (String) request.get("modifier");
		request.put("modifier", modifier);

		DrugUnitBean dub = drugService.selectUnitById(drugBean.getDrugUnitBean().getUnitnameId());
		request.put("unit", dub);

		DosageformBean dfb = drugService.selectFormById(drugBean.getDosageformBean().getDosageformId());
		request.put("dosageform", dfb);

		DrugCategoryBean dcb = drugService.selectCategoryById(drugBean.getDrugCategoryBean().getCategoryId());
		request.put("category", dcb);

		String manufacturer = (String) request.get("manufacturer");
		request.put("manufacturer", manufacturer);

		Date modifyTime = (Date) request.get("modifyTime");

		request.put("modifyTime", modifyTime);

		PageModel<DrugBean> page = drugService.splitDrug(currPage, ConstantUtils.PAGESIZE, drugBean.getDrugName(),
				drugBean.getDosageformBean().getDosageformId(), drugBean.getDrugUnitBean().getUnitnameId(),
				drugBean.getDrugCategoryBean().getCategoryId(), drugBean.getManufacturer(), drugBean.getModifyTime(),
				drugBean.getModifier());
		request.put("page", page);
		List<DrugBean> list = page.getList();
		request.put("list", list);
		return "showDrugByOptions";
	}

	/**
	 * 跳转新增
	 */
	public String doaddDrug() {
		List<DrugCategoryBean> drugCategoryList = drugService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugService.selectUnit();
		List<DosageformBean> dosageformList = drugService.selectForm();
		drugCode = drugService.select();

		request.put("drugCode", drugCode);
		request.put("drugCategoryList", drugCategoryList);
		request.put("drugUnitList", drugUnitList);
		request.put("dosageformList", dosageformList);
		
		return "doadd";
	}

	/**
	 * 新增药品
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addDrug() {
		List<DrugCategoryBean> drugCategoryList = drugService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugService.selectUnit();
		List<DosageformBean> dosageformList = drugService.selectForm();

		request.put("drugCategoryList", drugCategoryList);
		request.put("drugUnitList", drugUnitList);
		request.put("dosageformList", dosageformList);
		if (null == selectDrugByName() && null == selectDrugByDrugcode()) {
			drugCode = drugService.select();
			try {
				drugService.addDrug(drugCode,drugBean, time);
			} catch (Exception e) {
				System.out.println("时间转换错误");
				e.printStackTrace();
			}
			return "show";
		}
		request.put("message", "药品名已被使用！");
		request.put("message2", "药品编号已被使用！");
		return "addDrug";

	}

	/**
	 * 删除药品
	 */
	public String delDrug() {
		List<DrugBean> listDrug = drugService.showAllDrug(ids);
		drugService.deleteAllDrug(listDrug);
		return "show";
	}

	/**
	 * 编辑药品
	 */
	public String editDrug() {
		DrugBean drugBean1 = drugService.selectById(id);
		List<DrugCategoryBean> drugCategoryList = drugService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugService.selectUnit();
		List<DosageformBean> dosageformList = drugService.selectForm();

		request.put("drugCategoryList", drugCategoryList);
		request.put("drugUnitList", drugUnitList);
		request.put("dosageformList", dosageformList);
		if (null != drugBean1) {
			request.put("drug", drugBean1);
		}
		return "edit";
	}

	/**
	 * 修改药品
	 */
	public String updateDrug() {
		List<DrugCategoryBean> drugCategoryList = drugService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugService.selectUnit();
		List<DosageformBean> dosageformList = drugService.selectForm();

		request.put("drugCategoryList", drugCategoryList);
		request.put("drugUnitList", drugUnitList);
		request.put("dosageformList", dosageformList);
		if (null == selectDrugByNameAndDrugId()) {
			drugService.updateDrug(drugBean, time);
			return "show";
		}
		request.put("message", "药品名已被使用！");
		return editDrug();
	}

	/**
	 * 根据药品名查重
	 * 
	 * @return
	 */
	public DrugBean selectDrugByName() {
		DrugBean bean = drugService.selectDrugByName(drugBean.getDrugName());
		return bean;
	}

	/**
	 * 根据药品名和id查重
	 * 
	 * @return
	 */
	public DrugBean selectDrugByNameAndDrugId() {
		DrugBean bean = drugService.selectDrugByNameAndDrugId(drugBean.getDrugName(), drugBean.getDrugId());
		return bean;
	}

	/**
	 * 根据药品编号查重
	 * 
	 * @return
	 */
	public DrugBean selectDrugByDrugcode() {
		DrugBean bean = drugService.selectDrugByDrugcode(drugBean.getDrugCode());
		return bean;
	}

	/**
	 * ajax校验药品名是否可用
	 */
	public String validateName() {
		if(null != name){
			DrugBean bean = drugService.selectDrugByName(name);
			if (null == bean) {
				mess = "药品名可用";
			} else if (null != bean) {
				mess = "药品名不可用";
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

	public Integer getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(Integer drugCode) {
		this.drugCode = drugCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
