package com.excilys.cdb.persistance;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

import javax.sql.DataSource;

import com.excilys.cdb.binding.mapper.MapperCompany;
import com.excilys.cdb.model.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class DaoCompany {

	private final static String RQTSELECTALL = "select id,name from company  ;";
	private final static String RQTSELECTALLOFFSET = "select id,name from company Limit ? Offset ?";
	private final static String RQTCOMPANYBYID = "select id,name from company where id =? ";
	private final static String RQTCOMPANYBYNAME = "select id,name from company where name = ? ";
	private final static String RQTNBCOMPANY = "SELECT COUNT(*) FROM company ;";
	private final static String RQTDELETECOMPUTER = "DELET  FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE company.id = ?";
	private final static String RQTDELETECOMPANY = "DELET  FROM company WHERE company.id = ?";
	private JdbcTemplate vJdbcTemplate;

	Logger logger = LoggerFactory.getLogger(DaoComputer.class);

	public DaoCompany(DataSource datasource) {
		vJdbcTemplate = new JdbcTemplate(datasource);
	}

	// version jdbc
	public List<Company> getListCompany() {
		String vSQL = RQTSELECTALL;
		List<Company> listCompany = vJdbcTemplate.query(vSQL, new MapperCompany());

		return listCompany;
	}

	public Company getCompanyById(int i) {
		Company company = null;
		String vSQL = RQTCOMPANYBYID;

		company = vJdbcTemplate.queryForObject(vSQL, Company.class);

		return company;
	}

	public Company getCompanyByName(String name) {
		Company company = null;
		String vSQL = RQTCOMPANYBYNAME;

		company = vJdbcTemplate.queryForObject(vSQL, Company.class);

		return company;
	}

	public int nbCompany() {

		String vSQL = RQTNBCOMPANY;

		int nbCompany = vJdbcTemplate.queryForObject(vSQL, int.class);

		return nbCompany;
	}
	
	public List<Company> getListCompanyOffset(int offset){
		String vSQL = RQTSELECTALLOFFSET;
		return vJdbcTemplate.query(vSQL,new MapperCompany(),20 ,20*offset);
	}
	
	
	@Transactional
	public boolean delete(int id) {
		
		if(id < 1) {
			logger.trace("Tentative de suppression d'une company sans id");
			return false;
		}
		
		vJdbcTemplate.update(RQTDELETECOMPUTER,id);
		vJdbcTemplate.update(RQTDELETECOMPANY,id);
		return true;
		
		
	}
	


}
