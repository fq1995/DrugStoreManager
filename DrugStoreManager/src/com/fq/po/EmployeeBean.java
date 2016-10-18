package com.fq.po;

// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * EmployeeBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_employee"
    ,catalog="yaodian"
)

public class EmployeeBean  implements java.io.Serializable {


    // Fields    

     private String empId;
     private String empCode;
     private String empName;
     private String sex;
     private Integer age;
     private String tel;
     private String title;
     private Date startdate;


    // Constructors

    /** default constructor */
    public EmployeeBean() {
    }

	/** minimal constructor */
    public EmployeeBean(String empId, String empCode) {
        this.empId = empId;
        this.empCode = empCode;
    }
    
    /** full constructor */
    public EmployeeBean(String empId, String empCode, String empName, String sex, Integer age, String tel, String title, Date startdate) {
        this.empId = empId;
        this.empCode = empCode;
        this.empName = empName;
        this.sex = sex;
        this.age = age;
        this.tel = tel;
        this.title = title;
        this.startdate = startdate;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="EMP_ID", unique=true, nullable=false, length=32)

    public String getEmpId() {
        return this.empId;
    }
    
    public void setEmpId(String empId) {
        this.empId = empId;
    }
    
    @Column(name="EMP_CODE", nullable=false, length=32)

    public String getEmpCode() {
        return this.empCode;
    }
    
    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
    
    @Column(name="EMP_NAME", length=10)

    public String getEmpName() {
        return this.empName;
    }
    
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    @Column(name="SEX", length=1)

    public String getSex() {
        return this.sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    @Column(name="AGE")

    public Integer getAge() {
        return this.age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    @Column(name="TEL", length=11)

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    @Column(name="TITLE", length=20)

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="STARTDATE", length=10)

    public Date getStartdate() {
        return this.startdate;
    }
    
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }
   








}