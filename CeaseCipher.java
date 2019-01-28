import java.math.BigInteger;
import java.util.Scanner;

public class CeaseCipher {

	public static void main(String[] args)
	{
		CeaseCipher e = new CeaseCipher();
		System.out.println("Please Enter your message : ");		
		Scanner scan = new Scanner(System.in);
		String msg = scan.nextLine();
		boolean flag = e.checkMessage(msg);
		try{
		if (flag == true) {
			String cipherText = e.encryt(msg, 222, 'l');					//Method to Call Encrypt Function
			String updatedCipherText = e.tamper(cipherText);				//Method to Tamper Encrypt Function
			e.decrypt(updatedCipherText, 222, 'r');							//Method to Call Decrypt Function
			String encryptMsg1 = e.encryt(msg, 222, 999, 'l', 'r');			//Method to Call Double Encrypt Function
			e.decryt(encryptMsg1, 222, 999, 'r', 'l');						//Method to Call Double Decrypt Function
		}
		else
			System.out.println("Please enter appropiate message which"
					+ " is more than or equals to 32 bits and contians only aplhabets with no space");
	
		}catch(Exception ex){
			System.err.println("Caught Exception" + ex.getMessage());
		}
	}

	// Method to Check whether Message is 32 bits long AND contains only Alphabets
	public boolean checkMessage(String msg) {
		if (msg.matches("[a-zA-Z]+") == true && msg.length() >= 32)
			return true;
		else
			return false;
	}

	// Method to Encrypt Message using Caeser Cipher
	public String encryt(String msg, int key, char direction) {
		System.out.println("Original Message : " + msg);
		String encryptMsg = "";
		if (key == 222) {													//Check Key			
			for (int i = 0; i < msg.length(); i++) {
				char oldChar = msg.charAt(i);
				int ascii = (int) oldChar;
				if (direction == 'r' || direction == 'R') {					//Check Direction equals to Right
					if (ascii == 120 || ascii == 121 || ascii == 122)
						ascii = ascii - 23;
					else
						ascii = ascii + 3;
				} else if (direction == 'l' || direction == 'L') {			//Check Direction equals to Left	
					if (ascii == 97 || ascii == 98 || ascii == 99)
						ascii = ascii + 23;
					else
						ascii = ascii - 3;
				}
				char newChar = (char) ascii;
				String a = Character.toString(newChar);
				encryptMsg += a;
			}
		}
		else
			System.out.println("Key does not match or Key is not proper");
		System.out.println("Encrypted Message after single Caesar Cipher : " + encryptMsg);
		return encryptMsg;
	}

	
	//Method to Decrypt MEssage using Caesar Cipher
	public String decrypt(String msg, int key, char direction) {
		String decryptMsg = "";
		if (key == 222) {														//Check Key 		
			for (int i = 0; i < msg.length(); i++) {
				char oldChar = msg.charAt(i);
				int ascii = (int) oldChar;
				if (direction == 'r' || direction == 'R') {						//Check direction equals to right
					if (ascii == 120 || ascii == 121 || ascii == 122)
						ascii = ascii - 23;
					else
						ascii = ascii + 3;											
				} else if (direction == 'l' || direction == 'L') {				//Check direction equals to left
					if (ascii == 97 || ascii == 98 || ascii == 99)
						ascii = ascii + 23;
					else
						ascii = ascii - 3;
				}
				char newChar = (char) ascii;
				String a = Character.toString(newChar);
				decryptMsg += a;
			}
		}
		else
			System.out.println("Key does not match or Key is not proper");
		System.out.println("Decrypted Message after single Caesar Cipher : " + decryptMsg);
		return decryptMsg;
	}

	
	//Method to Tamper Message, Charlie will tamper the message by flipping bits for first SIX alphabets
	public String tamper(String cipherText) {
		String updateCipherText = "";
		String mySubString = cipherText.substring(0, 6);
		String myRemString = cipherText.substring(7, cipherText.length());
		String a = new BigInteger(mySubString.getBytes()).toString(2);
		System.out.println("Message in bits (before Tampering)" + a);
		for (int i = 0; i < a.length(); i++) {
			char myChar = a.charAt(i);
			if (myChar == '1')
				myChar = '0';
			else
				myChar = '1';
			updateCipherText += Character.toString(myChar);
		}
		System.out.println("Message updated by Charlie in bits : " + updateCipherText);

		updateCipherText = new String(new BigInteger(updateCipherText, 2).toByteArray());
		updateCipherText += myRemString; 
		
		System.out.println("Message updated by Charlie in String : " + updateCipherText);
		return updateCipherText;
	}

	
	//Method to Encrpyt Message using 2-round Caeser Cipher
	public String encryt(String msg, int key1, int key2, char direction1, char direction2) {
		System.out.println("Original Message : " + msg);
		String encryptMsg1 = "";
		String encryptMsg2 = "";
		if (key1 == 222) {
			for (int i = 0; i < msg.length(); i++) {
				char oldChar = msg.charAt(i);
				int ascii = (int) oldChar;
				if (direction1 == 'r' || direction1 == 'R') {
					if (ascii == 120 || ascii == 121 || ascii == 122)
						ascii = ascii - 23;
					else
						ascii = ascii + 3;
				} else if (direction1 == 'l' || direction1 == 'L') {
					if (ascii == 97 || ascii == 98 || ascii == 99)
						ascii = ascii + 23;
					else
						ascii = ascii - 3;
				}				
				char newChar = (char) ascii;
				String a = Character.toString(newChar);
				encryptMsg1 += a;
			}
		
		if (key2 == 999) {
			for (int i = 0; i < encryptMsg1.length(); i++) {
				char oldChar = encryptMsg1.charAt(i);
				int ascii = (int) oldChar;
				if (direction2 == 'r' || direction2 == 'R') {
					if (ascii == 122)
						ascii = ascii - 25;
					else
						ascii = ascii + 1;
				} else if (direction2 == 'l' || direction2 == 'L') {
					if (ascii == 97)
						ascii = ascii + 25;
					else
						ascii = ascii - 1;
				}
				char newChar = (char) ascii;
				String a = Character.toString(newChar);
				encryptMsg2 += a;
			}
		}
			else
				System.out.println("Key does not match or Key is not proper");
		} 
			else
				System.out.println("Key does not match or Key is not proper");
		System.out.println("Encrypted Message after two round of Caesar Cipher : " + encryptMsg2);
		return encryptMsg2;
	}

	//Method to Decrpyt Message using 2-round Caeser Cipher
	public String decryt(String msg, int key1, int key2, char direction1, char direction2) {
		String decryptMsg1 = "";
		String decryptMsg2 = "";
		if (key1 == 222) {
			for (int i = 0; i < msg.length(); i++) {
				char oldChar = msg.charAt(i);
				int ascii = (int) oldChar;
				if (direction1 == 'r') {
					if (ascii == 120 || ascii == 121 || ascii == 122)
						ascii = ascii - 23;
					else
						ascii = ascii + 3;
				} else if (direction1 == 'l') {
					if (ascii == 97 || ascii == 98 || ascii == 99)
						ascii = ascii + 23;
					else
						ascii = ascii - 3;
				}
				char newChar = (char) ascii;
				String a = Character.toString(newChar);
				decryptMsg1 += a;
			}
		
		if (key2 == 999) {
			for (int i = 0; i < decryptMsg1.length(); i++) {
				char oldChar = decryptMsg1.charAt(i);
				int ascii = (int) oldChar;
				if (direction2 == 'r') {
					if (ascii == 122)
						ascii = ascii - 25;
					else
						ascii = ascii + 1;
				} else if (direction2 == 'l') {
					if (ascii == 97)
						ascii = ascii + 25;
					else
						ascii = ascii - 1;
				}
				char newChar = (char) ascii;
				String a = Character.toString(newChar);
				decryptMsg2 += a;
			}
		}
			else
				System.out.println("Key does not match or Key is not proper");
		} 
			else
				System.out.println("Key does not match or Key is not proper");
		System.out.println("Decrypted Message after Two round of Caesar Cipher : " + decryptMsg2);
		return decryptMsg2;
	}
}
