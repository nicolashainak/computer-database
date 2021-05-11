package persistance;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import mapper.mapperComputer;
import model.Computer;
import ui.vueComputer;

public class DaoComputer {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private mapperComputer mC =new mapperComputer();
	private vueComputer vC=new vueComputer();
	
	public void readDatabase() throws Exception {
		try {
			open();
			resultSet=statement.executeQuery("select * from computer");
			vC.affComputer(mC.writeResultSet(resultSet));
			
		
		}catch(Exception e){
			throw e ;
		}finally {
			close();
		}
	}
	
	
	public void newComputer ( Computer c ) throws Exception {
		try {
			open();
			int l =0;
			//Date d = new Date();
			//pb id ou other a check 
			l=statement.executeUpdate("INSERT INTO computer( name,introduced,discontinued,company_id)"
					+ "VALUES("+c.getName()+","+c.getIntroduced()+","+c.getDiscontinued()+","+c.getCompany_id()+");");
		System.out.println("INSERT INTO computer( name,introduced,discontinued,company_id)"
				+ "VALUES("+c.getName()+","+c.getIntroduced()+","+c.getDiscontinued()+","+c.getCompany_id()+");");
		}catch(Exception e){
			throw e ;
		}finally {
			close();
		}
	}
	
	public void searchComputer(int idComputer) throws Exception {
		try {
			open();
			resultSet=statement.executeQuery("select * from computer where id =" + idComputer);
			System.out.println("search computer n° : "+idComputer);
			vC.affComputer(mC.writeResultSet(resultSet));
			

		}catch(Exception e){
			throw e ;
		}finally {
			close();
		}
	}
// accees to BD
	private void open ()throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/computer-database-db","admincdb","qwerty1234");
			statement = connect.createStatement();
        } catch (Exception e) {
        	throw e;
        }
		
		
	}
	
// close the accees to the bd 
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
