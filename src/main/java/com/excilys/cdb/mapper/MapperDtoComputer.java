package com.excilys.cdb.mapper;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.DtoComputer;
import com.excilys.cdb.service.Service;

public class MapperDtoComputer {
	public static Computer dtoToComputer(DtoComputer dtoComputer) {
		Computer c = new Computer (dtoComputer.getName(),dtoComputer.getIntroduced(),dtoComputer.getDiscontnued(),Service.getInstance().getCompanyById(Integer.parseInt( dtoComputer.getCompany())));
		
		
		return c;
	}
}
