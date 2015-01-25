package com.fenuk.java.quiz.listener;

import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mongodb.MongoClient;

public class MongoDBContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {

		MongoClient mongo = (MongoClient) sce.getServletContext().getAttribute(
				"MONGO_CLIENT");
		mongo.close();
		System.out.println("MongoClient closed successfully");
	}

	public void contextInitialized(ServletContextEvent sce) {

		try {
			ServletContext ctx = sce.getServletContext();
			MongoClient mongo = new MongoClient(
					ctx.getInitParameter("MONGODB_HOST"), Integer.parseInt(ctx
							.getInitParameter("MONGODB_PORT")));

			System.out.println("MongoClient initialized successfully");

			sce.getServletContext().setAttribute("MONGO_CLIENT", mongo);
		} catch (UnknownHostException e) {
			throw new RuntimeException("MongoClient init failed");
		}
	}

}