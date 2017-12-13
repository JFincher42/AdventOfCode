package PackageSeven;
import java.util.ArrayList;
import java.util.Arrays;

public class Tower {
	public String name;
	public int weight;
	public int branchWeight;
	public ArrayList<Tower> children;
	public boolean processed;

	public Tower()
	{
		this.name = "";
		this.weight = 0;
		this.branchWeight = 0;
		this.children = null;
		processed = false;
	}
	
	public Tower(String name) {
		this.name = name;
		this.branchWeight = 0;
		processed = false;
	}

	public Tower(String name, int weight) {
		this.name = name;
		this.weight = weight;
		this.branchWeight = 0;
		processed = false;
	}
	
	public void addChild(Tower child) {
		if (null==children) {
			children=new ArrayList<Tower>(1);
		}
		children.add(child);
	}
}
