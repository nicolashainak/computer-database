package com.excilys.cdb.binding.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class MapperComputer {
	
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
