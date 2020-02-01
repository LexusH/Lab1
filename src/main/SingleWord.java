package main;

import java.util.Scanner;
import java.io.*;
//imports needed for SHA-256 hashing
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException; 

//Any number of chars single word from /usr/share/dict/words (Linux or Mac)
public class SingleWord {
	private String password;
	
	SingleWord(String password){
		this.password = password;
	}
	
	String found() throws NoSuchAlgorithmException{
		System.out.println(this.password);
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("assets/words.txt"));
			
			String word = reader.readLine().trim();
			while(word != null) {
				String hashedPassword = shaHash(word);
				System.out.println(word + ": " + hashedPassword);
				if (hashedPassword.equals(this.password)) {
					return word;
				}
				word = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return "";
		
	}
	public static String shaHash(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();
		String hexPSWD = String.format("%064x", new BigInteger(1, digest));
		return hexPSWD;
	}
}
