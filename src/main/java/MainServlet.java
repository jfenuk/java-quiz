import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fenuk.java.quiz.dao.DaoFactory;
import com.fenuk.java.quiz.dao.QuizDao;
import com.fenuk.java.quiz.dao.mongodb.MongoDBDaoFactory;
import com.mongodb.MongoClient;

@WebServlet(urlPatterns = "/home")
public class MainServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		DaoFactory factory = new MongoDBDaoFactory((MongoClient) request
				.getServletContext().getAttribute("MONGO_CLIENT"));
		QuizDao quizDao = factory.getQuizDao();

		// response.sendRedirect("home.jsp");

	}

}
