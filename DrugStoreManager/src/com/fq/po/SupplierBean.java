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
 * SupplierBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_supplier"
    ,catalog="yaodian"
)

public class SupplierBean  implements java.io.Serializable {


    // Fields    

     private String supplierId;
     private String supplierCode;
     private String supplier;
     private String name;
     private String tel;
     private String status;
     private Set<DrugPurchaseBean> drugPurchaseBeans = new HashSet<DrugPurchaseBean>(0);


    // Constructors

    /** default constructor */
    public SupplierBean() {
    }

	/** minimal constructor */
    public SupplierBean(String supplierId, String supplierCode, String supplier) {
        this.supplierId = supplierId;
        this.supplierCode = supplierCode;
        this.supplier = supplier;
    }
    
    /** full constructor */
    public SupplierBean(String supplierId, String supplierCode, String supplier, String name, String tel, String status, Set<DrugPurchaseBean> drugPurchaseBeans) {
        this.supplierId = supplierId;
        this.supplierCode = supplierCode;
        this.supplier = supplier;
        this.name = name;
        this.tel = tel;
        this.status = status;
        this.drugPurchaseBeans = drugPurchaseBeans;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="SUPPLIER_ID", unique=true, nullable=false, length=32)

    public String getSupplierId() {
        return this.supplierId;
    }
    
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    
    @Column(name="SUPPLIER_CODE", nullable=false, length=32)

    public String getSupplierCode() {
        return this.supplierCode;
    }
    
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }
    
    @Column(name="SUPPLIER", nullable=false, length=20)

    public String getSupplier() {
        return this.supplier;
    }
    
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    
    @Column(name="NAME", length=5)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="TEL", length=11)

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    @Column(name="STATUS", length=1)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="supplierBean")

    public Set<DrugPurchaseBean> getDrugPurchaseBeans() {
        return this.drugPurchaseBeans;
    }
    
    public void setDrugPurchaseBeans(Set<DrugPurchaseBean> drugPurchaseBeans) {
        this.drugPurchaseBeans = drugPurchaseBeans;
    }
   








}