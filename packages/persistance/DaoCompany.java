package persistance;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import mapper.mapperCompany;
import ui.vueCompany;

public class DaoCompany {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private mapperCompany mC = new mapperCompany();
	private vueCompany vC=new vueCompany();
	public void readDatabase() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/computer-database-db","admincdb","qwerty1234");
			statement = connect.createStatement();
			resultSet=statement.executeQuery("select * from company");
			
			vC.affCompany(mC.writeResultSet(resultSet));
			
		}catch(Exception e){
			throw e ;
		}finally {
			close();
		}
	}
	
	
	private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
	
}

