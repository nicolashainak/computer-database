package com.excilys.cdb.model;

import java.time.LocalDate;
import java.util.Optional;

public class Computer {
	private Integer id  ;
    private String name  ;
    private LocalDate introduced ;
    private LocalDate discontinued ;
    private Company company_id  ;
//without id 
    
    public Computer() {
    	
    };
	public Computer( String name,LocalDate introduced,LocalDate discontinued,Company company_id ) {
		this.id=null;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}
	//with id
	public Computer( Integer id,String name,LocalDate introduced,LocalDate discontinued,Company company_id ) {
		this.id=id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}
	
	public Optional<Integer> getId() {
		return Optional.ofNullable(this.id);
	}
	
	public String getName() {
		return this.name;
	}
	public LocalDate getIntroduced() {
		return this.introduced;
	}
	public LocalDate getDiscontinued() {
		return this.discontinued;
	}
	public Company getCompany_id() {
		return this.company_id;
	}
	public String toString() {
		String str = "Id= " +this.id+" Name = "+this.name+" Introduced = "+this.introduced+" Discontinued = "+this.discontinued+" Company id = "+this.company_id.getId()+" Company Name = "+this.company_id.getName();
		return str;	
	}
	
}
