package com.excilys.cdb.binding.mapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

@Component 
public class MapperComputer implements RowMapper<Computer> {
	 @Override
	    public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		 Computer computer = new Computer();
		 Company company = new Company();
		 LocalDate introduced = null;
 		 LocalDate discontinued =null;
		 computer.setId(rs.getInt("id"));
		 computer.setName(rs.getString("name"));
		 
		 if (rs.getDate("introduced")!=null) {
         	introduced = rs.getDate("introduced").toLocalDate();
         }
         if (rs.getDate("discontinued")!=null) {            
         	discontinued = rs.getDate("discontinued").toLocalDate();
         
         }
         computer.setIntroduced(introduced);
		 computer.setDiscontinued(discontinued);
		 company.setId(rs.getInt("company_id"));
		 company.setName(rs.getString("company.name"));
		 computer.setCompany_id(company);
	        return computer;
	    }
	
	
	
	
	
	public static ArrayList<Computer> writeResultSet(ResultSet resultSet) throws SQLException {
		
		ArrayList<Computer> computer_list = new ArrayList<Computer>();
		
        while (resultSet.next()) {
        	LocalDate introduced = null;
    		LocalDate discontinued =null;
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            if (resultSet.getDate("introduced")!=null) {
            	introduced = resultSet.getDate("introduced").toLocalDate();
            }
            if (resultSet.getDate("discontinued")!=null) {            
            	discontinued = resultSet.getDate("discontinued").toLocalDate();
            
            }
            int company_id = resultSet.getInt("company_id");
            String company_name=resultSet.getString("company.name");
            
            Computer c = new Computer(id,name,introduced,discontinued,new Company(company_id,company_name));
            computer_list.add(c);
              
        }		
        
       
        return computer_list;
	}
public static Computer writeAResultSet(ResultSet resultSet) throws SQLException {
		
		Computer computer = new Computer();
		
        while (resultSet.next()) {
        	LocalDate introduced = null;
    		LocalDate discontinued =null;
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            if (resultSet.getDate("introduced")!=null) {
            	introduced = resultSet.getDate("introduced").toLocalDate();
            }
            if (resultSet.getDate("discontinued")!=null) {            
            	discontinued = resultSet.getDate("discontinued").toLocalDate();
            
            }
            int company_id = resultSet.getInt("company_id");
            String company_name=resultSet.getString("company.name");
            
             computer = new Computer(id,name,introduced,discontinued,new Company(company_id,company_name));
            
              
        }		
        
       
        return computer;
	}
}
