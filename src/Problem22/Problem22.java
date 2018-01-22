package Problem22;

import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

public class Problem22 {

	public static void main(String[] args) {
		Scanner inFile;
		
		char[][] network = new char[500][500];

		for (int i=0; i<network.length; i++) {
			for (int j=0; j<network[0].length; j++) {
				network[i][j]= '.';
			}
		}
		
		
		try {
			inFile = new Scanner(new File("input/problem22.txt"));
		}
		catch (Exception e) {
			return;
		}
		
		int startRow = 220;
		while (inFile.hasNextLine()) {
			int startCol = 220;
			String line = inFile.nextLine();
			for (char node: line.toCharArray())
				network[startRow][startCol++]=node;
			startRow++;
		}
		
		// Direction is up=0, right=1, down=2, left=3
		// Right turns are +1, left turns are -1, check for bounds.
		int carrierRow = 232, carrierCol = 232, carrierDirection = 0;
		int infected=0;

//		System.out.println("Infected: "+infected + ", r,c,d:" + carrierRow + ", " + carrierCol + ", " + carrierDirection);
//		for (char[] row: network)
//			System.out.println(Arrays.toString(row));

		for(int i=0; i<10000; i++) {
			if (network[carrierRow][carrierCol]=='.') {
				// Uninfected, infect and turn left
//				System.out.println("Move " + i + ", clean");
				carrierDirection= (carrierDirection+3)%4;
				network[carrierRow][carrierCol]='#';
				infected++;
			} else {
				// Infected, clean and turn right
//				System.out.println("Move " + i + ", infected");
				carrierDirection=(carrierDirection+1)%4;
				network[carrierRow][carrierCol]='.';
			}
			switch (carrierDirection) {
			case 0:	carrierRow--;  break;
			case 1: carrierCol++;  break;
			case 2: carrierRow++;  break;
			case 3: carrierCol--;  break;
			}
//			System.out.println("Infected: "+infected + ", r,c,d:" + carrierRow + ", " + carrierCol + ", " + carrierDirection);
//			for (char[] row: network)
//				System.out.println(Arrays.toString(row));
//
		}
		System.out.println("Infected: "+infected + ", r,c,d:" + carrierRow + ", " + carrierCol + ", " + carrierDirection);
//		for (char[] row: network)
//			System.out.println(Arrays.toString(row));
	}

}
