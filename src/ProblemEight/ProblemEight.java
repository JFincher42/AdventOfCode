package ProblemEight;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ProblemEight {

	static ArrayList<Register> registers = new ArrayList<Register>();

	public static void main(String[] args) {
		int highest=0;
		Scanner inFile=null;
		System.out.println("Entering...");
		try {
			inFile = new Scanner(new File("input\\problem-eight.txt"));
		}
		catch (Exception e) {
			System.out.println("Couldn't find file");
			//return;
		}

		System.out.print("Parsing...");
		while (inFile.hasNextLine()) {
			System.out.print(".");
			parse(inFile.nextLine());
			int currentLargest = findLargest();
			if (currentLargest > highest) highest = currentLargest;
		}
		inFile.close();
		// Now we find the largest value
		System.out.print("\nLargest value: " + findLargest());
		System.out.print("\nLargest value ever: " + highest);

		System.out.println("\nDone!");

	}

	public static void parse(String line) {
		// Split the line in tokens
		String[] source = line.split("\\s");

		// Token 0 is the register we will work on
		String dest = source[0];
		// Token 1 is whether we increment or decrement
		boolean increment = (source[1].equals("inc"));
		// Token 2 is how much to increment or decrement
		int value = Integer.parseInt(source[2]);

		// Find the register on which we're working
		// If it doesn't exist, we create it and initialize it to zero
		Register destRegister= findRegister(dest);

		// Token 4 is the comparator register
		Register compareRegister = findRegister(source[4]);
		// Token 5 is comparison operator
		String compare = source[5];
		// Token 6 is what to compare the register against
		int compareValue = Integer.parseInt(source[6]);

		// Check the comparison operator and act if required

		switch (compare) {
		case ">":
			if (compareRegister.value > compareValue)
				if (increment)	destRegister.inc(value);
				else			destRegister.dec(value);
			break;

		case "<":
			if (compareRegister.value < compareValue)
				if (increment)	destRegister.inc(value);
				else			destRegister.dec(value);
			break;

		case ">=":
			if (compareRegister.value >= compareValue)
				if (increment)	destRegister.inc(value);
				else			destRegister.dec(value);
			break;

		case "<=":
			if (compareRegister.value <= compareValue)
				if (increment)	destRegister.inc(value);
				else			destRegister.dec(value);
			break;

		case "==":
			if (compareRegister.value == compareValue)
				if (increment)	destRegister.inc(value);
				else			destRegister.dec(value);
			break;

		case "!=":
			if (compareRegister.value != compareValue)
				if (increment)	destRegister.inc(value);
				else			destRegister.dec(value);
			break;
		}
		
	}

	public static int findLargest() {
		int max=0;
		// Get an iterator to walk through the array list
		Iterator<Register> iterator = registers.iterator();

		// Loop through the array list, looking for the one with the right name
		// Return it if we find it
		while (iterator.hasNext()) {
			Register next = (Register) iterator.next();
			if (next.value > max) max = next.value;
		}
		return max;
	}

	public static Register findRegister(String registerName) {
		// Get an iterator to walk through the array list
		Iterator<Register> iterator = registers.iterator();

		// Loop through the array list, looking for the one with the right name
		// Return it if we find it
		while (iterator.hasNext()) {
			Register next = (Register) iterator.next();
			if (next.name.equals(registerName))
				return next;
		}
		// If we get this far, we didn't find the register we want
		// So create a new one and add it to the list, then return it
		Register newRegister = new Register(registerName);
		registers.add(newRegister);
		return newRegister;
	}

}