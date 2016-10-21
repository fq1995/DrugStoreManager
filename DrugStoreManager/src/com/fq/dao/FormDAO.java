package com.fq.dao;

import java.util.List;

import com.fq.po.DosageformBean;
import com.fq.util.PageModel;

public interface FormDAO {
			//根据名称查询
			DosageformBean selectFormByName(String formname);
			//添加
			void addForm(DosageformBean formBean);
			//分页查询
			PageModel<DosageformBean> splitForm(Integer currPage, Integer pageSize,String keyword);
			//批量查询
			List<DosageformBean> showAllForm(String ids);
			//批量删除对象
			void deleteAllForm(List<DosageformBean> formList);
			//修改
			void updateForm(DosageformBean formBean);
			//根据ID查询
			DosageformBean selectById(String id);
			//查询全部
			DosageformBean selectAll();
			//根据药品剂型名和药品剂型编号查询
			DosageformBean selectFormByNameAndFormId(String formname, String formid);
}
