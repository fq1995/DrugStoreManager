package com.fq.util;
/**
 * 
 * @author fu
 * 销售统计
 */
public class SaleStats {
	 private Integer salesVolume;
	 private String drugName;
	
	 public SaleStats() {
		super();
	}
	public SaleStats(Integer salesVolume, String drugName) {
		super();
		this.salesVolume = salesVolume;
		this.drugName = drugName;
	}
	public Integer getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	 
	 
}
