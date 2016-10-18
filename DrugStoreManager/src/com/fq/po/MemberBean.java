package com.fq.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
     private byte[] age;
     private String memberLevel;
     private Integer integration;
     private String suppliertel;
     private byte[] address;


    // Constructors

    /** default constructor */
    public MemberBean() {
    }

    
    /** full constructor */
    public MemberBean(String memberCode, String memberName, String sex, byte[] age, String memberLevel, Integer integration, String suppliertel, byte[] address) {
        this.memberCode = memberCode;
        this.memberName = memberName;
        this.sex = sex;
        this.age = age;
        this.memberLevel = memberLevel;
        this.integration = integration;
        this.suppliertel = suppliertel;
        this.address = address;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
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
    
    @Column(name="AGE")

    public byte[] getAge() {
        return this.age;
    }
    
    public void setAge(byte[] age) {
        this.age = age;
    }
    
    @Column(name="MEMBER_LEVEL", length=1)

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
    
    @Column(name="SUPPLIERTEL", length=11)

    public String getSuppliertel() {
        return this.suppliertel;
    }
    
    public void setSuppliertel(String suppliertel) {
        this.suppliertel = suppliertel;
    }
    
    @Column(name="ADDRESS")

    public byte[] getAddress() {
        return this.address;
    }
    
    public void setAddress(byte[] address) {
        this.address = address;
    }
   








}