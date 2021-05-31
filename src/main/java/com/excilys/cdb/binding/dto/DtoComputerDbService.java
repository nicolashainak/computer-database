package com.excilys.cdb.binding.dto;

import java.sql.Date;
import java.time.LocalDate;

import com.excilys.cdb.model.Company;

public class DtoComputerDbService {

	
    private String name  ;
    private Date introduced ;
    private Date discontinued ;
    private DtoCompanyDbService company  ;
    
    
    public DtoComputerDbService(DtoComputerDbServiceBuilder computerBuilder) {
    	super();
    	this.name=computerBuilder.name;
    	this.introduced=computerBuilder.introduced;
    	this.discontinued=computerBuilder.discontinued;
    	this.company=computerBuilder.company;
    	
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
	
	
	
	public static class DtoComputerDbServiceBuilder{
		private String name ;
	    private Date introduced ;
	    private Date discontinued ;
	    private DtoCompanyDbService company ;
	    
	    public DtoComputerDbServiceBuilder(String name) {
	    	this.name=name;
	    }
	    
	    public DtoComputerDbServiceBuilder withIntroduced(Date introduced) {
	    	this.introduced=introduced;
	    	return this;
	    }
	    
	    public DtoComputerDbServiceBuilder withDiscontinued(Date discontinued) {
	    	this.discontinued=discontinued;
	    	return this;
	    }
	    
	    public DtoComputerDbServiceBuilder withCompany(DtoCompanyDbService company) {
	    	this.company=company;
	    	return this;
	    }
	    
	    public DtoComputerDbService build() {
	    	return new DtoComputerDbService(this);
	    }
	    
	}
	
}
