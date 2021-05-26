package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistance.DaoCompany;
import com.excilys.cdb.persistance.DaoComputer;
import com.excilys.cdb.ui.Page;


public class Service {
	
	
	private static Service instance= new Service();
	private Service() {}
	
	public static Service getInstance() {
		return instance;
	}
	
	private DaoComputer daoComputer=DaoComputer.getInstance();
	private DaoCompany daoCompany=DaoCompany.getInstance();
	public List<Computer> getListComputer(Page page){
		return daoComputer.getListComputer(page);
		
	}

	public int getNbComputerTotal(Page page) {
		
		return daoComputer.nbComputer();
	}
	public List<Company> getListCompany(){
		return daoCompany.getListCompany();
	}
	public void addComputer(String name,String introduced,String discontinued,String company) {
		
	}
	
}
