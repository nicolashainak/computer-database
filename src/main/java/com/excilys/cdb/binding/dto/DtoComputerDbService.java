package com.excilys.cdb.binding.dto;

import java.sql.Date;
import java.time.LocalDate;


public class DtoComputerDbService {

	
    @Override
	public String toString() {
		return "DtoComputerDbService [name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + company + "]";
	}


	private String name  ;
    private Date introduced ;
    private Date discontinued ;
    private DtoCompanyDbService company  ;
    
    
    public DtoComputerDbService(String name,LocalDate introduced,LocalDate discontinued, DtoCompanyDbService company ) {
    	this.name=name;
    	if (introduced==null) {
    	this.introduced=null;
    	}else {
        	this.introduced=Date.valueOf(introduced);
        	
    	}
    	if (discontinued==null) {
        	this.discontinued=null;
        	}else {
            	this.discontinued=Date.valueOf(discontinued);
            	
        	}
    	
    	this.company=company;
    	
    }

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getIntroduced() {
		return introduced;
	}


	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}


	public Date getDiscontinued() {
		return discontinued;
	}


	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}


	public DtoCompanyDbService getCompany() {
		return company;
	}


	public void setCompany(DtoCompanyDbService company) {
		this.company = company;
	}
	
	
	
	
}
	

