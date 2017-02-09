package com.fq.service;

import java.util.Date;
import java.util.List;

import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugPurchaseBean;
import com.fq.po.DrugUnitBean;
import com.fq.po.MemberBean;
import com.fq.po.SupplierBean;
import com.fq.po.UserBean;
import com.fq.util.PageModel;

public interface DrugPurchaseService {
	DrugPurchaseBean selectPseByName(String name);

	DrugPurchaseBean selectPseByDrugcode(Integer code);

	DrugPurchaseBean selectPseByDrugId(String id);

	void addPse(Integer drugCode, Integer pseCode, SupplierBean supBean, DosageformBean dfBean, DrugCategoryBean dcBean,
			DrugUnitBean duBean, DrugBean drugBean, DrugPurchaseBean drugPseBean, String time);

	PageModel<DrugPurchaseBean> splitPse(Integer currPage, Integer pageSize, String keyword);

	// 批量查询
	List<DrugPurchaseBean> showAllPse(String ids);

	// 批量删除对象
	void deleteAllPse(List<DrugPurchaseBean> list);

	// 修改
	void updatePse(DrugPurchaseBean bean);

	// 根据ID查询
	DrugPurchaseBean selectById(String id);

	// 查询类别
	List<DrugCategoryBean> selectCategory();

	// 查询单位
	List<DrugUnitBean> selectUnit();

	// 查询剂型
	List<DosageformBean> selectForm();

	// 查询药品
	List<DrugBean> selectDrug();

	// 查询会员
	List<MemberBean> selectMember();

	// 查询员工
	List<UserBean> selectUser();

	// 查询供货商
	List<SupplierBean> selectSupplier();

	// 有效期预警
	PageModel<DrugPurchaseBean> splitDateWarn(Integer currPage, Integer pagesize, String keyword);

	// 查询进货
	List<DrugPurchaseBean> show(Date date);

	// 查询编号
	Integer selectCode();
	
	// 过期药
	PageModel<DrugPurchaseBean> splitOverDate(Integer currPage, Integer pagesize, String keyword);

}
