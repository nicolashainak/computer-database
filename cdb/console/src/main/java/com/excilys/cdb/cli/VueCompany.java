package com.excilys.cdb.cli;

import java.util.List;

import com.excilys.cdb.model.Company;

public class VueCompany {

	public static void affCompany(List<Company> company_list) {
		System.out.println("Id: \t Name:");
		System.out.println("-------------------------------");
		for (Company c : company_list) {
			System.out.println(c.getId()+"\t " + c.getName());
					
		
		}
	}
}