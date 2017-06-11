package com.cxc.domain;

import java.io.Serializable;
import java.sql.Timestamp;

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
	private Integer sendType;
	private Integer friendFlag;
	private Integer sex;
	private Timestamp createTime;
	private Integer deleted;
	private Integer creator;

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
}
