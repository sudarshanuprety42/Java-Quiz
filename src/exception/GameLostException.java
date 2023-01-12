package exception;

public class GameLostException extends Exception{

	public GameLostException(String username) {
		super("\nAlas Wrong Answer! Sorry, You lost the game! \nIt was fun having you here "+ username);
	}

}
