package com.fq.dao;

import java.util.List;

import com.fq.po.DosageformBean;
import com.fq.po.DrugBean;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugPurchaseBean;
import com.fq.po.DrugUnitBean;
import com.fq.po.MemberBean;
import com.fq.po.UserBean;
import com.fq.util.PageModel;

public interface DrugPurchaseDAO {
	
	DrugPurchaseBean selectPseByName(String name);
	
	DrugPurchaseBean selectPseByDrugcode(Integer code);
	
	DrugPurchaseBean selectPseByDrugId(String id);
	
	void addPse(DrugBean drugBean,DrugPurchaseBean bean,String time) throws Exception;
	
	void addPse(DosageformBean dfBean, DrugCategoryBean dcBean, DrugUnitBean duBean, DrugBean drugBean,
			DrugPurchaseBean drugPseBean, String time);
	
	PageModel<DrugPurchaseBean> splitPse(Integer currPage, Integer pageSize, String keyword);
	//批量查询
	List<DrugPurchaseBean> showAllPse(String ids);
	//批量删除对象
	void deleteAllPse(List<DrugPurchaseBean> list);
	//修改
	void updatePse(DrugPurchaseBean bean);
	//根据ID查询
	DrugPurchaseBean selectById(String id);
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
	//查询员工
	List<UserBean> selectUser();
}