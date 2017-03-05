package com.fq.dao;

import java.util.Date;
import java.util.List;

import com.fq.po.DrugBean;
import com.fq.po.DrugSalesBean;
import com.fq.po.MemberBean;
import com.fq.po.UserBean;
import com.fq.util.PageModel;

public interface DrugSaleDAO {
	
	DrugSalesBean selectSaleByName(String name);
	
	DrugSalesBean selectSaleByDrugcode(Integer code);
	
	DrugBean selectSaleByDrugId(String id);
	
	void addSale(String tel, DrugSalesBean bean) throws Exception;
	
	PageModel<DrugSalesBean> splitSale(Integer currPage, Integer pageSize, String keyword);
	//批量查询
	List<DrugSalesBean> showAllSale(String ids);
	//批量删除对象
	void deleteAllSale(List<DrugSalesBean> list);
	//修改
	void updateSale(DrugSalesBean bean,String time);
	
	//根据ID查询
	DrugSalesBean selectById(String id);
	
	//查询药品
	List<DrugBean> selectDrug();
	//查询会员
	List<MemberBean> selectMember();
	//查询员工
	List<UserBean> selectUser();
	//查询销售
	List<DrugSalesBean> show(Date date);
	//查询会员电话
	MemberBean selectSaleByTel(String suppliertel);
	//查询编号
	Integer selectCode();
	//统计
	List<DrugSalesBean> stats();
	//数据表格下拉
	List<DrugBean> comboGrid();
	//修改会员积分
	void updateMember(String tel,DrugSalesBean bean);

}
