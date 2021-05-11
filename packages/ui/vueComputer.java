package ui;

import java.util.ArrayList;

import model.Computer;

public class vueComputer {
	
	public void affComputer(ArrayList<Computer> computer_list) {
		for (Computer c : computer_list) {
			System.out.println("Id: " + c.getId());
			System.out.println("Name: " + c.getName());
			System.out.println("Introduced: " + c.getIntroduced());
			System.out.println("Discontinued: " + c.getDiscontinued());
			System.out.println("Company_id: " + c.getCompany_id());
		}
	}
}
