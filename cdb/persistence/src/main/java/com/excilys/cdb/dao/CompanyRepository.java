package com.excilys.cdb.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.dto.DtoCompanyDbService;

@Repository
public interface CompanyRepository extends JpaRepository<DtoCompanyDbService,Integer>{
	
	public DtoCompanyDbService findByName(String name);
	public DtoCompanyDbService findById(int id);
	public int countByNameLike(String search);
	public void deleteById(int id);
	public List<DtoCompanyDbService> searchByNameLike(String name);
	public List<DtoCompanyDbService> searchByName(String search,Pageable pageable);

}
