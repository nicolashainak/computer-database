package com.excilys.cdb.binding.dto;

import com.excilys.cdb.service.Service;

public class DtoCompanyDbService {
	private int id;
	private String name;
	
	public DtoCompanyDbService(int id, String name) {
		this.id=id;
		this.name=name;
	}
	public DtoCompanyDbService(int id) {
		this.id=id;
		this.name=Service.getInstance().getCompanyById(id).getName();
	}
	public DtoCompanyDbService(String name) {
		this.id=Service.getInstance().getCompanyByName(name).getId();;
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


