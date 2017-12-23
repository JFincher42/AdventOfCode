package PackageSeventeen;

import java.util.ArrayList;

public class ProgramSeventeen {
	public static final int STEP = 3;
	public static ArrayList<Integer> buffer = new ArrayList<Integer>();

	public static void main(String[] args) {
		int insertionPoint = 0;
		buffer.add(0);
		for (int count = 1; count<=2017; count++) {
			insertionPoint = insert(count, insertionPoint);
		}
		System.out.println(buffer.get(insertionPoint+1));
	}

	private static int insert(int value, int point) {
		int length = buffer.size();
		int step = STEP;
		
		// If we have < STEP items in the list, we're going to wrap around
		// So let's figure out the wrap
		if (length <= STEP) {
			step = STEP % length;
		}
		
		// Now we just move to the insertion point
		point += step;
		if (point >= length)
			point %= length;
		
		if (point == length-1)
			buffer.add(value);
		else
			buffer.add(point+1, value);
		
		return point+1;
	}
	

}
