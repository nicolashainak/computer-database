package com.excilys.cdb.binding.mapper;

import com.excilys.cdb.binding.dto.DtoCompanyOutput;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperDtoCompanyServletService {
	
	private MyService service;
	
	public MapperDtoCompanyServletService(MyService service) {
		this.service=service;
	}
	public  Company mapperDtoToCompanyServletService(DtoCompanyOutput dtoCompany) {
		if (dtoCompany.getId()!= 0) {
			return service.getCompanyById(dtoCompany.getId());
		}else if(dtoCompany.getName()!=null) {
			return service.getCompanyByName(dtoCompany.getName());
		}else return new Company();
	}
	
	public  DtoCompanyOutput mapperDtoFromCompanyServletService(Company company) {
		return new DtoCompanyOutput(company.getId(),company.getName());
		
		
		
	}
	
}
