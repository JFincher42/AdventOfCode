package package23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem23 {

	public static void main(String[] args) throws FileNotFoundException{
		Scanner inFile;
		Program program = new Program();
		inFile = new Scanner(new File("input/problem23.txt"));

		while (inFile.hasNextLine()) {
			String line = inFile.nextLine();
			program.addProgramLine(line);
		}
		inFile.close();

		program.execute(0);
		System.out.println("Mul called " + program.mulCount + " times");
		System.out.println("Register H = " + program.registers.get("h"));
	}

}
