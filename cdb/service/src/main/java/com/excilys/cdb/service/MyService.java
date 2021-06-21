package com.excilys.cdb.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.excilys.cdb.dto.DtoCompanyDbService;
import com.excilys.cdb.dto.DtoComputerDbService;
import com.excilys.cdb.dto.DtoComputerServiceDb;
import com.excilys.cdb.mapper.MapperDtoCompanyDbService;
import com.excilys.cdb.mapper.MapperDtoComputerDbService;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.CompanyRepository;
import com.excilys.cdb.ComputerRepository;

import java.util.ArrayList;

@Service
public class MyService {

	
	private ComputerRepository computerRepository;
	private CompanyRepository companyRepository;
	private MapperDtoCompanyDbService mapperDtoCompanyDbService;
//	public List<Computer> getListComputer(Page page) {
//		return daoComputer.getListComputer(page);
//
//	}
	
	
	public MyService( CompanyRepository companyRepository,MapperDtoCompanyDbService mapperDtoCompanyDbService,ComputerRepository computerRepository) {
		super();
		
		this.companyRepository =companyRepository;
		this.mapperDtoCompanyDbService=mapperDtoCompanyDbService;
		this.computerRepository=computerRepository;
	}

	public int nbComputerSearch(String search) {
		
		return computerRepository.countByNameLikeOrDtoCompanyDbServiceNameLike("%"+search+"%","%"+ search+"%");
	}
	public Computer searchComputer(int id) {
		
		return MapperDtoComputerDbService.mapperDtoToDbService(computerRepository.findById(id));
	}
	
	public int getNbComputerTotal(Page page) {

		return computerRepository.countByNameLikeOrDtoCompanyDbServiceNameLike("% %","% %");
	}

	public void addComputer(Computer computer) {
		DtoComputerServiceDb dtoComputerServiceDb=  MapperDtoComputerDbService.mapperDtoToServiceDb(computer);
		this.computerRepository.save(dtoComputerServiceDb);
	}

	public Company getCompanyById(int id_company) {
		return MapperDtoCompanyDbService.mapperDtoToCompanyDbService((companyRepository.findById(id_company)));
	}

	public Company getCompanyByName(String name) {
		return(MapperDtoCompanyDbService.mapperDtoToCompanyDbService(companyRepository.findByName(name)));
	}

	public int getNbCompany() {
		return companyRepository.countByNameLike("% %");
	}
	
	public List<Computer> getListComputer(String search,Pageable pageable ) {
		System.out.println("meo ");
		return computerRepository.searchByNameLikeOrDtoCompanyDbServiceNameLike("%"+ search+"%","%"+ search+"%", pageable).stream().map(c->MapperDtoComputerDbService.mapperDtoToDbService(c)).collect(Collectors.toList());
		//return daoComputer.getListComputer(page,search, collonne,reverse);

	}
	
	public List<Company> getListCompany(){
		return companyRepository.searchByNameLike("% %").stream().map(c->MapperDtoCompanyDbService.mapperDtoToCompanyDbService(c)).collect(Collectors.toList());
	}
	
	public void deleteCompany(int company_id) {
		companyRepository.deleteById(company_id);
	}
	public void updateComputer(int id,Computer computer) {
		computer.setId(id);
		DtoComputerServiceDb dtoComputerServiceDb=  MapperDtoComputerDbService.mapperDtoToServiceDb(computer);
		computerRepository.save(dtoComputerServiceDb);
	}
	public void deleteComputer(int idComputer) {
		computerRepository.deleteById(idComputer);
	}
	public List<Company> getListCompanyOffset( Pageable pageable){
		return companyRepository.searchByName("% %",pageable).stream().map(c->MapperDtoCompanyDbService.mapperDtoToCompanyDbService(c)).collect(Collectors.toList());
	}

}
