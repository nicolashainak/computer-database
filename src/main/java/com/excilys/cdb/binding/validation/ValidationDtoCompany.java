package com.excilys.cdb.binding.validation;
import org.springframework.beans.factory.annotation.Autowired;
import com.excilys.cdb.binding.dto.DtoCompanyServletService;
import com.excilys.cdb.binding.validation.ValidationDtoCompany;
import com.excilys.cdb.service.MyService;
import com.excilys.cdb.persistance.DaoCompany;
import org.springframework.stereotype.Component;


@Component
public class ValidationDtoCompany {
	private Boolean isZero = false;
	
	@Autowired
	DaoCompany daoCompany ;
	@Autowired
	MyService service;
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
		} else if (id > 0 && service.getNbCompany() + 1 > id) {
			return true;
		} else {

			return false;
		}
	}
	
	
}
