package com.excilys.cdb.ui;

import java.util.List;

import com.excilys.cdb.model.Computer;

public class VueComputer {
	
	public static void affComputer(List<Computer> computer_list) {
		System.out.println("id \t Name \t Introduced \t Discontinued \t Company_id \t Company_name");
		System.out.println("-------------------------------");
		for (Computer c : computer_list) {
			
			System.out.println( c.getId().get()
			+"\t"+ c.getName()
			+"\t \t \t \t" + c.getIntroduced()
			+"\t \t" + c.getDiscontinued()
			+"\t \t" + c.getCompany_id().getId()
			+"\t \t" + c.getCompany_id().getName());
		}
	}
}
