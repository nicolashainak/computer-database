package com.excilys.cdb.binding.validation;

import java.time.LocalDate;

import com.excilys.cdb.binding.dto.DtoComputer;
import com.excilys.cdb.persistance.DaoCompany;


public class ValidationDtoComputer {

	private ValidationDtoComputer() {
	}

	private static ValidationDtoComputer instance = new ValidationDtoComputer();

	public static ValidationDtoComputer getInstance() {
		return instance;
	}
	
	DaoCompany daoCompany=DaoCompany.getInstance();
	
	public Boolean isValidDto(DtoComputer dtoComputer) {
		
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
		if (!dtoComputer.getCompany().equals("") && daoCompany.getCompany(Integer.parseInt(dtoComputer.getCompany()))==null ) {
			return false;
		}

		
		return true;
	}
	
	
	
	

}