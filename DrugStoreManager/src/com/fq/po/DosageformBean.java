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
 * DosageformBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_dosageform"
    ,catalog="yaodian"
)

public class DosageformBean  implements java.io.Serializable {


    // Fields    

     private String dosageformId;
     private String dosageform;
     private Set<DrugBean> drugBeans = new HashSet<DrugBean>(0);


    // Constructors

    /** default constructor */
    public DosageformBean() {
    }

	/** minimal constructor */
    public DosageformBean(String dosageformId) {
        this.dosageformId = dosageformId;
    }
    
    /** full constructor */
    public DosageformBean(String dosageformId, String dosageform, Set<DrugBean> drugBeans) {
        this.dosageformId = dosageformId;
        this.dosageform = dosageform;
        this.drugBeans = drugBeans;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="DOSAGEFORM_ID", unique=true, nullable=false, length=32)

    public String getDosageformId() {
        return this.dosageformId;
    }
    
    public void setDosageformId(String dosageformId) {
        this.dosageformId = dosageformId;
    }
    
    @Column(name="DOSAGEFORM", length=10)

    public String getDosageform() {
        return this.dosageform;
    }
    
    public void setDosageform(String dosageform) {
        this.dosageform = dosageform;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="dosageformBean")

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
		result = prime * result + ((dosageformId == null) ? 0 : dosageformId.hashCode());
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
		if (!(obj instanceof DosageformBean)) {
			return false;
		}
		DosageformBean other = (DosageformBean) obj;
		if (dosageformId == null) {
			if (other.dosageformId != null) {
				return false;
			}
		} else if (!dosageformId.equals(other.dosageformId)) {
			return false;
		}
		return true;
	}
   








}