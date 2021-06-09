package com.excilys.cdb.binding.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

@Component
public class MapperCompany implements RowMapper<Company> {

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		Company company = new Company();
		company.setId(rs.getInt("id"));
		company.setName(rs.getString("name"));
		return company;

	}

	public static ArrayList<Company> writeResultSet(ResultSet resultSet) throws SQLException {
		ArrayList<Company> company_list = new ArrayList<Company>();
		while (resultSet.next()) {

			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			Company c = new Company(id, name);
			company_list.add(c);

		}
		return company_list;
	}

	public static Company getThisCompany(ResultSet resultSet) throws SQLException {
		Company c = new Company(0, null);
		if (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			c.setId(id);
			c.setName(name);
		}

		return c;
	}

}
