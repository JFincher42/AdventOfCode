package PackageSixteen;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ProblemSixteen {

	public static void main(String[] args) {

		// Setup the programs alignment
		ArrayList<Integer> programs = new ArrayList<Integer>(16);
		ArrayList<String> instructions = new ArrayList<String>();
		
		for (int i=0; i<16; i++) programs.add(i+'a');
		
		// Start reading the file
		Scanner inFile;
		try {
			inFile = new Scanner(new File("input/problem-sixteen.txt"));
			inFile.useDelimiter(",");
		}
		catch (Exception e) {
			return;
		}
		
		// Get and parse each line
		while (inFile.hasNext()) {
			instructions.add(inFile.next());
			//System.out.println(instruction);
			//parse(instruction, programs);
		}
		inFile.close();

		for (int counter=0; counter<1; counter++) {
			for (String instruction:instructions) {
				parse(instruction, programs);
			}
		}
		
		for (int prog:programs)
			System.out.print((char) prog);
		System.out.println();

		// PART TWO
		// We need to do 1,000,000,000 of these
		// But we need to do it in a timely manner.
		// Just spinning the above 1,000,000,000 times will take around 26 days to finish...

	}
	
	public static void parse(String instruction, ArrayList<Integer> programs) {
		int length = programs.size();
		
		// The first character is the 'opcode'
		// Arguments follow, separated by a slash ('/')
		
		char opcode = instruction.charAt(0);
		String[] arguments = instruction.substring(1).split("/");
		
		switch (opcode) {
		case 's':							// Spin
			// Grab the first argument, make it an int, 
			// then count back from the length of the list 
			int count = Integer.parseInt(arguments[0]);
			while (count>0) {
				programs.add(0, programs.remove(length-1));
				count--;
			}
			break;
			
		case 'x':							// Exchange
			int first = Integer.parseInt(arguments[0]);
			int second = Integer.parseInt(arguments[1]);
			int temp = programs.get(first);
			programs.set(first, programs.get(second));
			programs.set(second, temp);
			break;
			
		case 'p':							// Partner
			first = programs.indexOf((int)arguments[0].charAt(0));
			second = programs.indexOf((int)arguments[1].charAt(0));
			temp = programs.get(first);
			programs.set(first, programs.get(second));
			programs.set(second, temp);
			break;
		}
	}
	
	

}
