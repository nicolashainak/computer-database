package com.excilys.cdb.binding.dto;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.MyService;



public class DtoCompanyDbService {
	private Integer id;
	private String name;
	
	public DtoCompanyDbService(int id, String name) {
		this.id=id;
		this.name=name;
	}
	public DtoCompanyDbService(Computer computer) {
	
		if (computer.getId().isPresent()) {
			this.id=computer.getId().get();	
		}else {
			this.id=null;
		}
		this.name=computer.getName();
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


