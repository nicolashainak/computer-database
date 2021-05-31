package com.excilys.cdb.binding.mapper;

import com.excilys.cdb.binding.dto.DtoCompanyServletService;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.Service;

public class MapperDtoCompanyServletService {

	
	public static Company mapperDtoToCompanyServletService(DtoCompanyServletService dtoCompany) {
		if (dtoCompany.getId()!= 0) {
			return Service.getInstance().getCompanyById(dtoCompany.getId());
		}else if(dtoCompany.getName()!=null) {
			return Service.getInstance().getCompanyByName(dtoCompany.getName());
		}else return new Company();
	}
	
	public static DtoCompanyServletService mapperDtoFromCompanyServletService(Company company) {
		return new DtoCompanyServletService(company.getId(),company.getName());
		
		
		
	}
	
}
