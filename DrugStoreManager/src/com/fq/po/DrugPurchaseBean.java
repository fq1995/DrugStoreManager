package com.fq.po;

// default package

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * DrugPurchaseBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_drugpurchase"
    ,catalog="yaodian"
)

public class DrugPurchaseBean  implements java.io.Serializable {


    // Fields    

     private String purchaseId;
     private DrugBean drugBean;
     private UserBean userBean;
     private SupplierBean supplierBean;
     private String purchaseCode;
     private Integer amount;
     private Date productionDate;
     private Date validityDate;
     private Double purchaseprice;
     private Double salepeice;
     private Date purchasedate;
     private Double memberprice;


    // Constructors

    /** default constructor */
    public DrugPurchaseBean() {
    }

	/** minimal constructor */
    public DrugPurchaseBean(String purchaseId, String purchaseCode) {
        this.purchaseId = purchaseId;
        this.purchaseCode = purchaseCode;
    }
    
    /** full constructor */
    public DrugPurchaseBean(String purchaseId, DrugBean drugBean, UserBean userBean, SupplierBean supplierBean, String purchaseCode, Integer amount, Date productionDate, Date validityDate, Double purchaseprice, Double salepeice,  Date purchasedate, Double memberprice ) {
        this.purchaseId = purchaseId;
        this.drugBean = drugBean;
        this.userBean = userBean;
        this.supplierBean = supplierBean;
        this.purchaseCode = purchaseCode;
        this.amount = amount;
        this.productionDate = productionDate;
        this.validityDate = validityDate;
        this.purchaseprice = purchaseprice;
        this.salepeice = salepeice;
        this.purchasedate = purchasedate;
        this.memberprice = memberprice;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="PURCHASE_ID", unique=true, nullable=false, length=32)

    public String getPurchaseId() {
        return this.purchaseId;
    }
    
    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
        @JoinColumn(name="DRUG_ID")

    public DrugBean getDrugBean() {
        return this.drugBean;
    }
    
    public void setDrugBean(DrugBean drugBean) {
        this.drugBean = drugBean;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="USER_ID")

    public UserBean getUserBean() {
        return this.userBean;
    }
    
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="SUPPLIER_ID")

    public SupplierBean getSupplierBean() {
        return this.supplierBean;
    }
    
    public void setSupplierBean(SupplierBean supplierBean) {
        this.supplierBean = supplierBean;
    }
    
    @Column(name="PURCHASE_CODE", nullable=false, length=32)

    public String getPurchaseCode() {
        return this.purchaseCode;
    }
    
    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }
    
    
    @Column(name="AMOUNT")

    public Integer getAmount() {
        return this.amount;
    }
    
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="PRODUCTION_DATE", length=10)

    public Date getProductionDate() {
        return this.productionDate;
    }
    
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="VALIDITY_DATE", length=10)

    public Date getValidityDate() {
        return this.validityDate;
    }
    
    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }
    
    @Column(name="PURCHASEPRICE", precision=10)

    public Double getPurchaseprice() {
        return this.purchaseprice;
    }
    
    public void setPurchaseprice(Double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }
    
    @Column(name="SALEPEICE", precision=10)

    public Double getSalepeice() {
        return this.salepeice;
    }
    
    public void setSalepeice(Double salepeice) {
        this.salepeice = salepeice;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name="PURCHASEDATE", length=10)

    public Date getPurchasedate() {
        return this.purchasedate;
    }
    
    public void setPurchasedate(Date purchasedate) {
        this.purchasedate = purchasedate;
    }
    
    @Column(name="MEMBERPRICE", precision=10)

    public Double getMemberprice() {
        return this.memberprice;
    }
    
    public void setMemberprice(Double memberprice) {
        this.memberprice = memberprice;
    }
 
   








}