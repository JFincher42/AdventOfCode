package Package19;

import java.io.File;
import java.util.Scanner;

public class Problem19 {

	public static void main(String[] args) {
		Scanner inFile;
		
		char[][] maze = new char[201][];
		try {
			inFile = new Scanner(new File("input/problem19.txt"));
		}
		catch (Exception e) {
			return;
		}
		
		int row = 0;
		while (inFile.hasNextLine()) {
			maze[row++] = inFile.nextLine().toCharArray();
		}
		inFile.close();
		int x=0, y = 0;
		
		for (int i=0; i<maze[y].length; i++)
			if (maze[y][i] == '|') x = i;
		
		y=1;
		
		int dirY = 1, dirX = 0;
		int steps = 1;
		
		while (maze[y][x] != ' ') {
			switch (maze[y][x]) {
				// Doesn't matter which way we're heading - if it's straight, keep moving.
				case '|': 
				case '-': y+=dirY;  x += dirX;  break;
				
				// If we hit a corner, we need to figure out which way next
				case '+':
					// Moving left or right?  Look up and down
					if (dirY == 0) {
						dirX = 0;
						if (maze[y+1][x]=='|') dirY = 1;
						else dirY = -1;
					} else {
						dirY = 0;
						if (maze[y][x+1]=='-') dirX = 1;
						else dirX = -1;
					}
					y+=dirY;  x += dirX;
					break;
				
				// Otherwise it's a letter, so we print it and keep moving
				default:
					System.out.print(maze[y][x]);
					y+=dirY;  x += dirX; break;
			}
			steps += 1;
		}
		System.out.println("\nSteps = " + steps);
	}

}
