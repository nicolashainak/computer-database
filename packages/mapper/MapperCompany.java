package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Company;

public class MapperCompany {
	public static ArrayList<Company> writeResultSet(ResultSet resultSet) throws SQLException {
		ArrayList<Company> company_list = new ArrayList<Company>();
        while (resultSet.next()) {
            
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Company c = new Company(id,name);
            company_list.add(c);
        
        }
        return company_list;
    }
}
