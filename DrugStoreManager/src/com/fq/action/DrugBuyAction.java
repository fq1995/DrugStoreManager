package com.fq.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.DosageformBean;
import com.fq.po.DrugBuy;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
import com.fq.service.DrugBuyService;
import com.fq.service.DrugService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 
 * @author fu
 * 药品管理
 */
@Controller("drugBuyAction")
@Scope("prototype")
public class DrugBuyAction extends BaseAction implements ModelDriven<DrugBuy>, RequestAware {
	// 药品管理
	private Map<String, Object> session;
	private Map<String, Object> request;
	private boolean flag;
	private Integer currPage;
	
	private String param;
	private String ids;
	private String id;
	private String time;
	private String mess;
	private String keyword;
	private Integer drugCode;
	private String name;
	private String drugbuyCode;
	
	@Autowired
	private DrugBuyService drugbuyService;
	@Autowired
	private DrugService drugService;
	private DrugBuy drugbuyBean = new DrugBuy();

	/**
	 * 药品采购单分页
	 * 
	 * @return
	 */
	public String showBuy() {
		if (null == keyword) {
			keyword = "";
		}
		if (null != keyword) {
			request.put("keyword", keyword);
		}

		if (currPage == null) {
			currPage = 1;
		}
		PageModel<DrugBuy> page = drugbuyService.splitDrug(currPage, ConstantUtils.PAGESIZE, keyword);
		request.put("page", page);
		return "showbuy";
	}

	
	/**
	 * 跳转新增
	 */
	public String doaddBuy() {
		List<DrugCategoryBean> drugCategoryList = drugService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugService.selectUnit();
		List<DosageformBean> dosageformList = drugService.selectForm();
		drugCode = drugbuyService.select();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		StringBuilder sb = new StringBuilder();
		sb.append("1000").append(sdf.format(date));
		drugbuyCode = sb.toString();
				
		request.put("drugbuyCode", drugbuyCode);
		request.put("drugCode", drugCode);
		request.put("drugCategoryList", drugCategoryList);
		request.put("drugUnitList", drugUnitList);
		request.put("dosageformList", dosageformList);
		
		return "doadd";
	}
	
	/**
	 * 新增
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addBuy() {
		List<DrugCategoryBean> drugCategoryList = drugService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugService.selectUnit();
		List<DosageformBean> dosageformList = drugService.selectForm();

		request.put("drugCategoryList", drugCategoryList);
		request.put("drugUnitList", drugUnitList);
		request.put("dosageformList", dosageformList);
		
		if (null == selectDrugByName() && null == selectDrugByDrugcode()) {
			drugCode = drugbuyService.select();
			try {
				drugbuyService.addBuy(drugCode,drugbuyBean, time);
			} catch (Exception e) {
				System.out.println("时间转换错误");
				e.printStackTrace();
			}
			return "show";
		}
		request.put("message", "药品名已被使用！");
		request.put("message2", "药品编号已被使用！");
		return "addbuy";

	}

	/**
	 * 删除
	 */
	public String delBuy() {
		List<DrugBuy> listDrug = drugbuyService.showAllDrug(ids);
		drugbuyService.deleteAllDrug(listDrug);
		return "show";
	}

	/**
	 * 编辑
	 */
	public String editBuy() {
		DrugBuy drugBean1 = drugbuyService.selectById(id);
		List<DrugCategoryBean> drugCategoryList = drugService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugService.selectUnit();
		List<DosageformBean> dosageformList = drugService.selectForm();

		request.put("drugCategoryList", drugCategoryList);
		request.put("drugUnitList", drugUnitList);
		request.put("dosageformList", dosageformList);
		if (null != drugBean1) {
		request.put("buy", drugBean1);
		}
		return "edit";
	}

	/**
	 * 修改
	 */
	public String updateBuy() {
		List<DrugCategoryBean> drugCategoryList = drugService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugService.selectUnit();
		List<DosageformBean> dosageformList = drugService.selectForm();

		request.put("drugCategoryList", drugCategoryList);
		request.put("drugUnitList", drugUnitList);
		request.put("dosageformList", dosageformList);
		if (null == selectDrugByNameAndDrugId()) {
			
			drugbuyService.updateDrug(drugbuyBean, time);
			return "show";
		}
		request.put("message", "药品名已被使用！");
		return editBuy();
	}

	/**
	 * 根据药品名查重
	 * 
	 * @return
	 */
	public DrugBuy selectDrugByName() {
		DrugBuy bean = drugbuyService.selectDrugByName(drugbuyBean.getDrugBean().getDrugName());
		return bean;
	}

	/**
	 * 根据药品名和id查重
	 * 
	 * @return
	 */
	public DrugBuy selectDrugByNameAndDrugId() {
		DrugBuy bean = drugbuyService.selectDrugByNameAndDrugId(drugbuyBean.getDrugBean().getDrugName(), drugbuyBean.getDrugBean().getDrugId());
		return bean;
	}

	/**
	 * 根据药品编号查重
	 * 
	 * @return
	 */
	public DrugBuy selectDrugByDrugcode() {
		DrugBuy bean = drugbuyService.selectDrugByDrugcode(drugbuyBean.getDrugBean().getDrugCode());
		return bean;
	}

	/**
	 * ajax校验药品名是否可用
	 */
	public String validateName() {
		if(null != name){
			DrugBuy bean = drugbuyService.selectDrugByName(name);
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
	public DrugBuy getModel() {
		return drugbuyBean;
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
	
	

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}


	public String getDrugbuyCode() {
		return drugbuyCode;
	}


	public void setDrugbuyCode(String drugbuyCode) {
		this.drugbuyCode = drugbuyCode;
	}


	public DrugBuyService getDrugbuyService() {
		return drugbuyService;
	}


	public void setDrugbuyService(DrugBuyService drugbuyService) {
		this.drugbuyService = drugbuyService;
	}


}
