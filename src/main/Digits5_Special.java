package main;

import java.util.ArrayList;

//imports needed for SHA-256 hashing
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  



//A five digit password with at least one of the following special characters in the
//beginning: *, ~, !, #
public class Digits5_Special {
	private char characters[] = {'*', '~', '!','#'};
	private int counter = 100000;
	private String password;
	private static ArrayList<String> charPerms = new ArrayList<String>();
	
	Digits5_Special(String password){
		this.password = password;
		
	}
	
	public String found() throws NoSuchAlgorithmException {
		
		//Get every permutation of the special characters up to a length of 5
		charPerms.add("");
		permutation(characters, 5, "");
		
		//Use every permutation for every possible number combination
		for (int i = 0; i < charPerms.size();i ++) {
			String frontPad = charPerms.get(i);
			String hashedPassword = "";
			String password = "";
	
			//Character permutation length == 0
			if(frontPad.length()==0) {
				for(int j = 0; j < counter; j++) {
					password = String.format("%05d", j);
					hashedPassword = shaHash(password);
					if (hashedPassword.equals(this.password)) {
						return password;
					}
				}
				
			//Character permutation length == 1
			}else if(frontPad.length()==1) {
				for(int j = 0; j < counter/10; j++) {
					String numberPad = String.format("%04d", j);
					password = frontPad + numberPad;
					hashedPassword = shaHash(password);
					if (hashedPassword.equals(this.password)) {
						return password;
					}
				}
				
			//Character permutation length == 2
			}else if(frontPad.length()==2) {
				for(int j = 0; j < counter/100; j++) {
					String numberPad = String.format("%03d", j);
					password = frontPad + numberPad;
					hashedPassword = shaHash(password);
					if (hashedPassword.equals(this.password)) {
						return password;
					}
				}
				
			//Character permutation length == 3
			}else if(frontPad.length()==3) {
				for(int j = 0; j < counter/1000; j++) {
					String numberPad = String.format("%02d", j);
					password = frontPad + numberPad;
					hashedPassword = shaHash(password);
					if (hashedPassword.equals(this.password)) {
						return password;
					}
				}
				
			//Character permutation length == 4
			}else if(frontPad.length()==4) {
				for(int j = 0; j < counter/10000; j++) {
					String numberPad = String.format("%01d", j);
					password = frontPad + numberPad;
					hashedPassword = shaHash(password);
					if (hashedPassword.equals(this.password)) {
						return password;
					}
				}
				
			//Character permutation length == 5
			}else if(frontPad.length()==5) {
				password = frontPad;
				hashedPassword = shaHash(password);
				if (hashedPassword.equals(this.password)) {
					return password;
				}
			}
		}
		return "";
		
	}
	
	//Recursive method to create permutations for each combination of special characters
	public static void permutation(char[] characters, int limit, String perm) {
		
		//if the maximum length has been reached, add the permutation to the array list
		if(perm.length() >= limit) {
			charPerms.add(perm);
		}else{
			
			//if the length of the permutation is greater than 0 add it to the array
			//recursively call the permutation() method
			if(perm.length() > 0) {
				charPerms.add(perm);
			}
			for(int i = 0; i < characters.length; i++) {
				permutation(characters, limit, perm + characters[i]);
			}
		}
	}
	
	
	//Create an SHA-256 hash for any string
	public static String shaHash(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes(StandardCharsets.UTF_8));
		byte[] digest = md.digest();
		String hexPSWD = String.format("%064x", new BigInteger(1, digest));
		return hexPSWD;
	}
	
}