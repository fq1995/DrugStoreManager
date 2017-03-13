package com.fq.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
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
import com.fq.util.StrUtils;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 
 * @author fu
 * 药品管理
 */
@Controller("drugAction")
@Scope("prototype")
public class DrugAction extends BaseAction implements ModelDriven<DrugBean>, RequestAware {
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
	private File photo;
	private String photoFileName;
	private String newFileName;
	private String photoContentType;
	private String path; 
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
	 * 剂型、单位、类别
	 */
	public void before(){
		List<DrugCategoryBean> drugCategoryList = drugService.selectCategory();
		List<DrugUnitBean> drugUnitList = drugService.selectUnit();
		List<DosageformBean> dosageformList = drugService.selectForm();

		request.put("drugCategoryList", drugCategoryList);
		request.put("drugUnitList", drugUnitList);
		request.put("dosageformList", dosageformList);
	}
	/**
	 * 多条件查询回显数据
	 */
	public void after(){
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
	}
	/**
	 * 跳转到多条件查询
	 */
	public String doShowDrugByOptions() {
		before();
		return "showDrugByOptions";
	}

	/**
	 * 多条件查询
	 * 
	 */
	public String showDrugByOptions() {
		before();
		after();
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
		before();
		drugCode = drugService.select();
		request.put("drugCode", drugCode);
		return "doadd";
	}
	/**
	 * 上传图片
	 */
	public String addPicture(){
		if(photo != null){
			//利用程序建立upload文件
			/*String path=ServletActionContext.getServletContext().getRealPath("/upload");*/
			String path= ConstantUtils.picturepath;
			
			request.put("path", path);
			File fupload = new File(path);
			if(!fupload.exists()){
				fupload.mkdirs();
			}
			//指定本地文件的名字
			//重命名
			if(photoFileName==null){
				mess="上传文件失败";
				return "doadd";
			}
			newFileName = StrUtils.getNewFileName()+photoFileName.substring(photoFileName.indexOf("."));
			
			File newFile = new File(fupload,newFileName);
			try {
				FileUtils.copyFile(photo, newFile);
				request.put("photoFileName", photoFileName);
				request.put("newFileName", newFileName);
				 
				drugBean.setOldName(photoFileName);
				drugBean.setNewName(newFileName);
				mess="上传成功";
			} catch (IOException e) {
				mess="上传失敗"; 
				e.printStackTrace();
			}
		}
		return "";
		
		
	}
	
	/**
	 * 新增药品
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addDrug() {
		before();
		if (null == selectDrugByName() && null == selectDrugByDrugcode()) {
			addPicture();
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
		before();
		if (null != drugBean1) {
			request.put("drug", drugBean1);
		}
		return "edit";
	}

	/**
	 * 修改药品
	 */
	public String updateDrug() {
		before();
		if (null == selectDrugByNameAndDrugId()) {
			addPicture();
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
	
	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
