package com.excilys.cdb.binding.dto;
import org.springframework.beans.factory.annotation.Autowired;



public class DtoCompanyOutput {
	private int id;
	private String name;
	
	public DtoCompanyOutput(int id, String name) {
		this.id = id;
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
