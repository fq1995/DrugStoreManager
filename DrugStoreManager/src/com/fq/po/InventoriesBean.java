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
 * InventoriesBean entity. @author MyEclipse Persistence Tools
 * 库存
 */
@Entity
@Table(name="tbl_inventories"
    ,catalog="yaodian"
)

public class InventoriesBean  implements java.io.Serializable {


    // Fields    

     private String stockId;
     private DrugBean drugBean;
     private String stockCode;
     private Integer stocknumber;
     private Integer stocklimit;
     private Date date;
     private Set<BussinessBean> bussinessBeans = new HashSet<BussinessBean>(0);


    // Constructors

    /** default constructor */
    public InventoriesBean() {
    }

	/** minimal constructor */
    public InventoriesBean(String stockId) {
        this.stockId = stockId;
    }
    
    /** full constructor */
    public InventoriesBean(String stockId, DrugBean drugBean, String stockCode, Integer stocknumber, Integer stocklimit, Date date, Set<BussinessBean> bussinessBeans) {
        this.stockId = stockId;
        this.drugBean = drugBean;
        this.stockCode = stockCode;
        this.stocknumber = stocknumber;
        this.stocklimit = stocklimit;
        this.date = date;
        this.bussinessBeans = bussinessBeans;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="STOCK_ID", unique=true, nullable=false, length=32)

    public String getStockId() {
        return this.stockId;
    }
    
    public void setStockId(String stockId) {
        this.stockId = stockId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="DRUG_ID")

    public DrugBean getDrugBean() {
        return this.drugBean;
    }
    
    public void setDrugBean(DrugBean drugBean) {
        this.drugBean = drugBean;
    }
    
    @Column(name="STOCK_CODE", length=32)

    public String getStockCode() {
        return this.stockCode;
    }
    
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
    
    @Column(name="STOCKNUMBER")

    public Integer getStocknumber() {
        return this.stocknumber;
    }
    
    public void setStocknumber(Integer stocknumber) {
        this.stocknumber = stocknumber;
    }
    
    @Column(name="STOCKLIMIT")

    public Integer getStocklimit() {
        return this.stocklimit;
    }
    
    public void setStocklimit(Integer stocklimit) {
        this.stocklimit = stocklimit;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DATE", length=10)

    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="inventoriesBean")

    public Set<BussinessBean> getBussinessBeans() {
        return this.bussinessBeans;
    }
    
    public void setBussinessBeans(Set<BussinessBean> bussinessBeans) {
        this.bussinessBeans = bussinessBeans;
    }
   








}