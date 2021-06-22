package com.excilys.cdb.console.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


	@Configuration
	@EnableJpaRepositories(basePackages= {"com.excilys.cdb.persistence"})
	@ComponentScan(basePackages = {"com.excilys.cdb.binding.dto","com.excilys.cdb.binding.mapper","com.excilys.cdb.binding.validation","com.excilys.cdb.persistence","com.excilys.cdb.service","com.excilys.cdb.ui","com.excilys.cdb.cli"})
	public class ConfigurationUi {
	
		 private static HikariConfig config = new HikariConfig();
		 private static HikariDataSource ds;
		@Bean
		 public DataSource getDataConnection(){
		        config.setJdbcUrl( "jdbc:mysql://localhost:3306/computer-database-db " );
		        config.setUsername( "admincdb" );
		        config.setPassword( "qwerty1234" );
		        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		        ds = new HikariDataSource( config );
		        return ds;
		    }
		
		  @Bean
		   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		      LocalContainerEntityManagerFactoryBean em 
		        = new LocalContainerEntityManagerFactoryBean();
		      em.setDataSource(getDataConnection());
		      em.setPackagesToScan(new String[] { "com.excilys.cdb.binding.dto" ,"com.excilys.cdb.model"});

		      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		      em.setJpaVendorAdapter(vendorAdapter);
		      em.setJpaProperties(additionalProperties());

		      return em;
		   }
		  
		  Properties additionalProperties() {
		       Properties properties = new Properties();
		      // properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		       properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		          
		       return properties;
		   }
		  
		  @Bean
		   public PlatformTransactionManager transactionManager() {
		       JpaTransactionManager transactionManager = new JpaTransactionManager();
		       transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		       return transactionManager;
		   }
	}
	
