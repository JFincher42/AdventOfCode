package PackageThirteen;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ProblemThirteen {

	public static void main(String[] args) {
		Scanner inFile;
		try {
			inFile = new Scanner(new File("input/problem-thirteen.txt"));
			inFile.useDelimiter("[\\s:]");
		}
		catch (Exception e) {
			return;
		}

		int[] layers = new int[100];				// This is the layer data
		int lastLayer = 0;
		while (inFile.hasNext()) {
			lastLayer= inFile.nextInt();			// Get the layer number
			inFile.next();							// Skip the blank
			layers[lastLayer] = inFile.nextInt();	// Get the size of the layer
			System.out.println("Layer[" + lastLayer + "] = " + layers[lastLayer]);
		}
		inFile.close();

		int[] scanner = new int[100];				// Where is the scanner now?
		int[] direction = new int[100];				// Which way are we moving?
		for (int i=0; i<100; i++)		// 1 is down, -1 is up
			direction[i]=1;

		// And the current severity
		int severity = -1;

		// Let's play with some delays
		int delay = 0;
		int[] savedScanner=new int[100];
		int[] savedDirection=new int[100];

		for (int i=0; i<delay; i++)			// Now delay
			moveScanner(scanner, layers, direction);

		// Save the scanner and direction
		for (int i=0; i<100; i++) {
			savedScanner[i] = scanner[i];
			savedDirection[i] = direction[i];
		}

		while (severity!=0) {						// Loop until we get a severity = 0
			
			for (int i=0; i<100; i++) {
				scanner[i]=savedScanner[i];			// Reset the scanner
				direction[i]=savedDirection[i];		// And directions
			}
			
			delay += 1;								// Increment the delay
			//for (int i=0; i<delay; i++)			// Now delay
			moveScanner(scanner, layers, direction);

			// Save these settings
			for (int i=0; i<100; i++) {
				savedScanner[i]=scanner[i];			// Reset the scanner
				savedDirection[i]=direction[i];		// And directions
			}


			severity = 0;							// Reset severity

			// Now loop from 0 to the end of the firewall
			// If we move into a location where the scanner is, calculate the severity
			// Then move all the scanners
			for (int packet=0; packet<100; packet++) {			// Loop through the whole scanner
				if (layers[packet]!=0 && scanner[packet]==0) {	// Is there a scanner here?
																// Is the scanner at layer 0?
					severity += (packet * layers[packet]);		// We're boned, calc the severity
//					System.out.println("Boned at " + packet + ", layer is " + layers[packet] + ", severity is " + severity);
				}
				moveScanner(scanner, layers, direction);
			}
			if (0==delay%1000)
				System.out.println("Delay: " + delay + ", severity: " + severity);
		}
		System.out.println("Delay for safe passage = " + delay);
	}

	public static void moveScanner(int[] scanner, int[] layers, int[] direction) {

		for (int i=0; i<scanner.length; i++){			// Look at each scanner
			if (layers[i]!=0) {							// If there is a scanner here
				if (direction[i]==1) {					// Are we moving down?
					if (layers[i]-1==scanner[i]) {		// Are we at the bottom? 
						direction[i]=-1;				// If so, let's go back up
					}
				} else {								// We're moving up
					if (scanner[i]==0) {				// Are we at the top?
						direction[i]=1;					// Start moving back down
					}
				}
			}

			scanner[i]+=direction[i];					// Move the scanner in the right direction
		}

	}

}
