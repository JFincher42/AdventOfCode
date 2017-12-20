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
		for (int i=0; i<direction.length; i++)		// 1 is down, -1 is up
			direction[i]=1;

		// And the current severity
		int severity = 0;

		// Now loop from 0 to the end of the firewall
		// If we move into a location where the scanner is, calculate the severity
		// Then move all the scanners
		for (int packet=0; packet<100; packet++) {			// Loop through the whole scanner
			if (layers[packet]!=0 && scanner[packet]==0) {	// Is there a scanner here?
															// Is the scanner at layer 0?
				severity += (packet * layers[packet]);		// We're boned, calc the severity
				System.out.println("Boned at " + packet + ", layer is " + layers[packet] + ", severity is " + severity);
			}
			moveScanner(scanner, layers, direction);
		}

		System.out.println("Severity = " + severity);
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
