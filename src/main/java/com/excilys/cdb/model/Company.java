package com.excilys.cdb.model;

public class Company {
	private int id;
	private String name;
	
	public Company(int id, String name) {
		this.id=id;
		this.name=name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String toString() {
		String str = "Id = " + this.id + " Name = " + this.name;
		return str;
	}
}
