package com.excilys.cdb.binding.validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import com.excilys.cdb.binding.dto.DtoComputerServletService;
import com.excilys.cdb.persistance.DaoCompany;

@Component
public class ValidationDtoComputer {

	private ValidationDtoComputer() {
	}

	@Autowired
	DaoCompany daoCompany;
	
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