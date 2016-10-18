package com.fq.po;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * BussinessBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_business"
    ,catalog="yaodian"
)

public class BussinessBean  implements java.io.Serializable {


    // Fields    

     private String businessId;
     private DrugBean drugBean;
     private DrugSalesBean drugSalesBean;
     private InventoriesBean inventoriesBean;
     private DrugPurchaseBean drugPurchaseBean;
     private ReturnsBean returnsBean;


    // Constructors

    /** default constructor */
    public BussinessBean() {
    }

    
    /** full constructor */
    public BussinessBean(DrugBean drugBean, DrugSalesBean drugSalesBean, InventoriesBean inventoriesBean, DrugPurchaseBean drugPurchaseBean, ReturnsBean returnsBean) {
        this.drugBean = drugBean;
        this.drugSalesBean = drugSalesBean;
        this.inventoriesBean = inventoriesBean;
        this.drugPurchaseBean = drugPurchaseBean;
        this.returnsBean = returnsBean;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="BUSINESS_ID", unique=true, nullable=false, length=32)

    public String getBusinessId() {
        return this.businessId;
    }
    
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="DRUG_ID")

    public DrugBean getDrugBean() {
        return this.drugBean;
    }
    
    public void setDrugBean(DrugBean drugBean) {
        this.drugBean = drugBean;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="SALES_ID")

    public DrugSalesBean getDrugSalesBean() {
        return this.drugSalesBean;
    }
    
    public void setDrugSalesBean(DrugSalesBean drugSalesBean) {
        this.drugSalesBean = drugSalesBean;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="STOCK_ID")

    public InventoriesBean getInventoriesBean() {
        return this.inventoriesBean;
    }
    
    public void setInventoriesBean(InventoriesBean inventoriesBean) {
        this.inventoriesBean = inventoriesBean;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="PURCHASE_ID")

    public DrugPurchaseBean getDrugPurchaseBean() {
        return this.drugPurchaseBean;
    }
    
    public void setDrugPurchaseBean(DrugPurchaseBean drugPurchaseBean) {
        this.drugPurchaseBean = drugPurchaseBean;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="RETURN_ID")

    public ReturnsBean getReturnsBean() {
        return this.returnsBean;
    }
    
    public void setReturnsBean(ReturnsBean returnsBean) {
        this.returnsBean = returnsBean;
    }
   








}