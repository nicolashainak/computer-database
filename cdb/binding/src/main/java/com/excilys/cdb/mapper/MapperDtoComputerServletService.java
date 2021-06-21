package com.excilys.cdb.mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import com.excilys.cdb.dto.DtoComputerServletService;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;


@Component
public class MapperDtoComputerServletService {
		
	public  Computer dtoToComputer(DtoComputerServletService dtoComputer) {
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Company company= null;
		if (!dtoComputer.getIntroduced().equals("")) {
		introduced = LocalDate.parse(dtoComputer.getIntroduced());
		}
		if(!dtoComputer.getDiscontinued().equals("")) {
		discontinued = LocalDate.parse(dtoComputer.getDiscontinued());
		}
		if (!"".equals(dtoComputer.getCompany())) {
			
			company=new Company(Integer.parseInt(dtoComputer.getCompany()),"");
		}
		
		
		return new Computer(dtoComputer.getName(),introduced,discontinued,company);
	}
}
