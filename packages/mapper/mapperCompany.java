package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.company;

public class mapperCompany {
	public ArrayList<company> writeResultSet(ResultSet resultSet) throws SQLException {
		ArrayList<company> company_list = new ArrayList<company>();
        while (resultSet.next()) {
            
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            company c = new company(id,name);
            company_list.add(c);
        
        }
        return company_list;
    }
}
