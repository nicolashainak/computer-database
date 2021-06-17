package com.excilys.cdb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.excilys.cdb.binding.dto.DtoCompanyDbService;
import com.excilys.cdb.binding.dto.DtoComputerDbService;
import com.excilys.cdb.binding.dto.DtoComputerServiceDb;
import com.excilys.cdb.binding.mapper.MapperDtoCompanyDbService;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistance.CompanyRepository;
import com.excilys.cdb.persistance.ComputerRepository;
import com.excilys.cdb.persistance.DaoCompany;
import com.excilys.cdb.persistance.DaoComputer;
import java.util.ArrayList;

@Service
public class MyService {

	
	private ComputerRepository computerRepository;
	private DaoComputer daoComputer ;
	private DaoCompany daoCompany ;
	private CompanyRepository companyRepository;
	private MapperDtoCompanyDbService mapperDtoCompanyDbService;
//	public List<Computer> getListComputer(Page page) {
//		return daoComputer.getListComputer(page);
//
//	}
	
	
	public MyService(DaoComputer daoComputer,DaoCompany daoCompany, CompanyRepository companyRepository,MapperDtoCompanyDbService mapperDtoCompanyDbService,ComputerRepository computerRepository) {
		super();
		this.daoComputer=daoComputer;
		this.daoCompany=daoCompany;
		this.companyRepository =companyRepository;
		this.mapperDtoCompanyDbService=mapperDtoCompanyDbService;
		this.computerRepository=computerRepository;
	}

	public int nbComputerSearch(String search) {
		
		return computerRepository.countByNameLikeOrDtoCompanyDbServiceNameLike("%"+search+"%","%"+ search+"%");
	}
	public Computer searchComputer(int id) {
		
		return daoComputer.searchComputer(id);
	}
	
	public int getNbComputerTotal(Page page) {

		return computerRepository.countByNameLikeOrDtoCompanyDbServiceNameLike("% %","% %");
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

	public DtoCompanyDbService getCompanyById(int id_company) {
		return(companyRepository.findById(id_company));
	}

	public Company getCompanyByName(String name) {
		return daoCompany.getCompanyByName(name);
	}

	public int getNbCompany() {
		return daoCompany.nbCompany();
	}
	
	public List<DtoComputerServiceDb> getListComputer(String search,Pageable pageable ) {
		
		return computerRepository.searchByNameLikeOrDtoCompanyDbServiceNameLike("%"+ search+"%","%"+ search+"%", pageable);
		//return daoComputer.getListComputer(page,search, collonne,reverse);

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
