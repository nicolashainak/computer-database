package cli;

import java.util.Scanner;

public class Actions {

	private boolean always=true;
	Scanner scanner = new Scanner( System.in ) ;
	
	public void boucle() {
		
		while (always) {
			Graphic.drawBase();
			waitAction();
			
		}
	
	}
	public void waitAction() {
	
			String s= scanner.nextLine();
			System.out.println(s);
			
		
	}
	public void showComputer() {
		VueComputer.affComputer(DaoComputer.readDatabase());
	}
	public void showCompany() {
		VueCompany.affCompany(DaoCompany.readDatabase());
	}
	public void searchComputer() {
		
	}
	public void addComputer() {
		
	}
	public void updateComputer() {
		
	}
	public void deleteComputer() {
		
	}
	
	public void stop() {
		this.always=false;
	}
	
}
