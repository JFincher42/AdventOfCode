import java.util.ArrayList;
import java.util.Arrays;

public class Tower {
	String name;
	int weight;
	ArrayList<Tower> children;

	public Tower()
	{
		this.name = "";
		this.weight = 0;
		this.children = null;
	}
	
	public Tower(String name) {
		this.name = name;
	}

	public Tower(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public void addWeight(int weight) {
		this.weight = weight;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public ArrayList<Tower> getChildren(){
		return this.children;
	}
	
	public void addChild(Tower child) {
		if (null==children) {
			children=new ArrayList<Tower>(1);
		}
		children.add(child);
	}
}
