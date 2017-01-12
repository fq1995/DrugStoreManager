package com.fq.po;

// default package

import java.util.Date;

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
 * ReturnsBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_returns"
    ,catalog="yaodian"
)

public class ReturnsBean  implements java.io.Serializable {


    // Fields    

     private String returnId;
     private DrugBean drugBean;
     private UserBean userBean;
     private DrugPurchaseBean drugPurchaseBean;
     private String returnCode;
     private Integer amount;
     private Double money;
     private byte[] reason;
     private Date submitTime;


    // Constructors

    /** default constructor */
    public ReturnsBean() {
    }

	/** minimal constructor */
    public ReturnsBean(String returnId) {
        this.returnId = returnId;
    }
    
    /** full constructor */
    public ReturnsBean(String returnId, DrugBean drugBean, UserBean userBean, DrugPurchaseBean drugPurchaseBean, String returnCode, Integer amount, Double money, byte[] reason, Date submitTime) {
        this.returnId = returnId;
        this.drugBean = drugBean;
        this.userBean = userBean;
        this.drugPurchaseBean = drugPurchaseBean;
        this.returnCode = returnCode;
        this.amount = amount;
        this.money = money;
        this.reason = reason;
        this.submitTime = submitTime;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="RETURN_ID", unique=true, nullable=false, length=32)

    public String getReturnId() {
        return this.returnId;
    }
    
    public void setReturnId(String returnId) {
        this.returnId = returnId;
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
        @JoinColumn(name="USER_ID")

    public UserBean getUserBean() {
        return this.userBean;
    }
    
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="PURCHASE_ID")

    public DrugPurchaseBean getDrugPurchaseBean() {
        return this.drugPurchaseBean;
    }
    
    public void setDrugPurchaseBean(DrugPurchaseBean drugPurchaseBean) {
        this.drugPurchaseBean = drugPurchaseBean;
    }
    
    @Column(name="RETURN_CODE", length=32)

    public String getReturnCode() {
        return this.returnCode;
    }
    
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    
    @Column(name="AMOUNT")

    public Integer getAmount() {
        return this.amount;
    }
    
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    
    @Column(name="MONEY", precision=10)

    public Double getMoney() {
        return this.money;
    }
    
    public void setMoney(Double money) {
        this.money = money;
    }
    
    @Column(name="REASON")

    public byte[] getReason() {
        return this.reason;
    }
    
    public void setReason(byte[] reason) {
        this.reason = reason;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="SUBMIT_TIME", length=10)

    public Date getSubmitTime() {
        return this.submitTime;
    }
    
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }








}