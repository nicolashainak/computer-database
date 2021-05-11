package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import model.Computer;

public class MapperComputer {
	
	public static ArrayList<Computer> writeResultSet(ResultSet resultSet) throws SQLException {
		boolean b = true;
		ArrayList<Computer> computer_list = new ArrayList<Computer>();
		LocalDate introduced = null;
		LocalDate discontinued =null;
        while (resultSet.next()) {
            b=false;
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            if (resultSet.getDate("introduced")!=null) {
            	introduced = resultSet.getDate("introduced").toLocalDate();
            }
            if (resultSet.getDate("discontinued")!=null) {            
            	discontinued = resultSet.getDate("discontinued").toLocalDate();
            
            }
            int company_id = resultSet.getInt("company_id");
            Computer c = new Computer(id,name,introduced,discontinued,company_id);
            computer_list.add(c);
              
        }		
        
        if (b) {
        	System.out.println("L'ordinateur n'a pas été trouver" );
        }
        return computer_list;
	}
}
