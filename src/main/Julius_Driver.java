package main;

import java.util.Scanner;
import java.io.*;
import java.security.NoSuchAlgorithmException;

public static void main(String[] args) throws NoSuchAlgorithmException {
		
		
		//Call on password file by name
		String inputFile;
		Scanner usrInput = new Scanner(System.in);
		
		//Put file in assets folder
		System.out.print("Input File Name: ");
		inputFile = usrInput.nextLine().trim();
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			
			//Reading the file line by line
			String passwordLine = reader.readLine();
			while(passwordLine != null) {
				String[] splitPSWD = passwordLine.split(":", 0);
				String hashedPSWD = splitPSWD[1];
				/*Digits5_Special rule1 = new Digits5_Special(hashedPSWD);
				String rule = rule1.found();
				System.out.println(rule + " is the password");*/
				
				/*
				SingleWord singleWord = new SingleWord(hashedPSWD);
				String rule = singleWord.found();
				System.out.println(rule + " is the password");
				*/

				//parse input by rules: username:encyption[:otherstuff]
				//send parsed values through different password cracks,
				//if one method finds answer, don't send it to the others!
				//ends when passwords have been thought all calls
				passwordLine = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
>>>>>>> 557aa2a59dbc58fae76203e6a662da74bc835da1
	}
