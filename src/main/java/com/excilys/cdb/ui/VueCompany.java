package com.excilys.cdb.ui;

import java.util.ArrayList;

import com.excilys.cdb.model.Company;

public class VueCompany {

	public static void affCompany(ArrayList<Company> company_list) {
		System.out.println("Id: \t Name:");
		System.out.println("-------------------------------");
		for (Company c : company_list) {
			System.out.println(c.getId()+"\t " + c.getName());
					
		
		}
	}
}