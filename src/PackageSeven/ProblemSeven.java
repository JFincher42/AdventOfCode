package PackageSeven;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ProblemSeven {

	static ArrayList<Tower> towers;
	
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
		towers=new ArrayList<Tower>();
		while (inFile.hasNextLine()) {
			towers.add(parse(inFile.nextLine()));
		}
		inFile.close();
		
		// Now, process the list into a tree
		// For each node in the list
		//   If the node has children
		//     For each child in that list
		//       Locate the child node
		//       Add it to a new arraylist
		//       Remove it from the main list
		//     Replace the child list with the new list
		// 
		// When done, the only item left in the ArrayList should be the root node
		
		// Walk the entire list
		for (int i=0; i<towers.size(); i++) {
			Tower current = towers.get(i);
			// If the current tower has children
			if (null!=current.children) {
				// Create a new children list
				ArrayList<Tower> newChildren = new ArrayList<Tower>(current.children.size());
				// Walk through the list of children
				for (int childIndex=0; childIndex<current.children.size(); childIndex++) {
					Tower child = current.children.get(childIndex);
					// Find the child in the array list and mark it as processed.
					newChildren.add(findAndRemove(child));
				}
				// Set the new list as the current list in this node
				current.children = newChildren;
			}
		}
		
		Tower root;
		for (Tower current:towers) {
			if (!current.processed)
				root = current;
		}
		
		// Now we have to compute branch weights
		// 
		
	}
	
	public static Tower findAndRemove(Tower item) {
		Tower removed = null;
		for (int i=0; i<towers.size(); i++) {
			Tower current = towers.get(i);
			if (current.name.equals(item.name)) {
				removed = current;
				current.processed = true;
			}
		}
		return removed;
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
