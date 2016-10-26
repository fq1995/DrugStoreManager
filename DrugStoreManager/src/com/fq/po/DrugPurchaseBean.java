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
     private String drugname;
     private Integer amount;
     private Date productionDate;
     private Date validityDate;
     private Double purchaseprice;
     private Double salepeice;
     private Float totalamount;
     private Date purchasedate;
     private Double memberprice;
     private Set<BussinessBean> bussinessBeans = new HashSet<BussinessBean>(0);
     private Set<ReturnsBean> returnsBeans = new HashSet<ReturnsBean>(0);


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
    public DrugPurchaseBean(String purchaseId, DrugBean drugBean, UserBean userBean, SupplierBean supplierBean, String purchaseCode, String drugname, Integer amount, Date productionDate, Date validityDate, Double purchaseprice, Double salepeice, Float totalamount, Date purchasedate, Double memberprice, Set<BussinessBean> bussinessBeans, Set<ReturnsBean> returnsBeans) {
        this.purchaseId = purchaseId;
        this.drugBean = drugBean;
        this.userBean = userBean;
        this.supplierBean = supplierBean;
        this.purchaseCode = purchaseCode;
        this.drugname = drugname;
        this.amount = amount;
        this.productionDate = productionDate;
        this.validityDate = validityDate;
        this.purchaseprice = purchaseprice;
        this.salepeice = salepeice;
        this.totalamount = totalamount;
        this.purchasedate = purchasedate;
        this.memberprice = memberprice;
        this.bussinessBeans = bussinessBeans;
        this.returnsBeans = returnsBeans;
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
    
    @Column(name="DRUGNAME", length=20)

    public String getDrugname() {
        return this.drugname;
    }
    
    public void setDrugname(String drugname) {
        this.drugname = drugname;
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
    
    @Column(name="TOTALAMOUNT", precision=12, scale=0)

    public Float getTotalamount() {
        return this.totalamount;
    }
    
    public void setTotalamount(Float totalamount) {
        this.totalamount = totalamount;
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
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="drugPurchaseBean")

    public Set<BussinessBean> getBussinessBeans() {
        return this.bussinessBeans;
    }
    
    public void setBussinessBeans(Set<BussinessBean> bussinessBeans) {
        this.bussinessBeans = bussinessBeans;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="drugPurchaseBean")

    public Set<ReturnsBean> getReturnsBeans() {
        return this.returnsBeans;
    }
    
    public void setReturnsBeans(Set<ReturnsBean> returnsBeans) {
        this.returnsBeans = returnsBeans;
    }
   








}