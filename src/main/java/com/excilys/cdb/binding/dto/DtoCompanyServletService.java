package com.excilys.cdb.binding.dto;

import com.excilys.cdb.service.Service;

public class DtoCompanyServletService {
	private int id;
	private String name;

	public DtoCompanyServletService(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public DtoCompanyServletService(int id) {

		this.name = Service.getInstance().getCompanyById(id).getName();
	}

	public DtoCompanyServletService(String name) {
		this.id = Service.getInstance().getCompanyByName(name).getId();
		;
		this.name = name;
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
		this.name = name;
	}

	public String toString() {
		String str = "Id = " + this.id + " Name = " + this.name;
		return str;
	}

}
