package com.excilys.cdb.configuration;

import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;



import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.MessageSource;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages= {"com.excilys.cdb.persistance"})
@ComponentScan(basePackages = {"com.excilys.cdb.binding.dto","com.excilys.cdb.binding.mapper","com.excilys.cdb.binding.validation","com.excilys.cdb.persistance","com.excilys.cdb.service","com.excilys.cdb.session"})
//,"com.excilys.cdb.servlet"
public class ConfigurationWeb implements WebMvcConfigurer{
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
	

	   @Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em 
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(getDataConnection());
	      em.setPackagesToScan(new String[] { "com.excilys.cdb.binding.dto" });

	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());

	      return em;
	   }
	   @Bean
	   public PlatformTransactionManager transactionManager() {
	       JpaTransactionManager transactionManager = new JpaTransactionManager();
	       transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

	       return transactionManager;
	   }

	   @Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	       return new PersistenceExceptionTranslationPostProcessor();
	   }

	   Properties additionalProperties() {
	       Properties properties = new Properties();
	       properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	       properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	          
	       return properties;
	   }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = 
          new InternalResourceViewResolver();
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");
        return bean;
    }
    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*Add css file resource url here*/
          registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        }
    
    @Bean("messageSource")
    public MessageSource messageSource() {
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    	messageSource.setBasenames("./languages/messages");
    	messageSource.setDefaultEncoding("ISO-8859-1");
    	return messageSource;
    }
    
    
    @Bean
    public LocaleResolver localeResolver() {
    	SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    	localeResolver.setDefaultLocale(new Locale("en"));
    	return localeResolver;
    }

    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    	localeChangeInterceptor.setParamName("lang");
    	registry.addInterceptor(localeChangeInterceptor);
    
    }
    
}
