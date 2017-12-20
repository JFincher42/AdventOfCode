package PackageFifteen;

public class ProblemFifteen {
	public static final int GENASEED = 699;
	public static final int GENBSEED = 124;
	
	public static final int FACTORA = 16807;
	public static final int FACTORB = 48271;
	

	public static void main(String[] args) {
		long generatorA = GENASEED;
		long generatorB = GENBSEED;
		
		long count = 40000000;
		int score = 0;
		
		while (count>0) {
			generatorA = nextValue(generatorA, FACTORA);
			generatorB = nextValue(generatorB, FACTORB);
			if ((generatorA%65536) == (generatorB%65536)) score+=1;
			count-=1;
			if (0==count%1000000) System.out.println(count + "...");
		}
		System.out.println("Score = " + score);

	}

	public static long nextValue(long seed, int factor) {
		return (seed*factor) % 2147483647;
	}
}
