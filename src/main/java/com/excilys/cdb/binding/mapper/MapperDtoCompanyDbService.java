package com.excilys.cdb.binding.mapper;
import org.springframework.stereotype.Component;

import com.excilys.cdb.binding.dto.DtoCompanyDbService;

import com.excilys.cdb.model.Company;


@Component
public class MapperDtoCompanyDbService {

	public static DtoCompanyDbService mapperDtoFromCompanyDbService(Company company) {
		return new DtoCompanyDbService(company.getId(),company.getName());
	}
	
	public static Company mapperDtoToCompanyDbService(DtoCompanyDbService dtoCompany) {
		return new Company(dtoCompany.getId(),dtoCompany.getName());
		
		
		
	}
	
}

