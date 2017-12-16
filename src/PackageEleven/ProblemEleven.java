package PackageEleven;

import java.util.Scanner;
import java.io.File;

public class ProblemEleven {

	public static void main(String[] args) {
		Scanner inFile;
		try {
			inFile = new Scanner(new File("input\\problem-eleven.txt"));
			inFile.useDelimiter(",");
		}
		catch (Exception e) {
			return;
		}
		int n = 0, ne = 0, nw = 0, s = 0, se = 0, sw = 0;
		int maxN=0; maxNW=0; maxNE=0;
		int distance=0;
		while (inFile.hasNext()) {
			String token = inFile.next();
//			int oldX = x;
//			int oldY = y;

			switch (token) {
			case "n":  n+=1;
						if (n>)
						break;
			case "s":  s+=1;  maxN-=1; break;
			case "nw": nw+=1; maxNW+=1; break;
			case "sw": sw+=1; maxNE-=1; break;
			case "ne": ne+=1; maxNE+=1; break;
			case "se": se+=1; maxNW-=1; break;
			}
		}
		inFile.close();

		System.out.format("N: %d, S: %d - NE: %d, SW: %d - NW: %d, SE: %d",n,s,ne,sw,nw,se);
	}
} 

