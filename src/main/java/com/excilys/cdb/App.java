package com.excilys.cdb;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistance.DaoComputer;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args)  {
		DaoComputer daoComputer= DaoComputer.getInstance();
		Page page = new Page();
		System.out.println(daoComputer.getListComputer(page).get(0));
	}
	}
	
	


