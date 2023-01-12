package finalexam;
import database.*;

import java.util.*;

	public class LifeLine {
		
	
		private ArrayList<String> lifeLines = new ArrayList<String>();
		
		public LifeLine() {
			lifeLines.add("fiftyFifty");
			lifeLines.add("phoneFriend");
			lifeLines.add("audiencePoll");
		}
		
		public ArrayList<String> useLifeline(String lifeLine, QuestionDb question) {
			this.lifeLines.remove(lifeLine);
			if(lifeLine == "fiftyFifty") {	
				return fiftyFifty(question);
			}
			else if(lifeLine == "phoneFriend"){	
				this.phoneAfriend(question);
			}
			else{	
				this.audiencePoll(question);
			}
			return null;
			
		}
		
		public ArrayList<String> getLifelineOptions(){
			return lifeLines;
		}
		
		public String getLifeline(int index){
			return lifeLines.get(index - 1);
		}
		
		public boolean ifAvalaible(int index){
			try {
				String lol = lifeLines.get(index-1);
				return true;
			}catch(Exception e) {
				return false;
			}
			
		}
		
		public void printLifelineOptions() {
			for(int i=0; i<=lifeLines.size()-1; i++)
			{
				System.out.println((i+1) +".\t" + (String)lifeLines.get(i));
			}	
		}
		
		
		public LifeLine(String line, ArrayList<QuestionDb> q) {
			//this.line = line;
			//this.que = q;
			}
	
		
		public Map<String, String> getLifeLines() {
			Map<String, String> lifeLineList = new HashMap<>();
			lifeLineList.put("A", "50/50");
			lifeLineList.put("B", "Ask the audience");
			lifeLineList.put("C", "Phone A Friend");
			return lifeLineList;
		}
		
		public ArrayList<String> fiftyFifty(QuestionDb q){

			String answer = q.getAnswer();
			ArrayList<String> options = new ArrayList<String>();
			//boolean removed = options.remove(answer);
			String option= null;
			char opt = 'A';
			Random r = new Random();
			int answerIndex =  q.getOptions().indexOf(answer);
			int randomIndex = r.nextInt(3);
			if (randomIndex == answerIndex)
			{
				System.out.println("Indexes: " + randomIndex + " " + answerIndex);
				randomIndex = r.nextInt(3);
			}
			System.out.println("Indexes: " + randomIndex + " " + answerIndex);
			for(int i=0; i<q.getOptions().size(); i++)
			{
				
				if (i%2==0)
					System.out.print("\n");
				if (i != answerIndex && i != randomIndex)
					System.out.print(((char)((int)opt + i)) +". .\t\t");
				else
					System.out.print(((char)((int)opt + i)) +". " + (String)q.getOption(i)+".\t\t");
					
			}	
			System.out.print("\n");
			options.add(  (String.valueOf((char)( (int)opt + randomIndex  )).toLowerCase()));
			options.add(  (String.valueOf((char)( (int)opt + answerIndex  )).toLowerCase()));
			
			return options;
		}
	
	
		public int audiencePoll(QuestionDb q){
			String realAnswer;
			realAnswer = q.getAnswer();
			int total =0;
			
			Random r = new Random();
			int low = 50;
			int high = 100;
			int result = r.nextInt(45) + low;
			total += result;
		
			ArrayList<Integer> probability = new ArrayList<Integer>();
			for(int i=0; i<q.getOptions().size(); i++)
			{
				if(q.getOptions().get(i) == realAnswer){
					probability.add(result);
				}
				else{
					probability.add(r.nextInt(100-total)-i);
				}
			}
			System.out.println(probability);
			System.out.println("Audience Poll\n");
			for(int i=0;i<probability.size(); i++) {
				System.out.print("\n"+ q.getOptions().get(i) + ":\t" );
				printStar(probability.get(i));
			}

			return result;	
		}
	
		public void phoneAfriend(QuestionDb q){
			System.out.println("Hey there.. I'm stuck on this question and i need your help. \n You got 45 seconds to help me with the answer");
			System.out.println(q.getQuestion());
			System.out.println("the options are:" + q.getOptions());
			System.out.println("Hey.. I'm quite sure that the answer is:" +q.getAnswer());
		}
		
		public static void printStar(int x)
		{
		   if(x > 0)
		   {
		     System.out.print("*");
		     printStar(x-1);
		   }
		}
		
	
	}