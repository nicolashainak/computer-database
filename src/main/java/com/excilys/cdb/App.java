package com.excilys.cdb;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistance.DaoComputer;

/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.excilys.cdb.binding.dto","com.excilys.cdb.binding.mapper","com.excilys.cdb.binding.validation","com.excilys.cdb.persistance","com.excilys.cdb.service","com.excilys.cdb.servlet","com.excilys.cdb.ui"})
//@PropertySource("classpath:application.properties")
public class App 
{
	public static void main(String[] args)  {
		ApplicationContext context=new AnnotationConfigApplicationContext(App.class);
		DaoComputer daoComputer= DaoComputer.getInstance();
		Page page = new Page();
		System.out.println(daoComputer.getListComputer(page).get(0));
	}
	}
	
	


