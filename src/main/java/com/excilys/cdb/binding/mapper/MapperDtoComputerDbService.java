package com.excilys.cdb.binding.mapper;

import java.time.LocalDate;

import com.excilys.cdb.binding.dto.DtoCompanyDbService;
import com.excilys.cdb.binding.dto.DtoCompanyServletService;
import com.excilys.cdb.binding.dto.DtoComputerDbService;
import com.excilys.cdb.model.Computer;

public class MapperDtoComputerDbService {
	public static DtoComputerDbService mapperDtoToDbService(Computer computer ) {
		
		DtoCompanyDbService companyDto = MapperDtoCompanyDbService.mapperDtoFromCompanyDbService(computer.getCompany_id());
		return new DtoComputerDbService(computer.getName(),computer.getIntroduced(),computer.getDiscontinued(),companyDto);
	}

	


}
