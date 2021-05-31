package com.excilys.cdb.binding.validation;

import java.time.LocalDate;

import com.excilys.cdb.binding.dto.DtoComputerServletService;
import com.excilys.cdb.persistance.DaoCompany;


public class ValidationDtoComputer {

	private ValidationDtoComputer() {
	}

	private static ValidationDtoComputer instance = new ValidationDtoComputer();

	public static ValidationDtoComputer getInstance() {
		return instance;
	}
	
	DaoCompany daoCompany=DaoCompany.getInstance();
	
	public Boolean isValidDto(DtoComputerServletService dtoComputer) {
		
		if( dtoComputer.getName().equals("") ){
			return false ;
		}
		if (!dtoComputer.getIntroduced().equals("") && !dtoComputer.getDiscontinued().equals("")){
			try {
			if (LocalDate.parse(dtoComputer.getIntroduced()).isAfter(LocalDate.parse(dtoComputer.getDiscontinued()))) {
				return false;
			}
			}catch(RuntimeException e){
				e.getStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
	
	
	

}