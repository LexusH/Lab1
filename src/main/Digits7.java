package main;

//Any word that is made with digits up to 7 digits length.
public class Digits7 {
	private String password;
	
	Digits7(String password){
		this.password = password;
	}
	
	public void found(){
		try {
			//Permutations of all digits of length 7
			for (int i = 0; i < 9999999; i++) {
				//Makes number into string
				String numbers = "" + i + "";
				//Determines how many zeros to add
				int len = numbers.length();
				len = 7 - len;
				//Adds heading zeros to add to length
				for(int j = 0; j <= len; ++j) {
					numbers = "0" + numbers;
				}
				//Hash password made
				shaHash H = new shaHash(numbers);
				String hashed = H.hexPSWD;
				//Checking if hashed number equals password
				if(hashed.equals(this.password)){
					//Printing outcome to console and file	
					String output = hashed + ":" + numbers;
					System.out.println(output);
					ToFile TF= new ToFile();
					TF.BuffWrit(output);
					return;
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
