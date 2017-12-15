package PackageTen;

import java.io.File;
import java.util.Scanner;

public class ProblemTen {

	public static final int SIZE = 256; 
	public static int[] list = new int[SIZE];
	
	public static void main(String[] args) {
	
		// First, initialize the list
		for (int i=0; i<SIZE; i++) list[i]=i;
		
		Scanner inFile;
		try {
			inFile = new Scanner(new File("input/problem-ten.txt"));
			inFile.useDelimiter(",");
		}
		catch (Exception e) {
			return;
		}
		
		int current=0, skip=0;
		while (inFile.hasNextInt()) {
			int length = inFile.nextInt();
//			System.out.println("Length = " + length);
			twist(current, length, skip);
			current += length+skip++;
			if (current >= SIZE) current-=SIZE;
//			System.out.println(Arrays.toString(list));
		}
		
		System.out.println(list[0]*list[1]);
		inFile.close();
	}
	
	public static void twist(int start, int length, int skip) {
		int end = start+length-1;
		if (end>=SIZE) end-=SIZE;
		
		while (length>0) {
			int temp = list[start];
			list[start] = list[end];
			list[end] = temp;
			
			start+=1;
			if (start>=SIZE) start -= SIZE;
			
			end-=1;
			if (end<0) end=SIZE-1;
			
			length -= 2;
		}
		
	}
}
