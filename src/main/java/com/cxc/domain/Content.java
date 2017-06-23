package com.cxc.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * author:chenxinchao
 * date:2017-06-11 12:01
 * desc:com.cxc.domain
 */
public class Content implements Serializable {
	private Integer id;
	private  Integer version;
	private String text;
	private String picUrl;
	@Range(min = 0,max = 2,message = "sendType为1或2")
	private Integer sendType;
	@Range(min = 0,max = 2,message = "friendFlag为1或2")
	private Integer friendFlag;
	@Range(min = 0,max = 2,message = "sex只能在0-2之间")
	private Integer sex;
	private Timestamp createTime;
	private Integer deleted;
	private Integer creator;

	private String timeStr;

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public Integer getFriendFlag() {
		return friendFlag;
	}

	public void setFriendFlag(Integer friendFlag) {
		this.friendFlag = friendFlag;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
}
