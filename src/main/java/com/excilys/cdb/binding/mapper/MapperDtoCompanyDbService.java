package com.excilys.cdb.binding.mapper;

import com.excilys.cdb.binding.dto.DtoCompanyDbService;
import com.excilys.cdb.binding.dto.DtoCompanyServletService;
import com.excilys.cdb.binding.dto.DtoComputerDbService;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.Service;

public class MapperDtoCompanyDbService {

	public static DtoCompanyServletService mapperDtoFromCompanyDbService(Company company) {
		return new DtoCompanyServletService(company.getId(),company.getName());
	}
	
	public static Company mapperDtoToCompanyDbService(DtoCompanyDbService dtoCompany) {
		return new Company(dtoCompany.getId(),dtoCompany.getName());
		
		
		
	}
	
}

