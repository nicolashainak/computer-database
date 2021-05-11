package model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Computer {
	private Integer id  ;
    private String name  ;
    private LocalDate introduced ;
    private LocalDate discontinued ;
    private int company_id  ;
//without id 
	public Computer( String name,LocalDate introduced,LocalDate discontinued,int company_id ) {
		this.id=null;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}
	//with id
	public Computer( Integer id,String name,LocalDate introduced,LocalDate discontinued,int company_id ) {
		this.id=id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}
	public String toString() {
		return String.format("<id = %s,name = %s,introduced= %s,discontinued = %s,company_id = %s>", this.id, this.name,this.introduced,this.discontinued,this.company_id);
		
	}
	
	
	public Integer getId() {
		return this.id;
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
	public int getCompany_id() {
		return this.company_id;
	}
	
}
