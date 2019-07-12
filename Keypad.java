import TerminalIO.KeyboardReader;

public class Keypad {

	static boolean open = false;
	
	public static void main(String[] args) {
		KeyboardReader reader = new KeyboardReader();
		
		
		char[] pass = {'1','2','3','4'};
		char[] inputs = new char[100];
		
		char current;
		
		do {
			boolean userLocked = false;
			int count = 0;
			System.out.println("Enter Code(followed by '#' to confirm password):");
			do {
				
				current = reader.readChar();
				if(current == '*' && open) {
					//lock box
					lock();
					userLocked = true;
					break;
				}
				if(current != '#') {
					inputs[count] = current;
					count++;	
				}
			}while(current != '#');
			
			if(!userLocked) {
			
				boolean valid = true;
				
				if(pass.length == count) {
					for(int i = 0; i < pass.length; i++) {
						if(inputs[i] != pass[i])valid = false;
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
