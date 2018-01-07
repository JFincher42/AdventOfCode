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
			if (current.distance <= closestDistance) closest = i;
		}

		// Now we cycle until the closest doesn't change for 500 cycles
		int cycle = 5000;
		while (cycle>0) {
			closestDistance = particles.get(closest).distance;
			int oldClosest = closest;

			// Check for collisions
			checkCollisions();

			for (int i=0; i<particles.size(); i++) {
				Particle current = particles.get(i);
				if (current.inPlay()) {
					current.move();

					current.calcDistance();
					if (current.distance <= particles.get(closest).distance) closest = i;
				}
			}
			if (closest == oldClosest) cycle-=1;
			else cycle = 5000;
		}
		System.out.println("Closest = " + closest);
		int count=0;
		for (Particle particle:particles)
			if (particle.inPlay()) count++;
		System.out.println("Count = " + count);
			
	}

	public static void checkCollisions() {

		for (int i=0; i<particles.size()-1; i++) {
			Particle part1 = particles.get(i);
			if (part1.inPlay()) {
				for (int j=i+1; j<particles.size(); j++) {
					Particle part2 = particles.get(j);
					if (part2.inPlay()) {
						if ((part1.posX==part2.posX) && (part1.posY==part2.posY) && (part1.posZ==part2.posZ)) {
							part1.collided = true;
							part2.collided = true;
						}
					}
				}
			}
		}
	}
}