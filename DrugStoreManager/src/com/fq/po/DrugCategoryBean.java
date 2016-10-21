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
 * DrugCategoryBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_drugcategory"
    ,catalog="yaodian"
)

public class DrugCategoryBean  implements java.io.Serializable {


    // Fields    

     private String categoryId;
     private String category;
     private Set<DrugBean> drugBeans = new HashSet<DrugBean>(0);


    // Constructors

    /** default constructor */
    public DrugCategoryBean() {
    }

	/** minimal constructor */
    public DrugCategoryBean(String categoryId) {
        this.categoryId = categoryId;
    }
    
    /** full constructor */
    public DrugCategoryBean(String categoryId, String category, Set<DrugBean> drugBeans) {
        this.categoryId = categoryId;
        this.category = category;
        this.drugBeans = drugBeans;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="CATEGORY_ID", unique=true, nullable=false, length=32)

    public String getCategoryId() {
        return this.categoryId;
    }
    
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
    @Column(name="CATEGORY", length=20)

    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="drugCategoryBean")

    public Set<DrugBean> getDrugBeans() {
        return this.drugBeans;
    }
    
    public void setDrugBeans(Set<DrugBean> drugBeans) {
        this.drugBeans = drugBeans;
    }
   








}