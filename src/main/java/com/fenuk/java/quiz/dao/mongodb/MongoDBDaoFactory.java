package com.fenuk.java.quiz.dao.mongodb;

import com.fenuk.java.quiz.dao.DaoFactory;
import com.fenuk.java.quiz.dao.QuizDao;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBDaoFactory implements DaoFactory {
	private DB db;
	private DBCollection collection;

	public MongoDBDaoFactory(MongoClient client) {
		this.db = client.getDB("javaquiz");
		this.collection = db.getCollection("quizzes");
		System.out.println(collection.getFullName());
	}

	public QuizDao getQuizDao() {

		return new MongoDBQuizDao(collection);

	}

}
