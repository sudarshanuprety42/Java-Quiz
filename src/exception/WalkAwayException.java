package exception;

public class WalkAwayException extends Exception  {

	public WalkAwayException(String moneyWalkedAway) {
		// TODO Auto-generated constructor stub
		 super("Congratulations ! You won $" + moneyWalkedAway + " !" );  
	}

}
