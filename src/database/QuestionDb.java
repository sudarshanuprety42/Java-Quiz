package database;


import java.util.*;
public class QuestionDb {
	
	private static int id = 0;
	private String question;
	private String answer;
	private ArrayList<String> options;
	
	
	public QuestionDb(String question, String answer,  ArrayList<String> options) {
		// TODO Auto-generated constructor stub
		this.id = this.id + 1;
		this.question = question;
		this.answer = answer;
		this.options = options;
	}

	
	
	//getter method

	public static int getId() {
		return id;
	}


	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}


	public ArrayList<String> getOptions() {
		return options;
	}
	
	public String getOption(int n) {
		return options.get(n);
	}



}
