package com.excilys.cdb.binding.mapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import com.excilys.cdb.binding.dto.DtoComputerServletService;
import com.excilys.cdb.model.Computer;


@Component
public class MapperDtoComputerServletService {
	public static Computer dtoToComputer(DtoComputerServletService dtoComputer) {
		LocalDate introduced = null;
		LocalDate discontinued = null;
		if (!dtoComputer.getIntroduced().equals("")) {
		introduced = LocalDate.parse(dtoComputer.getIntroduced());
		}
		if(!dtoComputer.getDiscontinued().equals("")) {
		discontinued = LocalDate.parse(dtoComputer.getDiscontinued());
		}
		
		Computer computer = new Computer (dtoComputer.getName(),introduced,discontinued,
				MapperDtoCompanyServletService.mapperDtoToCompanyServletService(dtoComputer.getCompany()));
		
		
		return computer;
	}
}
