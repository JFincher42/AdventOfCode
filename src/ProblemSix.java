import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

public class ProblemSix {

	public static void main(String[] args) {
		int[] banks = {14, 0, 15, 12, 11, 11, 3, 5, 1, 6, 8, 4, 9, 1, 8, 4};
		ArrayList hashes = doPartOne(banks);
		doPartTwo(hashes);
	}
	
	public static void doPartTwo(ArrayList hashes) {
		String lastHash = (String) hashes.get(hashes.size()-1);
		for (int i=0; i<hashes.size()-1; i++) {
			if (lastHash.equals((String) hashes.get(i))) {
				System.out.println("Distance: " + (hashes.size()-i-1));
			}
		}
	}
	
	public static ArrayList doPartOne(int[] banks) {
		int cycles = 0;
		ArrayList hashes = new ArrayList();
		while (!hashes.contains(hash(banks))) {
			hashes.add(hash(banks));
			reconfigure(banks);
			cycles++;
		}
		hashes.add(hash(banks));
		System.out.println("Cycles: " + cycles);
		return hashes;
	}

	public static void reconfigure(int[] banks) {
		int maxIndex = 0;
		int banksCount = banks.length;
		
//		System.out.println("banks in: " + Arrays.toString(banks));
//		System.out.println("  Hash: " + hash(banks));
		for (int i=0; i<banksCount; i++) {
			if (banks[i]>banks[maxIndex])
				maxIndex=i;
		}
		
		int redist=banks[maxIndex];
		banks[maxIndex]=0;
		
		int current = maxIndex+1;
		if (current>banksCount-1) current=0;
		for (int count=0; count<redist; count++) {
			banks[current++]++;
			if (current>banksCount-1) current=0;
		}
//		System.out.println("banks out: " + Arrays.toString(banks));
//		System.out.println("  Hash: " + hash(banks));

	}

	
	public static String hash(int[] banks) {
		String sum = "";
		for (int i=0; i<banks.length; i++) {
//			System.out.println("    sum: " + sum);
			sum += 'a' + banks[i];
		}
		return sum;
	}
}
