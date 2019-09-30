package com.ipartek.formacion.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

/**
 * Application Lifecycle Listener implementation class Log4JListenner
 *
 */
@WebListener
public class Log4JListenner implements ServletContextListener {

	private final static Log LOG = LogFactory.getLog(Log4JListenner.class);

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// cuando arrsanca la webapp configuramos el Log4j
		LOG.info("Arranca Aplicacion y carga contexto servlets");
		BasicConfigurator.configure();
	}

}
