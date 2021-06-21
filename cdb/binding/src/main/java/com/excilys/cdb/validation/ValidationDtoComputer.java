package com.excilys.cdb.validation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

import com.excilys.cdb.dto.DtoComputerServletService;

@Component
public class ValidationDtoComputer implements Validator{
	
	

	
	
	@Override
	public void validate (Object target , Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "computer name required");
	
			DtoComputerServletService computerDto = (DtoComputerServletService) target;
			nameValidator(errors, computerDto.getName());
			dateValidator(errors, computerDto.getIntroduced(), computerDto.getDiscontinued());
			companyValidator(errors, computerDto.getCompany());
		
	}

	private void nameValidator(Errors errors, String name) {
		if (name == null || "null".equals(name) || "".equals(name)) {
			errors.rejectValue("name", "the computer name is invalid");
		}
		
	}
	
	private void companyValidator(Errors errors, String company) {
		if (! company.isEmpty() ) {
			int companyId = Integer.parseInt(company);
			if (companyId < 0) {
				errors.rejectValue("company", "company is invalid" );
			}
		}
		
	}
	
	private void dateValidator(Errors errors, String introduced, String discontinued) {
		if (!introduced.isEmpty() && introduced != null) {
			LocalDate dateIntroduced = LocalDate.parse(introduced);
			if (!discontinued.isEmpty() && discontinued != null) {
				LocalDate dateDiscontinued = LocalDate.parse(discontinued);
				if (dateIntroduced.isAfter(dateDiscontinued)) {
					errors.rejectValue("discontinued", "computer discontinued invalid");
				}
			}
		}
		
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return DtoComputerServletService.class.equals(clazz);
	}


	
	
	
	

}