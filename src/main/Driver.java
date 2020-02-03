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
			Char5_A_L C = new Char5_A_L(parse.get(0));
			C.found();
		}

		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
