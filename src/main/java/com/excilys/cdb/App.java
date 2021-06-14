package com.excilys.cdb;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.cli.Cli;
import com.excilys.cdb.configuration.ConfigurationUi;


/**
 * Hello world!
 *
 */
public class App 
{
	Cli cli;
	public App(Cli cli) {
		this.cli=cli;
	}
	public static void main(String[] args)  {
		ApplicationContext context=new AnnotationConfigApplicationContext(ConfigurationUi.class);
		Cli cli = context.getBean(Cli.class);
		cli.boucle();
	}
}
	
	


