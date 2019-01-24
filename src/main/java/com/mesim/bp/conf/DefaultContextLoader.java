package com.mesim.bp.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class DefaultContextLoader implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(DefaultContextLoader.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

		try {
			
	        Enumeration<Driver> drivers = DriverManager.getDrivers();
			
			while(drivers.hasMoreElements()) {
				Driver driver = drivers.nextElement();
				DriverManager.deregisterDriver(driver);
				logger.info("Deregister " + driver.getClass().getName());
			} 
	
		} catch(Exception e) {
			logger.error("Context Destroyed Error : ", e);
		}
		
	}

}
