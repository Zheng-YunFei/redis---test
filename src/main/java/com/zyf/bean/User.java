package com.zyf.bean;

import java.io.Serializable;

public class User implements Serializable {

	private int uid;
	private String uname;
	private String sex;
	private String phone;
	private String email;
	private String birthday;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", sex=" + sex + ", phone=" + phone + ", email=" + email
				+ ", birthday=" + birthday + "]";
	}

}
