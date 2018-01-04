package Package20;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class Problem20 {

	static ArrayList<Particle> particles = new ArrayList<Particle>();
	
	public static void main(String[] args) {
		Scanner inFile;
		
		try {
			inFile = new Scanner(new File("input/problem20.txt"));
			inFile.useDelimiter("\\s");
		}
		catch (Exception e) {
			return;
		}
		
		while (inFile.hasNext()) {
			String pos = inFile.next();
			String vel = inFile.next();
			String acc = inFile.next();
			System.out.println("pos:" + pos + " | vel:" +vel + " | acc:" + acc);
			particles.add(new Particle(pos, vel, acc));
		}
		inFile.close();
		
		
		// Figure out the distance for each particle, and which is closest
		int closest = 0;
		int closestDistance = particles.get(closest).distance;
		for (int i=0; i<particles.size(); i++) {
			Particle current = particles.get(i);
			current.calcDistance();
			if (current.distance < closestDistance) closest = i;
		}
		
		// Now we cycle until the closest doesn't change for 500 cycles
		int cycle = 5000;
		while (cycle>0) {
			closestDistance = particles.get(closest).distance;
			int oldClosest = closest;
			for (int i=0; i<particles.size(); i++) {
				Particle current = particles.get(i);
				current.move();
				current.calcDistance();
				if (current.distance < particles.get(closest).distance) closest = i;
			}
			if (closest == oldClosest) cycle-=1;
			else cycle = 5000;
		}
		System.out.println("Closest = " + closest);
		
	}

}
