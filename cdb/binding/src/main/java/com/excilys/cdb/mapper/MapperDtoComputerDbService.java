package com.excilys.cdb.mapper;

import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.DtoCompanyDbService;
import com.excilys.cdb.dto.DtoComputerDbService;
import com.excilys.cdb.dto.DtoComputerServiceDb;
import com.excilys.cdb.model.Computer;


@Component
public class MapperDtoComputerDbService {
	public  static DtoComputerDbService mapperDtoToDbService(Computer computer ) {
		
		DtoCompanyDbService companyDto = MapperDtoCompanyDbService.mapperDtoFromCompanyDbService(computer.getCompany_id());
		return new DtoComputerDbService(computer.getName(),computer.getIntroduced(),computer.getDiscontinued(),companyDto);
	}

	public  static DtoComputerServiceDb mapperDtoToServiceDb(Computer computer ) {
		
		DtoCompanyDbService companyDto = MapperDtoCompanyDbService.mapperDtoFromCompanyDbService(computer.getCompany_id());
		Integer id = 0;
		if (computer.getId().isPresent()) {
			id=computer.getId().get();
		}
		
		return new DtoComputerServiceDb(id,computer.getName(),computer.getIntroduced(),computer.getDiscontinued(),companyDto);
	}
	public  static Computer mapperDtoToDbService(DtoComputerServiceDb dtoComputerServiceDb ) {
		return new Computer(dtoComputerServiceDb.getId(),dtoComputerServiceDb.getName(),dtoComputerServiceDb.getIntroduced(),dtoComputerServiceDb.getDiscontinued(),MapperDtoCompanyDbService.mapperDtoToCompanyDbService(dtoComputerServiceDb.getDtoCompanyDbService()));
	}

}