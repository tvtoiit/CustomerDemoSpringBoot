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

@Entity
@Table(name = "MSTCUSTOMER")
public class MstCustomer {
	@Id
    @Column(name = "CUSTOMER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId; 

    @Column(name = "CUSTOMER_NAME", length = 50)
    private String customerName;

    @Column(name = "SEX", length = 1)
    private String sex;

    @Column(name = "BIRTHDAY", length = 10)
    private String birthDay;

    @Column(name = "EMAIL", length = 40)
    private String email;

    @Column(name = "ADDRESS", length = 256)
    private String address;

    @Column(name = "DELETE_YMD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteYmd;

    @Column(name = "INSERT_YMD", updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertYmd;

    @Column(name = "INSERT_PSN_CD", columnDefinition = "numeric(5,0)")
    private Date insertPsnCd;

    @Column(name = "UPDATE_YMD", updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateYmd;

    @Column(name = "UPDATE_PSN_CD", columnDefinition = "numeric(5,0)")
    private BigDecimal updatePsnCd;

    public MstCustomer() {
    	super();
    }
    
	public MstCustomer(int customerId, String customerName, String sex, String birthDay, String email, String address,
			Date deleteYmd, Date insertYmd, Date insertPsnCd, Date updateYmd, BigDecimal updatePsnCd) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.sex = sex;
		this.birthDay = birthDay;
		this.email = email;
		this.address = address;
		this.deleteYmd = deleteYmd;
		this.insertYmd = insertYmd;
		this.insertPsnCd = insertPsnCd;
		this.updateYmd = updateYmd;
		this.updatePsnCd = updatePsnCd;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDeleteYmd() {
		return deleteYmd;
	}

	public void setDeleteYmd(Date deleteYmd) {
		this.deleteYmd = deleteYmd;
	}

	public Date getInsertYmd() {
		return insertYmd;
	}

	public void setInsertYmd(Date insertYmd) {
		this.insertYmd = insertYmd;
	}

	public Date getInsertPsnCd() {
		return insertPsnCd;
	}

	public void setInsertPsnCd(Date insertPsnCd) {
		this.insertPsnCd = insertPsnCd;
	}

	public Date getUpdateYmd() {
		return updateYmd;
	}

	public void setUpdateYmd(Date updateYmd) {
		this.updateYmd = updateYmd;
	}

	public BigDecimal getUpdatePsnCd() {
		return updatePsnCd;
	}

	public void setUpdatePsnCd(BigDecimal updatePsnCd) {
		this.updatePsnCd = updatePsnCd;
	}
}
