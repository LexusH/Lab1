package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ToFile {
	String file = "passwords.txt";
	
	public void BuffWrit(String content) throws IOException{
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writer.write(content);
	    writer.close();
	}
	
	public void clearFile() throws IOException {
		FileWriter del = new FileWriter(file, false);
		del.close();
	}
}
