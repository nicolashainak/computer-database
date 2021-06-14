package com.excilys.cdb.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


	@Configuration
	@ComponentScan(basePackages = {"com.excilys.cdb.binding.dto","com.excilys.cdb.binding.mapper","com.excilys.cdb.binding.validation","com.excilys.cdb.persistance","com.excilys.cdb.service","com.excilys.cdb.ui","com.excilys.cdb.cli"})
	public class ConfigurationUi {
	
		 private static HikariConfig config = new HikariConfig();
		 private static HikariDataSource ds;
		@Bean
		 public DataSource getDataConnection(){
		        config.setJdbcUrl( "jdbc:mysql://localhost:3306/computer-database-db" );
		        config.setUsername( "admincdb" );
		        config.setPassword( "qwerty1234" );
		        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		        ds = new HikariDataSource( config );
		        return ds;
		    }
	}
	
