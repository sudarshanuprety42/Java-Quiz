package finalexam;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import exception.WalkAwayException;
import exception.GameLostException;
import database.*;


	public class Game {

		private String username ;
		private Database db = new Database();
		private LifeLine lf = new LifeLine();
		Scanner sc = new Scanner(System.in);
		private int askedQuestions = 1;
		private ArrayList<String> selection = new ArrayList<String>(4);
	
		public int getAskedQuestions() {
			return askedQuestions;
		}
	
		public void incrementAskQuestions() {
			this.askedQuestions = this.askedQuestions + 1;
		}
		
		/**
		 * Constructor for Game which takes username
		 * Initialize Database 
		 * Setup Available total Selection Choices
		 * @param username
		 */
		public Game(String username) {
			System.out.println("Setting up the database");
			// setup Database
			db.init();
			
			//setup available selection 
			this.selection.add("a");
			this.selection.add("b");
			this.selection.add("c");
			this.selection.add("d");
			
			//setup username
			this.username = username;
		}

	
		/**
		 * Method responsible for playing round of quiz
		 * @param no_of_questions
		 * @param isWalkAway
		 * @param isLifeline
		 * @param round
		 * @throws WalkAwayException
		 * @throws GameLostException
		 */
	 	public void round(int no_of_questions, boolean isWalkAway, boolean isLifeline, int round) throws WalkAwayException, GameLostException {
			//initialize variables 
			boolean askConfirm = true;
			boolean confirm = false;
			String userAnswer = null;
			String chosenLf;
			
			// get all questions
			ArrayList<QuestionDb> questions = db.getArrayOfQuestions(no_of_questions);	
			System.out.println(questions);
			//gather round_type
			String roundType = "hard";
			if(no_of_questions == 3)
				roundType = "easy";
			
			// available selection
			ArrayList<String> selection = this.selection;
			
			for(int i=0; i<questions.size(); i++){	
				
				// intialize available selection
				selection = this.selection;
				
				// print question no along with the money you will win after answering the question
				if(this.askedQuestions == 1)
					System.out.println("Here is your first question for :" + db.getMappedMoney(1, roundType));
				else
					System.out.println("Question for :" + db.getMappedMoney(this.askedQuestions, roundType));
				
				// ask the question on display
				askQuestion(questions.get(i));
				
				// increment ask question no (total number of questions asked to user) 
				incrementAskQuestions();
				
				/**
				 * Loop to get the answer from available selection only
				 * To select lifeline if possible 
				 * 
				 */
				do {
					confirm = false;
					askConfirm = true;
					
					while(true) {
						
						// Inform user to enter answer or select lifeline
						System.out.println("\nPlease enter your answer.");
						
						//if lifeline is available and available in this round then
						if(isLifeline) {
							System.out.println("To choose lifeline select : L");
						}
						
						userAnswer = sc.next();
						
						//if user selected lifeline
						if (userAnswer.toLowerCase().equals("l")) {
							//show lifeline
							lf.printLifelineOptions();
							while(true) {
								System.out.println("Please select your lifeline.");
								chosenLf= sc.next();
								try {
									// if selected lifeline is available for user
									// P.S. We remove lifeline as we use them but just incase it causes issue this is the chekcker for that
									if(lf.ifAvalaible(Integer.valueOf(chosenLf))) {
										// Changed option after lifeline 
										ArrayList<String> options = lf.useLifeline(lf.getLifeline(Integer.valueOf(chosenLf)), questions.get(i));
										// If question is null ask question again and options
										if(options == null) {
											askQuestion(questions.get(i));
										}
										else
										{	
											// else only print new options
											selection = options;
											System.out.println(selection);
										}
										
										break;
	
									}else {
										throw new Exception();
									}
								}catch(Exception e) {
									e.printStackTrace();
									System.out.println("Incorrect lifeline option");
									System.out.println("Do you want to select lifeline again");
									System.out.println("Press Y or N");
									
									if(((sc.next().toLowerCase().equals("y"))))
									{
										continue;
									}else {
										break;
									}
								}
							}
							askConfirm = false;
							break;
						// to check user confirmed his answer
						}else if (selection.contains(userAnswer.toLowerCase())) {
							askConfirm = true;
							break;
						}else {
							System.out.println("Please enter correct option");
							askConfirm = false;
							break;
						}
					}
					
					if(askConfirm) {
						System.out.println("Do you want to confirm your choice? Press Y or N");
						
						if(((sc.next().toLowerCase().equals("y"))))
						{
							confirm = true;
						}
					}
				}while(!confirm);
				
				
				if (!(checkAnswer(questions.get(i),userAnswer)))
				{
					//userAnswer goes in option a/b/c/d 
					throw new GameLostException(this.username);
					
				}
				
			}
			
				if(isWalkAway)
			{
				System.out.println("Do you want to continue to next round?");
				System.out.println("Type Y or N");
				if(((sc.next().toLowerCase().equals("y"))))
				{
					System.out.println("Welcome to the next round");
				}
				//after each round ask the user if he wants to play another round or quit
				else 
				{	
					
					System.out.println("Game over. Your final won amount is: " +"$" +db.getMappedMoney(round*no_of_questions,roundType));
					throw new WalkAwayException(db.getMappedMoney(round*no_of_questions, roundType));				
				}
			}
		} 
	
		public static void askQuestion(QuestionDb question) {
			System.out.println(question.getQuestion());
			ArrayList<String> options = question.getOptions();
			char opt = 'A';
			for(int i=0; i<=options.size()-1; i++)
			{
				if (i%2==0)
					System.out.print("\n");
				System.out.print(((char)((int)opt + i)) +". " + (String)options.get(i)+".\t\t");
			}		
		}
		
		public static String getAnswerFromOption(QuestionDb question, char s) {
			int ans = (int) s;
			int a = (int)'a';

			return question.getOptions().get(ans-a);
		}
		public static boolean checkAnswer(QuestionDb question, String answer) {
			answer = getAnswerFromOption(question, answer.toLowerCase().charAt(0));
			if(answer.toLowerCase().equals(question.getAnswer().toLowerCase()))
			{
				System.out.println("You are correct!");
				return true;
			}
			return false;
		}

}
