package Package21;

// Represents a block of the diagram
// Can be 2x2 or 3x3
public class Block {
	boolean[][] block;
	int height, width;
	
	public Block(int h, int w) {
		this.height = h;  this.width = w;
		this.block = new boolean[height][width];
	}
	
	// Appends another array of the same height to this one, returning the result.
	public boolean[][] append(boolean[][] next) {
		boolean [][] newArray = new boolean[this.height][this.width*2];
		for (int i=0; i<this.height; i++) {
			for (int j=0; j<this.width; j++) {
				newArray[i][j]	          = this.block[i][j];
				newArray[i][j+this.width] = next[i][j];
			}
		}
		return newArray;
	}

	// Stacks this array on top of another one of the same width, returning the result
	public boolean[][] stack(boolean[][] bottom)
	{
		boolean[][] newArray = new boolean[this.height*2][this.width];
		for (int i=0; i<this.height; i++) {
			for (int j=0; j<this.width; j++) {
				newArray[i][j] 	           = this.block[i][j];
				newArray[i+this.height][j] = bottom[i][j];
			}
		}
		return newArray;
	}

	// Compares this block against the pattern
	public boolean compare(boolean[][] pattern)
	{

	}
}
