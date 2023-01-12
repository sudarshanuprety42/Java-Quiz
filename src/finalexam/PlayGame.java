package finalexam;
/**
 * @author Sudarshan c0847333
 * Project Main File
 */
import java.util.ArrayList;


import java.util.Scanner;

import exception.GameLostException;
import exception.WalkAwayException;

public class PlayGame {
 // Main class for the project

	public static void main(String[] args) throws WalkAwayException {
		// initialize variables and read user input
		String choosen_game_type;
		System.out.println("JKHKLJHKLj");
		// Welcome message
		System.out.println("Welcome1111111 to Who wants to be a Billionare");
		Scanner sc = new Scanner(System.in); // creating scanner object to read user input
		// Displaying menu to user
		System.out.println("Please enter game type you want to prefer");
		System.out.println("Press H for Hard");
		System.out.println("Press E for Easy");
		// Read user choice
		choosen_game_type = sc.next();
		
		try {
			// read user name
			System.out.println("Please enter your username: ");
			// check if user wants to play hard or easy level
			if(choosen_game_type.toLowerCase().equals("h")) {
				Hard gameHard = new Hard(sc.next()); // initialize Hard class object
				// Play all three rounds
				gameHard.roundOne();
				gameHard.roundTwo();
				gameHard.roundThree();
		
			}else {
				Easy gameEasy = new Easy(sc.next()); // initialize Hard class object
				// Play all three rounds
				gameEasy.roundOne();
				gameEasy.roundTwo();
				gameEasy.roundThree();
			}
			
		}catch(WalkAwayException e) { // Catch WalkAwayException
			System.out.println(e.getMessage());
		}catch(GameLostException e) { // Catch GameLostException
			System.out.println(e.getMessage());
		}catch(Exception e) { // Catch other Exception
			System.out.println(e);
		}finally {
			// Display exiting message
			System.out.println("Thank you for playing");
		}
	}

}
