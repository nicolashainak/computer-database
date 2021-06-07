package com.excilys.cdb.binding.mapper;

import com.excilys.cdb.binding.dto.DtoCompanyServletService;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperDtoCompanyServletService {
	@Autowired
	public static MyService service;// hum moyen ouf non ? 
	public static Company mapperDtoToCompanyServletService(DtoCompanyServletService dtoCompany) {
		if (dtoCompany.getId()!= 0) {
			return service.getCompanyById(dtoCompany.getId());
		}else if(dtoCompany.getName()!=null) {
			return service.getCompanyByName(dtoCompany.getName());
		}else return new Company();
	}
	
	public static DtoCompanyServletService mapperDtoFromCompanyServletService(Company company) {
		return new DtoCompanyServletService(company.getId(),company.getName());
		
		
		
	}
	
}
