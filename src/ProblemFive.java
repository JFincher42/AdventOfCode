import java.util.Scanner;
import java.io.File;

public class ProblemFive {

	public static void main(String[] args) {
		Scanner inFile;
		try {
			inFile = new Scanner(new File("input\\problem-five.txt"));
		}
		catch (Exception e) {
			return;
		}
		
		int[] instructions=new int[1090];
		int index=0;
		while (inFile.hasNextInt()) {
			instructions[index++] = inFile.nextInt();
		}
		inFile.close();
		
		//doPartOne(instructions);
		doPartTwo(instructions);

	}

	public static void doPartOne(int[] instructions) {
		int current = 0, next = 0, count = 0;
		while (current<instructions.length) {
			next += instructions[current];
			instructions[current]++;
			current = next;
			count++;
		}
		System.out.println("Part One: " + count);
	}

	public static void doPartTwo(int[] instructions) {
		int current = 0, next = 0, count = 0;
		while (current<instructions.length) {
			next += instructions[current];
			if (instructions[current]>=3)	instructions[current]--;
			else 							instructions[current]++;
			current = next;
			count++;
		}
		System.out.println("Part Two: " + count);
		
	}
}
