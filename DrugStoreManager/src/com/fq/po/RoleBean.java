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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * RoleBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_role"
    ,catalog="yaodian"
)

public class RoleBean  implements java.io.Serializable {


    // Fields    

     private Integer roleid;
     private PermissionBean permissionBean;
     private Integer rolecode;
     private String rolename;
     private Set<UserBean> userBeans = new HashSet<UserBean>(0);


    // Constructors

    /** default constructor */
    public RoleBean() {
    }

    
    /** full constructor */
    public RoleBean(PermissionBean permissionBean, Integer rolecode, String rolename, Set<UserBean> userBeans) {
        this.permissionBean = permissionBean;
        this.rolecode = rolecode;
        this.rolename = rolename;
        this.userBeans = userBeans;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="ROLEID", unique=true, nullable=false)

    public Integer getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="PERID")

    public PermissionBean getPermissionBean() {
        return this.permissionBean;
    }
    
    public void setPermissionBean(PermissionBean permissionBean) {
        this.permissionBean = permissionBean;
    }
    
    @Column(name="ROLECODE")

    public Integer getRolecode() {
        return this.rolecode;
    }
    
    public void setRolecode(Integer rolecode) {
        this.rolecode = rolecode;
    }
    
    @Column(name="ROLENAME", length=10)

    public String getRolename() {
        return this.rolename;
    }
    
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="roleBean")

    public Set<UserBean> getUserBeans() {
        return this.userBeans;
    }
    
    public void setUserBeans(Set<UserBean> userBeans) {
        this.userBeans = userBeans;
    }
   








}