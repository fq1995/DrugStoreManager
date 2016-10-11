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
 * Permission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_permission"
    ,catalog="yaodian"
)

public class Permission  implements java.io.Serializable {


    // Fields    

     private Integer perid;
     private Integer percode;
     private String pername;
     private Set<RoleBean> roleBeans = new HashSet<RoleBean>(0);


    // Constructors

    /** default constructor */
    public Permission() {
    }

    
    /** full constructor */
    public Permission(Integer percode, String pername, Set<RoleBean> roleBeans) {
        this.percode = percode;
        this.pername = pername;
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
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="permission")

    public Set<RoleBean> getRoleBeans() {
        return this.roleBeans;
    }
    
    public void setRoleBeans(Set<RoleBean> roleBeans) {
        this.roleBeans = roleBeans;
    }
   








}