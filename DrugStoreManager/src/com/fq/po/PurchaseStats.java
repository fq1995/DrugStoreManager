package com.fq.po;

public class PurchaseStats {
	 private Integer amount;
	 private String drugName;
	 
	 
	public PurchaseStats() {
		super();
	}
	public PurchaseStats(Integer amount, String drugName) {
		super();
		this.amount = amount;
		this.drugName = drugName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	 
	 
}
