package com.datastax.samples.javamapper.entity;

public class Person {
	
	private String name;
	@Override
	public String toString() {
		return "Person [name=" + name + ", city=" + city + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	private String  city;

}
