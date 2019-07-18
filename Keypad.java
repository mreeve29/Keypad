/* Keypad.java
 * Author: Michael Reeve
 * Most Recent Edit: 7/17/19
 */

//I/O Class that was used to get user input, link in readme
import TerminalIO.KeyboardReader;

public class Keypad {

	//keep track of state of keypad(lockbox)
	static boolean open = false;
	
	public static void main(String[] args) {
		//KeyboardReader object for user input
		KeyboardReader reader = new KeyboardReader();
		
		//array 'pass' is the correct password, feel free to change this to any combination of characters
		char[] pass = {'1','2','3','4'};
		
		//String that holds the user's attempted password to be checked with the correct password
		String inputs = "";
		
		//char that holds the most recent char that the user entered while attempting the password
		char current;
		
		do {
			//reset inputs after every attempt
			inputs = "";
			
			//boolean that keeps track if the user entered '*' to lock the box(keypad)
			boolean userLocked = false;
			
			//boolean that is used to determine if the input should be ignored when box is locked and user does not enter '*' to unlock
			boolean validInput = true;
			
			//prompt user to attempt the password
			if(!open) System.out.println("Enter Code(followed a return after each character, then by '#' to confirm password):");
			do {
				
				//get input
				current = reader.readChar();
				//if the box is open and the user does not enter '*', ignore all inputs
				if(current != '*' && open) {
					validInput = false;
				}
				
				//if user wishes to lock and the box is open, lock the box
				if(current == '*' && open) {
					//lock box
					lock();
					userLocked = true;
					break;
				}
				
				//if the most recent char entered is not the accept key, add it to the attempt
				if(current != '#') {
					inputs += current;
				}
			}while(current != '#');
			
			//only compare attempt to password if the user did not enter '*' 
			if(!userLocked && validInput) {
				//keeps track of the validity of the attempt while checking 
				boolean valid = true;
				
				//convert the attempt to an array of chars
				char[] passToChar = inputs.toCharArray();
				
				//if pass length != input length the attempt cannot be correct
				if(pass.length == inputs.length()) {
					//loop through each character of the attempt and compare it to the correct password
					for(int i = 0; i < pass.length; i++) {
						if(passToChar[i] != pass[i])valid = false;
					}
				}else {
					valid = false;
				}
				
				if(valid) {
					System.out.println("Correct");
					open();
				}
				else if(!valid) {
					System.out.println("Incorrect");
				}
			}
		}while(true);

	}

	private static void lock() {
		System.out.println("Locked!");
		open = false;
		
	}

	private static void open() {
		System.out.println("Unlocked!('*' to lock)");
		open = true;
		
	}

}
