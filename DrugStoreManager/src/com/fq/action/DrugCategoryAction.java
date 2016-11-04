package com.fq.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.DosageformBean;
import com.fq.po.DrugCategoryBean;
import com.fq.service.DrugCategoryService;
import com.fq.util.BaseAction;
import com.fq.util.ConstantUtils;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("drugCategoryAction")
@Scope("prototype")
public class DrugCategoryAction extends BaseAction implements ModelDriven<DrugCategoryBean>, RequestAware {
	// 药品类别管理
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
	private DrugCategoryService drugCategoryService;
	private DrugCategoryBean drugCategoryBean = new DrugCategoryBean();

	/**
	 * 药品类别分页
	 * 
	 * @return
	 */
	public String showCategory() {
		if (null == keyword) {
			keyword = "";
		}
		if (null != keyword) {
			request.put("keyword", keyword);
		}

		if (currPage == null) {
			currPage = 1;
		}
		PageModel<DrugCategoryBean> page = drugCategoryService.splitDrugCategory(currPage, ConstantUtils.PAGESIZE,
				keyword);
		request.put("page", page);
		return "showCategory";
	}

	/**
	 * 跳转新增
	 */
	public String doaddCategory() {

		return "doadd";
	}

	/**
	 * 新增药品类别
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addCategory() {

		if (null == selectDrugCateByName()) {
			drugCategoryService.addDrugCategory(drugCategoryBean);
			return "show";
		}
		request.put("message", "药品类别名已被使用！");
		return "addCategory";

	}

	/**
	 * 删除药品类别
	 */
	public String delCategory() {
		List<DrugCategoryBean> listDrug = drugCategoryService.showAllDrugCategory(ids);
		drugCategoryService.deleteAllDrugCategory(listDrug);
		return "show";
	}

	/**
	 * 编辑药品类别
	 */
	public String editCategory() {
		DrugCategoryBean drugCategoryBean1 = new DrugCategoryBean();
		if (null != id) {
			System.out.println(id);
			drugCategoryBean1 = drugCategoryService.selectById(id);
		}
		if (null != drugCategoryBean1) {
			request.put("category", drugCategoryBean1);
		}
		return "edit";
	}

	/**
	 * 修改药品类别
	 */
	public String updateCategory() {

		if (null == selectDrugCateByName()) {
			drugCategoryService.updateDrugCategory(drugCategoryBean);
			return "show";
		}
		request.put("message", "药品类别名已被使用！");
		return editCategory();
	}

	/**
	 * 根据药品类别名查重
	 * 
	 * @return
	 */
	public DrugCategoryBean selectDrugCateByName() {
		DrugCategoryBean bean = drugCategoryService.selectDrugCategoryByName(drugCategoryBean.getCategory());
		return bean;
	}

	/**
	 * 根据药品类别名和id查重
	 * 
	 * @return
	 */
	public DrugCategoryBean selectDrugCateByNameAndDrugUnitId() {
		DrugCategoryBean bean = drugCategoryService.selectDrugCategoryByNameAndId(drugCategoryBean.getCategory(),
				drugCategoryBean.getCategoryId());
		return bean;
	}

	/**
	 * ajax校验药品类别名是否可用
	 */
	public String validateName() {
		if(null != name){
			DrugCategoryBean bean = drugCategoryService.selectDrugCategoryByName(name);
			if (null == bean) {
				mess = "药品类别名可用";
			} else if (null != bean) {
				mess = "药品类别名不可用";
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
	public DrugCategoryBean getModel() {
		return drugCategoryBean;
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
