package com.excilys.cdb;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistance.DaoComputer;

import configuration.Configurationjdbc;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args)  {
		ApplicationContext context=new AnnotationConfigApplicationContext(Configurationjdbc.class);
		DaoComputer daoComputer= DaoComputer.getInstance();
		Page page = new Page();
		System.out.println(daoComputer.getListComputer(page).get(0));
	}
	}
	
	


