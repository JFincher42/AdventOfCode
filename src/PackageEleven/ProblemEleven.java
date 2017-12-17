package PackageEleven;

import java.util.Scanner;
import java.io.File;

public class ProblemEleven {

	public static void main(String[] args) {
		Scanner inFile;
		try {
			inFile = new Scanner(new File("input/problem-eleven.txt"));
			inFile.useDelimiter(",");
		}
		catch (Exception e) {
			return;
		}
		int n = 0, ne = 0, nw = 0, s = 0, se = 0, sw = 0;
		int maxDistance=0;
		int distance=0;
		while (inFile.hasNext()) {
			String token = inFile.next();
//			int oldX = x;
//			int oldY = y;

			switch (token) {
			case "n":  n+=1;  break;
			case "s":  s+=1;  break;
			case "nw": nw+=1; break;
			case "sw": sw+=1; break;
			case "ne": ne+=1; break;
			case "se": se+=1; break;
			}
			distance = findDistance(n,s,ne,nw,se,sw);
			if (distance>maxDistance) maxDistance = distance;
		}
		inFile.close();

		System.out.format("N: %d, S: %d - NE: %d, SW: %d - NW: %d, SE: %d",n,s,ne,sw,nw,se);
		System.out.format("Distance = %d, Max Distance = %d", distance, maxDistance);
	}
	
	public static int findDistance(int n, int s, int ne, int nw, int se, int sw) {
		int distance = 0;
		
		// Get the raw movement along the N-S axis, NE-SW axis, and NW-SE axis
		int ns=n-s;
		int nesw = ne-sw;
		int nwse = nw-se;
		
		if (ns<0) ns*=-1;
		if (nesw<0) nesw*=-1;
		if (nwse<0) nwse*=-1;
		
		// Here's the algorithm - movement along the NS axis is single step, so it always gets added.
		// From there, we find the longest distance along either diagonal axis.
		// The smaller distance gets subtracted from that
		// But is readded to bring us back to the NS axis
		// So we just add the longer distance to get the raw 
		// That gets added to the NS distance to get the total distance
		if (nesw<nwse) distance = ns + nwse;
		else distance = ns + nesw;
		
		return distance;
	}
} 