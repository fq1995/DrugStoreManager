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
 * DrugSalesBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_drugsales"
    ,catalog="yaodian"
)

public class DrugSalesBean  implements java.io.Serializable {


    // Fields    

     private String salesId;
     private DrugBean drugBean;
     private MemberBean memberBean;
     private UserBean userBean;
     private String salesCode;
     private Double salepeice;
     private Double memberprice;
     private Integer salesVolume;
     private Date salesDate;
     private Float totalamount;
     private Set<BussinessBean> bussinessBeans = new HashSet<BussinessBean>(0);


    // Constructors

    /** default constructor */
    public DrugSalesBean() {
    }

	/** minimal constructor */
    public DrugSalesBean(String salesId) {
        this.salesId = salesId;
    }
    
    /** full constructor */
    public DrugSalesBean(String salesId, DrugBean drugBean, MemberBean memberBean, UserBean userBean, String salesCode, Double salepeice, Double memberprice, Integer salesVolume, Date salesDate, Float totalamount, Set<BussinessBean> bussinessBeans) {
        this.salesId = salesId;
        this.drugBean = drugBean;
        this.memberBean = memberBean;
        this.userBean = userBean;
        this.salesCode = salesCode;
        this.salepeice = salepeice;
        this.memberprice = memberprice;
        this.salesVolume = salesVolume;
        this.salesDate = salesDate;
        this.totalamount = totalamount;
        this.bussinessBeans = bussinessBeans;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="SALES_ID", unique=true, nullable=false, length=32)

    public String getSalesId() {
        return this.salesId;
    }
    
    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
        @JoinColumn(name="DRUG_ID")

    public DrugBean getDrugBean() {
        return this.drugBean;
    }
    
    public void setDrugBean(DrugBean drugBean) {
        this.drugBean = drugBean;
    }
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
        @JoinColumn(name="MEMBER_ID")

    public MemberBean getMemberBean() {
        return this.memberBean;
    }
    
    public void setMemberBean(MemberBean memberBean) {
        this.memberBean = memberBean;
    }
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
        @JoinColumn(name="USER_ID")

    public UserBean getUserBean() {
        return this.userBean;
    }
    
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    
    @Column(name="SALES_CODE", length=32)

    public String getSalesCode() {
        return this.salesCode;
    }
    
    public void setSalesCode(String salesCode) {
        this.salesCode = salesCode;
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
    
    @Column(name="SalesVolume")

    public Integer getSalesVolume() {
        return this.salesVolume;
    }
    
    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="SalesDATE", length=10)

    public Date getSalesDate() {
        return this.salesDate;
    }
    
    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }
    
    @Column(name="TOTALAMOUNT", precision=12, scale=0)

    public Float getTotalamount() {
        return this.totalamount;
    }
    
    public void setTotalamount(Float totalamount) {
        this.totalamount = totalamount;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="drugSalesBean")

    public Set<BussinessBean> getBussinessBeans() {
        return this.bussinessBeans;
    }
    
    public void setBussinessBeans(Set<BussinessBean> bussinessBeans) {
        this.bussinessBeans = bussinessBeans;
    }
   








}