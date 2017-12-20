package PackageFourteen;

import java.util.Arrays;

public class ProblemFourteen {

	public static int[] input = {'j','z','g','q','c','d','p','d','-',0,0,0};
	// flqrgnkx
//	public static int[] input = {'f','l','q','r','g','n','k','x','-',0,0,0};
	public static final int SIZE=256;
	public static int[][] diskMap = new int[128][128];
	
	public static void main(String[] args) {
		int countOnes=0;
		int[] hash = null;
		for (int row=0; row<128; row++) {
			int newLength = input.length;
			if (row/100==0) {
				if (row/10==0) {
					input[input.length-3]='0'+row%10;
					newLength-=2;
				} else {
					input[input.length-2]='0'+row%10;
					input[input.length-3]='0'+row/10%10;
					newLength -= 1;
				}
			} else {
				input[input.length-1]='0'+row%10;
				input[input.length-2]='0'+row/10%10;
				input[input.length-3]='0'+row/100%10;
			}
			hash = knotHash(Arrays.copyOf(input, newLength));
			countOnes += countUsed(hash);
			addRowToDiskMap(row,hash);
		}
//		System.out.println(Arrays.deepToString(diskMap));
//		printArray(diskMap);
		System.out.println("Used sectors: " + countOnes);
		System.out.println("Regions: " + countRegions(hash));
		printArray(diskMap);
	}
	
	public static void addRowToDiskMap(int row, int[] hash) {
		for (int i=0; i<hash.length; i++) {
			for (int bit=0; bit<8; bit++) {
				if (1==((hash[i]>>bit)%2))
					diskMap[row][bit+i*8] = -1;
			}
		}
	}
	
	public static int countRegions(int[] bytes) {
		int currentRegion = 1;
		for (int row=0; row<128; row++) {
			for (int col=0; col<128; col++) {
				// Every sector is either:
				// a. Not taken (diskMap[row][col] == 0), 
				// b. Already in a region (diskMap[row][col]>0, or
				// c. Not yet in a region (diskMap[row][col]==-1
				// We can ignore cases a. and b.
				// For case c., fill in the region with the current region number
				// And increment it when we're done.
				if (diskMap[row][col]>=0) {				// Case a. and b. combined
					continue;
				} else {								// Case c.
					fillRegion(row, col, currentRegion++);
				}
			}
		}
		return currentRegion-1;
	}
	
	public static void fillRegion(int row, int col, int region) {
		// Here's the algorithm:
		// If the current sector is -1, claim it, then scan top, bottom, right, and left
		// If the current sector is >=0, we're done
		//   0 means we are looking at an empty sector
		//   >0 means we're on an already spoken for sector (should be ours)
		if (diskMap[row][col]>=0) return;
		
		diskMap[row][col]=region;
		if (row>0)   fillRegion(row-1, col, region);
		if (row<127) fillRegion(row+1, col, region);
		if (col>0)   fillRegion(row, col-1, region);
		if (col<127) fillRegion(row, col+1, region);
	}
	
	public static int countUsed(int[] bytes) {
		int ones = 0;
		for (int i=0; i<bytes.length; i++) {
			int singleByte = bytes[i];
			for (int bit=0; bit<8; bit++) {
				ones += (singleByte % 2);
				singleByte>>=1;
			}
		}
		return ones;
	}
	
	public static int[] knotHash(int[] inputString) {
		
		int[] newInput = Arrays.copyOf(inputString, inputString.length+5);
		newInput[newInput.length-5]=17;
		newInput[newInput.length-4]=31;
		newInput[newInput.length-3]=73;
		newInput[newInput.length-2]=47;
		newInput[newInput.length-1]=23;
		System.out.println(Arrays.toString(newInput));
		
		int[] list = new int[SIZE];
		for (int i=0; i<SIZE; i++) list[i]=i;
		
		// Start everything at zero
		int current=0, skip=0;
		// But now we do 64 rounds
		int round = 64;
		while (round>0) {
			// Loop through all the items in the inputString
			for (int i=0; i<newInput.length; i++) {
				int length = newInput[i];
				twist(current, length, list);
				current = (current+length+skip++)%SIZE;
			}
			round--;
		}
		
		// Now we convert this to a dense hash
		int[] dense = new int[16];
		for (int block = 0; block<16; block++) {
			int start = block*16;
			int end = start + 16;
			dense[block] = list[start];
			for (int item=start+1; item<end; item++) dense[block] = dense[block] ^ list[item];
		}
		return dense;
	}
	
	public static void twist(int start, int length, int[] list) {
		int end = (start+length-1)%SIZE;
		
		while (length>0) {
			int temp = list[start];
			list[start] = list[end];
			list[end] = temp;
			
			start=(start+1)%SIZE;
			
			end-=1;
			if (end<0) end=SIZE-1;
			
			length -= 2;
		}
	}
	
	public static void printArray(int[][] array) {
//		System.out.print("[");
		for (int[] row: array) {
//			System.out.print("[");
			for (int element: row) {
				if (element==0)
					System.out.print("-");
				else if (element==-1)
					System.out.println("PROBLEM PROBLEM");
				else System.out.print((char)(('a'+element)%94+32));
			}
			System.out.println();
		}
		System.out.println("");
	}

}
