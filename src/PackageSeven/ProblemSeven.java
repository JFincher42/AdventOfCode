package PackageSeven;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ProblemSeven {

	public static void main(String[] args) {
		Scanner inFile;
		try {
			inFile = new Scanner(new File("input\\problem-seven.txt"));
		}
		catch (Exception e) {
			return;
		}
		
		ArrayList<Tower> towers=new ArrayList<Tower>();
		while (inFile.hasNextLine()) {
			towers.add(parse(inFile.nextLine()));
		}
		inFile.close();
	}
	
	public static Tower parse(String line) {
		String[] items = line.split("[(),\\s]");
		
		Tower tower = new Tower();
		return tower;
	}

}
