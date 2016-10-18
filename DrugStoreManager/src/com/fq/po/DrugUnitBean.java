package com.fq.po;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * DrugUnitBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_drugunit"
    ,catalog="yaodian"
)

public class DrugUnitBean  implements java.io.Serializable {


    // Fields    

     private String unitnameId;
     private String unitname;
     private Set<DrugBean> drugBeans = new HashSet<DrugBean>(0);


    // Constructors

    /** default constructor */
    public DrugUnitBean() {
    }

    
    /** full constructor */
    public DrugUnitBean(String unitname, Set<DrugBean> drugBeans) {
        this.unitname = unitname;
        this.drugBeans = drugBeans;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="UNITNAME_ID", unique=true, nullable=false, length=32)

    public String getUnitnameId() {
        return this.unitnameId;
    }
    
    public void setUnitnameId(String unitnameId) {
        this.unitnameId = unitnameId;
    }
    
    @Column(name="UNITNAME", length=5)

    public String getUnitname() {
        return this.unitname;
    }
    
    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="drugUnitBean")

    public Set<DrugBean> getDrugBeans() {
        return this.drugBeans;
    }
    
    public void setDrugBeans(Set<DrugBean> drugBeans) {
        this.drugBeans = drugBeans;
    }
   








}