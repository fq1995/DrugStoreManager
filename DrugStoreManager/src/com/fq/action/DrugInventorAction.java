package com.fq.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
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
import com.fq.util.DownloadUtil;
import com.fq.util.PageModel;
import com.opensymphony.xwork2.ModelDriven;

@Controller("drugInventorAction")
@Scope("prototype")
public class DrugInventorAction extends BaseAction implements ModelDriven<InventoriesBean>,RequestAware{
	//药品库存管理
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
	
	private HttpServletResponse response = ServletActionContext.getResponse();
	/**
	 * 药品库存分页
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
	 * 药品库存预警分页
	 * @return
	 */
	public String showWarn() {
		if(null == keyword){
			keyword="";
		}
		if(null != keyword){
			request.put("keyword", keyword);
		}
		
		if(currPage == null) {
			currPage = 1;
		}
		PageModel<InventoriesBean>  page = inventorService.splitWarn(currPage, ConstantUtils.PAGESIZE, keyword);
		request.put("page", page);
		return "showInventor";
	}
	
	
	/**
	 * 跳转新增库存
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
	 * 新增库存药品
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
			inventorService.addInventor(drugBean,inventoriesBean,time);
			return "show";
		}
		request.put("message","药品名已被使用！");
		request.put("message2","药品编号已被使用！");
		return "addInventor";
		
	}
	/**
	 * 删除库存药品
	 */
	public String delInventor(){
		List<InventoriesBean> list = inventorService.showAllInventor(ids);
		inventorService.deleteAllInventor(list);
		return "show";
	}
	
	/**
	 * 编辑库存药品
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
	 * 修改库存药品 
	 */
	public String updateInventor(){
		List<DrugCategoryBean>  drugCategoryList = inventorService.selectCategory();
		List<DrugUnitBean> drugUnitList = inventorService.selectUnit();
		List<DosageformBean> dosageformList = inventorService.selectForm();
		
		request.put("drugCategoryList",drugCategoryList);
		request.put("drugUnitList",drugUnitList);
		request.put("dosageformList",dosageformList);
		if(null != selectInventorByDrugcode()){
			inventorService.updateInventor(inventoriesBean);
			return "show";
		}
		request.put("message","药品名已被使用！");
		return editInventor();
	}
	/**
	 * 跳转打印界面
	 */
	public String doPrint(){
		return "print";
	}
	
	public void print() throws IOException{
		
		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/") + "/xlsprint/";
		
		InputStream is = new FileInputStream(new File(path + "kucun.xls"));
		Workbook wb = new HSSFWorkbook(is);
		Sheet sheet = wb.getSheetAt(0);
		
		Date date =(Date) request.get("date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String inputDate = sdf.format(date);
		List<InventoriesBean> list = inventorService.selectByDate(date);
		
		
		Row nRow = null;   
		Cell nCell = null; 
		
		int rowNo = 0;	//行号
		int colNo = 1;  //列号
		
		//获取模板单元格样式
		nRow = sheet.getRow(2);
		
		//药品名
		nCell = nRow.getCell(1);
		CellStyle nameStyle = nCell.getCellStyle();
		
		//剂型名
		nCell = nRow.getCell(2);
		CellStyle formStyle = nCell.getCellStyle();
		
		//单元名
		nCell = nRow.getCell(3);
		CellStyle unitStyle = nCell.getCellStyle();
				
		//类别名
		nCell = nRow.getCell(4);
		CellStyle cateStyle = nCell.getCellStyle();
		
		//生产厂商
		nCell = nRow.getCell(5);
		CellStyle facturerStyle = nCell.getCellStyle();
		
		//批准文号
		nCell = nRow.getCell(6);
		CellStyle ApproStyle = nCell.getCellStyle();
		
		//修改人
		nCell = nRow.getCell(7);
		CellStyle ModifierStyle = nCell.getCellStyle();
		
		//入库时间
		nCell = nRow.getCell(8);
		CellStyle modifyTimeStyle = nCell.getCellStyle();
		
		//库存数量
		nCell = nRow.getCell(9);
		CellStyle stocknumberStyle = nCell.getCellStyle();
		
		//库存下限
		nCell = nRow.getCell(10);
		CellStyle stocklimitStyle = nCell.getCellStyle();
		
		//建单时间
		nCell = nRow.getCell(10);
		CellStyle dateStyle = nCell.getCellStyle();
		
		//大标题 合并单元格
		nRow = sheet.getRow(rowNo++);
		nCell = nRow.getCell(colNo);
		
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年")+"月份库存表");
		
		
		rowNo++;
		
		
		//处理数据
		for(int j = 0 ; j<list.size(); j++){
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			colNo = 1;
			InventoriesBean itBean = list.get(j); //获取库存对象
			
			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(itBean.getDrugBean().getDrugName());
			nCell.setCellStyle(nameStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(itBean.getDrugBean().getDosageformBean().getDosageform());
			nCell.setCellStyle(formStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(itBean.getDrugBean().getDrugUnitBean().getUnitname());
			nCell.setCellStyle(unitStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(itBean.getDrugBean().getDrugCategoryBean().getCategory());
			nCell.setCellStyle(cateStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(itBean.getDrugBean().getManufacturer());
			nCell.setCellStyle(facturerStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(itBean.getDrugBean().getApprovalNumber());
			nCell.setCellStyle(ApproStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(itBean.getDrugBean().getModifier());
			nCell.setCellStyle(ModifierStyle);
			
			nCell = nRow.createCell(colNo++);
			String time = sdf1.format(itBean.getDrugBean().getModifyTime());
			nCell.setCellValue(time);
			nCell.setCellStyle(modifyTimeStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(itBean.getStocknumber());
			nCell.setCellStyle(stocknumberStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(itBean.getStocklimit());
			nCell.setCellStyle(stocklimitStyle);
			
			
			nCell = nRow.createCell(colNo++);
			String result = sdf1.format(itBean.getDate());
	        nCell.setCellValue(result);
	        nCell.setCellStyle(dateStyle);
			
		}
		
		//下载
		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		du.download(os, response, inputDate.replaceFirst("-0", "-").replaceFirst("-", "年")+"月份库存表.xls");
		
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
