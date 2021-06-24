package com.excilys.cdb.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {

        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(ConfigurationWeb.class);

        // Create and register the DispatcherServlet  check jerome ok
        DispatcherServlet dispatcher = new DispatcherServlet(context);
        dispatcher.setThrowExceptionIfNoHandlerFound(true);

        Dynamic servlet = servletContext.addServlet("dispatcher", dispatcher);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }

}