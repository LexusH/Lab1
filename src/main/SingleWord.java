package main;

import java.io.*;

import java.security.NoSuchAlgorithmException; 

//Any number of chars single word from /usr/share/dict/words (Linux or Mac)
public class SingleWord {
	private String password;
	
	SingleWord(String password){
		this.password = password;
	}
	
	public boolean found() throws NoSuchAlgorithmException{
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("/usr/share/dict/words"));
			
			String word = reader.readLine().trim();
			while(word != null) {
				shaHash H = new shaHash(word);
				String hashedPassword = H.hexPSWD;
				if (hashedPassword.equals(this.password)) {
					reader.close();
					//Printing outcome to console and file	
					String output = hashedPassword + ":" + word;
					System.out.println(output);
					ToFile TF= new ToFile();
					TF.BuffWrit(output);
					return true;
				}
				word = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return false;
	}
}
