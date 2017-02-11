package com.fq.po;



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

import com.fq.po.DrugBean;


/**
 * DrugBuy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_drugbuy"
    ,catalog="yaodian"
)

public class DrugBuy  implements java.io.Serializable {


    // Fields    

     private String drugbuyId;
     private String drugbuyCode;
     private DrugBean drugBean;
     private String modifier;
     private Date modifyTime;
     private String mount;


    // Constructors

    /** default constructor */
    public DrugBuy() {
    }

	/** minimal constructor */
    public DrugBuy(String drugbuyId) {
        this.drugbuyId = drugbuyId;
    }
    
    /** full constructor */
    public DrugBuy(String drugbuyId, String drugbuyCode,DrugBean drugBean, String modifier, Date modifyTime, String mount) {
        this.drugbuyId = drugbuyId;
        this.drugbuyCode = drugbuyCode;
        this.drugBean = drugBean;
        this.modifier = modifier;
        this.modifyTime = modifyTime;
        this.mount = mount;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="DRUGBUY_ID", unique=true, nullable=false, length=32)

    public String getDrugbuyId() {
        return this.drugbuyId;
    }
    
    public void setDrugbuyId(String drugbuyId) {
        this.drugbuyId = drugbuyId;
    }
    @Column(name="DRUGBUY_CODE", length=255)
    
    public String getDrugbuyCode() {
		return drugbuyCode;
	}

	public void setDrugbuyCode(String drugbuyCode) {
		this.drugbuyCode = drugbuyCode;
	}

	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="DRUG_ID")

    public DrugBean getDrugBean() {
        return this.drugBean;
    }
    
	public void setDrugBean(DrugBean drugBean) {
        this.drugBean = drugBean;
    }
    
    @Column(name="MODIFIER", length=10)

    public String getModifier() {
        return this.modifier;
    }
    
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="MODIFY_TIME", length=10)

    public Date getModifyTime() {
        return this.modifyTime;
    }
    
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    @Column(name="MOUNT")

    public String getMount() {
        return this.mount;
    }
    
    public void setMount(String mount) {
        this.mount = mount;
    }
   








}