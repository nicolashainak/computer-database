package com.excilys.cdb.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "computer")
public class DtoComputerServiceDb {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private DtoCompanyDbService dtoCompanyDbService;

	public DtoComputerServiceDb() {

	}

	
	
	public DtoComputerServiceDb(int id, String name, LocalDate introduced, LocalDate discontinued,
			DtoCompanyDbService dtoCompanyDbService) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;

		this.discontinued = discontinued;

		this.dtoCompanyDbService = dtoCompanyDbService;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public DtoCompanyDbService getDtoCompanyDbService() {
		return dtoCompanyDbService;
	}

	public void setDtoCompanyDbService(DtoCompanyDbService dtoCompanyDbService) {
		this.dtoCompanyDbService = dtoCompanyDbService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DtoComputerServiceDb [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company=" + dtoCompanyDbService + "]";
	}

}
