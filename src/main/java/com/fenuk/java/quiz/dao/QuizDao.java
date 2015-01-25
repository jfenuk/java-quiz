package com.fenuk.java.quiz.dao;

import java.util.List;

import com.fenuk.java.quiz.domain.Quiz;

public interface QuizDao {
	
	public boolean  create(Quiz z); // return value?
	public Quiz read(int i);
	public boolean update(Quiz z);
	public boolean delete(Quiz z);
	
	public List<Quiz> getAll();
	
	
	
	
	

}
