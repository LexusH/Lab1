package main;

//@Author Lexus Hartung
//@Author Julius Walton

import java.util.*;
import java.io.*;

//Class for running all password breaking efforts
public class Driver {

	public static void main(String[] args) {
		List<String> pass = new ArrayList<>();

		//Call on password file by name
		System.out.println("Please input the path to your password file ");
		
		Scanner in = new Scanner(System.in);
		String path = in.next();
		in.close();
		
		File file = new File(path);

		try{
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			
			String hold;
			while ((hold = buffer.readLine()) != null){ 
    				pass.add(hold);
			}
			
			buffer.close();
			
			//parse input by form: username:encyption[:otherstuff]
			List<String> parse = new ArrayList<>();
			int S = pass.size();
			
			for(int i = 0; i < S; ++i){
				String[] split = pass.get(i).split("[:]");
				parse.add(split[1]);	
			}

			//Clear file to go into
			ToFile export = new ToFile();
			export.clearFile();
			
			//Send parsed values through different password cracks
			Boolean cont;
			for(int i = 0; i < parse.size(); ++i) {
				cont = false;
				Char5_A_L C5 = new Char5_A_L(parse.get(i));
				cont = C5.found();
				if (cont) {
					continue;
				}
				
				Char7_Capital_Num C7 = new Char7_Capital_Num(parse.get(i));
				cont = C7.found();
				if (cont) {
					continue;
				}
				
				SingleWord SW = new SingleWord(parse.get(i));
				cont = SW.found();
				if (cont) {
					continue;
				}
				
				Digits5_Special D5 = new Digits5_Special(parse.get(i));
				cont = D5.found();
				if (cont) {
					continue;
				}
				
				Digits7 D7 = new Digits7(parse.get(i));
				cont = D7.found();
				if (cont) {
					continue;
				}
				
				//No answer found
				System.out.println("No answer found");
				ToFile TF= new ToFile();
				TF.BuffWrit("No answer found");
			}
			
		}

		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
