package com.excilys.cdb.binding.validation;

import java.time.LocalDate;

import com.excilys.cdb.binding.dto.DtoCompanyServletService;
import com.excilys.cdb.persistance.DaoCompany;
import com.excilys.cdb.service.Service;

public class ValidationDtoCompany {
	private Boolean isZero = false;
	private static ValidationDtoCompany instance = new ValidationDtoCompany();

	public static ValidationDtoCompany getInstance() {
		return instance;
	}

	DaoCompany daoCompany = DaoCompany.getInstance();

	public Boolean isValidDto(DtoCompanyServletService dtoCompany) {
		this.isZero = false;
		if (this.validId(dtoCompany.getId()) ) {
			if(this.isZero) {
				if (dtoCompany.getName()!=null) {

					return false;
				}
			}
			return true ;
		}
		System.out.println("novaldidi");

		return false;
	}

	private Boolean validId(int id) {
		if (id == 0) {
			this.isZero = true;
			return true;
		} else if (id > 0 && Service.getInstance().getNbCompany() + 1 > id) {
			return true;
		} else {

			return false;
		}
	}
	
	
}
