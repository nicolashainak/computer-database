package ui;

import java.util.ArrayList;

import model.Computer;

public class VueComputer {
	
	public static void affComputer(ArrayList<Computer> computer_list) {
		for (Computer c : computer_list) {
			System.out.println("Id: " + c.getId()
			+"\t Name: " + c.getName()
			+"\t Introduced: " + c.getIntroduced()
			+"\t Discontinued: " + c.getDiscontinued()
			+"\t Company_id: " + c.getCompany_id());
		}
	}
}
