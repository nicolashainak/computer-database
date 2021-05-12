package ui;

import java.util.ArrayList;

import model.Company;

public class VueCompany {

	public static void affCompany(ArrayList<Company> company_list) {
		System.out.println("Id: \t Name:");
		System.out.println("-------------------------------");
		for (Company c : company_list) {
			System.out.println(c.getId()+"\t " + c.getName());
					
		
		}
	}
}