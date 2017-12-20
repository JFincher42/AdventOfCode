package PackageTwelve;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeSet;

public class ProblemTwleve {

	public static void main(String[] args) {
		Scanner inFile;
		try {
			inFile = new Scanner(new File("input/problem-twelve.txt"));
//			inFile.useDelimiter("[\s,]");
		}
		catch (Exception e) {
			return;
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] connections = new ArrayList[2000];
		while (inFile.hasNext()) {
			int index = inFile.nextInt();
			@SuppressWarnings("unused")
			String skip = inFile.next();
			connections[index] = parseConnections(inFile.nextLine().trim());
		}
		
		inFile.close();
		
		// Now we walk the list and keep adding items until we're done
		
		int groups = 1;

		for (int index = 0; index<connections.length; index++) {

			if (connections[index]!=null) {
				
				// Start of a new group - create a new set and add the first index
				TreeSet<Integer> group0 = new TreeSet<Integer>();
				group0.add(index);
				// Cache the current size of the set
				int size = group0.size();
				// Add all the items which link to this one 
				//for (Integer ID: connections[index]) group0.add(ID);
				group0.addAll(connections[index]);
				// Then set all the items to null
				connections[index]=null;
				
				// If the set size changes, we have more to process
				while (group0.size() != size) {
					// Make a new set to hold the new items, and cache the new size
					TreeSet<Integer> newIDs = new TreeSet<Integer>();
					size = group0.size();
					// For every ID in the original set
					for (Integer member: group0) {
						// As long as it has connections to process
						if (connections[member]!=null) {
							newIDs.addAll(connections[member]);
							connections[member]=null;
						}
					}
					group0.addAll(newIDs);
				}
				groups+=1;
			}
			connections[index]=null;
		}
		System.out.println("Number of groups: " + groups);
	}

	public static ArrayList<Integer> parseConnections(String idList) {
		ArrayList<Integer> connections = new ArrayList<Integer>(0);
		int start = 0;
		int end = 0;
		
		while((end=idList.indexOf(','))!=-1) {
			connections.add(Integer.parseInt(idList.substring(start,end)));
			idList=idList.substring(end+1).trim();
		}
		connections.add(Integer.parseInt(idList.substring(start)));
		return connections;
	}
}
