package com.example.CustomerSystem.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "MSTUSER")
public class MstUser {
	 @Id
	 @Column(name = "PSN_CD")
	    // GeneratedValue annotation indicates that the value for this field will be generated by the database
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int psnCd;

	    @Column(name = "USERID", length = 8)
	    @NotEmpty
	    private String userId;

	    @Column(name = "PASSWORD", length = 8)
	    private String password;

	    @Column(name = "USERNAME", length = 40)
	    private String username;

	    @Column(name = "DELETE_YMD")
	    // Temporal annotation specifies the type of temporal data for this field
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date deleteYmd;

	    @Column(name = "INSERT_YMD", updatable = false, insertable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date insertYmd;

	    @Column(name = "INSERT_PSN_CD", columnDefinition = "numeric(5,0)")
	    private BigDecimal insertPsnCd;

	    @Column(name = "UPDATE_YMD", updatable = false, insertable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date updateYmd;

	    @Column(name = "UPDATE_PSN_CD", columnDefinition = "numeric(5,0)")
	    private BigDecimal updatePsnCd;

	    // Default constructor required by JPA
	    public MstUser() {
	        super();
	    }

	    // Parameterized constructor for creating instances with initial values
	    public MstUser(int psnCd, String userId, String password, String username, Date deleteYmd, Date insertYmd,
	            BigDecimal insertPsnCd, Date updateYmd, BigDecimal updatePsnCd) {
	        super();
	        this.psnCd = psnCd;
	        this.userId = userId;
	        this.password = password;
	        this.username = username;
	        this.deleteYmd = deleteYmd;
	        this.insertYmd = insertYmd;
	        this.insertPsnCd = insertPsnCd;
	        this.updateYmd = updateYmd;
	        this.updatePsnCd = updatePsnCd;
	    }
	    
	 // Getter method for retrieving the value of psnCd
	    public int getPsnCd() {
	        return psnCd;
	    }

	    // Setter method for updating the value of psnCd
	    public void setPsnCd(int psnCd) {
	        this.psnCd = psnCd;
	    }

	    // Getter method for retrieving the value of userId
	    public String getUserId() {
	        return userId;
	    }

	    // Setter method for updating the value of userId
	    public void setUserId(String userId) {
	        this.userId = userId;
	    }

	    // Getter method for retrieving the value of password
	    public String getPassword() {
	        return password;
	    }

	    // Setter method for updating the value of password
	    public void setPassword(String password) {
	        this.password = password;
	    }

	    // Getter method for retrieving the value of username
	    public String getUsername() {
	        return username;
	    }

	    // Setter method for updating the value of username
	    public void setUsername(String username) {
	        this.username = username;
	    }

	    // Getter method for retrieving the value of deleteYmd
	    public Date getDeleteYmd() {
	        return deleteYmd;
	    }

	    // Setter method for updating the value of deleteYmd
	    public void setDeleteYmd(Date deleteYmd) {
	        this.deleteYmd = deleteYmd;
	    }

	    // Getter method for retrieving the value of insertYmd
	    public Date getInsertYmd() {
	        return insertYmd;
	    }

	    // Setter method for updating the value of insertYmd
	    public void setInsertYmd(Date insertYmd) {
	        this.insertYmd = insertYmd;
	    }

	    // Getter method for retrieving the value of insertPsnCd
	    public BigDecimal getInsertPsnCd() {
	        return insertPsnCd;
	    }

	    // Setter method for updating the value of insertPsnCd
	    public void setInsertPsnCd(BigDecimal insertPsnCd) {
	        this.insertPsnCd = insertPsnCd;
	    }

	    // Getter method for retrieving the value of updateYmd
	    public Date getUpdateYmd() {
	        return updateYmd;
	    }

	    // Setter method for updating the value of updateYmd
	    public void setUpdateYmd(Date updateYmd) {
	        this.updateYmd = updateYmd;
	    }

	    // Getter method for retrieving the value of updatePsnCd
	    public BigDecimal getUpdatePsnCd() {
	        return updatePsnCd;
	    }

	    // Setter method for updating the value of updatePsnCd
	    public void setUpdatePsnCd(BigDecimal updatePsnCd) {
	        this.updatePsnCd = updatePsnCd;
	    }
	    
}
