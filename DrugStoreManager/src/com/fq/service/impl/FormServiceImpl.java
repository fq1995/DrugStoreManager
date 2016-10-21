package com.fq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.FormDAO;
import com.fq.po.DosageformBean;
import com.fq.service.FormService;
import com.fq.util.PageModel;
@Service("formService")
public class FormServiceImpl implements FormService {
	
	@Autowired
	private FormDAO formdao;
	
	@Override
	public DosageformBean selectFormByName(String formname) {
		return formdao.selectFormByName(formname);
	}

	@Override
	public void addForm(DosageformBean formBean) {
		formdao.addForm(formBean);

	}

	@Override
	public PageModel<DosageformBean> splitForm(Integer currPage, Integer pageSize,String keyword) {
		return formdao.splitForm(currPage, pageSize,keyword);
	}

	@Override
	public List<DosageformBean> showAllForm(String ids) {
		return formdao.showAllForm(ids);
	}

	@Override
	public void deleteAllForm(List<DosageformBean> formList) {
		formdao.deleteAllForm(formList);

	}

	@Override
	public void updateForm(DosageformBean formBean) {
		formdao.updateForm(formBean);

	}

	@Override
	public DosageformBean selectById(String id) {
		return formdao.selectById(id);
	}

	@Override
	public DosageformBean selectAll() {
		return formdao.selectAll();
	}

	@Override
	public DosageformBean selectFormByNameAndFormId(String formname, String formid) {
		return formdao.selectFormByNameAndFormId(formname, formid);
	}

}
