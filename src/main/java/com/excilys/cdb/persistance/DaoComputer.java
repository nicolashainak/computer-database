package com.excilys.cdb.persistance;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.binding.dto.DtoComputerDbService;
import com.excilys.cdb.binding.mapper.MapperComputer;
import com.excilys.cdb.binding.mapper.MapperDtoComputerDbService;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class DaoComputer {
	Logger logger = LoggerFactory.getLogger(DaoComputer.class);

	private final static String RQTINSERT = "INSERT INTO computer( name,introduced,discontinued,company_id) VALUES(?,?,?,?);";
	private final static String RQTSELECTCOMPuTERALL = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id limit ? offset ? ;";
	private final static String RQTSEARCH = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id where computer.id = ? ; ";
	private final static String RQTUPDATE = "update computer set name = ?,introduced = ? , discontinued = ?, company_id = ? where id = ?";
	private final static String RQTDELETEBYID = "delete from computer where id=?";
	private final static String RQTNBCOMPUTER = "SELECT COUNT(*) FROM computer ;";
	private final static String RQTORDERBY = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id ORDER BY ";
	private final static String RQTSEARCHWITH = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id  WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ";
	private static final String RQTNOMBREELEMENTSSEARCH = "SELECT COUNT(computer.id)FROM computer LEFT JOIN company on company.id = computer.company_id WHERE computer.name LIKE ? OR company.name LIKE ? ";
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

		String vSQL = "Select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  "
				+ "left join company on computer.company_id = company.id  "
				+ "WHERE computer.name LIKE ? OR company.name LIKE ? " + "ORDER BY ? ?" + "Limit ? Offset ?";

		listComputer = vJdbcTemplate.query(vSQL, new MapperComputer(), "%" + search + "%", "%" + search + "%", collonne,
				order, page.getNbComputerParPage(), page.getNbComputerParPage() * (page.getNumPage() - 1));

		return listComputer;
	}

	public int nbElementSearch(String search) {
		String vSQL = "SELECT COUNT(computer.id)"
				+ "FROM computer LEFT JOIN company on company.id = computer.company_id "
				+ "WHERE computer.name LIKE ? OR company.name LIKE ? ";

		int nbElementSearch = vJdbcTemplate.queryForObject(vSQL, int.class, "%" + search + "%", "%" + search + "%");

		return nbElementSearch;
	}

	public void newComputer(Computer computer) {
		DtoComputerDbService c = MapperDtoComputerDbService.mapperDtoToDbService(computer);
		Integer id =null;
		System.out.println(c);
		String vSQL = "INSERT INTO computer( name,introduced,discontinued,company_id) VALUES(?,?,?,?);";
		if(c.getCompany().getId()!=0) {
			id =c.getCompany().getId();
		}
		vJdbcTemplate.update(vSQL, c.getName(), c.getIntroduced(), c.getDiscontinued(),id );

	}

	public Computer searchComputer(int idComputer) {
		Computer computer = new Computer();
		String vSQL = "Select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  "
				+ "left join company on computer.company_id = company.id  " + "WHERE computer.id = ? ";

		computer = vJdbcTemplate.queryForObject(vSQL,new MapperComputer(),idComputer);
		

		return computer;
	}

	public void updateComputer(int idComputer, Computer computer) {
		DtoComputerDbService c = MapperDtoComputerDbService.mapperDtoToDbService(computer);

		String vSQL = "update computer set name = ?,introduced = ? , discontinued = ?, company_id = ? "
				+ "where id = ?;";

		vJdbcTemplate.update(vSQL, c.getName(), c.getIntroduced(), c.getDiscontinued(), c.getCompany().getId(), idComputer);

	}

	public void deleteComputer(int idComputer) {
		String vSQL = "delete from computer where id= ? ";

		vJdbcTemplate.update(vSQL, idComputer);
	}

}
