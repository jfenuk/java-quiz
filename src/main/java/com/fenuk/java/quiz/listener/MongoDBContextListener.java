package com.fenuk.java.quiz.listener;

import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;
import org.mongodb.morphia.logging.slf4j.SLF4JLoggerImplFactory;

import com.fenuk.java.quiz.entity.Answer;
import com.fenuk.java.quiz.entity.Examination;
import com.fenuk.java.quiz.entity.Option;
import com.fenuk.java.quiz.entity.Question;
import com.fenuk.java.quiz.entity.User;
import com.mongodb.MongoClient;

public class MongoDBContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {

		Datastore datastore = (Datastore) sce.getServletContext().getAttribute(
				"MONGO_DATASTORE");
		datastore.getMongo().close();
		System.out.println("MongoClient closed successfully");
	}

	public void contextInitialized(ServletContextEvent sce) {

		try {
			ServletContext ctx = sce.getServletContext();
			MongoClient mongo = new MongoClient(
					ctx.getInitParameter("MONGODB_HOST"), Integer.parseInt(ctx
							.getInitParameter("MONGODB_PORT")));

			MorphiaLoggerFactory.registerLogger(SLF4JLoggerImplFactory.class);

			Morphia morphia = new Morphia();
			morphia.map(Answer.class).map(Examination.class).map(Option.class)
					.map(Question.class).map(User.class);

			new ValidationExtension(morphia);

			Datastore ds = morphia.createDatastore(mongo, "my_database");

			System.out.println("MongoClient initialized successfully");

			sce.getServletContext().setAttribute("MONGO_DATASTORE", ds);
		} catch (UnknownHostException e) {
			throw new RuntimeException("MongoClient init failed");
		}
	}

}