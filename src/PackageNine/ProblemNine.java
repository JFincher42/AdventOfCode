package PackageNine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProblemNine {
	enum Tokens{
		OPEN, 
		CLOSE, 
		COMMA, 
		GARBAGE,
		EOF
		};
		
	public static int garbageCount=0;

	public static void main(String[] args) throws IOException {
		BufferedReader inFile;
		try {
			inFile = new BufferedReader(new FileReader("input\\problem-nine.txt"));
		}
		catch (Exception e) {
			return;
		}
		
		// Set some variables
		int currentLevel = 0;
		int score = 0;
		
		// Tokenize the input stream
		Tokens current = tokenize(inFile);
		while (current != Tokens.EOF) {
			// Now to implement a state machine... Easy!
			// Each group starts with an OPEN token
			//   If we see one, we increase the level
			//   After an OPEN, we can see any other token
			// Each group ends with a CLOSE token
			//   If we see one, we add the current level to score, and reduce the level
			//   After a CLOSE, we can see another CLOSE or a COMMA
			// COMMAS are separators
			//   After a COMMA, we can see GABAGE or an OPEN
			//   But for now, we do nothing
			// Groups can contain GARBAGE
			//   After GARBAGE, we can see a COMMA OR CLOSE
			//   But for now, we do nothing
		
			switch (current) {
			case OPEN:
//				System.out.println("  "+current);
				currentLevel++;  break;
			case CLOSE:
//				System.out.println("  "+current);
				score += currentLevel--;  break;
			case COMMA:    
			case GARBAGE:  
			case EOF:
//				System.out.println("  "+current);
				break;
			}
			
			current = tokenize(inFile);
		}
		inFile.close();
//		score+=currentLevel--;
		System.out.println("Score = " + score + ", garbage=" + garbageCount);
	}
	
	public static Tokens tokenize(BufferedReader inFile) throws IOException {
		// We're always at the start of a new token
		// They are:
		//   { = OPEN
		//   } = CLOSE
		//   , = COMMA
		//   <.*> = GARBAGE
		//   !. = ignored character
		//   -1 = EOF
		
		Tokens returnToken=null;
		int nextChar;
		while (returnToken==null) {
			try {
				nextChar = inFile.read();
//				System.out.println("Read: " + (char)nextChar);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

			switch (nextChar) {
			case -1:
				returnToken = Tokens.EOF;    break;
			case '{':
				returnToken = Tokens.OPEN;   break;
			case '}':
				returnToken = Tokens.CLOSE;  break;
			case ',':
				returnToken = Tokens.COMMA;  break;
			case '<':
				// At this point we will return garbage, but we need to know how much
				// Scan to the end of the garbage (noted by '>')
				// Skip every character
				// If you find a !, skip the next character as well.
				boolean end = false;
				while (!end) {
					nextChar = inFile.read();
					switch (nextChar) {
					case '!': nextChar = inFile.read(); break;
					case '>': end=true; break;
					default: garbageCount+=1; break;
					}
				}
				returnToken = Tokens.GARBAGE;  break;
			case '!':
				// Ignored character, so we grab one more and skip it
				nextChar = inFile.read();  break;
			}
		}
		return returnToken;
	}

}
