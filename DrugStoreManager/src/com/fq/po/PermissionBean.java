package com.fq.po;

// default package

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
 * PermissionBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_permission"
    ,catalog="yaodian"
)

public class PermissionBean  implements java.io.Serializable {


    // Fields    

     private Integer perid;
     private Integer percode;
     private String pername;
     private byte[] remark;
     private Set<RoleBean> roleBeans = new HashSet<RoleBean>(0);


    // Constructors

    /** default constructor */
    public PermissionBean() {
    }

    
    /** full constructor */
    public PermissionBean(Integer percode, String pername, byte[] remark, Set<RoleBean> roleBeans) {
        this.percode = percode;
        this.pername = pername;
        this.remark = remark;
        this.roleBeans = roleBeans;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="PERID", unique=true, nullable=false)

    public Integer getPerid() {
        return this.perid;
    }
    
    public void setPerid(Integer perid) {
        this.perid = perid;
    }
    
    @Column(name="PERCODE")

    public Integer getPercode() {
        return this.percode;
    }
    
    public void setPercode(Integer percode) {
        this.percode = percode;
    }
    
    @Column(name="PERNAME", length=20)

    public String getPername() {
        return this.pername;
    }
    
    public void setPername(String pername) {
        this.pername = pername;
    }
    
    @Column(name="REMARK")

    public byte[] getRemark() {
        return this.remark;
    }
    
    public void setRemark(byte[] remark) {
        this.remark = remark;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="permissionBean")

    public Set<RoleBean> getRoleBeans() {
        return this.roleBeans;
    }
    
    public void setRoleBeans(Set<RoleBean> roleBeans) {
        this.roleBeans = roleBeans;
    }
   








}