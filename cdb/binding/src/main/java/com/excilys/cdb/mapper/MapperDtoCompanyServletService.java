package com.excilys.cdb.mapper;

import com.excilys.cdb.dto.DtoCompanyOutput;
import com.excilys.cdb.model.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperDtoCompanyServletService {
	
	
	
	public MapperDtoCompanyServletService() {
		
	}
	public  Company mapperDtoToCompanyServletService(DtoCompanyOutput dtoCompany) {
//		if (dtoCompany.getId()!= 0) {
//			return service.getCompanyById(dtoCompany.getId());
//		}else if(dtoCompany.getName()!=null) {
//			return service.getCompanyByName(dtoCompany.getName());
//		}else return new Company();
	return new Company();
	}
	
	public  DtoCompanyOutput mapperDtoFromCompanyServletService(Company company) {
		return new DtoCompanyOutput(company.getId(),company.getName());
		
		
		
	}
	
}
