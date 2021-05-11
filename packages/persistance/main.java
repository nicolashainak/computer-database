package persistance;
import java.sql.Timestamp;
import java.time.LocalDate;

import model.Computer;


public class main {
	public static void main(String[] args)throws Exception{
		DaoComputer computerDB = new DaoComputer();
		computerDB.readDatabase();
		DaoCompany companyDB = new DaoCompany();
		companyDB.readDatabase();
		computerDB.searchComputer(66);
		computerDB.searchComputer(5);
		LocalDate introduced =  LocalDate.of(2008, 1, 1);
		LocalDate discontinued = LocalDate.of(2010,3,3);
		Computer c =new Computer ("Gateway LT3103U", introduced,discontinued,0);
		computerDB.newComputer(c);
	}
}
