package ui;

import java.util.ArrayList;

import model.Company;

public class VueCompany {

	public static void affCompany(ArrayList<Company> company_list) {
		for (Company c : company_list) {
			System.out.println("Id: " + c.getId());
			System.out.println("Name: " + c.getName());
					
		
		}
	}
}