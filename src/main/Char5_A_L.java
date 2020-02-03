package main;

import java.io.*;

//A five char word from /usr/share/dict/words with the letter 'a' in it which gets replaced with the special character @ and the character "l" is substituted by the number "1". 
public class Char5_A_L {
	private String password;

	Char5_A_L(String password){
		this.password = password;
	}

	public void found(){
		try {
			//Opens a buffer to read from the dictionary file
			BufferedReader buffer = new BufferedReader(new FileReader("/usr/share/dict/words"));
			 
			String word = buffer.readLine().trim();
			char[] letters = word.toCharArray();
			//While their are still words in the dictionary
			while(word != null) {
				//Check that the words length is appropriate
				if (letters.length == 5){
					//Replaces "a" with "@" and "l" with "1"
					for(int i = 0; i < letters.length; ++i){
						if (letters[i] == 'a'){
							letters[i] = '@';	
						}
						if (letters[i] == 'l'){
							letters[i] = '1';
						}
					}
					word = String.valueOf(letters);
					shaHash H = new shaHash(word);
					String hashed = H.hexPSWD;
					//Checking if hashed word equals password
					if(hashed.equals(this.password)){
						buffer.close();	
						String output = hashed + ":" + word;
						//Printing outcome to console and file
						System.out.println(output);
						ToFile TF= new ToFile();
						TF.BuffWrit(output);
						return;
					}
				}
				//If no match/word not length 5 move to new word
				word = buffer.readLine();
				if (word != null) {
					letters = word.toCharArray();
				}
			}
			//if end of dictionary met, close dictionary
			buffer.close();	
		}
		
		catch(Exception e) {
			System.out.println("ERROR");
			System.out.println(e.getMessage());
		}
	}
}
