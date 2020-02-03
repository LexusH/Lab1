package main;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class shaHash {
	public String hexPSWD;
		
		//Method for hashing a String
		public shaHash(String password) throws NoSuchAlgorithmException {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes(StandardCharsets.UTF_8));
			byte[] digest = md.digest();
			hexPSWD = String.format("%064x", new BigInteger(1, digest));
		}
}
