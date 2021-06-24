package com.excilys.cdb.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excilys.cdb.binding.dto.DtoUser;

public interface UserRepository extends JpaRepository<DtoUser,Integer>{
	public DtoUser findByUsername(String search);
	public DtoUser findById(int id);
	public int countByUsernameLike(String search);
	public List<DtoUser> searchByUsernameLike(String search);
	public void deleteById(int id);

}
