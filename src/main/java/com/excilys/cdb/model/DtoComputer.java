package com.excilys.cdb.model;

public class DtoComputer {
	private String name;
	private String introduced;
	private String discontnued;
	private String company;
	
	public DtoComputer() {
	}
	public DtoComputer( String name , String introduced,String discontinued,String company) {
		this.name=name;
		this.introduced=introduced;
		this.discontnued=discontinued;
		this.company=company;
	}
	
}
