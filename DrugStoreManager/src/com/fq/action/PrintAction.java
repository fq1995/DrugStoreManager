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

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fq.po.DrugBean;
import com.fq.po.DrugPurchaseBean;
import com.fq.po.DrugSalesBean;
import com.fq.po.EmployeeBean;
import com.fq.po.InventoriesBean;
import com.fq.po.MemberBean;
import com.fq.po.SupplierBean;
import com.fq.po.UserBean;
import com.fq.service.DrugInventorService;
import com.fq.service.DrugPurchaseService;
import com.fq.service.DrugSaleService;
import com.fq.service.DrugService;
import com.fq.service.EmpService;
import com.fq.service.MemberService;
import com.fq.service.SupplierService;
import com.fq.service.UserService;
import com.fq.util.DownloadUtil;

@Controller("printAction")
@Scope("prototype")
public class PrintAction implements RequestAware {

	private Map<String, Object> request;
	@Autowired
	private DrugInventorService inventorService;
	@Autowired
	private DrugService drugService;
	@Autowired
	private EmpService empService;
	@Autowired
	private UserService userService;
	@Autowired
	private DrugSaleService drugSaleService;
	@Autowired
	private DrugPurchaseService drugPurchaseService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private SupplierService supplierService;
	private HttpServletResponse response = ServletActionContext.getResponse();
	private Date date;

	// 打印库存
	public void printKucun() throws IOException {
		Date date = (Date) request.get("date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String inputDate = sdf.format(date);
		List<InventoriesBean> list = inventorService.selectByDate(date);

		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")
				+ "/xlsprint/";
		InputStream is = new FileInputStream(new File(path + "kucun.xls"));
		Workbook wb = new HSSFWorkbook(is);
		Sheet sheet = wb.getSheetAt(0);
		Row nRow = null;
		Cell nCell = null;

		int rowNo = 0; // 行号
		int colNo = 1; // 列号

		// 获取模板单元格样式
		nRow = sheet.getRow(2);

		// 药品名
		nCell = nRow.getCell(1);
		CellStyle nameStyle = nCell.getCellStyle();

		// 剂型名
		nCell = nRow.getCell(2);
		CellStyle formStyle = nCell.getCellStyle();

		// 单元名
		nCell = nRow.getCell(3);
		CellStyle unitStyle = nCell.getCellStyle();

		// 类别名
		nCell = nRow.getCell(4);
		CellStyle cateStyle = nCell.getCellStyle();

		// 生产厂商
		nCell = nRow.getCell(5);
		CellStyle facturerStyle = nCell.getCellStyle();

		// 批准文号
		nCell = nRow.getCell(6);
		CellStyle ApproStyle = nCell.getCellStyle();

		// 修改人
		nCell = nRow.getCell(7);
		CellStyle ModifierStyle = nCell.getCellStyle();

		// 入库时间
		nCell = nRow.getCell(8);
		CellStyle modifyTimeStyle = nCell.getCellStyle();

		// 库存数量
		nCell = nRow.getCell(9);
		CellStyle stocknumberStyle = nCell.getCellStyle();

		// 库存下限
		nCell = nRow.getCell(10);
		CellStyle stocklimitStyle = nCell.getCellStyle();

		// 建单时间
		nCell = nRow.getCell(10);
		CellStyle dateStyle = nCell.getCellStyle();

		// 大标题 合并单元格
		nRow = sheet.getRow(rowNo++);
		nCell = nRow.getCell(colNo);
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份库存表");
		rowNo++;

		// 处理数据
		for (int j = 0; j < list.size(); j++) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			colNo = 1;
			InventoriesBean itBean = list.get(j); // 获取库存对象

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

		// 下载
		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		du.download(os, response, inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份库存表.xls");
	}

	// 打印药品
	public void printDrug() throws IOException {
		date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String inputDate = sdf.format(date);
		List<DrugBean> list = drugService.show();

		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")
				+ "/xlsprint/";
		InputStream is = new FileInputStream(new File(path + "drug.xls"));
		Workbook wb = new HSSFWorkbook(is);
		Sheet sheet = wb.getSheetAt(0);
		Row nRow = null;
		Cell nCell = null;

		int rowNo = 0; // 行号
		int colNo = 1; // 列号

		// 获取模板单元格样式
		nRow = sheet.getRow(2);

		// 药品名
		nCell = nRow.getCell(1);
		CellStyle nameStyle = nCell.getCellStyle();

		// 剂型名
		nCell = nRow.getCell(2);
		CellStyle formStyle = nCell.getCellStyle();

		// 单位名
		nCell = nRow.getCell(3);
		CellStyle unitStyle = nCell.getCellStyle();

		// 类别名
		nCell = nRow.getCell(4);
		CellStyle cateStyle = nCell.getCellStyle();

		// 生产厂商
		nCell = nRow.getCell(5);
		CellStyle facturerStyle = nCell.getCellStyle();

		// 批准文号
		nCell = nRow.getCell(6);
		CellStyle ApproStyle = nCell.getCellStyle();

		// 修改人
		nCell = nRow.getCell(7);
		CellStyle ModifierStyle = nCell.getCellStyle();

		// 入库时间
		nCell = nRow.getCell(8);
		CellStyle modifyTimeStyle = nCell.getCellStyle();

		// 大标题 合并单元格
		nRow = sheet.getRow(rowNo++);
		nCell = nRow.getCell(colNo);
		nCell.setCellValue("药品表");
		rowNo++;

		// 处理数据
		for (int j = 0; j < list.size(); j++) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			colNo = 1;
			DrugBean bean = list.get(j); // 获取库存对象

			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugName());
			nCell.setCellStyle(nameStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDosageformBean().getDosageform());
			nCell.setCellStyle(formStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugUnitBean().getUnitname());
			nCell.setCellStyle(unitStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugCategoryBean().getCategory());
			nCell.setCellStyle(cateStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getManufacturer());
			nCell.setCellStyle(facturerStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getApprovalNumber());
			nCell.setCellStyle(ApproStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getModifier());
			nCell.setCellStyle(ModifierStyle);

			nCell = nRow.createCell(colNo++);
			String time = sdf1.format(bean.getModifyTime());
			nCell.setCellValue(time);
			nCell.setCellStyle(modifyTimeStyle);

		}

		// 下载
		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		du.download(os, response, "药品表.xls");

	}

	// 打印员工
	public void printEmp() throws IOException {
		date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String inputDate = sdf.format(date);
		List<EmployeeBean> list = empService.show();

		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")
				+ "/xlsprint/";
		InputStream is = new FileInputStream(new File(path + "emp.xls"));
		Workbook wb = new HSSFWorkbook(is);
		Sheet sheet = wb.getSheetAt(0);
		Row nRow = null;
		Cell nCell = null;

		int rowNo = 0; // 行号
		int colNo = 1; // 列号

		// 获取模板单元格样式
		nRow = sheet.getRow(2);

		// 员工编号
		nCell = nRow.getCell(1);
		CellStyle codeStyle = nCell.getCellStyle();

		// 员工名
		nCell = nRow.getCell(2);
		CellStyle nameStyle = nCell.getCellStyle();

		// 员工性别
		nCell = nRow.getCell(3);
		CellStyle sexStyle = nCell.getCellStyle();

		// 员工年龄
		nCell = nRow.getCell(4);
		CellStyle ageStyle = nCell.getCellStyle();

		// 员工职位
		nCell = nRow.getCell(5);
		CellStyle titleStyle = nCell.getCellStyle();

		// 员工电话
		nCell = nRow.getCell(6);
		CellStyle telStyle = nCell.getCellStyle();

		// 员工开始工作时间
		nCell = nRow.getCell(7);
		CellStyle dateStyle = nCell.getCellStyle();

		// 大标题 合并单元格
		nRow = sheet.getRow(rowNo++);
		nCell = nRow.getCell(colNo);
		nCell.setCellValue("员工表");
		rowNo++;

		// 处理数据
		for (int j = 0; j < list.size(); j++) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			colNo = 1;
			EmployeeBean bean = list.get(j); // 获取库存对象

			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getEmpCode());
			nCell.setCellStyle(codeStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getEmpName());
			nCell.setCellStyle(nameStyle);

			nCell = nRow.createCell(colNo++);
			if ("1".equals(bean.getSex())) {
				nCell.setCellValue("男");
			} else {
				nCell.setCellValue("女");
			}
			nCell.setCellStyle(sexStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getAge());
			nCell.setCellStyle(ageStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getTitle());
			nCell.setCellStyle(titleStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getTel());
			nCell.setCellStyle(telStyle);

			nCell = nRow.createCell(colNo++);
			String time = sdf1.format(bean.getStartdate());
			nCell.setCellValue(time);
			nCell.setCellStyle(dateStyle);

		}

		// 下载
		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		du.download(os, response, inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份员工表.xls");

	}

	// 打印用户
	public void printUser() throws IOException {
		date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String inputDate = sdf.format(date);
		List<UserBean> list = userService.show();

		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")
				+ "/xlsprint/";
		InputStream is = new FileInputStream(new File(path + "user.xls"));
		Workbook wb = new HSSFWorkbook(is);
		Sheet sheet = wb.getSheetAt(0);
		Row nRow = null;
		Cell nCell = null;

		int rowNo = 0; // 行号
		int colNo = 1; // 列号

		// 获取模板单元格样式
		nRow = sheet.getRow(2);

		// 用户编号
		nCell = nRow.getCell(1);
		CellStyle codeStyle = nCell.getCellStyle();

		// 用户姓名
		nCell = nRow.getCell(2);
		CellStyle nameStyle = nCell.getCellStyle();

		// 添加时间
		nCell = nRow.getCell(3);
		CellStyle dateStyle = nCell.getCellStyle();

		// 审核
		nCell = nRow.getCell(3);
		CellStyle shenheStyle = nCell.getCellStyle();

		nRow = sheet.getRow(rowNo++);
		nCell = nRow.getCell(colNo);
		nCell.setCellValue("用户表");
		rowNo++;

		// 处理数据
		for (int j = 0; j < list.size(); j++) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			colNo = 1;
			UserBean bean = list.get(j); // 获取库存对象

			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getUserCode());
			nCell.setCellStyle(codeStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getUsername());
			nCell.setCellStyle(nameStyle);

			nCell = nRow.createCell(colNo++);
			String time = sdf1.format(bean.getAddtime());
			nCell.setCellValue(time);
			nCell.setCellStyle(dateStyle);

			nCell = nRow.createCell(colNo++);
			if (1 == bean.getStatus()) {
				nCell.setCellValue("已审核");
			} else {
				nCell.setCellValue("未审核");
			}
			nCell.setCellStyle(shenheStyle);
		}
		// 下载
		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		du.download(os, response, "用户表.xls");

	}

	// 打印供货商
	public void printSupplier() throws IOException {
		date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String inputDate = sdf.format(date);
		List<SupplierBean> list = supplierService.show();

		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")
				+ "/xlsprint/";
		InputStream is = new FileInputStream(new File(path + "supplier.xls"));
		Workbook wb = new HSSFWorkbook(is);
		Sheet sheet = wb.getSheetAt(0);
		Row nRow = null;
		Cell nCell = null;

		int rowNo = 0; // 行号
		int colNo = 1; // 列号

		// 获取模板单元格样式
		nRow = sheet.getRow(2);

		// 供货商编号
		nCell = nRow.getCell(1);
		CellStyle codeStyle = nCell.getCellStyle();

		// 供货商名
		nCell = nRow.getCell(2);
		CellStyle nameStyle = nCell.getCellStyle();

		// 联系人
		nCell = nRow.getCell(3);
		CellStyle supStyle = nCell.getCellStyle();


		// 电话
		nCell = nRow.getCell(4);
		CellStyle telStyle = nCell.getCellStyle();

		// 状态
		nCell = nRow.getCell(5);
		CellStyle stutasStyle = nCell.getCellStyle();


		nRow = sheet.getRow(rowNo++);
		nCell = nRow.getCell(colNo);
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份供货商表");
		rowNo++;

		// 处理数据
		for (int j = 0; j < list.size(); j++) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			colNo = 1;
			SupplierBean bean = list.get(j); // 获取库存对象

			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getSupplierCode());
			nCell.setCellStyle(codeStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getSupplier());
			nCell.setCellStyle(nameStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getName());
			nCell.setCellStyle(supStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getTel());
			nCell.setCellStyle(telStyle);

			nCell = nRow.createCell(colNo++);
			if("1".equals(bean.getStatus())){
				nCell.setCellValue("已审核");
			}else{
				nCell.setCellValue("未审核");
			}
			nCell.setCellStyle(stutasStyle);

		}
		// 下载
		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		du.download(os, response, "供货商表.xls");

	}

	// 打印会员
	public void printMember() throws IOException {
		date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String inputDate = sdf.format(date);
		List<MemberBean> list = memberService.show();

		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")
				+ "/xlsprint/";
		InputStream is = new FileInputStream(new File(path + "member.xls"));
		Workbook wb = new HSSFWorkbook(is);
		Sheet sheet = wb.getSheetAt(0);
		Row nRow = null;
		Cell nCell = null;

		int rowNo = 0; // 行号
		int colNo = 1; // 列号

		// 获取模板单元格样式
		nRow = sheet.getRow(2);

		// 会员编号
		nCell = nRow.getCell(1);
		CellStyle codeStyle = nCell.getCellStyle();

		// 会员姓名
		nCell = nRow.getCell(2);
		CellStyle nameStyle = nCell.getCellStyle();

		// 会员性别
		nCell = nRow.getCell(3);
		CellStyle sexStyle = nCell.getCellStyle();

		// 会员年龄
		nCell = nRow.getCell(4);
		CellStyle ageStyle = nCell.getCellStyle();

		// 会员等级
		nCell = nRow.getCell(5);
		CellStyle levelStyle = nCell.getCellStyle();

		// 会员电话
		nCell = nRow.getCell(6);
		CellStyle telStyle = nCell.getCellStyle();

		// 会员地址
		nCell = nRow.getCell(7);
		CellStyle addStyle = nCell.getCellStyle();

		// 会员积分
		nCell = nRow.getCell(6);
		CellStyle intStyle = nCell.getCellStyle();

		nRow = sheet.getRow(rowNo++);
		nCell = nRow.getCell(colNo);
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份会员表");
		rowNo++;

		// 处理数据
		for (int j = 0; j < list.size(); j++) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			colNo = 1;
			MemberBean bean = list.get(j); // 获取库存对象

			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getMemberCode());
			nCell.setCellStyle(codeStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getMemberName());
			nCell.setCellStyle(nameStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getAge());
			nCell.setCellStyle(ageStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getSex());
			nCell.setCellStyle(sexStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getMemberLevel());
			nCell.setCellStyle(levelStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getSuppliertel());
			nCell.setCellStyle(telStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getAddress());
			nCell.setCellStyle(addStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getIntegration());
			nCell.setCellStyle(intStyle);
		}
		// 下载
		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		du.download(os, response, "会员表.xls");

	}

	// 打印销售表
	public void printSale() throws IOException {
		Date date = (Date) request.get("date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String inputDate = sdf.format(date);
		List<DrugSalesBean> list = drugSaleService.show(date);

		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")
				+ "/xlsprint/";
		InputStream is = new FileInputStream(new File(path + "sale.xls"));
		Workbook wb = new HSSFWorkbook(is);
		Sheet sheet = wb.getSheetAt(0);
		Row nRow = null;
		Cell nCell = null;

		int rowNo = 0; // 行号
		int colNo = 1; // 列号

		// 获取模板单元格样式
		nRow = sheet.getRow(2);

		// 药品名
		nCell = nRow.getCell(1);
		CellStyle nameStyle = nCell.getCellStyle();

		// 剂型名
		nCell = nRow.getCell(2);
		CellStyle formStyle = nCell.getCellStyle();

		// 单位名
		nCell = nRow.getCell(3);
		CellStyle unitStyle = nCell.getCellStyle();

		// 类别名
		nCell = nRow.getCell(4);
		CellStyle cateStyle = nCell.getCellStyle();

		// 生产厂商
		nCell = nRow.getCell(5);
		CellStyle facturerStyle = nCell.getCellStyle();

		// 批准文号
		nCell = nRow.getCell(6);
		CellStyle ApproStyle = nCell.getCellStyle();

		// 销售价
		nCell = nRow.getCell(7);
		CellStyle salepriceStyle = nCell.getCellStyle();

		// 会员价
		nCell = nRow.getCell(8);
		CellStyle memStyle = nCell.getCellStyle();

		// 销售时间
		nCell = nRow.getCell(9);
		CellStyle saleTimeStyle = nCell.getCellStyle();

		// 总金额
		nCell = nRow.getCell(10);
		CellStyle saleamountStyle = nCell.getCellStyle();

		// 修改人
		nCell = nRow.getCell(11);
		CellStyle ModifierStyle = nCell.getCellStyle();

		nRow = sheet.getRow(rowNo++);
		nCell = nRow.getCell(colNo);
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份用户表");
		rowNo++;

		// 处理数据
		for (int j = 0; j < list.size(); j++) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			colNo = 1;
			DrugSalesBean bean = list.get(j); // 获取库存对象

			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getDrugName());
			nCell.setCellStyle(nameStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getDosageformBean().getDosageform());
			nCell.setCellStyle(formStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getDrugUnitBean().getUnitname());
			nCell.setCellStyle(unitStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getDrugCategoryBean().getCategory());
			nCell.setCellStyle(cateStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getManufacturer());
			nCell.setCellStyle(facturerStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getApprovalNumber());
			nCell.setCellStyle(ApproStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getSalepeice());
			nCell.setCellStyle(salepriceStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getMemberprice());
			nCell.setCellStyle(memStyle);

			nCell = nRow.createCell(colNo++);
			String time = sdf1.format(bean.getSalesDate());
			nCell.setCellValue(time);
			nCell.setCellStyle(saleTimeStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getTotalamount());
			nCell.setCellStyle(saleamountStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getModifier());
			nCell.setCellStyle(ModifierStyle);

		}

		// 下载
		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		du.download(os, response, inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份药品销售表.xls");

	}

	// 打印进货表
	public void printPurchase() throws IOException {
		Date date = (Date) request.get("date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String inputDate = sdf.format(date);
		List<DrugPurchaseBean> list = drugPurchaseService.show(date);

		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")
				+ "/xlsprint/";
		InputStream is = new FileInputStream(new File(path + "purchase.xls"));
		Workbook wb = new HSSFWorkbook(is);
		Sheet sheet = wb.getSheetAt(0);
		Row nRow = null;
		Cell nCell = null;

		int rowNo = 0; // 行号
		int colNo = 1; // 列号

		// 获取模板单元格样式
		nRow = sheet.getRow(2);

		// 药品进货单号
		nCell = nRow.getCell(1);
		CellStyle codeStyle = nCell.getCellStyle();

		// 药品名
		nCell = nRow.getCell(2);
		CellStyle nameStyle = nCell.getCellStyle();

		// 剂型名
		nCell = nRow.getCell(3);
		CellStyle formStyle = nCell.getCellStyle();

		// 单位名
		nCell = nRow.getCell(4);
		CellStyle unitStyle = nCell.getCellStyle();

		// 类别名
		nCell = nRow.getCell(5);
		CellStyle cateStyle = nCell.getCellStyle();

		// 数量
		nCell = nRow.getCell(6);
		CellStyle amountStyle = nCell.getCellStyle();

		// 生产日期
		nCell = nRow.getCell(7);
		CellStyle prodateStyle = nCell.getCellStyle();

		// 有效期
		nCell = nRow.getCell(8);
		CellStyle vardateStyle = nCell.getCellStyle();

		// 进货日期
		nCell = nRow.getCell(9);
		CellStyle jindateStyle = nCell.getCellStyle();

		// 供货商
		nCell = nRow.getCell(10);
		CellStyle supStyle = nCell.getCellStyle();

		// 生产厂商
		nCell = nRow.getCell(11);
		CellStyle facturerStyle = nCell.getCellStyle();

		// 批准文号
		nCell = nRow.getCell(12);
		CellStyle ApproStyle = nCell.getCellStyle();

		// 修改人
		nCell = nRow.getCell(13);
		CellStyle ModifierStyle = nCell.getCellStyle();

		nRow = sheet.getRow(rowNo++);
		nCell = nRow.getCell(colNo);
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份进货表");
		rowNo++;

		// 处理数据
		for (int j = 0; j < list.size(); j++) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			colNo = 1;
			DrugPurchaseBean bean = list.get(j); // 获取库存对象

			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getPurchaseCode());
			nCell.setCellStyle(codeStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getDrugName());
			nCell.setCellStyle(nameStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getDosageformBean().getDosageform());
			nCell.setCellStyle(formStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getDrugUnitBean().getUnitname());
			nCell.setCellStyle(unitStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getDrugCategoryBean().getCategory());
			nCell.setCellStyle(cateStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getAmount());
			nCell.setCellStyle(amountStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(sdf.format(bean.getProductionDate()));
			nCell.setCellStyle(prodateStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(sdf.format(bean.getValidityDate()));
			nCell.setCellStyle(vardateStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(sdf.format(bean.getPurchasedate()));
			nCell.setCellStyle(jindateStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getSupplierBean().getSupplier());
			nCell.setCellStyle(amountStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getManufacturer());
			nCell.setCellStyle(facturerStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getApprovalNumber());
			nCell.setCellStyle(ApproStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(bean.getDrugBean().getModifier());
			nCell.setCellStyle(ModifierStyle);

		}

		// 下载
		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		du.download(os, response, inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份药品进货表.xls");

	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
