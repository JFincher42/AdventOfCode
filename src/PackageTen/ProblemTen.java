package PackageTen;

import java.io.File;
import java.util.Scanner;

public class ProblemTen {

	public static int[] list = new int[256];
	
	public static void main(String[] args) {
	
		// First, initialize the list
		for (int i=0; i<list.length; i++) list[i]=i;
		
		Scanner inFile;
		try {
			inFile = new Scanner(new File("input\\problem-ten.txt"));
			inFile.useDelimiter(",");
		}
		catch (Exception e) {
			return;
		}
		

	}

}
