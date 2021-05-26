package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.binding.dto.DtoComputer;
import com.excilys.cdb.binding.mapper.MapperDtoComputer;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistance.DaoCompany;
import com.excilys.cdb.persistance.DaoComputer;
import com.excilys.cdb.binding.validation.ValidationDtoComputer;


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
		DtoComputer dtoComputer = new DtoComputer(name,introduced,discontinued,company);
		DaoComputer daoComputer=DaoComputer.getInstance();
		if (ValidationDtoComputer.getInstance().isValidDto(dtoComputer)){
			Computer computer = MapperDtoComputer.dtoToComputer(dtoComputer);
			daoComputer.newComputer(computer);
		}
		
	}
	
	public Company getCompanyById(int id_company) {
		return daoCompany.getCompany(id_company);
	}
}
