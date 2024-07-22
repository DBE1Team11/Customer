package com.fastcampus.customer;

import java.time.LocalDateTime;

public class Customer {
	private String name;
	private String id; 
	private String pwd;
	private String phone;
	private String address;
	private String sex;
	private String email;
	private String job;
	private LocalDateTime date;
	
	
	public Customer(String name, String id, String pwd, String phone, String address, String sex, String email, String job) {
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.phone = phone;
		this.address = address;
		this.sex = sex;
		this.email = email;
		this.job = job;
		this.date = LocalDateTime.now();
	}


	public String getName() {
		return name;
	}


	public String getId() {
		return id;
	}


	public String getPwd() {
		return pwd;
	}


	public String getAddress() {
		return address;
	}


	public String getSex() {
		return sex;
	}


	public String getEmail() {
		return email;
	}


	public String getJob() {
		return job;
	}


	public LocalDateTime getDate() {
		return date;
	}

	public String getPhone() {
		return phone;
	}
}
