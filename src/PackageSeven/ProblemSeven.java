package PackageSeven;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ProblemSeven {

	public static void main(String[] args) {
		// Open the input file
		Scanner inFile;
		try {
			inFile = new Scanner(new File("input/problem-seven.txt"));
		}
		catch (Exception e) {
			return;
		}
		
		// Read and parse each line, add each to an ArrayList
		ArrayList<Tower> towers=new ArrayList<Tower>();
		while (inFile.hasNextLine()) {
			towers.add(parse(inFile.nextLine()));
		}
		inFile.close();
		
		// Now, process the list into a tree
		// Visit each node with children
		//  For each child, find the 
	}
	
	public static Tower parse(String line) {
		String[] items = line.split("[(),\\s]");
		
		System.out.println(line + " --> " + Arrays.toString(items));
		
		Tower tower = new Tower(items[0], Integer.parseInt(items[2]));
		System.out.println("  Name: " + items[0] + ", weight: " + items[2]);
		
		if (items.length>3) {
			for (int i=5; i<items.length; i+=2) {
				System.out.println("  Adding child named: " + items[i]);
				tower.addChild(new Tower(items[i]));
			}
		}
		return tower;
	}

}
