package com.cxc.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 * author:chenxinchao
 * date:2016-09-21 15:51
 * desc:com.cxc.domain
 */
public class User implements Serializable {

	private int id;
	private String name;
	private int gender;
	private int age;
	private int flag;
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	@Override public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", gender=" + gender +
				", age=" + age +
				", flag=" + flag +
				", createTime=" + createTime +
				'}';
	}
}
