package com.excilys.cdb.binding.dto;

public class DtoComputerServletService {
	@Override
	public String toString() {
		return "DtoComputer [name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + company + "]";
	}
	private String name;
	private String introduced;
	private String discontinued;
	private DtoCompanyServletService company;
	
	public DtoComputerServletService() {
	}
	public DtoComputerServletService( String name , String introduced,String discontinued,DtoCompanyServletService company) {
		this.name=name;
		this.introduced=introduced;
		this.discontinued=discontinued;
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
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public DtoCompanyServletService getCompany() {
		return company;
	}
	public void setCompany(DtoCompanyServletService company) {
		this.company = company;
	}
	
}
