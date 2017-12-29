package com.cloudstore.sql.table;


// int tinyint float long double String 



public class StorePayCount {
	private String store_id;
	private String wechat_count;
	private String alipay_count;
	private String bank_name;
	private String bank_count;
	private String bank_username;
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getWechat_count() {
		return wechat_count;
	}
	public void setWechat_count(String wechat_count) {
		this.wechat_count = wechat_count;
	}
	public String getAlipay_count() {
		return alipay_count;
	}
	public void setAlipay_count(String alipay_count) {
		this.alipay_count = alipay_count;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_count() {
		return bank_count;
	}
	public void setBank_count(String bank_count) {
		this.bank_count = bank_count;
	}
	public String getBank_username() {
		return bank_username;
	}
	public void setBank_username(String bank_username) {
		this.bank_username = bank_username;
	}
	@Override
	public String toString() {
		return "{store_id:" + store_id + ", wechat_count:" + wechat_count + ", alipay_count:"
				+ alipay_count + ", bank_name:" + bank_name + ", bank_count:" + bank_count + ", bank_username:"
				+ bank_username + "}";
	}
	
	
	
	
	
}
