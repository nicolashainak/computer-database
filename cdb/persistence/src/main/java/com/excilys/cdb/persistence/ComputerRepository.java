package com.excilys.cdb.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.binding.dto.DtoComputerDbService;
import com.excilys.cdb.binding.dto.DtoComputerServiceDb;

@Repository
public interface ComputerRepository extends JpaRepository<DtoComputerServiceDb,Integer>{
	
	public int countByNameLikeOrDtoCompanyDbServiceNameLike(String name,String name2);
	
	public  List<DtoComputerServiceDb> searchByNameLikeOrDtoCompanyDbServiceNameLike(String name,String name2,Pageable pageable);
	
	public DtoComputerServiceDb findById(int id);
	
	public List<DtoComputerServiceDb> findByNameLike(String search,Pageable pageable);
	
	public void deleteById(int id);

	
}