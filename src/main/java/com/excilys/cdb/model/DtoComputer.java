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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontnued() {
		return discontnued;
	}
	public void setDiscontnued(String discontnued) {
		this.discontnued = discontnued;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
}
