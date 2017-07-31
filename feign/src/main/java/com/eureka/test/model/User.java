package com.eureka.test.model;

public class User {
	private Integer id;
	private String name;
	private Integer age;
	private Float ht;
	private Float wg;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Float getHt() {
		return ht;
	}
	public void setHt(Float ht) {
		this.ht = ht;
	}
	public Float getWg() {
		return wg;
	}
	public void setWg(Float wg) {
		this.wg = wg;
	}
}
