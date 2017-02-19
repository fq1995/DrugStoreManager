package com.fq.po;

public class Invenstats {
	private String drugName;
	private Integer stocknumber;
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
	public Invenstats(String drugName, Integer stocknumber) {
		super();
		this.drugName = drugName;
		this.stocknumber = stocknumber;
	}
	public Invenstats() {
		super();
	}
	
	
}
