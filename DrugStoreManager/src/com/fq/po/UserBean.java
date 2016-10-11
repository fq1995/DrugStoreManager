package com.fq.po;

// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * UserBean entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="tbl_users"
    ,catalog="yaodian"
)

public class UserBean  implements java.io.Serializable {


    // Fields    

     private Integer userid;
     private RoleBean roleBean;
     private Integer usercode;
     private String username;
     private String password;
     private Date addtime;
     private Integer status;


    // Constructors

    /** default constructor */
    public UserBean() {
    }

	/** minimal constructor */
    public UserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    /** full constructor */
    public UserBean(RoleBean roleBean, Integer usercode, String username, String password, Date addtime, Integer status) {
        this.roleBean = roleBean;
        this.usercode = usercode;
        this.username = username;
        this.password = password;
        this.addtime = addtime;
        this.status = status;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="userid", unique=true, nullable=false)

    public Integer getUserid() {
        return this.userid;
    }
    
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="rolecode")

    public RoleBean getRoleBean() {
        return this.roleBean;
    }
    
    public void setRoleBean(RoleBean roleBean) {
        this.roleBean = roleBean;
    }
    
    @Column(name="usercode")

    public Integer getUsercode() {
        return this.usercode;
    }
    
    public void setUsercode(Integer usercode) {
        this.usercode = usercode;
    }
    
    @Column(name="username", nullable=false, length=20)

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="password", nullable=false, length=20)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="addtime", length=10)

    public Date getAddtime() {
        return this.addtime;
    }
    
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
    
    @Column(name="status")

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
   








}