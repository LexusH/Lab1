package main;

import java.io.BufferedReader;
import java.io.FileReader;

//A seven char word from /usr/share/dict/words (Linux or Mac) which gets the first
//letter capitalized and a 1-digit number appended.
public class Char7_Capital_Num {
	private String password;

	Char7_Capital_Num(String password){
		this.password = password;
	}
	
	public void found(){
		try {
			//Opens a buffer to read from the dictionary file
			BufferedReader buffer = new BufferedReader(new FileReader("/usr/share/dict/words"));
			 
			String word = buffer.readLine().trim();
			//While their are still words in the dictionary
			while(word != null) {
				//Check that the words length is appropriate
				if (word.length() == 7){
					//Capitalizes first character
					String temp = word.substring(0, 1).toUpperCase();
				    String Cap_word = temp + word.substring(1);
					//Append numbers, trying each number from 0-9
				    for(int i = 0; i < 10; i++) {
				    	String attempt = Cap_word.concat("" + i + "");
				    	shaHash H = new shaHash(attempt);
				    	String hashed = H.hexPSWD;
				    	//Checking if hashed word equals password
				    	if(hashed.equals(this.password)){
				    		buffer.close();	
				    		String output = hashed + ":" + attempt;
				    		//Printing outcome to console and file
				    		System.out.println(output);
				    		ToFile TF= new ToFile();
				    		TF.BuffWrit(output);
				    		return;
				    	}
				    }
				}
				//If no match/word not length 7 move to new word
				word = buffer.readLine();
			}
			//if end of dictionary met, close dictionary
			buffer.close();	
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
