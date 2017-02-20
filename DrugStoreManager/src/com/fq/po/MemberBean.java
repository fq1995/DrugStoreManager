package com.fq.po;

// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * MemberBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_member"
    ,catalog="yaodian"
)

public class MemberBean  implements java.io.Serializable {


    // Fields    

     private String memberId;
     private String memberCode;
     private String memberName;
     private String sex;
     private String age;
     private String memberLevel;
     private Integer integration;
     private String suppliertel;
     private String address;
     private Set<DrugSalesBean> drugSalesBeans = new HashSet<DrugSalesBean>(0);


    // Constructors

    /** default constructor */
    public MemberBean() {
    }

	/** minimal constructor */
    public MemberBean(String memberId) {
        this.memberId = memberId;
    }
    
    public MemberBean(String memberName, String memberLevel, Integer integration) {
		super();
		this.memberName = memberName;
		this.memberLevel = memberLevel;
		this.integration = integration;
	}

	/** full constructor */
    public MemberBean(String memberId, String memberCode, String memberName, String sex, String age, String memberLevel, Integer integration, String suppliertel, String address, Set<DrugSalesBean> drugSalesBeans) {
        this.memberId = memberId;
        this.memberCode = memberCode;
        this.memberName = memberName;
        this.sex = sex;
        this.age = age;
        this.memberLevel = memberLevel;
        this.integration = integration;
        this.suppliertel = suppliertel;
        this.address = address;
        this.drugSalesBeans = drugSalesBeans;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="MEMBER_ID", unique=true, nullable=false, length=32)

    public String getMemberId() {
        return this.memberId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    @Column(name="MEMBER_CODE", length=32)

    public String getMemberCode() {
        return this.memberCode;
    }
    
    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }
    
    @Column(name="MEMBER_NAME", length=10)

    public String getMemberName() {
        return this.memberName;
    }
    
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
    
    @Column(name="SEX", length=1)

    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    @Column(name="AGE", length=3)

    public String getAge() {
        return this.age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    @Column(name="MEMBER_LEVEL", length=10)

    public String getMemberLevel() {
        return this.memberLevel;
    }
    
    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }
    
    @Column(name="INTEGRATION")

    public Integer getIntegration() {
        return this.integration;
    }
    
    public void setIntegration(Integer integration) {
        this.integration = integration;
    }
    
    @Column(name="SUPPLIERTEL", length=20)

    public String getSuppliertel() {
        return this.suppliertel;
    }
    
    public void setSuppliertel(String suppliertel) {
        this.suppliertel = suppliertel;
    }
    
    @Column(name="ADDRESS", length=30)

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="memberBean")

    public Set<DrugSalesBean> getDrugSalesBeans() {
        return this.drugSalesBeans;
    }
    
    public void setDrugSalesBeans(Set<DrugSalesBean> drugSalesBeans) {
        this.drugSalesBeans = drugSalesBeans;
    }
   








}