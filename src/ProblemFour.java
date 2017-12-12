import java.util.Scanner;
import java.io.File;

public class ProblemFour {

	public static void main(String[] args) {
		Scanner inFile;
		try {
			inFile = new Scanner(new File("input\\problem-four.txt"));
		}
		catch (Exception e) {
			return;
		}
		
		boolean match=true;
		int valid = 0;
		int count = 0;
		
		while (inFile.hasNextLine()) {
			count+=1;
			match = true;
			String line = inFile.nextLine();
			String[] passphrases = line.split("\\s");
			for (int i=0; i<passphrases.length-1; i++)
				for (int j=i+1; j<passphrases.length; j++) {
					match = match && !(passphrases[i].equals(passphrases[j]));
					match = match && !anagram(passphrases[i], passphrases[j]);
				}
			if (match) valid+=1;
		}
		System.out.println("Valid: " + valid + ", lines: " + count);
		inFile.close();
	}
	
	public static boolean anagram(String first, String second) {
		if (first.length() != second.length()) return false;
		
		boolean match = true;
		char[] firstChars = first.toCharArray();
		
		for (char ch: firstChars)
			match = match && (second.indexOf(ch)!=-1); 
		
		return match; 
	}

}
