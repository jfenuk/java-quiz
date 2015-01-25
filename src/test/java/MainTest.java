import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fenuk.java.quiz.dao.DaoFactory;
import com.fenuk.java.quiz.dao.QuizDao;
import com.fenuk.java.quiz.dao.mongodb.MongoDBDaoFactory;
import com.fenuk.java.quiz.domain.Quiz;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MainTest {

	private MongoClient client;
	DaoFactory factory;
	QuizDao quizDao;

	@Before
	public void setUp() throws Exception {

		client = new MongoClient();
		client.dropDatabase("javaquiz");
		factory = new MongoDBDaoFactory(client);
		quizDao = factory.getQuizDao();

	}

	@After
	public void tearDown() throws Exception {
		client.close();
	}

	@Test
	public void test_0() {

		DBCollection collection = client.getDB("javaquiz").getCollection("quizzes");
		collection.insert(new BasicDBObject("id",1));
		
		List quizess = quizDao.getAll();
		assertEquals(quizess.size(), 0);

	}

	//@Test
	public void test_1() {

		Quiz q = new Quiz();
		q.setQuestion("What is your name?");
		q.setOptions(new ArrayList());
		assertEquals(true, quizDao.create(q));

	}
	//@Test
	public void test_2() {

		Quiz q = new Quiz();
		q.setQuestion("What is your name?");
		q.setOptions(new ArrayList());
		quizDao.delete(q);
		assertEquals(false, quizDao.delete(q));

		
		
	}
}
