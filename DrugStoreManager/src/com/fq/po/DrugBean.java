package com.fq.po;

// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;



/**
 * DrugBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_drug"
    ,catalog="yaodian"
, uniqueConstraints = @UniqueConstraint(columnNames={"DRUG_CODE", "DOSAGEFORM_ID", "UNITNAME_ID", "CATEGORY_ID"})
)

public class DrugBean  implements java.io.Serializable {


    // Fields    

     private String drugId;
     private DrugUnitBean drugUnitBean;
     private DosageformBean dosageformBean;
     private DrugCategoryBean drugCategoryBean;
     private Integer drugCode;
     private String drugName;
     private String manufacturer;
     private String approvalNumber;
     private String modifier;
     private Date modifyTime;
     private String memo;
     private String status;
     private Double salepeice;
     private Double memberprice;
     private String oldName;
     private String newName;
     private Set<InventoriesBean> inventoriesBeans = new HashSet<InventoriesBean>(0);
     private Set<ReturnsBean> returnsBeans = new HashSet<ReturnsBean>(0);
     private Set<DrugBuy> drugBuies = new HashSet<DrugBuy>(0);
     private Set<DrugPurchaseBean> drugPurchaseBeans = new HashSet<DrugPurchaseBean>(0);
     private Set<DrugSalesBean> drugSalesBeans = new HashSet<DrugSalesBean>(0);


    // Constructors

    /** default constructor */
    public DrugBean() {
    }

	/** minimal constructor */
    public DrugBean(String drugId, Integer drugCode) {
        this.drugId = drugId;
        this.drugCode = drugCode;
    }
    
    /** full constructor */
    public DrugBean(String drugId, DrugUnitBean drugUnitBean, DosageformBean dosageformBean, DrugCategoryBean drugCategoryBean, Integer drugCode, String drugName, String manufacturer, String approvalNumber, String modifier, Date modifyTime, String memo, String status, Double salepeice, Double memberprice,  String oldName, String newName, Set<InventoriesBean> inventoriesBeans, Set<DrugBuy> drugBuies, Set<ReturnsBean> returnsBeans, Set<DrugPurchaseBean> drugPurchaseBeans, Set<DrugSalesBean> drugSalesBeans) {
        this.drugId = drugId;
        this.drugUnitBean = drugUnitBean;
        this.dosageformBean = dosageformBean;
        this.drugCategoryBean = drugCategoryBean;
        this.drugCode = drugCode;
        this.drugName = drugName;
        this.manufacturer = manufacturer;
        this.approvalNumber = approvalNumber;
        this.modifier = modifier;
        this.modifyTime = modifyTime;
        this.memo = memo;
        this.status = status;
        this.salepeice = salepeice;
        this.memberprice = memberprice;
        this.oldName = oldName;
        this.newName = newName;
        this.inventoriesBeans = inventoriesBeans;
        this.drugBuies = drugBuies;
        this.returnsBeans = returnsBeans;
        this.drugPurchaseBeans = drugPurchaseBeans;
        this.drugSalesBeans = drugSalesBeans;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="DRUG_ID", unique=true, nullable=false, length=32)

    public String getDrugId() {
        return this.drugId;
    }
    
    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="UNITNAME_ID")

    public DrugUnitBean getDrugUnitBean() {
        return this.drugUnitBean;
    }
    
    public void setDrugUnitBean(DrugUnitBean drugUnitBean) {
        this.drugUnitBean = drugUnitBean;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="DOSAGEFORM_ID")

    public DosageformBean getDosageformBean() {
        return this.dosageformBean;
    }
    
    public void setDosageformBean(DosageformBean dosageformBean) {
        this.dosageformBean = dosageformBean;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="CATEGORY_ID")

    public DrugCategoryBean getDrugCategoryBean() {
        return this.drugCategoryBean;
    }
    
    public void setDrugCategoryBean(DrugCategoryBean drugCategoryBean) {
        this.drugCategoryBean = drugCategoryBean;
    }
    
    @Column(name="DRUG_CODE", nullable=false)

    public Integer getDrugCode() {
        return this.drugCode;
    }
    
    public void setDrugCode(Integer drugCode) {
        this.drugCode = drugCode;
    }
    
    @Column(name="DRUG_NAME", length=20)

    public String getDrugName() {
        return this.drugName;
    }
    
    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }
    
    @Column(name="MANUFACTURER", length=30)

    public String getManufacturer() {
        return this.manufacturer;
    }
    
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    @Column(name="ApprovalNumber", length=32)

    public String getApprovalNumber() {
        return this.approvalNumber;
    }
    
    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }
    
    @Column(name="MODIFIER", length=10)

    public String getModifier() {
        return this.modifier;
    }
    
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="MODIFY_TIME", length=10)

    public Date getModifyTime() {
        return this.modifyTime;
    }
    
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    @Column(name="MEMO", length=100)

    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    @Column(name="STATUS", length=1)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="SALEPEICE", precision=10)

    public Double getSalepeice() {
        return this.salepeice;
    }
    
    public void setSalepeice(Double salepeice) {
        this.salepeice = salepeice;
    }
    
    @Column(name="MEMBERPRICE", precision=10)

    public Double getMemberprice() {
        return this.memberprice;
    }
    
    public void setMemberprice(Double memberprice) {
        this.memberprice = memberprice;
    }
    
    @Column(name="old_name", length=100)

    public String getOldName() {
        return this.oldName;
    }
    
    public void setOldName(String oldName) {
        this.oldName = oldName;
    }
    
    @Column(name="new_name", length=100)

    public String getNewName() {
        return this.newName;
    }
    
    public void setNewName(String newName) {
        this.newName = newName;
    }
    
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="drugBean")

    public Set<InventoriesBean> getInventoriesBeans() {
        return this.inventoriesBeans;
    }
    
    public void setInventoriesBeans(Set<InventoriesBean> inventoriesBeans) {
        this.inventoriesBeans = inventoriesBeans;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="drugBean")

    public Set<DrugBuy> getDrugBuies() {
        return this.drugBuies;
    }
    
    public void setDrugBuies(Set<DrugBuy> drugBuies) {
        this.drugBuies = drugBuies;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="drugBean")

    public Set<ReturnsBean> getReturnsBeans() {
        return this.returnsBeans;
    }
    
    public void setReturnsBeans(Set<ReturnsBean> returnsBeans) {
        this.returnsBeans = returnsBeans;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="drugBean")

    public Set<DrugPurchaseBean> getDrugPurchaseBeans() {
        return this.drugPurchaseBeans;
    }
    
    public void setDrugPurchaseBeans(Set<DrugPurchaseBean> drugPurchaseBeans) {
        this.drugPurchaseBeans = drugPurchaseBeans;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="drugBean")

    public Set<DrugSalesBean> getDrugSalesBeans() {
        return this.drugSalesBeans;
    }
    
    public void setDrugSalesBeans(Set<DrugSalesBean> drugSalesBeans) {
        this.drugSalesBeans = drugSalesBeans;
    }
   

}