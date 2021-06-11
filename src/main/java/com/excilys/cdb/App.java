package com.excilys.cdb;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.configuration.ConfigurationUi;


/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args)  {
		ApplicationContext context=new AnnotationConfigApplicationContext(ConfigurationUi.class);
		
		StringBuilder teams = new StringBuilder("333");
		teams.append(" 806");
		teams.append(" 1601");
		System.out.println(teams);
	}
}
	
	


