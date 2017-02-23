package com.fq.util;

public class DrugCombogrid {
	 private String drugId;
	private Integer drugCode;
	 private String drugName;
	 private String dosageform;
	 private String unitname;
	 private String category;
	 private Double salepeice;
	 private String approvalNumber;
	 private String manufacturer;
	 
	public DrugCombogrid() {
		super();
	}
	

	public DrugCombogrid(String drugId, Integer drugCode, String drugName, String dosageform, String unitname,
			String category, Double salepeice, String approvalNumber, String manufacturer) {
		super();
		this.drugId = drugId;
		this.drugCode = drugCode;
		this.drugName = drugName;
		this.dosageform = dosageform;
		this.unitname = unitname;
		this.category = category;
		this.salepeice = salepeice;
		this.approvalNumber = approvalNumber;
		this.manufacturer = manufacturer;
	}


	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public Integer getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(Integer drugCode) {
		this.drugCode = drugCode;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDosageform() {
		return dosageform;
	}
	public void setDosageform(String dosageform) {
		this.dosageform = dosageform;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getApprovalNumber() {
		return approvalNumber;
	}
	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Double getSalepeice() {
		return salepeice;
	}
	public void setSalepeice(Double salepeice) {
		this.salepeice = salepeice;
	}
	 
	 
	 
}
