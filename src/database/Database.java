package database;

import java.util.*;
import java.io.*;
//import org.json.*;

// database class we will be called as an object in Game class
public class Database {
	
	
	
	private static String [] EasyGame = { "100.0" , "500.0", "1000.0", "8000.0", "16000.0", "32000.0", "125000.00", "500000.00", "10000000.00"};
	private static String [] HardGame = { "100.0", "200.00", "300.00" , "500.0", "1000.0", "2000.00", "4000.00", "8000.0", "16000.0", "32000.0", "64000.00", "125000.00", "250000.00",  "500000.00", "10000000.00"};
	private ArrayList<QuestionDb> questiondb = new ArrayList<QuestionDb>();
	
	public Database() {
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		System.out.println("Setting up Database please wait...");
		
		ArrayList<String> options = new ArrayList<String>();
		
		try {
			//File file = new File(".");
			//for(String fileNames : file.list()) System.out.println(fileNames);
			File file=new File("./src/database/database.txt");    //creates a new file instance  
			FileReader fr= new FileReader(file);   //reads the file  
			BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
			//StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters  
			String line; 
			int count = 0; 
			String question = null;
			String answer= null;
			while((line=br.readLine())!=null)  
			{  
				
				count++;
				//System.out.println("Count" + count +"Line: " +  line );
				if( count == 7) {
					count = 0;
					System.out.println("Q" + question +" answer: " +  answer );
					this.questiondb.add(new QuestionDb(question, answer, options));
					options = new ArrayList<String>();
					//System.out.println(answer + " "+ question);
					
				}else if(count==1) {
					
					question = line;
					//System.out.println(answer + " "+ question);
				}else if(count==6) {
					//System.out.println(answer + " "+ question);
					answer = line;
					//System.out.println(answer + " "+ question);
				}else {
					
					options.add(line);
				}
				
				       
			}  
			br.close();
			fr.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			for(int i=0; i<15;i++) {
				
				options.add("Not fine");
				options.add("Not fine");
				options.add("Not fine");
				options.add("I am fine");
				
				//System.out.println("Setting up Database please wait...");
				this.questiondb.add(new QuestionDb("How are you?", "I am fine", options));
			}
		}
//		for(int i=0; i<15;i++) {
//			//ArrayList<String> options = new ArrayList<String>();
//			options.add("Not fine");
//			options.add("Not fine");
//			options.add("Not fine");
//			options.add("I am fine");
//			
//			//System.out.println("Setting up Database please wait...");
//			this.questiondb.add(new QuestionDb("How are you?", "I am fine", options));
//		}
		
		System.out.println("Database setup done!");
	}
	
	// function would be in the game
	public ArrayList<QuestionDb> getArrayOfQuestions(int n){
		ArrayList<QuestionDb> returnQuetionDb = new ArrayList<QuestionDb>();
		Collections.shuffle(this.questiondb);
		do{
			returnQuetionDb.add(questiondb.remove(0));
		}while(returnQuetionDb.size()!=n);
		System.out.println("Size: " + returnQuetionDb.size());
		return returnQuetionDb;
	}
	
	public String getMappedMoney(int total_asked_questions, String game_type) {
		if (game_type.toLowerCase().equals("hard")) {
			return HardGame[total_asked_questions - 1 ] ;
		}
		return EasyGame[total_asked_questions - 1 ] ;
	}

//	public ArrayList<QuestionDb> getArrayOfQuestions() {
//		return questiondb;
//	}


}
