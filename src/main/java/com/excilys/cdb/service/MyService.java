package com.excilys.cdb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistance.DaoCompany;
import com.excilys.cdb.persistance.DaoComputer;


@Service
public class MyService {

	
	
	private DaoComputer daoComputer ;
	private DaoCompany daoCompany ;

//	public List<Computer> getListComputer(Page page) {
//		return daoComputer.getListComputer(page);
//
//	}
	
	
	public MyService(DaoComputer daoComputer,DaoCompany daoCompany) {
		this.daoComputer=daoComputer;
		this.daoCompany=daoCompany;
	}

	public int nbComputerSearch(String search) {
		return daoComputer.nbElementSearch(search);
	}
	public Computer searchComputer(int id) {
		
		return daoComputer.searchComputer(id);
	}
	
	public int getNbComputerTotal(Page page) {

		return nbComputerSearch("");
	}

	public List<Company> getListCompany() {
		return daoCompany.getListCompany();
	}
	
	public List<Company> getListCompanyOffset(int offset) {
		return daoCompany.getListCompanyOffset(offset);
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
	
	public List<Computer> getListComputer(Page page, String search, String collonne,Boolean reverse) {
		return daoComputer.getListComputer(page,search, collonne,reverse);

	}
	

	
	public void deleteCompany(int company_id) {
		if (!daoCompany.delete(company_id)) {
			
		}
	}
	public void updateComputer(int id,Computer computer) {
		daoComputer.updateComputer(id, computer);
	}
	public void deleteComputer(int idComputer) {
		daoComputer.deleteComputer(idComputer);
	}
	

}
