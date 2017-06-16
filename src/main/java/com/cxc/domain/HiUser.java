package com.cxc.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 * author:chenxinchao date:2016-09-21 15:51 desc:com.cxc.domain
 */
public class HiUser implements Serializable {

    protected Integer id;

    protected Integer version;

    protected String userName;

    protected String password;

    protected Integer country;

    protected Integer timeZone;

    protected Integer accountEnabled;

    protected Integer accountLocked;

    protected java.util.Date expiredDate;

    protected Integer credentialsExpired;

    protected String fullName;

    private Integer org;

    protected Integer gender;

    protected String address;

    protected String phone;

    protected String mobile;

    protected String zip;

    protected String SSN;

    protected String mail;

    protected Integer userMgrType;

    protected String notifyMode;

    protected String description;

    protected Integer deleted = Integer.valueOf(0);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Integer getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	public Integer getAccountEnabled() {
		return accountEnabled;
	}

	public void setAccountEnabled(Integer accountEnabled) {
		this.accountEnabled = accountEnabled;
	}

	public Integer getAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(Integer accountLocked) {
		this.accountLocked = accountLocked;
	}

	public java.util.Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(java.util.Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Integer getCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(Integer credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getOrg() {
		return org;
	}

	public void setOrg(Integer org) {
		this.org = org;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String SSN) {
		this.SSN = SSN;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getUserMgrType() {
		return userMgrType;
	}

	public void setUserMgrType(Integer userMgrType) {
		this.userMgrType = userMgrType;
	}

	public String getNotifyMode() {
		return notifyMode;
	}

	public void setNotifyMode(String notifyMode) {
		this.notifyMode = notifyMode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
