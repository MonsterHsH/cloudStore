package com.cloudstore.sql.table;

import java.util.Date;

public class User {
	private String user_id;
	private String name;
	private byte sex;
	private Date birth;
	private String head_picture_url;
	private byte profession;
	private String profession_number;
	private String email;
	private String school_id;
	private String phone;
	private String qq_num;
	private String wechat;
	private int point;
	private float amount_money;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte getSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getHead_picture_url() {
		return head_picture_url;
	}
	public void setHead_picture_url(String head_picture_url) {
		this.head_picture_url = head_picture_url;
	}
	public byte getProfession() {
		return profession;
	}
	public void setProfession(byte profession) {
		this.profession = profession;
	}
	public String getProfession_number() {
		return profession_number;
	}
	public void setProfession_number(String profession_number) {
		this.profession_number = profession_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSchool_id() {
		return school_id;
	}
	public void setSchool_id(String school_id) {
		this.school_id = school_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq_num() {
		return qq_num;
	}
	public void setQq_num(String qq_num) {
		this.qq_num = qq_num;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public float getAmount_money() {
		return amount_money;
	}
	public void setAmount_money(float amount_money) {
		this.amount_money = amount_money;
	}
	@Override
	public String toString() {
		return "{user_id:" + user_id + ", name:" + name + ", sex:" + sex + ", birth:" + birth
				+ ", head_picture_url:" + head_picture_url + ", profession=" + profession + ", profession_number:"
				+ profession_number + ", email:" + email + ", school_id:" + school_id + ", phone:" + phone + ", qq_num:"
				+ qq_num + ", wechat:" + wechat + ", point:" + point + ", amount_money:" + amount_money + "}";
	}
	
}
