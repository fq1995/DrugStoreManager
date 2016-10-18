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
 * UserBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_users"
    ,catalog="yaodian"
)

public class UserBean  implements java.io.Serializable {


    // Fields    

     private String userId;
     private RoleBean roleBean;
     private Integer userCode;
     private String username;
     private String password;
     private Date addtime;
     private Integer status;
     private Set<DrugPurchaseBean> drugPurchaseBeans = new HashSet<DrugPurchaseBean>(0);
     private Set<DrugSalesBean> drugSalesBeans = new HashSet<DrugSalesBean>(0);
     private Set<ReturnsBean> returnsBeans = new HashSet<ReturnsBean>(0);


    // Constructors

    /** default constructor */
    public UserBean() {
    }

	/** minimal constructor */
    public UserBean(String userId, Integer userCode) {
        this.userId = userId;
        this.userCode = userCode;
    }
    
    /** full constructor */
    public UserBean(String userId, RoleBean roleBean, Integer userCode, String username, String password, Date addtime, Integer status, Set<DrugPurchaseBean> drugPurchaseBeans, Set<DrugSalesBean> drugSalesBeans, Set<ReturnsBean> returnsBeans) {
        this.userId = userId;
        this.roleBean = roleBean;
        this.userCode = userCode;
        this.username = username;
        this.password = password;
        this.addtime = addtime;
        this.status = status;
        this.drugPurchaseBeans = drugPurchaseBeans;
        this.drugSalesBeans = drugSalesBeans;
        this.returnsBeans = returnsBeans;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="USER_ID", unique=true, nullable=false, length=32)

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
        @JoinColumn(name="ROLEID")

    public RoleBean getRoleBean() {
        return this.roleBean;
    }
    
    public void setRoleBean(RoleBean roleBean) {
        this.roleBean = roleBean;
    }
    
    @Column(name="USER_CODE", nullable=false)

    public Integer getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="username", length=4)

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="PASSWORD", length=20)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="addtime", length=10)

    public Date getAddtime() {
        return this.addtime;
    }
    
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
    
    @Column(name="status")

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="userBean")

    public Set<DrugPurchaseBean> getDrugPurchaseBeans() {
        return this.drugPurchaseBeans;
    }
    
    public void setDrugPurchaseBeans(Set<DrugPurchaseBean> drugPurchaseBeans) {
        this.drugPurchaseBeans = drugPurchaseBeans;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="userBean")

    public Set<DrugSalesBean> getDrugSalesBeans() {
        return this.drugSalesBeans;
    }
    
    public void setDrugSalesBeans(Set<DrugSalesBean> drugSalesBeans) {
        this.drugSalesBeans = drugSalesBeans;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="userBean")

    public Set<ReturnsBean> getReturnsBeans() {
        return this.returnsBeans;
    }
    
    public void setReturnsBeans(Set<ReturnsBean> returnsBeans) {
        this.returnsBeans = returnsBeans;
    }
   








}