package finalexam;

import exception.GameLostException;
import exception.WalkAwayException;

public class Hard extends Game{
	
	static int max_questions = 5;
	static String gameType = "Hard";
	
	public Hard(String username) {
		super(username);
		System.out.println("Called");
		// TODO Auto-generated constructor stub
	}
	
	public void roundOne() throws WalkAwayException,GameLostException {
		super.round(this.max_questions, true, false, 1);
	}
	
	public void roundTwo() throws WalkAwayException, GameLostException {
		super.round(this.max_questions, true, true, 2);
	}
	public void roundThree() throws WalkAwayException, GameLostException {
		super.round(this.max_questions, true, false, 3);
	}
	
}
