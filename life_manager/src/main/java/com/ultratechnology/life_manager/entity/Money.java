package com.ultratechnology.life_manager.entity;

import java.io.Serializable;
import java.math.BigDecimal;

//the entity of money object
public class Money implements Serializable{

	private static final long serialVersionUID = -5514622461865297518L;
	
	private Integer id;
	private String date_consumed;
	private Integer year;
	private Integer month;
	private Integer day;
	private Integer weekday;
	private BigDecimal amount;
	private String item;
	private String shop_name;
	private String category;
	private String payment;
	private String notes;
	
	
	public String getDate_consumed() {
		return date_consumed;
	}
	public void setDate_consumed(String date_consumed) {
		this.date_consumed = date_consumed;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getWeekday() {
		return weekday;
	}
	public void setWeekday(Integer weekday) {
		this.weekday = weekday;
	}
	@Override
	public String toString() {
		return "Money [id=" + id + ", date_consumed=" + date_consumed + ", year=" + year + ", month=" + month + ", day="
				+ day + ", weekday=" + weekday + ", amount=" + amount + ", item=" + item + ", shop_name=" + shop_name
				+ ", category=" + category + ", payment=" + payment + ", notes=" + notes + "]";
	}
	
	

}
