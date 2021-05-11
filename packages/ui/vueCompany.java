package ui;

import java.util.ArrayList;

import model.company;

public class vueCompany {

	public void affCompany(ArrayList<company> company_list) {
		for (company c : company_list) {
			System.out.println("Id: " + c.getId());
			System.out.println("Name: " + c.getName());
					
		
		}
	}
}