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

	/** minimal constructor */
    public DrugUnitBean(String unitnameId) {
        this.unitnameId = unitnameId;
    }
    
    /** full constructor */
    public DrugUnitBean(String unitnameId, String unitname, Set<DrugBean> drugBeans) {
        this.unitnameId = unitnameId;
        this.unitname = unitname;
        this.drugBeans = drugBeans;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="UNITNAME_ID", unique=true, nullable=false, length=32)

    public String getUnitnameId() {
        return this.unitnameId;
    }
    
    public void setUnitnameId(String unitnameId) {
        this.unitnameId = unitnameId;
    }
    
    @Column(name="UNITNAME", length=15)

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((unitnameId == null) ? 0 : unitnameId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DrugUnitBean)) {
			return false;
		}
		DrugUnitBean other = (DrugUnitBean) obj;
		if (unitnameId == null) {
			if (other.unitnameId != null) {
				return false;
			}
		} else if (!unitnameId.equals(other.unitnameId)) {
			return false;
		}
		return true;
	}
   








}