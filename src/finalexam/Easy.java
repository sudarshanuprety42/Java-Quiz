package finalexam;

import java.util.ArrayList;

import exception.GameLostException;
import exception.WalkAwayException;

public class Easy extends Game{

	static int max_questions = 3;
	static String gameType = "Easy";
	
	public Easy(String username) {
		super(username);
		System.out.println("Called");
		// TODO Auto-generated constructor stub
	}
	
	public void roundOne() throws WalkAwayException,GameLostException {
		System.out.println("1");
		 super.round(this.max_questions, false, true, 1);
	}
	
	public void roundTwo() throws WalkAwayException,GameLostException {
		System.out.println("2");
		super.round(this.max_questions, true, true, 2);
	}
	
	public void roundThree() throws WalkAwayException,GameLostException {
		System.out.println("3");
		super.round(this.max_questions, false, true, 3);
	}
	
}
