package com.fq.util;
/**
 * 
 * @author fu
 * 库存统计
 */
public class InvenStats {
	private String drugName;
	private Integer stocknumber;//库存数量
	private Integer stocklimit;//库存下限
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public Integer getStocknumber() {
		return stocknumber;
	}
	public void setStocknumber(Integer stocknumber) {
		this.stocknumber = stocknumber;
	}
	
	public InvenStats(String drugName, Integer stocknumber, Integer stocklimit) {
		super();
		this.drugName = drugName;
		this.stocknumber = stocknumber;
		this.stocklimit = stocklimit;
	}
	public InvenStats() {
		super();
	}
	public Integer getStocklimit() {
		return stocklimit;
	}
	public void setStocklimit(Integer stocklimit) {
		this.stocklimit = stocklimit;
	}
	
	
}
