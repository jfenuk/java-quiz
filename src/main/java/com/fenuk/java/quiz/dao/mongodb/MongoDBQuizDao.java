package com.fenuk.java.quiz.dao.mongodb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fenuk.java.quiz.dao.QuizDao;
import com.fenuk.java.quiz.domain.Option;
import com.fenuk.java.quiz.domain.Quiz;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class MongoDBQuizDao implements QuizDao {

	private final DBCollection collection;

	public MongoDBQuizDao(DBCollection collection) {
		this.collection = collection;

	}

	public Quiz read(int id) {

		DBObject doc = collection.findOne(new BasicDBObject("id", id));

		int quiz_id = Integer.parseInt(doc.get("id").toString());
		String quiz_question = doc.get("question").toString();
		List<Option> quiz_options = new ArrayList<Option>();

		BasicDBList list = (BasicDBList) doc.get("options");
		Iterator itr = list.iterator();

		while (itr.hasNext()) {
			DBObject option_doc = (BasicDBObject) itr.next();

			int option_id = Integer.parseInt(option_doc.get("id").toString());
			String option_text = option_doc.get("text").toString();
			boolean option_correct = Boolean.parseBoolean(option_doc.get(
					"correct").toString());

			Option option = new Option();

			option.setId(option_id);
			option.setText(option_text);
			option.setCorrect(option_correct);

			quiz_options.add(option);
		}

		Quiz quiz = new Quiz();
		quiz.setId(quiz_id);
		quiz.setQuestion(quiz_question);
		quiz.setOptions(quiz_options);

		return quiz;
	}

	public boolean delete(Quiz quiz) {

		WriteResult result = collection.remove(new BasicDBObject("id", quiz
				.getId()));
		if (result.getN() > 0)
			return true;
		else
			return false;

	}
	
	public boolean delete(int id) {

		WriteResult result = collection.remove(new BasicDBObject("id", id));
		if (result.getN() > 0)
			return true;
		else
			return false;

	}

	public List<Quiz> getAll() {

		DBCursor cursor = collection.find();
		List<Quiz> quizes = new ArrayList<Quiz>();

		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			Quiz quiz = read(Integer.parseInt(doc.get("id").toString()));
			quizes.add(quiz);
		}
		return quizes;
	}

	public boolean create(Quiz quiz) {

		int id = (int) collection.count();

		DBObject doc = new BasicDBObject("id", ++id).append("question",
				quiz.getQuestion()).append("options", quiz.getOptions());

		WriteResult result = collection.insert(doc);

		if (result.getN() > 0)
			return true;
		else
			return false;
	}

	public boolean update(Quiz z) {

		return false;
	}

}
