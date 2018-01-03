package PackageSeventeen;

import java.util.ArrayList;

public class ProgramSeventeen {
	public static final int STEP = 354;
	public static ArrayList<Integer> buffer = new ArrayList<Integer>(50000000);
	//public static int[] buffer = new int[50000000];

	public static void main(String[] args) {
		int insertionPoint = 0;
		//buffer[insertionPoint] = 0;
		buffer.add(0);

		for (int count = 1; count<=50000000; count++) {
			insertionPoint = insert(count, insertionPoint);
			if (count%100000 == 0) System.out.print(".");
		}
		
		System.out.println(buffer.get(buffer.indexOf(0)+1));
		//System.out.println("\n" + buffer[0] + ", " + buffer[1]);
		
	}

	private static int insert(int value, int point) {
		//int length = value;
		//int step = STEP;
		int length = buffer.size();
		
		// If we have < STEP items in the list, we're going to wrap around
		// So let's figure out the wrap
//		if (length < STEP) {
//			step = STEP % length;
//		}
		
		// Now we just move to the insertion point
		point = (point + STEP) % length;
		
//		for (int i=length; i>point; i--)
//			buffer[i] = buffer[i-1];
		
//		buffer[point+1]=value;
//		if (point == length-1)
//			buffer.add(value);
//		else
//			buffer.add(point+1, value);
//		
		buffer.add(point, value);
		return point+1;
	}
	

}
