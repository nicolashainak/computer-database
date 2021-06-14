package com.excilys.cdb.persistance;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.binding.dto.DtoComputerDbService;
import com.excilys.cdb.binding.mapper.MapperComputer;
import com.excilys.cdb.binding.mapper.MapperDtoComputerDbService;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;


@Repository
public class DaoComputer {
	Logger logger = LoggerFactory.getLogger(DaoComputer.class);

	private final static String RQTGETCOMPUTER = "Select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  "
			+ "left join company on computer.company_id = company.id  "
			+ "WHERE computer.name LIKE ? OR company.name LIKE ? " + "ORDER BY ? ?" + "Limit ? Offset ?";
	private final static String RQTNBCOMPUTER = "SELECT COUNT(computer.id)"
			+ "FROM computer LEFT JOIN company on company.id = computer.company_id "
			+ "WHERE computer.name LIKE ? OR company.name LIKE ? ";
	private final static String RQTNEWCOMPUTER ="INSERT INTO computer( name,introduced,discontinued,company_id) VALUES(?,?,?,?);";
	private final static String RQTSEARCHCOMPUTER ="Select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  "
	+ "left join company on computer.company_id = company.id  " + "WHERE computer.id = ? ";
	private final static String RQTDELETECOMPUTER ="delete from computer where id= ? ";
	private final static String RQTUPDATECOMPUTER ="update computer set name = ?,introduced = ? , discontinued = ?, company_id = ? "
	+ "where id = ?;";
	private JdbcTemplate vJdbcTemplate;

	public DaoComputer(DataSource datasource) {
		vJdbcTemplate = new JdbcTemplate(datasource);
	}

	// version jdbc
	public List<Computer> getListComputer(Page page, String search, String collonne, Boolean reverse) {
		List<Computer> listComputer = new ArrayList<Computer>();
		String order = "ASC";
		if (reverse) {
			order = "DESC";
		}

		String vSQL = RQTGETCOMPUTER ;

		listComputer = vJdbcTemplate.query(vSQL, new MapperComputer(), "%" + search + "%", "%" + search + "%", collonne,
				order, page.getNbComputerParPage(), page.getNbComputerParPage() * (page.getNumPage() - 1));

		return listComputer;
	}

	public int nbElementSearch(String search) {
		String vSQL = RQTNBCOMPUTER ;

		int nbElementSearch = vJdbcTemplate.queryForObject(vSQL, int.class, "%" + search + "%", "%" + search + "%");

		return nbElementSearch;
	}

	public void newComputer(Computer computer) {
		DtoComputerDbService c = MapperDtoComputerDbService.mapperDtoToDbService(computer);
		Integer id =null;
		System.out.println(c);
		String vSQL = RQTNEWCOMPUTER;
		if(c.getCompany().getId()!=0) {
			id =c.getCompany().getId();
		}
		vJdbcTemplate.update(vSQL, c.getName(), c.getIntroduced(), c.getDiscontinued(),id );

	}

	public Computer searchComputer(int idComputer) {
		Computer computer = new Computer();
		String vSQL = RQTSEARCHCOMPUTER ;

		computer = vJdbcTemplate.queryForObject(vSQL,new MapperComputer(),idComputer);
		

		return computer;
	}

	public void updateComputer(int idComputer, Computer computer) {
		DtoComputerDbService c = MapperDtoComputerDbService.mapperDtoToDbService(computer);

		String vSQL = RQTUPDATECOMPUTER;

		vJdbcTemplate.update(vSQL, c.getName(), c.getIntroduced(), c.getDiscontinued(), c.getCompany().getId(), idComputer);

	}

	public void deleteComputer(int idComputer) {
		String vSQL = RQTDELETECOMPUTER;

		vJdbcTemplate.update(vSQL, idComputer);
	}

}
