package PackageTen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProblemTen {

	public static final int SIZE = 256; 
	public static int[] list = new int[SIZE];
	
	public static void main(String[] args) throws IOException {
	
		// First, initialize the list
		for (int i=0; i<SIZE; i++) list[i]=i;
		
		// Need to read character by character
		BufferedReader inFile;
		try {
			inFile = new BufferedReader(new FileReader("input/problem-ten.txt"));
		}
		catch (Exception e) {
			return;
		}
		
		// Build an ArrayList out of the input string
		ArrayList<Integer> inputString = new ArrayList<Integer> ();
		int inputChar;
		while (-1 != (inputChar=inFile.read())) {
			inputString.add(inputChar);
		}
		// Add the final five items
		inputString.add(17);
		inputString.add(31);
		inputString.add(73);
		inputString.add(47);
		inputString.add(23);
		
		inFile.close();
		
		// Start everything at zero
		int current=0, skip=0;
		// But now we do 64 rounds
		int round = 64;
		while (round>0) {
			// Loop through all the items in the inputString
			for (int i=0; i<inputString.size(); i++) {
				int length = inputString.get(i);
				twist(current, length, skip);
				current += length+skip++;
				while (current >= SIZE) current-=SIZE;
			}
			round--;
		}
		
		// Now we convert this to a dense hash
		int[] dense = new int[16];
		for (int block = 0; block<16; block++) {
			int start = block*16;
			int end = start + 16;
			dense[block] = list[start];
			for (int item=start+1; item<end; item++) dense[block] = dense[block] ^ list[item];
			System.out.format("%x", dense[block]);
		}
		
		
	}
	
	public static void twist(int start, int length, int skip) {
		int end = start+length-1;
		if (end>=SIZE) end-=SIZE;
		
		while (length>0) {
			int temp = list[start];
			list[start] = list[end];
			list[end] = temp;
			
			start+=1;
			if (start>=SIZE) start -= SIZE;
			
			end-=1;
			if (end<0) end=SIZE-1;
			
			length -= 2;
		}
		
	}
}
