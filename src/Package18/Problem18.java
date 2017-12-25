package Package18;

import java.io.File;
import java.util.Scanner;

public class Problem18 {
	
	public static void main(String[] args) {
		Scanner inFile;
		Program newProgram = new Program();
		try {
			inFile = new Scanner(new File("input/problem18.txt"));
		}
		catch (Exception e) {
			return;
		}
		
		while (inFile.hasNextLine()) {
			newProgram.addProgramLine(inFile.nextLine());
		}
		inFile.close();
		
		System.out.println("Frequency = " + newProgram.execute(0));
	}

}
