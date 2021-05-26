package com.excilys.cdb.binding.mapper;

import java.time.LocalDate;

import com.excilys.cdb.binding.dto.DtoComputer;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.Service;

public class MapperDtoComputer {
	public static Computer dtoToComputer(DtoComputer dtoComputer) {
		LocalDate introduced = null;
		LocalDate discontinued = null;
		if (!dtoComputer.getIntroduced().equals("")) {
		introduced = LocalDate.parse(dtoComputer.getIntroduced());
		}
		if(!dtoComputer.getDiscontinued().equals("")) {
		discontinued = LocalDate.parse(dtoComputer.getDiscontinued());
		}
		
		Computer computer = new Computer (dtoComputer.getName(),introduced,discontinued,Service.getInstance().getCompanyById(Integer.parseInt( dtoComputer.getCompany())));
		
		
		return computer;
	}
}
