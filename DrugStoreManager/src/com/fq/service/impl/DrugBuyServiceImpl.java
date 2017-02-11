package com.fq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fq.dao.DrugBuyDAO;
import com.fq.po.DosageformBean;
import com.fq.po.DrugBuy;
import com.fq.po.DrugCategoryBean;
import com.fq.po.DrugUnitBean;
import com.fq.service.DrugBuyService;
import com.fq.util.PageModel;
@Service("drugBuyService")
public class DrugBuyServiceImpl implements DrugBuyService {
	
	@Autowired
	private DrugBuyDAO drugbuydao;
	@Override
	public List<DrugBuy> show() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DrugBuy selectDrugByName(String drugname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DrugBuy selectDrugByDrugcode(Integer drugcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DrugBuy selectDrugByNameAndDrugId(String drugname, String drugid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBuy(Integer drugCode, DrugBuy buy, String time) throws Exception {
		drugbuydao.addBuy(drugCode, buy, time);
	}

	@Override
	public PageModel<DrugBuy> splitDrug(Integer currPage, Integer pageSize, String keyword) {
		return drugbuydao.splitDrugBuy(currPage, pageSize, keyword);
	}

	
	@Override
	public List<DrugBuy> showAllDrug(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllDrug(List<DrugBuy> drugList) {
		drugbuydao.deleteAllDrugBuy(drugList);
	}

	@Override
	public void updateDrug(DrugBuy buy, String time) {
		drugbuydao.updateDrugBuy(buy, time);

	}

	@Override
	public DrugBuy selectById(String id) {
		return drugbuydao.selectById(id);
	}

	@Override
	public Integer select() {
		return drugbuydao.select();
	}

}
