package com.excilys.cdb;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.configuration.Configurationjdbc;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistance.DaoComputer;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args)  {
		ApplicationContext context=new AnnotationConfigApplicationContext(Configurationjdbc.class);
		
		
		
	}
}
	
	


