package com.excilys.cdb.persistance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.binding.dto.DtoCompanyDbService;
import com.excilys.cdb.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<DtoCompanyDbService,Integer>{
	
	
	public DtoCompanyDbService findById(int id);
	public void deleteById(int id);

}
