package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.binding.dto.DtoCompanyServletService;
import com.excilys.cdb.binding.dto.DtoComputerServletService;
import com.excilys.cdb.binding.mapper.MapperDtoCompanyServletService;
import com.excilys.cdb.binding.mapper.MapperDtoComputerServletService;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistance.DaoCompany;
import com.excilys.cdb.persistance.DaoComputer;
import com.excilys.cdb.binding.validation.ValidationDtoCompany;
import com.excilys.cdb.binding.validation.ValidationDtoComputer;

public class Service {

	private static Service instance = new Service();

	private Service() {
	}

	public static Service getInstance() {
		return instance;
	}

	private DaoComputer daoComputer = DaoComputer.getInstance();
	private DaoCompany daoCompany = DaoCompany.getInstance();

	public List<Computer> getListComputer(Page page) {
		return daoComputer.getListComputer(page);

	}

	public int nbComputerSearch(String search) {
		return daoComputer.nbElementSearch(search);
	}
	public Computer searchComputer(int id) {
		
		return daoComputer.searchComputer(id);
	}
	
	public int getNbComputerTotal(Page page) {

		return daoComputer.nbComputer();
	}

	public List<Company> getListCompany() {
		return daoCompany.getListCompany();
	}

	public void addComputer(Computer computer) {
		daoComputer.newComputer(computer);
	}

	public Company getCompanyById(int id_company) {
		return daoCompany.getCompanyById(id_company);
	}

	public Company getCompanyByName(String name) {
		return daoCompany.getCompanyByName(name);
	}

	public int getNbCompany() {
		return daoCompany.nbCompany();
	}
	
	public List<Computer> search(Page page, String search, String collonne) {
		return daoComputer.searchComputerWith(page,search, collonne);

	}
	
	public List<Computer> orderBy(Page page, String collonne, Boolean reverse) {

		return daoComputer.orderBy(page, collonne, reverse);

	}
	
	public void deleteCompany(int company_id) {
		daoCompany.delet(company_id);
	}
	public void updateComputer(Computer computer) {
		
	}
	

}
