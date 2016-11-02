package com.fq.service;

import java.util.Date;
import java.util.List;

import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugSalesBean;
import com.fq.po.DrugUnitBean;
import com.fq.po.MemberBean;
import com.fq.po.UserBean;
import com.fq.util.PageModel;

public interface DrugSaleService {
	
DrugSalesBean selectSaleByName(String name);
	
	DrugSalesBean selectSaleByDrugcode(Integer code);
	
	void addSale(UserBean userBean,DrugBean drugBean,DrugSalesBean bean,String time) throws Exception;
	
	PageModel<DrugSalesBean> splitSale(Integer currPage, Integer pageSize, String keyword);
	//批量查询
	List<DrugSalesBean> showAllSale(String ids);
	//批量删除对象
	void deleteAllSale(List<DrugSalesBean> list);
	//修改
	void updateSale(DrugSalesBean bean,String time);
	
	//根据ID查询
	DrugSalesBean selectById(String id);
	//查询类别
	List<DrugCategoryBean> selectCategory();
	//查询单位
	List<DrugUnitBean> selectUnit();
	//查询剂型
	List<DosageformBean> selectForm();
	//查询药品
	List<DrugBean> selectDrug();
	//查询会员
	List<MemberBean> selectMember();
	//根据药品ID查询
	DrugBean selectSaleByDrugId(String id);
	//查询员工
	List<UserBean> selectUser();
	//查询销售
	List<DrugSalesBean> show(Date date);
	//查询会员电话
	MemberBean selectSaleByTel(String suppliertel);
	
}
