package ui;

import java.util.ArrayList;

import model.Computer;

public class VueComputer {
	
	public static void affComputer(ArrayList<Computer> computer_list) {
		System.out.println("id \t Name \t Introduced \t Discontinued \t Company_id");
		System.out.println("-------------------------------");
		for (Computer c : computer_list) {
			
			System.out.println( c.getId()
			+"\t"+ c.getName()
			+"\t \t \t \t" + c.getIntroduced()
			+"\t \t" + c.getDiscontinued()
			+"\t \t" + c.getCompany_id());
		}
	}
}
