package persistance;
import java.sql.Timestamp;
import java.time.LocalDate;

import model.Computer;
import ui.VueCompany;
import ui.VueComputer;
import cli.Actions;

public class main {
	public static void main(String[] args)throws Exception{
		Actions A=new Actions();
		A.boucle();
		//		VueComputer.affComputer(DaoComputer.readDatabase());
//		VueComputer.affComputer(DaoComputer.searchComputer(50));
//		VueCompany.affCompany(DaoCompany.readDatabase());
//		LocalDate introduced =  LocalDate.of(2008, 1, 1);
//		LocalDate discontinued = LocalDate.of(2010,3,3);
//		Computer c =new Computer ("Gateway LT3103U", introduced,discontinued,1);
//		System.out.println(c);
//		DaoComputer.newComputer(c);
		//computerDB.readDatabase();
		
	}
}
