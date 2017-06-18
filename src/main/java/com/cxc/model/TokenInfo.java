package com.cxc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * author:chenxinchao
 * date:2017-06-11 19:38
 * desc:com.cxc.model
 */
public class TokenInfo {

	@JsonProperty(value = "Uin")
	private String uin;
	@JsonProperty(value = "Sid")
	private String sid;
	@JsonProperty(value = "Skey")
	private String skey;
	@JsonProperty(value = "DeviceID")
	private String deviceID;

	@JsonIgnore
	private String passTicket;
	@JsonIgnore
	private String dataTicket;

	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getPassTicket() {
		return passTicket;
	}

	public void setPassTicket(String passTicket) {
		this.passTicket = passTicket;
	}

	public String getDataTicket() {
		return dataTicket;
	}

	public void setDataTicket(String dataTicket) {
		this.dataTicket = dataTicket;
	}
}
